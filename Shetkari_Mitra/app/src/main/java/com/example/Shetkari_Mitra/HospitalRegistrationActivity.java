package com.example.Shetkari_Mitra;

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

public class HospitalRegistrationActivity extends AppCompatActivity {

    private EditText etHospitalName, etHospitalContactPerson, etHospitalNumber, etHospitalAddress;
    private Spinner spinnerDistrict, spinnerTaluka;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_registration);

        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        etHospitalName = findViewById(R.id.etHospital_name);
        etHospitalContactPerson = findViewById(R.id.etHospital_contact_person);
        etHospitalNumber = findViewById(R.id.setHospital_number);
        etHospitalAddress = findViewById(R.id.etHospitalAddress);
        spinnerDistrict = findViewById(R.id.spinnerDistrict);
        spinnerTaluka = findViewById(R.id.spinnerTaluka);
        btnSubmit = findViewById(R.id.btnSubmit);

        ArrayAdapter<CharSequence> districtAdapter = ArrayAdapter.createFromResource(this,
                R.array.maharashtra_districts_array, android.R.layout.simple_spinner_item);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrict.setAdapter(districtAdapter);

        // Pre-select Jalna (position 13)
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

        btnSubmit.setOnClickListener(view -> submitHospital());
    }

    private void submitHospital() {
        String hospitalName = etHospitalName.getText().toString().trim();
        String contactPerson = etHospitalContactPerson.getText().toString().trim();
        String hospitalNumber = etHospitalNumber.getText().toString().trim();
        String hospitalAddress = etHospitalAddress.getText().toString().trim();
        String selectedDistrict = spinnerDistrict.getSelectedItem() != null ? spinnerDistrict.getSelectedItem().toString() : "";
        String selectedTaluka = spinnerTaluka.getSelectedItem() != null ? spinnerTaluka.getSelectedItem().toString() : "";

        if (TextUtils.isEmpty(hospitalName)) {
            etHospitalName.setError("Please enter hospital name");
            etHospitalName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(hospitalNumber) || hospitalNumber.length() < 7) {
            etHospitalNumber.setError("Please enter a valid contact number");
            etHospitalNumber.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(hospitalAddress)) {
            etHospitalAddress.setError("Please enter hospital address");
            etHospitalAddress.requestFocus();
            return;
        }

        Hospital_Info hospitalInfo = new Hospital_Info(hospitalName, contactPerson, hospitalNumber, selectedTaluka, selectedDistrict, hospitalAddress);
        LocalHospitalData.addAdminHospital(hospitalInfo);

        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.hospital_registered_success)
                .setMessage(getString(R.string.hospital_registered_message, hospitalName, selectedTaluka))
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    clearForm();
                    finish();
                })
                .show();
    }

    private void clearForm() {
        etHospitalName.setText("");
        etHospitalContactPerson.setText("");
        etHospitalNumber.setText("");
        etHospitalAddress.setText("");
    }

    private void populateTalukasSpinner(int districtPosition) {
        ArrayAdapter<CharSequence> talukaAdapter = MaharashtraDistrictHelper.getTalukaAdapter(this, districtPosition);
        spinnerTaluka.setAdapter(talukaAdapter);
    }
}
