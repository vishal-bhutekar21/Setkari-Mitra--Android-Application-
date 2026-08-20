package com.example.Shetkari_Mitra;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class HarmfulCreaturesActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "shetkari_prefs";
    private static final String KEY_LANG_MARATHI = "creature_lang_marathi";

    private RecyclerView recyclerView;
    private CreatureAdapter adapter;
    private SearchView searchView;
    private TextView tvEmpty;
    private MaterialButton btnLangToggle;
    private TextView tvHeaderTitle, tvHeaderSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harmful_creatures);

        ImageButton btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        recyclerView = findViewById(R.id.recyclerViewCreatures);
        searchView = findViewById(R.id.searchViewCreatures);
        tvEmpty = findViewById(R.id.tvEmptyCreatures);
        btnLangToggle = findViewById(R.id.btnCreatureLangToggle);
        tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        tvHeaderSub = findViewById(R.id.tvHeaderSub);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<HarmfulCreature> creatures = CreatureDataProvider.getCreatures();
        adapter = new CreatureAdapter(this, creatures);
        recyclerView.setAdapter(adapter);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isMarathi = prefs.getBoolean(KEY_LANG_MARATHI, true);
        applyLanguage(isMarathi);

        if (btnLangToggle != null) {
            btnLangToggle.setOnClickListener(v -> {
                boolean newMode = !adapter.isMarathiMode();
                applyLanguage(newMode);
                prefs.edit().putBoolean(KEY_LANG_MARATHI, newMode).apply();
            });
        }

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText);
                    return true;
                }
            });
        }
    }

    private void applyLanguage(boolean isMarathi) {
        adapter.setMarathiMode(isMarathi);
        if (btnLangToggle != null) {
            btnLangToggle.setText(isMarathi ? "English" : "मराठी");
        }
        if (tvHeaderTitle != null) {
            tvHeaderTitle.setText(isMarathi ? "विंचू व विषारी कीटक" : "Insects & Scorpions");
        }
        if (tvHeaderSub != null) {
            tvHeaderSub.setText(isMarathi ? "ओळख, दक्षता व प्रथमोपचार" : "Identification & First Aid Protocol");
        }
    }
}
