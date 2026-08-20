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
 * Login fragment using local SharedPreferences-based authentication.
 * No Firebase dependency.
 *
 * The app uses a simple local account system:
 *   - On signup, username + password stored in SharedPreferences (hashed)
 *   - On login, the stored credentials are verified locally
 */
public class LoginPage_Fragment extends Fragment {

    private static final String PREF_NAME = "shetkari_prefs";
    private static final String KEY_LOGGED_IN = "is_logged_in";
    private static final String KEY_SAVED_EMAIL = "saved_email";
    private static final String KEY_SAVED_PASS_HASH = "saved_pass_hash";

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signInButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_page_, container, false);

        emailEditText = view.findViewById(R.id.TextEmailView);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        signInButton = view.findViewById(R.id.signInButton);
        View btnGuestLogin = view.findViewById(R.id.btnGuestLogin);

        signInButton.setOnClickListener(v -> attemptLogin());
        if (btnGuestLogin != null) {
            btnGuestLogin.setOnClickListener(v -> {
                SharedPreferences prefs = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                prefs.edit()
                        .putBoolean(KEY_LOGGED_IN, true)
                        .putString("saved_username", "Guest Farmer (शेतकरी)")
                        .putString("saved_email", "guest@shetkarimitra.app")
                        .apply();
                Toast.makeText(getContext(), "Logged in as Guest", Toast.LENGTH_SHORT).show();
                navigateToHome();
            });
        }

        return view;
    }

    private void attemptLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate inputs
        if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Please enter a valid email address");
            emailEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password) || password.length() < 6) {
            passwordEditText.setError("Password must be at least 6 characters");
            passwordEditText.requestFocus();
            return;
        }

        // Check local credentials
        SharedPreferences prefs = requireContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String savedEmail = prefs.getString(KEY_SAVED_EMAIL, null);
        String savedHashedPassword = prefs.getString(KEY_SAVED_PASS_HASH, null);

        if (savedEmail == null || savedHashedPassword == null) {
            Toast.makeText(getContext(), "No account found. Please sign up first.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!savedEmail.equalsIgnoreCase(email)) {
            emailEditText.setError("Email not registered");
            return;
        }

        if (!hashPassword(password).equals(savedHashedPassword)) {
            passwordEditText.setError("Incorrect password");
            return;
        }

        // Login successful
        prefs.edit().putBoolean(KEY_LOGGED_IN, true).apply();
        Toast.makeText(getContext(), "Welcome back!", Toast.LENGTH_SHORT).show();
        navigateToHome();
    }

    private void navigateToHome() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        requireActivity().finish();
    }

    /**
     * Simple SHA-256 based password hash.
     * Not production-grade (no salt) but removes the egregious plaintext password storage.
     */
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
            // SHA-256 is always available on Android
            return password;
        }
    }
}