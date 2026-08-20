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
                "MahaForest Wildlife & Rescue Portal",
                "महाराष्ट्र वन विभाग - वन्यजीव व सर्प बचाव",
                "Forest Department Maharashtra",
                "https://mahaforest.gov.in",
                "Official wildlife rescue standard operating procedures, rescuer registration guidelines, and 24/7 wildlife helpline.",
                "1926"
        ));

        portals.add(new GovtPortalItem(
                "Maharashtra Public Health Dept (Arogya Vibhag)",
                "सार्वजनिक आरोग्य विभाग, महाराष्ट्र शासन",
                "Public Health Department",
                "https://arogya.maharashtra.gov.in",
                "State-wide anti-snake venom protocols, district hospital directories, and emergency health advisories.",
                "104"
        ));

        portals.add(new GovtPortalItem(
                "MahaDBT Farmer Welfare & Compensation Portal",
                "महाडीबीटी शेतकरी कल्याण व नुकसान भरपाई दालन",
                "Agriculture & Revenue Department Maharashtra",
                "https://mahadbt.maharashtra.gov.in",
                "Direct benefit transfer (DBT) portal for Gopinath Munde Shetkari Apghat Vima Yojana claims and farmer assistance.",
                "02249150800"
        ));

        portals.add(new GovtPortalItem(
                "State Disaster Management Authority (SDMA)",
                "महाराष्ट्र राज्य आपत्ती व्यवस्थापन प्राधिकरण",
                "Relief & Rehabilitation Department",
                "https://sdma.maharashtra.gov.in",
                "State disaster control room, extreme weather alerts, flood monitoring, and emergency response.",
                "112"
        ));

        portals.add(new GovtPortalItem(
                "Maharashtra Agriculture Department (Krishi Vibhag)",
                "कृषी विभाग, महाराष्ट्र शासन",
                "Department of Agriculture Maharashtra",
                "https://krishi.maharashtra.gov.in",
                "Farmer advisory bulletins, agricultural safety practices, crop support, and government farm schemes.",
                "18001801551"
        ));

        portals.add(new GovtPortalItem(
                "Aaple Sarkar Citizen Services Portal",
                "आपले सरकार - महाराष्ट्र शासन नागरिक सेवा दालन",
                "Government of Maharashtra Citizen Services",
                "https://aaplesarkar.mahaonline.gov.in",
                "Official portal for digital land records (7/12 extract), revenue certificates, and government grievance redressal.",
                "18001208040"
        ));

        portals.add(new GovtPortalItem(
                "Maharashtra Emergency Medical Services (MEMS 108)",
                "महाराष्ट्र आपत्कालीन वैद्यकीय सेवा (१०८ रुग्णवाहिका)",
                "National Health Mission & BVG MEMS",
                "https://mems108.in",
                "Toll-free GPS-tracked ambulance network for rapid transport of snakebite and trauma patients to ASV hospitals.",
                "108"
        ));

        portals.add(new GovtPortalItem(
                "Jalna District Collectorate & Disaster Cell",
                "जिल्हाधिकारी कार्यालय व आपत्ती व्यवस्थापन कक्ष, जालना",
                "District Administration Jalna",
                "https://jalna.gov.in",
                "Official district portal for administrative notifications, civil hospital ASV stocks, and district emergency cell.",
                "02482224400"
        ));

        portals.add(new GovtPortalItem(
                "State Animal Husbandry & Veterinary Services",
                "पशुसंवर्धन व पशुवैद्यकीय सेवा विभाग, महाराष्ट्र शासन",
                "Department of Animal Husbandry",
                "https://ahd.maharashtra.gov.in",
                "Livestock safety guidelines, rural veterinary dispensary directory, and cattle snakebite assistance.",
                "1962"
        ));

        portals.add(new GovtPortalItem(
                "Chhatrapati Sambhajinagar District Portal",
                "जिल्हाधिकारी कार्यालय, छत्रपती संभाजीनगर",
                "District Administration Sambhajinagar",
                "https://aurangabad.gov.in",
                "Regional medical center notices, government medical college ASV helpline, and divisional control room.",
                "02402334000"
        ));

        rv.setAdapter(new GovtPortalAdapter(this, portals));
    }
}
