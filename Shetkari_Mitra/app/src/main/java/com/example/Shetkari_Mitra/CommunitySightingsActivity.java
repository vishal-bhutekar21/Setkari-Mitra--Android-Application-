package com.example.Shetkari_Mitra;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class CommunitySightingsActivity extends AppCompatActivity {

    private RadioGroup rgEnvironment;
    private TextInputEditText etSpeciesObserved, etGeneralLocation;
    private View btnSubmitSighting;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_sightings);

        View btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) btnBack.setOnClickListener(v -> finish());

        rgEnvironment = findViewById(R.id.rgEnvironment);
        etSpeciesObserved = findViewById(R.id.etSpeciesObserved);
        etGeneralLocation = findViewById(R.id.etGeneralLocation);
        btnSubmitSighting = findViewById(R.id.btnSubmitSighting);

        if (btnSubmitSighting != null) {
            btnSubmitSighting.setOnClickListener(v -> {
                int selectedEnvId = rgEnvironment.getCheckedRadioButtonId();
                RadioButton rb = findViewById(selectedEnvId);
                String env = rb != null ? rb.getText().toString() : "Farm";
                String species = etSpeciesObserved.getText() != null ? etSpeciesObserved.getText().toString() : "Unknown";

                Toast.makeText(this, "Sighting reported for " + species + " in " + env + " (Submitted for rescuer verification)", Toast.LENGTH_LONG).show();
                finish();
            });
        }
    }
}
