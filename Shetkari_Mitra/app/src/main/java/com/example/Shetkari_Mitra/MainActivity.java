package com.example.Shetkari_Mitra;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_NAME = "shetkari_prefs";
    private static final String KEY_SNAKE_LANG = "key_snake_language_marathi";

    private RecyclerView recyclerView;
    private List<DataClass> fullSnakeList;
    private List<DataClass> filteredList;
    private MyAdapter adapter;
    private SearchView searchView;
    private TextView emptyTextView;
    private TextView tvHeaderTitle, tvHeaderSubtitle;
    private MaterialButton btnLangToggle;
    private MaterialButton filterAll, filterVenomous, filterNonVenomous;

    private boolean isMarathi = false;
    private int currentFilter = 0; // 0: All, 1: Venomous, 2: Non-Venomous

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);

        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        isMarathi = prefs.getBoolean(KEY_SNAKE_LANG, false);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.snake_library);
        }

        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        emptyTextView = findViewById(R.id.emptyTextView);
        tvHeaderTitle = findViewById(R.id.tvHeaderTitle);
        tvHeaderSubtitle = findViewById(R.id.tvHeaderSubtitle);
        btnLangToggle = findViewById(R.id.btnLangToggle);
        filterAll = findViewById(R.id.filterAll);
        filterVenomous = findViewById(R.id.filterVenomous);
        filterNonVenomous = findViewById(R.id.filterNonVenomous);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load 20 verified species from SnakeDataProvider
        fullSnakeList = SnakeDataProvider.getSnakeDataList();
        filteredList = new ArrayList<>(fullSnakeList);

        adapter = new MyAdapter(MainActivity.this, filteredList);
        adapter.setLanguage(isMarathi);
        recyclerView.setAdapter(adapter);

        updateHeaderAndToggleText();

        btnLangToggle.setOnClickListener(v -> {
            isMarathi = !isMarathi;
            prefs.edit().putBoolean(KEY_SNAKE_LANG, isMarathi).apply();
            adapter.setLanguage(isMarathi);
            updateHeaderAndToggleText();
            applyFilterAndSearch();
        });

        setupFilterButtons();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                applyFilterAndSearch();
                return true;
            }
        });
    }

    private void updateHeaderAndToggleText() {
        if (isMarathi) {
            if (tvHeaderTitle != null) tvHeaderTitle.setText("सर्प माहिती मार्गदर्शक");
            if (tvHeaderSubtitle != null) tvHeaderSubtitle.setText(fullSnakeList.size() + " प्रजातींची सविस्तर माहिती");
            if (btnLangToggle != null) btnLangToggle.setText("English");
            if (searchView != null) searchView.setQueryHint("नावाने, शास्त्रीय नावाने किंवा आढळ स्थानाने शोधा…");
        } else {
            if (tvHeaderTitle != null) tvHeaderTitle.setText(R.string.snake_library);
            if (tvHeaderSubtitle != null) tvHeaderSubtitle.setText(fullSnakeList.size() + " Species in Maharashtra");
            if (btnLangToggle != null) btnLangToggle.setText("मराठी");
            if (searchView != null) searchView.setQueryHint("Search by English, Marathi or Scientific name…");
        }
    }

    private void setupFilterButtons() {
        View.OnClickListener listener = v -> {
            int id = v.getId();
            if (id == R.id.filterAll) {
                currentFilter = 0;
                setFilterButtonStyles(filterAll, filterVenomous, filterNonVenomous);
            } else if (id == R.id.filterVenomous) {
                currentFilter = 1;
                setFilterButtonStyles(filterVenomous, filterAll, filterNonVenomous);
            } else if (id == R.id.filterNonVenomous) {
                currentFilter = 2;
                setFilterButtonStyles(filterNonVenomous, filterAll, filterVenomous);
            }
            applyFilterAndSearch();
        };

        if (filterAll != null) filterAll.setOnClickListener(listener);
        if (filterVenomous != null) filterVenomous.setOnClickListener(listener);
        if (filterNonVenomous != null) filterNonVenomous.setOnClickListener(listener);
    }

    private void setFilterButtonStyles(MaterialButton activeBtn, MaterialButton inactive1, MaterialButton inactive2) {
        if (activeBtn == null || inactive1 == null || inactive2 == null) return;

        activeBtn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.color_primary));
        activeBtn.setTextColor(ContextCompat.getColor(this, R.color.color_on_primary));

        inactive1.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.transparent));
        inactive1.setTextColor(ContextCompat.getColor(this, R.color.color_text_primary));

        inactive2.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.transparent));
        inactive2.setTextColor(ContextCompat.getColor(this, R.color.color_text_primary));
    }

    private void applyFilterAndSearch() {
        String query = searchView != null ? searchView.getQuery().toString().trim().toLowerCase() : "";
        List<DataClass> result = new ArrayList<>();

        for (DataClass item : fullSnakeList) {
            // Apply category filter
            boolean matchesCategory = false;
            if (currentFilter == 0) {
                matchesCategory = true;
            } else if (currentFilter == 1) {
                matchesCategory = (item.getVenomLevel() == 1 || item.getVenomLevel() == 2);
            } else if (currentFilter == 2) {
                matchesCategory = (item.getVenomLevel() == 0);
            }

            if (!matchesCategory) continue;

            // Apply search query across English, Marathi, Scientific, and Habitat fields
            if (query.isEmpty() ||
                    item.getNameEn().toLowerCase().contains(query) ||
                    item.getNameMr().toLowerCase().contains(query) ||
                    item.getScientificName().toLowerCase().contains(query) ||
                    item.getHabitatEn().toLowerCase().contains(query) ||
                    item.getHabitatMr().toLowerCase().contains(query) ||
                    item.getVenomStatusEn().toLowerCase().contains(query) ||
                    item.getVenomStatusMr().toLowerCase().contains(query)) {
                result.add(item);
            }
        }

        if (result.isEmpty()) {
            if (emptyTextView != null) {
                emptyTextView.setVisibility(View.VISIBLE);
                emptyTextView.setText(isMarathi ? "कोणताही साप आढळला नाही" : "No snakes found matching search");
            }
            adapter.setSearchList(new ArrayList<>());
        } else {
            if (emptyTextView != null) emptyTextView.setVisibility(View.GONE);
            adapter.setSearchList(result);
        }
    }
}
