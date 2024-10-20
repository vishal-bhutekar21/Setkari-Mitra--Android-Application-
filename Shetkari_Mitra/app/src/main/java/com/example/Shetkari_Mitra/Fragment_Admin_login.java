package com.example.Shetkari_Mitra;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Fragment_Admin_login extends Fragment {

    private EditText emailEditText, passwordEditText;
    private Button signInButton;
    private FirebaseAuth mAuth;
    Dialog progressDialog;
    private DatabaseReference adminInfoRef;

    public Fragment_Admin_login() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_login, container, false);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Initialize Firebase Database reference
        adminInfoRef = FirebaseDatabase.getInstance().getReference().child("Admin_info");

        // Initialize views
        emailEditText = view.findViewById(R.id.TextEmailView);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        signInButton = view.findViewById(R.id.signInButton);

        // Set onClickListener for Sign In button
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        return view;
    }

    private void signIn() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // Highlight email field and display error message
            emailEditText.setError("Please enter a valid email address");
            return;
        }

        if (password.isEmpty() || password.length() < 6) {
            // Highlight password field and display error message
            passwordEditText.setError("Password must be at least 6 characters long");
            return;
        }

// If both email and password are valid, proceed with further actions


        // Retrieve user data from the Admin_Info node
        adminInfoRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot adminSnapshot : dataSnapshot.getChildren()) {
                        // Get user data
                        String dbPassword = adminSnapshot.child("pass").getValue(String.class);
                        // Check if the entered password matches the one in the database
                        if (password.equals(dbPassword)) {
                            // Sign in the user
                            signInWithEmailPassword(email, password);
                            return;
                        } else {
                            // Password doesn't match
                            Toast.makeText(getContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Email not found
                    Toast.makeText(getContext(), "Email not registered", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signInWithEmailPassword(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    showProgressDialog();

                    if (task.isSuccessful()) {
                        hideProgressDialog();
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            // Sign in successful, proceed to the next activity
                            Toast.makeText(getContext(), "Sign in successful", Toast.LENGTH_SHORT).show();
                            goToRegisterActivity();
                        }
                    } else {
                        // Sign in failed
                        Toast.makeText(getContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void goToRegisterActivity() {
        Intent intent = new Intent(getActivity(), HospitalRegistrationActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }

    private void showProgressDialog() {
        progressDialog= new Dialog(getContext());
        progressDialog.setContentView(R.layout.custom_progress_dialog);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    // Method to hide progress bar
    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
