package com.example.Shetkari_Mitra;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GovtPortalsActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govt_portals);

        View btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) btnBack.setOnClickListener(v -> finish());

        RecyclerView rv = findViewById(R.id.rvGovtPortals);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<GovtPortalItem> portals = new ArrayList<>();
        portals.add(new GovtPortalItem(
                "Jalna District Collectorate & Disaster Cell",
                "जिल्हाधिकारी कार्यालय व आपत्ती व्यवस्थापन, जालना",
                "Govt of Maharashtra • District Administration",
                "https://jalna.gov.in",
                "Official district portal for emergency notices, civil hospital advisories, and disaster contact cells.",
                "02482224400"
        ));

        portals.add(new GovtPortalItem(
                "Maharashtra Public Health Dept (Arogya Vibhag)",
                "सार्वजनिक आरोग्य विभाग, महाराष्ट्र शासन",
                "Public Health Department",
                "https://arogya.maharashtra.gov.in",
                "State-wide anti-snake venom protocols, district hospital directories, and monsoon healthcare advisories.",
                "104"
        ));

        portals.add(new GovtPortalItem(
                "MahaForest Wildlife & Rescue Portal",
                "महाराष्ट्र वन विभाग - वन्यजीव व सर्प बचाव",
                "Forest Department Maharashtra",
                "https://mahaforest.gov.in",
                "Official wildlife rescue standard operating procedures, registered rescuer norms, and helpline.",
                "1926"
        ));

        portals.add(new GovtPortalItem(
                "State Disaster Management Authority (SDMA)",
                "राज्य आपत्ती व्यवस्थापन प्राधिकरण",
                "Disaster Management Unit",
                "https://sdma.maharashtra.gov.in",
                "State emergency response, monsoon river flood alerts, and natural hazard rescue management.",
                "112"
        ));

        portals.add(new GovtPortalItem(
                "Chhatrapati Sambhajinagar District Portal",
                "जिल्हाधिकारी कार्यालय, छत्रपती संभाजीनगर",
                "District Administration",
                "https://aurangabad.gov.in",
                "Regional medical center notices and government medical college ASV helpline.",
                "02402334000"
        ));

        rv.setAdapter(new GovtPortalAdapter(this, portals));
    }
}
