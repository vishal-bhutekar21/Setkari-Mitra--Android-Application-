package com.example.Shetkari_Mitra;

import android.app.Dialog;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInPage_Fragment extends Fragment {

    private EditText usernameEditText, emailEditText, passwordEditText,mobile;
    private EditText[] emergencyNameEditTexts = new EditText[3];
    private EditText[] emergencyNumberEditTexts = new EditText[3];
    private Button signUpButton;
    private FirebaseAuth mAuth;
    Dialog progressDialog;
    private DatabaseReference mDatabase;

    public SignInPage_Fragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in_page_, container, false);


        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Initialize views
        usernameEditText = view.findViewById(R.id.usernameEditText);
        emailEditText = view.findViewById(R.id.emailEditTextView);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        mobile = view.findViewById(R.id.mobileNumberEditText);


        emergencyNameEditTexts[0] = view.findViewById(R.id.emergencyNameEditText1);
        emergencyNameEditTexts[1] = view.findViewById(R.id.emergencyNameEditText2);
        emergencyNameEditTexts[2] = view.findViewById(R.id.emergencyNameEditText3);
        emergencyNumberEditTexts[0] = view.findViewById(R.id.emergencyNumberEditText1);
        emergencyNumberEditTexts[1] = view.findViewById(R.id.emergencyNumberEditText2);
        emergencyNumberEditTexts[2] = view.findViewById(R.id.emergencyNumberEditText3);
        signUpButton = view.findViewById(R.id.signUpButton);

        // Set onClickListener for sign up button
        signUpButton.setOnClickListener(new View.OnClickListener() {
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
        String username = usernameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String mobile1 = mobile.getText().toString().trim();
        String[] emergencyNames = new String[3];
        String[] emergencyNumbers = new String[3];

        // Get emergency contact details
        for (int i = 0; i < 3; i++) {
            emergencyNames[i] = emergencyNameEditTexts[i].getText().toString().trim();
            emergencyNumbers[i] = emergencyNumberEditTexts[i].getText().toString().trim();
        }

        // Perform basic validation
        if (username.isEmpty()) {
            usernameEditText.setError("Please enter a username");
            return;
        }

// Validate email
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Please enter a valid email address");
            return;
        }

// Validate password
        if (password.isEmpty() || password.length() < 6) {
            passwordEditText.setError("Password must be at least 6 characters long");
            return;
        }

// Validate mobile number
        if (mobile1.isEmpty() || mobile1.length() != 10) {
            mobile.setError("Please enter a valid 10-digit mobile number");
            return;
        }


        // Create user in Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    showProgressDialog();
                    if (task.isSuccessful()) {
                        hideProgressDialog();
                        // Save user information to Firebase Database
                        String userId = mAuth.getCurrentUser().getUid();
                        User user = new User(username, email,mobile1,password);

                        // Reference to a new node "users" under your Firebase project
                        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

                        // Save user data under a new node with the user's ID
                        usersRef.child(userId).child("userInfo").setValue(user);

                        // Save emergency contact details under a new node with the user's ID
                        for (int i = 0; i < 3; i++) {
                            EmergencyContact contact = new EmergencyContact(emergencyNames[i], emergencyNumbers[i]);
                            usersRef.child(userId).child("emergencyContacts").child("contact" + (i + 1)).setValue(contact);
                        }

                        Toast.makeText(getContext(), "Sign up successful", Toast.LENGTH_SHORT).show();
                        usernameEditText.setText("");
                        passwordEditText.setText("");
                        emailEditText.setText("");
                        mobile.setText("");
                        for (int i = 0; i < 3; i++) {
                           emergencyNameEditTexts[i].setText("");
                           emergencyNumberEditTexts[i].setText("");}


                        } else {
                        Toast.makeText(getContext(), "Sign up failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void showProgressDialog() {
        progressDialog= new Dialog(getContext());
        progressDialog.setContentView(R.layout.signin_progress);
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
