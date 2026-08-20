package com.example.Shetkari_Mitra;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Calendar;
import java.util.Locale;

public class Registration_example extends AppCompatActivity {

    private EditText etName, etEmail, etMobile, etAddress, etPincode, etDateOfBirth;
    private Spinner spinnerDistrict, spinnerTaluka;
    private Button btnSubmit;

    @Override
    protected void attachBaseContext(android.content.Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_example);

        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        // Initialize views
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etMobile = findViewById(R.id.etMobile);
        etAddress = findViewById(R.id.etAddress);
        etPincode = findViewById(R.id.etpincode);
        etDateOfBirth = findViewById(R.id.etDateOfBirth);
        spinnerDistrict = findViewById(R.id.spinnerDistrict);
        spinnerTaluka = findViewById(R.id.spinnerTaluka);
        btnSubmit = findViewById(R.id.btnSubmit);

        ArrayAdapter<CharSequence> districtAdapter = ArrayAdapter.createFromResource(this,
                R.array.maharashtra_districts_array, android.R.layout.simple_spinner_item);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrict.setAdapter(districtAdapter);

        // Pre-select Jalna if available (position 13)
        spinnerDistrict.setSelection(13);
        populateTalukasSpinner(13);

        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                populateTalukasSpinner(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        etDateOfBirth.setOnClickListener(v -> showDatePickerDialog());
        btnSubmit.setOnClickListener(v -> submitRescuerRegistration());
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR) - 20; // Default to ~20 yrs ago
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, y, m, d) -> etDateOfBirth.setText(String.format(Locale.getDefault(), "%02d/%02d/%d", d, m + 1, y)),
                year, month, day);
        datePickerDialog.show();
    }

    private void submitRescuerRegistration() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String pincode = etPincode.getText().toString().trim();
        String dob = etDateOfBirth.getText().toString().trim();
        String district = spinnerDistrict.getSelectedItem() != null ? spinnerDistrict.getSelectedItem().toString() : "";
        String taluka = spinnerTaluka.getSelectedItem() != null ? spinnerTaluka.getSelectedItem().toString() : "";

        if (TextUtils.isEmpty(name)) {
            etName.setError(getString(R.string.error_enter_name));
            etName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mobile) || mobile.length() != 10) {
            etMobile.setError(getString(R.string.error_valid_mobile));
            etMobile.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(address)) {
            etAddress.setError(getString(R.string.error_enter_address));
            etAddress.requestFocus();
            return;
        }

        // Add to local rescuer data
        Rescuer rescuer = new Rescuer(name, email, district, taluka, mobile, address);
        LocalRescuerData.addRescuer(rescuer);

        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.registration_success_title)
                .setMessage(getString(R.string.registration_success_message, name, district))
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    clearForm();
                    finish();
                })
                .show();
    }

    private void clearForm() {
        etName.setText("");
        etEmail.setText("");
        etMobile.setText("");
        etAddress.setText("");
        etPincode.setText("");
        etDateOfBirth.setText("");
    }

    private void populateTalukasSpinner(int districtPosition) {
        ArrayAdapter<CharSequence> talukaAdapter = MaharashtraDistrictHelper.getTalukaAdapter(this, districtPosition);
        spinnerTaluka.setAdapter(talukaAdapter);
    }
}
