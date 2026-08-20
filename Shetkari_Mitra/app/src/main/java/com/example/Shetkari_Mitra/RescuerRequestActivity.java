package com.example.Shetkari_Mitra;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.Executors;

public class RescuerRequestActivity extends AppCompatActivity {

    private RadioGroup rgSituation;
    private TextInputEditText etLocation;
    private View btnSubmitRequest, cardRequestStatus;
    private TextView tvStatusDetails, tvTargetRescuerName;
    private String rescuerName = "Rahul Shinde (Sarpa Mitra)";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescuer_request);

        if (getIntent().hasExtra("RESCUER_NAME")) {
            rescuerName = getIntent().getStringExtra("RESCUER_NAME");
        }

        View btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) btnBack.setOnClickListener(v -> finish());

        rgSituation = findViewById(R.id.rgSituation);
        etLocation = findViewById(R.id.etLocation);
        btnSubmitRequest = findViewById(R.id.btnSubmitRequest);
        cardRequestStatus = findViewById(R.id.cardRequestStatus);
        tvStatusDetails = findViewById(R.id.tvStatusDetails);
        tvTargetRescuerName = findViewById(R.id.tvTargetRescuerName);

        if (tvTargetRescuerName != null) {
            tvTargetRescuerName.setText(rescuerName);
        }

        if (btnSubmitRequest != null) {
            btnSubmitRequest.setOnClickListener(v -> submitRescueRequest());
        }
    }

    private void submitRescueRequest() {
        int selectedId = rgSituation.getCheckedRadioButtonId();
        RadioButton selectedRb = findViewById(selectedId);
        String situation = selectedRb != null ? selectedRb.getText().toString() : "Snake encounter";
        String location = etLocation.getText() != null ? etLocation.getText().toString() : "Current Location";

        RescuerRequestEntity request = new RescuerRequestEntity(
                situation,
                location,
                rescuerName,
                "Request Sent",
                System.currentTimeMillis()
        );

        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase.getInstance(this).rescuerRequestDao().insertRequest(request);
        });

        if (cardRequestStatus != null) {
            cardRequestStatus.setVisibility(View.VISIBLE);
        }

        Toast.makeText(this, "Rescue request submitted to " + rescuerName, Toast.LENGTH_LONG).show();

        // Simulate Status Progression: Request Sent -> Accepted -> On the Way
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (!isFinishing() && tvStatusDetails != null) {
                tvStatusDetails.setText("STATUS: ACCEPTED by " + rescuerName + ".\nRescuer is preparing safety equipment.");
            }
        }, 2500);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (!isFinishing() && tvStatusDetails != null) {
                tvStatusDetails.setText("STATUS: ON THE WAY (ETA ~15 mins).\nKeep family and children at a safe distance.");
            }
        }, 5500);
    }
}
