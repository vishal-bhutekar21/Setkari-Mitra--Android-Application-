package com.example.Shetkari_Mitra;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RescuerDatabaseActivity extends AppCompatActivity {

    private RecyclerView recyclerViewRescuers;
    private RescuerAdapter rescuerAdapter;
    private List<Rescuer> rescuerList = new ArrayList<>();
    private SearchView searchView;
    private TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescuer_database);

        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        View cardOpenRescuerMap = findViewById(R.id.cardOpenRescuerMap);
        View btnOpenMapBanner = findViewById(R.id.btnOpenMapBanner);

        View.OnClickListener mapLaunchListener = v -> {
            android.content.Intent intent = new android.content.Intent(RescuerDatabaseActivity.this, MapsActivity.class);
            intent.putExtra("SHOW_RESCUERS", true);
            startActivity(intent);
        };

        if (cardOpenRescuerMap != null) cardOpenRescuerMap.setOnClickListener(mapLaunchListener);
        if (btnOpenMapBanner != null) btnOpenMapBanner.setOnClickListener(mapLaunchListener);

        recyclerViewRescuers = findViewById(R.id.recyclerViewRescuers);
        searchView = findViewById(R.id.searchView);
        emptyTextView = findViewById(R.id.emptyTextView);

        recyclerViewRescuers.setLayoutManager(new LinearLayoutManager(this));

        loadRescuers();

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (rescuerAdapter != null) {
                        rescuerAdapter.getFilter().filter(newText);
                    }
                    return true;
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRescuers();
    }

    private void loadRescuers() {
        rescuerList = LocalRescuerData.getRescuers();
        rescuerAdapter = new RescuerAdapter(rescuerList);
        recyclerViewRescuers.setAdapter(rescuerAdapter);

        if (emptyTextView != null) {
            emptyTextView.setVisibility(rescuerList.isEmpty() ? View.VISIBLE : View.GONE);
        }
    }
}
