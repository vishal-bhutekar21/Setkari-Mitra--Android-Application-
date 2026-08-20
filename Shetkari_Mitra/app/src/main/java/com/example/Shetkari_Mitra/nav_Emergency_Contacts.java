package com.example.Shetkari_Mitra;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class nav_Emergency_Contacts extends AppCompatActivity implements EmergencyContactsAdapter.OnContactActionListener {

    private RecyclerView recyclerView;
    private EmergencyContactsAdapter adapter;
    private final List<EmergencyContact> emergencyContactsList = new ArrayList<>();
    private TextView emptyTextView;
    private LinearLayout layoutOfficialHelplines;

    private AppDatabase db;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_emergency_contacts);

        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        db = AppDatabase.getInstance(this);

        layoutOfficialHelplines = findViewById(R.id.layoutOfficialHelplines);
        recyclerView = findViewById(R.id.recyclerViewEmergencyContacts);
        emptyTextView = findViewById(R.id.emptyTextView);

        View btnAddPersonalContact = findViewById(R.id.btnAddPersonalContact);
        if (btnAddPersonalContact != null) {
            btnAddPersonalContact.setOnClickListener(v -> showAddContactDialog());
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new EmergencyContactsAdapter(this, emergencyContactsList, this);
        recyclerView.setAdapter(adapter);

        BottomNavigationHelper.setupBottomNavigation(this, findViewById(R.id.bottom_navigation), R.id.bottom_nav_profile);

        populateOfficialSafetyHelplines();
        loadContactsFromDatabase();
    }

    private void populateOfficialSafetyHelplines() {
        if (layoutOfficialHelplines == null) return;
        layoutOfficialHelplines.removeAllViews();

        addOfficialHelplineCard("NDMA National Disaster Management", "राष्ट्रीय आपत्ती व्यवस्थापन प्राधिकरण", "1078", "Flood, severe storm, earthquake & national disaster relief");
        addOfficialHelplineCard("Childline National Safety Helpline", "बाल सहाय्यता व बाल सुरक्षा हेल्पलाईन", "1098", "24/7 emergency response for children in need of care & protection");
        addOfficialHelplineCard("National Anti-Ragging Helpline", "राष्ट्रीय रॅगिंग विरोधी हेल्पलाईन", "18001805522", "Toll-free student safety, harassment prevention & grievance redressal");
        addOfficialHelplineCard("Women Safety & Distress Helpline", "महिला सुरक्षा व सहाय्यता हेल्पलाईन", "1091", "24/7 emergency police support for women in distress (also call 181)");
        addOfficialHelplineCard("Senior Citizen National Helpline (Elderline)", "ज्येष्ठ नागरिक राष्ट्रीय हेल्पलाईन", "14567", "Elder care, elder abuse rescue & emergency legal/emotional support");
        addOfficialHelplineCard("Tele-MANAS Mental Health & Farmer Stress", "टेलि-मानस मानसिक आरोग्य व समुपदेशन हेल्पलाईन", "14416", "24/7 free mental health counseling, stress relief & farmer support");
        addOfficialHelplineCard("National Cyber Crime Reporting Helpline", "राष्ट्रीय सायबर गुन्हे नोंदणी हेल्पलाईन", "1930", "Immediate reporting of financial cyber fraud, online scams & harassment");
        addOfficialHelplineCard("Kisan Call Center (Agriculture Advisory)", "किसान कॉल सेंटर - शेतकरी सल्ला व मार्गदर्शन", "18001801551", "Free agricultural expert advice, crop disease solutions & government schemes");
        addOfficialHelplineCard("MahaForest Wildlife & Snake Rescue", "महाराष्ट्र वन विभाग वन्यजीव व सर्प बचाव", "1926", "Official state wildlife conflict, snakebite rescue & rescue teams");
        addOfficialHelplineCard("National Emergency Response System (ERSS)", "अखिल भारतीय आपत्कालीन प्रतिसाद सेवा", "112", "Unified all-in-one emergency response for Police, Fire, and Ambulance");
        addOfficialHelplineCard("Maharashtra Ambulance Service", "महाराष्ट्र रुग्णवाहिका सेवा", "108", "24/7 free GPS-tracked medical ambulance for snakebite and trauma victims");
        addOfficialHelplineCard("Maharashtra Health Advice & Medical Helpline", "आरोग्य सल्ला व मार्गदर्शन हेल्पलाईन", "104", "Medical expert advisory, hospital anti-venom availability info & SOPs");
        addOfficialHelplineCard("Jalna / District Emergency Control Room", "जिल्हा आपत्ती नियंत्रण कक्ष, जालना", "02482224400", "Official district disaster control room and civil hospital emergency desk");
    }

    private void addOfficialHelplineCard(String title, String marathiTitle, String phone, String description) {
        MaterialCardView card = new MaterialCardView(this);
        card.setRadius(dpToPx(14));
        card.setCardElevation(0);
        card.setStrokeWidth(0);
        card.setCardBackgroundColor(getResources().getColor(R.color.color_surface));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, dpToPx(4), 0, dpToPx(4));
        card.setLayoutParams(params);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(dpToPx(14), dpToPx(12), dpToPx(14), dpToPx(12));

        LinearLayout topRow = new LinearLayout(this);
        topRow.setOrientation(LinearLayout.HORIZONTAL);
        topRow.setGravity(android.view.Gravity.CENTER_VERTICAL);

        LinearLayout textCol = new LinearLayout(this);
        textCol.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        textCol.setLayoutParams(textParams);

        TextView tvTitle = new TextView(this);
        tvTitle.setText(title);
        tvTitle.setTextSize(14);
        tvTitle.setTextColor(getResources().getColor(R.color.color_text_primary));
        tvTitle.setTypeface(null, android.graphics.Typeface.BOLD);
        textCol.addView(tvTitle);

        TextView tvSub = new TextView(this);
        tvSub.setText(marathiTitle);
        tvSub.setTextSize(11);
        tvSub.setTextColor(getResources().getColor(R.color.color_primary));
        textCol.addView(tvSub);

        topRow.addView(textCol);

        MaterialButton btnCall = new MaterialButton(this, null, com.google.android.material.R.attr.materialButtonOutlinedStyle);
        btnCall.setText(phone);
        btnCall.setTextSize(12);
        btnCall.setTextColor(getResources().getColor(R.color.color_primary));
        btnCall.setCornerRadius(dpToPx(8));
        btnCall.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phone));
            startActivity(callIntent);
        });
        topRow.addView(btnCall);

        root.addView(topRow);

        TextView tvDesc = new TextView(this);
        tvDesc.setText(description);
        tvDesc.setTextSize(11);
        tvDesc.setTextColor(getResources().getColor(R.color.color_text_secondary));
        LinearLayout.LayoutParams descParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        descParams.setMargins(0, dpToPx(4), 0, 0);
        tvDesc.setLayoutParams(descParams);
        root.addView(tvDesc);

        card.addView(root);
        layoutOfficialHelplines.addView(card);
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    private void loadContactsFromDatabase() {
        executorService.execute(() -> {
            List<EmergencyContactEntity> entities = db.emergencyContactDao().getAll();
            List<EmergencyContact> contacts = new ArrayList<>();
            for (EmergencyContactEntity entity : entities) {
                contacts.add(EmergencyContact.fromEntity(entity));
            }

            mainHandler.post(() -> {
                emergencyContactsList.clear();
                emergencyContactsList.addAll(contacts);
                adapter.notifyDataSetChanged();

                if (emptyTextView != null) {
                    emptyTextView.setVisibility(emergencyContactsList.isEmpty() ? View.VISIBLE : View.GONE);
                }
            });
        });
    }

    private void showAddContactDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_emergency_contact, null);
        EditText etName = dialogView.findViewById(R.id.etContactName);
        EditText etPhone = dialogView.findViewById(R.id.etContactPhone);

        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.add_emergency_contact)
                .setView(dialogView)
                .setPositiveButton(R.string.save, (dialog, which) -> {
                    String name = etName.getText().toString().trim();
                    String phone = etPhone.getText().toString().trim();

                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
                        Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    executorService.execute(() -> {
                        db.emergencyContactDao().insert(new EmergencyContactEntity(name, phone));
                        loadContactsFromDatabase();
                    });
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @Override
    public void onEdit(EmergencyContact contact) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_emergency_contact, null);
        EditText etName = dialogView.findViewById(R.id.etContactName);
        EditText etPhone = dialogView.findViewById(R.id.etContactPhone);

        etName.setText(contact.getName());
        etPhone.setText(contact.getNumber());

        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.edit_contact)
                .setView(dialogView)
                .setPositiveButton(R.string.update, (dialog, which) -> {
                    String name = etName.getText().toString().trim();
                    String phone = etPhone.getText().toString().trim();

                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
                        Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    executorService.execute(() -> {
                        EmergencyContactEntity entity = new EmergencyContactEntity(name, phone);
                        entity.id = contact.getId();
                        db.emergencyContactDao().update(entity);
                        loadContactsFromDatabase();
                    });
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @Override
    public void onDelete(EmergencyContact contact) {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.delete_contact)
                .setMessage(getString(R.string.delete_contact_confirm, contact.getName()))
                .setPositiveButton(R.string.delete, (dialog, which) -> {
                    executorService.execute(() -> {
                        db.emergencyContactDao().deleteById(contact.getId());
                        loadContactsFromDatabase();
                    });
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
