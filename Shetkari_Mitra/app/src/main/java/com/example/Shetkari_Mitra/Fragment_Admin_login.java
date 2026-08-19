package com.example.Shetkari_Mitra;

import android.content.Intent;
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

public class Fragment_Admin_login extends Fragment {

    // Simple hardcoded admin credentials
    public static final String ADMIN_EMAIL = "admin@shetkari.com";
    public static final String ADMIN_PASS = "admin123";

    private EditText emailEditText, passwordEditText;
    private Button signInButton;

    public Fragment_Admin_login() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_login, container, false);

        emailEditText = view.findViewById(R.id.TextEmailView);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        signInButton = view.findViewById(R.id.signInButton);

        // Fill in default admin email for convenience
        emailEditText.setText(ADMIN_EMAIL);

        signInButton.setOnClickListener(v -> attemptAdminLogin());

        return view;
    }

    private void attemptAdminLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Please enter admin email");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Please enter admin password");
            return;
        }

        // Check hardcoded admin credentials or custom admin
        if ((ADMIN_EMAIL.equalsIgnoreCase(email) && ADMIN_PASS.equals(password)) ||
                ("admin".equalsIgnoreCase(email) && ADMIN_PASS.equals(password))) {
            Toast.makeText(getContext(), "Admin login successful!", Toast.LENGTH_SHORT).show();
            goToHospitalRegistration();
        } else {
            Toast.makeText(getContext(), "Invalid credentials. Use admin@shetkari.com / admin123", Toast.LENGTH_LONG).show();
        }
    }

    private void goToHospitalRegistration() {
        Intent intent = new Intent(getActivity(), HospitalRegistrationActivity.class);
        startActivity(intent);
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}
