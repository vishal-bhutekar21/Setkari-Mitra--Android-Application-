package com.example.Shetkari_Mitra;

import android.content.Context;
import android.content.Context;
import android.content.SharedPreferences;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPage_Fragment extends Fragment {

    private EditText passwordEditText, TextEmailView;
    private Button signInButton;
    private FirebaseAuth mAuth;
    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_LOGGED_IN = "isLoggedIn";

    private Dialog progressDialog;
    private DatabaseReference usersRef;


    public LoginPage_Fragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_page_, container, false);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // Initialize Firebase Database
        usersRef = FirebaseDatabase.getInstance().getReference().child("users");

        // Initialize views
        TextEmailView = view.findViewById(R.id.TextEmailView);
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

    // Method to sign in with mobile number and password
    private void signIn() {
        String Email = TextEmailView.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (Email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            // Highlight email field and display error message
            TextEmailView.setError("Please enter a valid email address");
            return;
        }

        if (password.isEmpty() || password.length() < 6) {
            // Highlight password field and display error message
            passwordEditText.setError("Password must be at least 6 characters long");
            return;
        }

// If both email and password are valid, proceed with further actions



        Query query = usersRef.orderByChild("userInfo/email").equalTo(Email);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {

                        String dbPassword = userSnapshot.child("userInfo/pass").getValue(String.class);

                        if (password.equals(dbPassword)) {
                            signInWithMobileAndPassword(Email, password);
                            return;
                        }
                    }

                    Toast.makeText(getContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(getContext(), "Email not registered", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }


    private void signInWithMobileAndPassword(String email, String password) {

        showProgressDialog();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    hideProgressDialog();
                    if (task.isSuccessful()) {

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            Toast.makeText(getContext(), "Sign in successful", Toast.LENGTH_SHORT).show();
                            saveLoginState(true);
                            goToHomeActivity();
                        }

                    } else {

                        Toast.makeText(getContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    // Method to navigate to HomeActivity
    private void goToHomeActivity() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }
    private void showProgressDialog() {
        progressDialog = new Dialog(getContext());
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
    private void saveLoginState(boolean isLoggedIn) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_LOGGED_IN, isLoggedIn);
        editor.apply();
    }
}