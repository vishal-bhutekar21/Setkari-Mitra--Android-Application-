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

public class Fragment_Admin_Signup extends Fragment {

    private EditText adminusernameEditText, adminemailEditText, adminpasswordEditText, adminmobile;
    private Button adminsignUpButton;

    public Fragment_Admin_Signup() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_signup, container, false);

        adminusernameEditText = view.findViewById(R.id.adminusernameEditText);
        adminemailEditText = view.findViewById(R.id.adminemailEditTextView);
        adminpasswordEditText = view.findViewById(R.id.adminpasswordEditText);
        adminmobile = view.findViewById(R.id.adminmobileNumberEditText);
        adminsignUpButton = view.findViewById(R.id.adminsignUpButton);

        adminsignUpButton.setOnClickListener(v -> signUpAdmin());

        return view;
    }

    private void signUpAdmin() {
        String username = adminusernameEditText.getText().toString().trim();
        String email = adminemailEditText.getText().toString().trim();
        String password = adminpasswordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(), "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(getContext(), "Admin privileges granted! You can now log in.", Toast.LENGTH_SHORT).show();

        adminusernameEditText.setText("");
        adminemailEditText.setText("");
        if (adminmobile != null) adminmobile.setText("");
        adminpasswordEditText.setText("");
    }
}
