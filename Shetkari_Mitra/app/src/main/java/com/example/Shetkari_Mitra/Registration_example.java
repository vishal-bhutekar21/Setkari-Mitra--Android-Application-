package com.example.Shetkari_Mitra;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;

public class Registration_example extends AppCompatActivity {

    private EditText etName, etEmail, etMobile, etAddress, etPincode, etDateOfBirth;
    private Spinner spinnerDistrict, spinnerTaluka;
    private Button btnSubmit;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_example);


        // Initialize Firebase database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("snakeresuerregistration");

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
        populateTalukasSpinner(0);
        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Populate district spinner based on selected state
                populateTalukasSpinner(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });



        etDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Set onClickListener for the submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToFirebase();
            }
        });
    }

    // Method to show the DatePickerDialog
    private void showDatePickerDialog() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog and set the selected date as the current date
        DatePickerDialog datePickerDialog = new DatePickerDialog(Registration_example.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Update the EditText field with the selected date
                        etDateOfBirth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);

        // Show the DatePickerDialog
        datePickerDialog.show();
    }

    // Method to send data to Firebase
    private void sendDataToFirebase() {
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String pincode = etPincode.getText().toString().trim();
        String dob = etDateOfBirth.getText().toString().trim();
        String district = spinnerDistrict.getSelectedItem().toString();
        String taluka = spinnerTaluka.getSelectedItem().toString();

        // Perform validation


        // Validate name
        if (name.isEmpty()) {
            etName.setError("Please enter your name");

            return;
        }

        // Validate email
        if (email.isEmpty()) {
            etEmail.setError("Please enter your email");

            return;
        }  if (!isValidEmail(email)) {
            etEmail.setError("Invalid email format");

            return;
        }

        // Validate mobile number
        if (mobile.isEmpty()) {
            etMobile.setError("Please enter your mobile number");

            return;
        } if (!isValidMobile(mobile)) {
            etMobile.setError("Invalid mobile number");

            return;
        }

        // Validate address
        if (address.isEmpty()) {
            etAddress.setError("Please enter your address");

            return;
        }

        // Validate pincode
        if (pincode.isEmpty()) {
            etPincode.setError("Please enter your pincode");
            etPincode.requestFocus();
            return;
        }  if (!isValidPincode(pincode)) {
            etPincode.setError("Invalid pincode");
            etPincode.requestFocus();
            return;
        }

        // Validate date of birth
        if (dob.isEmpty()) {
            etDateOfBirth.setError("Please enter your date of birth");
            etDateOfBirth.requestFocus();
            return;
        }

        // Validate state
        if (district.isEmpty() || district.equals("Select District")) {
            Toast.makeText(this, "Please select your District", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate district
        if (taluka.isEmpty() || taluka.equals("Select Sub District")) {
            Toast.makeText(this, "Please select your Sub District", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a unique key for the new registration entry
        String registrationId = mDatabase.push().getKey();

        // Create a Registration object to hold the data
        Registration registration = new Registration(name, email, mobile, address, pincode, dob, taluka, district);

        // Save the registration data to Firebase under the generated key
        mDatabase.child(registrationId).setValue(registration);

        Toast.makeText(this, "Registration submitted successfully!", Toast.LENGTH_SHORT).show();

        // Clear the form after submission if needed
        clearForm();
    }


    // Method to clear the form after submission
    private void clearForm() {
        etName.setText("");
        etEmail.setText("");
        etMobile.setText("");
        etAddress.setText("");
        etPincode.setText("");
        etDateOfBirth.setText("");
        Toast.makeText(this, "Rescuer data Uploaded.....!", Toast.LENGTH_SHORT).show();
        // Reset spinners to default selection if needed
    }

    private boolean isValidEmail(String email) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
        return email.matches(emailPattern);
    }


    private boolean isValidMobile(String mobile) {

        String mobilePattern = "^[6-9]\\d{9}$";
        return mobile.matches(mobilePattern);
    }


    private boolean isValidPincode(String pincode) {
        // Regex pattern for validating Indian PIN codes (6 digits)
        String pincodePattern = "^[1-9][0-9]{5}$";
        return pincode.matches(pincodePattern);
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
