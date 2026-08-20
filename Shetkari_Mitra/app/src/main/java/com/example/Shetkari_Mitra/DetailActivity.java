package com.example.Shetkari_Mitra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;

public class DetailActivity extends AppCompatActivity {

    private static final String PREF_NAME = "shetkari_prefs";
    private static final String KEY_SNAKE_LANG = "key_snake_language_marathi";

    private ImageView detailImage;
    private TextView detailTitle, detailScientificName, detailVenomBadge;
    private TextView detailDesc, detailVenomType, detailIdentification, detailLocation, detailDiet, detailSafetyTips, detailFirstAid;
    private TextView tvHeaderVenomType, tvHeaderIdentification, tvHeaderHabitat, tvHeaderDiet, tvHeaderSafety, tvHeaderFirstAid;
    private MaterialButton btnDetailLangToggle, btnFindHospital, btnCallRescuer;

    private DataClass snakeItem;
    private boolean isMarathi = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        detailScientificName = findViewById(R.id.detailScientificName);
        detailVenomBadge = findViewById(R.id.detailVenomBadge);
        detailDesc = findViewById(R.id.detailDesc);
        detailVenomType = findViewById(R.id.detailVenomType);
        detailIdentification = findViewById(R.id.detailIdentification);
        detailLocation = findViewById(R.id.detailLocation);
        detailDiet = findViewById(R.id.detailDiet);
        detailSafetyTips = findViewById(R.id.detailSafetyTips);
        detailFirstAid = findViewById(R.id.detailFirstAid);

        tvHeaderVenomType = findViewById(R.id.tvHeaderVenomType);
        tvHeaderIdentification = findViewById(R.id.tvHeaderIdentification);
        tvHeaderHabitat = findViewById(R.id.tvHeaderHabitat);
        tvHeaderDiet = findViewById(R.id.tvHeaderDiet);
        tvHeaderSafety = findViewById(R.id.tvHeaderSafety);
        tvHeaderFirstAid = findViewById(R.id.tvHeaderFirstAid);

        btnDetailLangToggle = findViewById(R.id.btnDetailLangToggle);
        btnFindHospital = findViewById(R.id.btnFindHospital);
        btnCallRescuer = findViewById(R.id.btnCallRescuer);

        // Retrieve passed snake object or fallback
        if (getIntent().hasExtra("snake_item")) {
            snakeItem = (DataClass) getIntent().getSerializableExtra("snake_item");
        }

        isMarathi = getIntent().getBooleanExtra("is_marathi", prefs.getBoolean(KEY_SNAKE_LANG, false));

        // If no full object, find in SnakeDataProvider by legacy title
        if (snakeItem == null) {
            String legacyTitle = getIntent().getStringExtra("Title");
            for (DataClass item : SnakeDataProvider.getSnakeDataList()) {
                if (legacyTitle != null && (legacyTitle.contains(item.getNameEn()) || legacyTitle.contains(item.getNameMr()))) {
                    snakeItem = item;
                    break;
                }
            }
            if (snakeItem == null && !SnakeDataProvider.getSnakeDataList().isEmpty()) {
                snakeItem = SnakeDataProvider.getSnakeDataList().get(0);
            }
        }

        renderSnakeDetails();

        if (btnDetailLangToggle != null) {
            btnDetailLangToggle.setOnClickListener(v -> {
                isMarathi = !isMarathi;
                prefs.edit().putBoolean(KEY_SNAKE_LANG, isMarathi).apply();
                renderSnakeDetails();
            });
        }

        if (btnFindHospital != null) {
            btnFindHospital.setOnClickListener(v -> {
                Intent intent = new Intent(DetailActivity.this, MapsActivity.class);
                startActivity(intent);
            });
        }

        if (btnCallRescuer != null) {
            btnCallRescuer.setOnClickListener(v -> {
                Intent intent = new Intent(DetailActivity.this, RescuerDatabaseActivity.class);
                startActivity(intent);
            });
        }
    }

    private void renderSnakeDetails() {
        if (snakeItem == null) return;

        detailImage.setImageResource(snakeItem.getImageRes());
        detailTitle.setText(snakeItem.getName(isMarathi));
        detailScientificName.setText(snakeItem.getScientificName());
        detailVenomBadge.setText(snakeItem.getVenomStatus(isMarathi));
        detailDesc.setText(snakeItem.getDesc(isMarathi));
        detailVenomType.setText(snakeItem.getVenomType(isMarathi));
        detailIdentification.setText(snakeItem.getIdentification(isMarathi));
        detailLocation.setText(snakeItem.getHabitat(isMarathi));
        detailDiet.setText(snakeItem.getDiet(isMarathi));
        detailSafetyTips.setText(snakeItem.getSafetyTips(isMarathi));
        detailFirstAid.setText(snakeItem.getFirstAid(isMarathi));

        // Language toggle button text
        if (btnDetailLangToggle != null) {
            btnDetailLangToggle.setText(isMarathi ? "English" : "मराठी");
        }

        // Section header labels strictly according to active language
        if (isMarathi) {
            if (tvHeaderVenomType != null) tvHeaderVenomType.setText("विष प्रकार व तीव्रता");
            if (tvHeaderIdentification != null) tvHeaderIdentification.setText("शारीरिक वैशिष्ट्ये व ओळख");
            if (tvHeaderHabitat != null) tvHeaderHabitat.setText("आढळ व अधिवास");
            if (tvHeaderDiet != null) tvHeaderDiet.setText("अन्न व स्वभाव");
            if (tvHeaderSafety != null) tvHeaderSafety.setText("शेतकऱ्यांसाठी सुरक्षितता व महत्त्व");
            if (tvHeaderFirstAid != null) tvHeaderFirstAid.setText("सर्पदंश प्रथमोपचार व वैद्यकीय उपचार");
            if (btnFindHospital != null) btnFindHospital.setText("जवळचे रुग्णालय शोधा");
            if (btnCallRescuer != null) btnCallRescuer.setText("सर्पमित्राशी संपर्क करा");
        } else {
            if (tvHeaderVenomType != null) tvHeaderVenomType.setText("Venom Classification & Toxicity");
            if (tvHeaderIdentification != null) tvHeaderIdentification.setText("Physical Characteristics & Identification");
            if (tvHeaderHabitat != null) tvHeaderHabitat.setText("Habitat & Regional Distribution");
            if (tvHeaderDiet != null) tvHeaderDiet.setText("Diet & Temperament");
            if (tvHeaderSafety != null) tvHeaderSafety.setText("Farmer Safety & Ecological Importance");
            if (tvHeaderFirstAid != null) tvHeaderFirstAid.setText("First Aid & Emergency Protocol");
            if (btnFindHospital != null) btnFindHospital.setText("Find Nearby Hospital");
            if (btnCallRescuer != null) btnCallRescuer.setText("Find Rescuer");
        }

        // Color badge styling
        if (snakeItem.getVenomLevel() == 1) {
            detailVenomBadge.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.color_emergency));
            detailVenomBadge.setTextColor(ContextCompat.getColor(this, R.color.color_on_emergency));
        } else if (snakeItem.getVenomLevel() == 2) {
            detailVenomBadge.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.color_warning));
            detailVenomBadge.setTextColor(ContextCompat.getColor(this, R.color.color_white));
        } else {
            detailVenomBadge.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.color_primary));
            detailVenomBadge.setTextColor(ContextCompat.getColor(this, R.color.color_on_primary));
        }
    }
}
