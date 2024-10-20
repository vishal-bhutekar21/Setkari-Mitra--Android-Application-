package com.example.Shetkari_Mitra;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelperForHospital {

    private DatabaseReference mDatabase;

    public FirebaseHelperForHospital() {
        // Initialize the Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("hospitalInfo");
    }

    public void addHospitalInfo(Hospital_Info hospitalInfo) {
        // Generate a unique key for the new hospital entry
        String uniqueKey = mDatabase.push().getKey();

        // Set the hospital information under the generated unique key
        mDatabase.child(uniqueKey).setValue(hospitalInfo);
    }

}
