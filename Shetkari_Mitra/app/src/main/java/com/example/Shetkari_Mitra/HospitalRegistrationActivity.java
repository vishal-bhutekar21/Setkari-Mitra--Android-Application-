package com.example.Shetkari_Mitra;

import android.annotation.SuppressLint;
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

import com.google.firebase.FirebaseApp;

public class HospitalRegistrationActivity extends AppCompatActivity {

    private EditText etHospitalName, etHospitalContactPerson, etHospitalNumber, etHospitalAddress;
    private Spinner spinnerDistrict, spinnerTaluka;
    private Button btnSubmit;

    private FirebaseHelperForHospital firebaseDatabaseHelper;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_registration);
        FirebaseApp.initializeApp(this);

        firebaseDatabaseHelper = new FirebaseHelperForHospital();

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

        populateTalukasSpinner(0);

        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                populateTalukasSpinner(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        btnSubmit.setOnClickListener(view -> {
            String hospitalName = etHospitalName.getText().toString();
            String contactPerson = etHospitalContactPerson.getText().toString();
            String hospitalNumber = etHospitalNumber.getText().toString();
            String hospitalAddress = etHospitalAddress.getText().toString();
            String selectedDistrict = spinnerDistrict.getSelectedItem().toString();
            String selectedTaluka = spinnerTaluka.getSelectedItem().toString();

            // Validation
            if (TextUtils.isEmpty(hospitalName) || TextUtils.isEmpty(contactPerson) || TextUtils.isEmpty(hospitalNumber) || TextUtils.isEmpty(hospitalAddress)) {
                Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Assuming you have some specific validation for hospital number (e.g., length)
            if (hospitalNumber.length() != 10) {
                Toast.makeText(getApplicationContext(), "Please enter a valid 10-digit hospital number", Toast.LENGTH_SHORT).show();
                return;
            }

            // Assuming you have some specific validation for hospital address
            if (hospitalAddress.length() < 5) {
                Toast.makeText(getApplicationContext(), "Please enter a valid hospital address", Toast.LENGTH_SHORT).show();
                return;
            }



            Hospital_Info hospitalInfo = new Hospital_Info(hospitalName, contactPerson, hospitalNumber, selectedTaluka, selectedDistrict, hospitalAddress);
            firebaseDatabaseHelper.addHospitalInfo(hospitalInfo);


            etHospitalName.setText("");
            etHospitalContactPerson.setText("");
            etHospitalNumber.setText("");
            etHospitalAddress.setText("");
            spinnerDistrict.setSelection(0);
            spinnerTaluka.setSelection(0);

            Toast.makeText(getApplicationContext(), "Hospital information submitted successfully", Toast.LENGTH_SHORT).show();
        });
    }


    private void populateTalukasSpinner(int districtPosition) {
        ArrayAdapter<CharSequence> talukaAdapter;


        switch (districtPosition) {
            case 0:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.ahmednagar_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 1:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.akola_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 2:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.amravati_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 3:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.sambhajinagar_taluka_array, android.R.layout.simple_spinner_item);
                break;
            case 4:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.beed_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 5:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.bhandara_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 6:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.buldhana_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 7:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.chandrapur_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 8:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.dhule_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 9:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.gadchiroli_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 10:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.gondiya_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 11:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.hingoli_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 12:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.jalgaon_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 13:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.jalna_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 14:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.kolhapur_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 15:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.latur_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 16:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.mumbai_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 17:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.mumbai_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 18:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.nagpur_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 19:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.nanded_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 20:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.nandurbar_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 21:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.nashik_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 22:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.osmanabad_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 23:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.palghar_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 24:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.parbhani_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 25:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.pune_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 26:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.raigad_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 27:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.ratnagiri_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 28:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.sangli_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 29:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.satara_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 30:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.sindhudurg_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 31:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.solapur_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 32:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.thane_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 33:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.wardha_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 34:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.washim_talukas_array, android.R.layout.simple_spinner_item);
                break;
            case 35:
                talukaAdapter = ArrayAdapter.createFromResource(this,
                        R.array.yavatmal_talukas_array, android.R.layout.simple_spinner_item);
                break;
            default:
                talukaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
                break;
        }


        talukaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTaluka.setAdapter(talukaAdapter);
    }
}
