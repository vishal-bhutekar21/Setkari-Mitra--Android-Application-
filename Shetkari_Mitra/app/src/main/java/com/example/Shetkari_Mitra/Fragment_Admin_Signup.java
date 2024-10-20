package com.example.Shetkari_Mitra;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Fragment_Admin_Signup extends Fragment {

    private EditText adminusernameEditText, adminemailEditText, adminpasswordEditText,adminmobile;
    private Button adminsignUpButton;
    private FirebaseAuth mAuth;
    Dialog progressDialog;
    private DatabaseReference mDatabase;

    public Fragment_Admin_Signup() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_signup, container, false);

        // Initialize Firebase Auth and Database
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Initialize views
        adminusernameEditText = view.findViewById(R.id.adminusernameEditText);
        adminemailEditText = view.findViewById(R.id.adminemailEditTextView);
        adminpasswordEditText = view.findViewById(R.id.adminpasswordEditText);
        adminmobile = view.findViewById(R.id.adminmobileNumberEditText);

        adminsignUpButton = view.findViewById(R.id.adminsignUpButton);

        // Set onClickListener for sign up button
        adminsignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        return view;
    }

    // Update the signUp() method to save data under a new node
    private void signUp() {
        // Get user input values
        String username = adminusernameEditText.getText().toString().trim();
        String email = adminemailEditText.getText().toString().trim();
        String password = adminpasswordEditText.getText().toString().trim();
        String mobile1 = adminmobile.getText().toString().trim();

        
        // Get emergency contact details

        // Perform basic validation
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if(username.isEmpty())
        {
            adminusernameEditText.setError("Please Enter Username");
            return;
        }
        if(password.isEmpty())
        {

            adminpasswordEditText.setError("Please Enter Password");
            return;
        }
        if(email.isEmpty())
        {
            adminemailEditText.setError("Please Enter Email");
            return;
        }

        // Create user in Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email, password)

                .addOnCompleteListener(task ->
                        {
                            showProgressDialog();
                    if (task.isSuccessful()) {
                        hideProgressDialog();
                        // Save user information to Firebase Database
                        String userId = mAuth.getCurrentUser().getUid();
                        Admin admin = new Admin(username, email,mobile1,password);

                        // Reference to the root node of your Firebase project
                        // Reference to the root node of your Firebase project
                        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

// Save user data under the "Admin_info" node
                        rootRef.child("Admin_info").push().setValue(admin);

                        // Save emergency contact details under a new node with the user's ID

                        Toast.makeText(getContext(), "Sign up successful", Toast.LENGTH_SHORT).show();

                        adminusernameEditText.setText("");
                        adminemailEditText.setText("");
                        adminmobile.setText("");
                        adminpasswordEditText.setText("");
                    }
                    else {
                        Toast.makeText(getContext(), "Sign up failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void showProgressDialog() {
        progressDialog= new Dialog(getContext());
        progressDialog.setContentView(R.layout.signin_progress);
        progressDialog.setCancelable(false);
        progressDialog.show();
        Button v=progressDialog.findViewById(R.id.btnClose);
        Button b=progressDialog.findViewById(R.id.btnClose);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),HomeActivity.class);
                startActivity(i);
            }
        });
        progressDialog.setCancelable(true);
        progressDialog.show();

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.dismiss();
            }
        });
    }

    // Method to hide progress bar
    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
