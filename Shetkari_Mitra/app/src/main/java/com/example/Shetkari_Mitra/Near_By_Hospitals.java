package com.example.Shetkari_Mitra;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Near_By_Hospitals extends AppCompatActivity {

    private HospitalAdapter hospitalAdapter;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_hospitals);

        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        emptyTextView = findViewById(R.id.emptyTextView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadHospitalData();

        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (hospitalAdapter != null) {
                        hospitalAdapter.getFilter().filter(newText);
                    }
                    return false;
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadHospitalData();
    }

    private void loadHospitalData() {
        List<Hospital_Info> hospitalList = LocalHospitalData.getAllHospitals();
        hospitalAdapter = new HospitalAdapter(this, hospitalList);
        recyclerView.setAdapter(hospitalAdapter);

        if (emptyTextView != null) {
            emptyTextView.setVisibility(hospitalList.isEmpty() ? View.VISIBLE : View.GONE);
        }
    }
}
