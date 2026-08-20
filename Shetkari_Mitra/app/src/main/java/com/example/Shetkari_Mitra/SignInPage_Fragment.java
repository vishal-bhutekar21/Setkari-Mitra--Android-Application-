package com.example.Shetkari_Mitra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Sign-up fragment using local SharedPreferences storage.
 * No Firebase dependency. User account is stored locally on device.
 */
public class SignInPage_Fragment extends Fragment {

    private static final String PREF_NAME = "shetkari_prefs";
    private static final String KEY_LOGGED_IN = "is_logged_in";
    private static final String KEY_SAVED_EMAIL = "saved_email";
    private static final String KEY_SAVED_PASS_HASH = "saved_pass_hash";
    private static final String KEY_SAVED_USERNAME = "saved_username";
    private static final String KEY_SAVED_MOBILE = "saved_mobile";

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText mobileEditText;
    private android.widget.CheckBox cbAddExtraDetails;
    private View layoutExtraDetails;
    private EditText emergencyNameEditText1, emergencyNameEditText2;
    private EditText emergencyNumberEditText1, emergencyNumberEditText2;
    private Button signUpButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in_page_, container, false);

        usernameEditText = view.findViewById(R.id.usernameEditText);
        emailEditText = view.findViewById(R.id.emailEditTextView);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        mobileEditText = view.findViewById(R.id.mobileNumberEditText);

        cbAddExtraDetails = view.findViewById(R.id.cbAddExtraDetails);
        layoutExtraDetails = view.findViewById(R.id.layoutExtraDetails);

        emergencyNameEditText1 = view.findViewById(R.id.emergencyNameEditText1);
        emergencyNameEditText2 = view.findViewById(R.id.emergencyNameEditText2);
        emergencyNumberEditText1 = view.findViewById(R.id.emergencyNumberEditText1);
        emergencyNumberEditText2 = view.findViewById(R.id.emergencyNumberEditText2);

        if (cbAddExtraDetails != null && layoutExtraDetails != null) {
            cbAddExtraDetails.setOnCheckedChangeListener((buttonView, isChecked) -> {
                layoutExtraDetails.setVisibility(isChecked ? View.VISIBLE : View.GONE);
            });
        }

        signUpButton = view.findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(v -> attemptSignUp());

        return view;
    }

    private void attemptSignUp() {
        String username = usernameEditText.getText().toString().trim();
        String email = emailEditText != null && emailEditText.getText() != null ? emailEditText.getText().toString().trim() : "";
        String password = passwordEditText.getText().toString().trim();
        String mobile = mobileEditText.getText().toString().trim();

        // Validate
        if (TextUtils.isEmpty(username)) {
            usernameEditText.setError("Please enter your name");
            usernameEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mobile) || mobile.length() != 10) {
            mobileEditText.setError("Please enter a valid 10-digit mobile number");
            mobileEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password) || password.length() < 6) {
            passwordEditText.setError("Password must be at least 6 characters");
            passwordEditText.requestFocus();
            return;
        }

        if (!TextUtils.isEmpty(email) && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Please enter a valid email address");
            emailEditText.requestFocus();
            return;
        }

        // Save user info locally
        SharedPreferences prefs = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_SAVED_EMAIL, email);
        editor.putString(KEY_SAVED_PASS_HASH, hashPassword(password));
        editor.putString(KEY_SAVED_USERNAME, username);
        editor.putString(KEY_SAVED_MOBILE, mobile);
        editor.putBoolean(KEY_LOGGED_IN, true);
        editor.apply();

        // Save emergency contacts to Room DB
        saveEmergencyContacts();

        Toast.makeText(getContext(), "Account created! Welcome, " + username + "!", Toast.LENGTH_SHORT).show();
        navigateToHome();
    }

    private void saveEmergencyContacts() {
        AppDatabase db = AppDatabase.getInstance(requireContext());

        new Thread(() -> {
            String[][] contacts = {
                    {emergencyNameEditText1 != null ? emergencyNameEditText1.getText().toString().trim() : "", emergencyNumberEditText1 != null ? emergencyNumberEditText1.getText().toString().trim() : ""},
                    {emergencyNameEditText2 != null ? emergencyNameEditText2.getText().toString().trim() : "", emergencyNumberEditText2 != null ? emergencyNumberEditText2.getText().toString().trim() : ""}
            };
            for (String[] contact : contacts) {
                if (!TextUtils.isEmpty(contact[0]) && !TextUtils.isEmpty(contact[1])) {
                    db.emergencyContactDao().insert(new EmergencyContactEntity(contact[0], contact[1]));
                }
            }
        }).start();
    }

    private void navigateToHome() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        requireActivity().finish();
    }

    private String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            return password;
        }
    }
}
