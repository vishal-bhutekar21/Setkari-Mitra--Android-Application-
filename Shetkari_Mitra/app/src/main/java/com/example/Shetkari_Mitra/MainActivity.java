package com.example.Shetkari_Mitra;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<DataClass> dataList;
    private MyAdapter adapter;
    private SearchView searchView;
    private TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);

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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();

        populateSnakeData();

        adapter = new MyAdapter(MainActivity.this, dataList);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });
    }

    private void populateSnakeData() {
        dataList.add(new DataClass("Indian Cobra (नाग)",
                "The Indian cobra (Naja naja), also known as the spectacled cobra, is a highly venomous snake species native to the Indian subcontinent. It is one of the 'Big Four' venomous snakes.",
                "Poisonous (Venomous)",
                "Grasslands, agricultural fields, dense forests, and rural areas across Maharashtra and Jalna.",
                R.drawable.p2));

        dataList.add(new DataClass("Common Krait (मण्यार)",
                "The Common Krait (Bungarus caeruleus) is one of the most venomous snakes in India. Nocturnal and dangerous. Member of the 'Big Four'.",
                "Poisonous (Venomous)",
                "Inhabits villages, fields, stone piles, termite mounds, and human settlements.",
                R.drawable.common_krait));

        dataList.add(new DataClass("Russell's Viper (घोणस)",
                "Russell's viper (Daboia russelii) is a highly venomous viper responsible for the majority of snakebite incidents in India. Member of the 'Big Four'.",
                "Poisonous (Venomous)",
                "Agricultural areas, near water sources, farmlands, scrublands, and rocky terrain across Maharashtra.",
                R.drawable.viper));

        dataList.add(new DataClass("Saw-Scaled Viper (फुरसे)",
                "The Saw-Scaled Viper (Echis carinatus) is small but highly aggressive and venomous. Member of the 'Big Four'. Rubs scales to create a raspy warning sound.",
                "Poisonous (Venomous)",
                "Dry rocky terrain, scrublands, arid agricultural fields of Maharashtra.",
                R.drawable.saw_scaled_viper));

        dataList.add(new DataClass("Bamboo Pit Viper (चाबूक)",
                "The Bamboo Pit Viper (Craspedocephalus gramineus) is a green venomous arboreal viper endemic to peninsular India.",
                "Poisonous (Venomous)",
                "Bamboo groves, Western Ghats forests, stream banks, and dense vegetation.",
                R.drawable.bamboo_pit_viper));

        dataList.add(new DataClass("Rat Snake (धामण)",
                "Rat snakes (Ptyas mucosa) are non-venomous, fast-moving, large snakes. Highly beneficial to farmers as they control the rodent population.",
                "Non-poisonous (Harmless)",
                "Agricultural fields, crop storage, wetlands, rural and urban areas throughout Maharashtra.",
                R.drawable.ratsnake));

        dataList.add(new DataClass("Indian Rock Python (अजगर)",
                "The Rock Python (Python molurus) is a heavy non-venomous constrictor snake protected under Wildlife Protection Act.",
                "Non-poisonous (Harmless)",
                "Riverbanks, rocky crevices, agricultural borders, and forest edges.",
                R.drawable.indian_rock_python));

        dataList.add(new DataClass("Checkered Keelback (दिवड)",
                "A semi-aquatic non-venomous snake that feeds primarily on frogs and fish. Very common in irrigation channels.",
                "Non-poisonous (Harmless)",
                "Wells, farm ponds, paddy fields, irrigation canals, and rivers in Jalna.",
                R.drawable.checkered_snake));

        dataList.add(new DataClass("Common Trinket Snake (तस्कर)",
                "A slender, non-venomous snake with distinct black stripes behind the eyes and bands along the body.",
                "Non-poisonous (Harmless)",
                "Gardens, agricultural lands, and open forests throughout India.",
                R.drawable.common_trinket_snake));

        dataList.add(new DataClass("Montane Trinket Snake",
                "Slender-bodied non-venomous snake with tan-olive color and dark lateral stripes on the body.",
                "Non-poisonous (Harmless)",
                "Western Ghats and peninsular plateau of Maharashtra.",
                R.drawable.montain_trinket_snake));

        dataList.add(new DataClass("Banded Racer",
                "Smooth-scaled non-venomous racer snake with tapered snout. Fast moving and harmless to humans.",
                "Non-poisonous (Harmless)",
                "Plains and dry scrub of central Maharashtra.",
                R.drawable.banded_racer));

        dataList.add(new DataClass("Slender Racer",
                "Small, slender, non-venomous snake with large eyes. Completely harmless to humans.",
                "Non-poisonous (Harmless)",
                "Grasslands and agricultural boundaries.",
                R.drawable.slender_racer));

        dataList.add(new DataClass("Russell's Kukri Snake (कुखरी)",
                "Non-venomous snake with smooth scales and distinctive chevron marks on head and neck.",
                "Non-poisonous (Harmless)",
                "Dry deciduous forests, fields, and rocky outcrops.",
                R.drawable.russells_kukri_snake));

        dataList.add(new DataClass("Common Kukri Snake",
                "Small non-venomous snake, active mainly at night. Harmless to humans and livestock.",
                "Non-poisonous (Harmless)",
                "Found in household surroundings, brick piles, and gardens.",
                R.drawable.common_kukri_snake));

        dataList.add(new DataClass("Common Bronzeback Tree Snake",
                "Long, slender tree snake with bronze-brown coloring and dark stripe along body.",
                "Non-poisonous (Harmless)",
                "Trees, shrubs, orchards, and farmlands.",
                R.drawable.common_bronzeback_tree_snake));

        dataList.add(new DataClass("Yellow-Spotted Wolf Snake (कवड्या)",
                "Small non-venomous snake with yellow spots. Often mistaken for Common Krait, but has distinct round pupil and yellow spots.",
                "Non-poisonous (Harmless)",
                "Found around old walls, rock crevices, and farm sheds.",
                R.drawable.yellow_spotted_wolf_snake));
    }

    private void searchList(String text) {
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList) {
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase()) ||
                    data.getDataLang().toLowerCase().contains(text.toLowerCase()) ||
                    data.getDataLocation().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()) {
            if (emptyTextView != null) emptyTextView.setVisibility(View.VISIBLE);
            adapter.setSearchList(new ArrayList<>());
        } else {
            if (emptyTextView != null) emptyTextView.setVisibility(View.GONE);
            adapter.setSearchList(dataSearchList);
        }
    }
}
