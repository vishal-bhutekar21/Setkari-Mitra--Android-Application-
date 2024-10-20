package com.example.Shetkari_Mitra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    DataClass androidData;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);

        searchView.clearFocus();
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();

        androidData = new DataClass("Indian Cobra", "The Indian cobra (Naja naja), also known as the spectacled cobra or the Asian cobra, is a highly venomous snake species native to the Indian subcontinent", "Poisonous", " They inhabit diverse habitats within these areas, such as grasslands, agricultural fields, dense forests, and urban environments. Additionally, they are known to thrive in both rural and urban settings, making them adaptable to a wide range of habitats within the Indian subcontinent.\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n", R.drawable.p2);
        dataList.add(androidData);

        androidData = new DataClass("Black Krait", "The \"black krait\" is a common name for Bungarus niger, a species of venomous snake ", "Poisonous", " It can be found in a variety of habitats, including mangrove swamps, inhabited villages, montane forests, and tea gardens. \n", R.drawable.common_krait);
        dataList.add(androidData);

        androidData = new DataClass("Russial Viper", "\n" +
                "The Russell's viper (Daboia russelii) is a highly venomous snake", "Poisonous", " They may inhabit areas near water sources, such as rivers, streams, and rice paddies, where prey availability is higher. Additionally, their camouflage and nocturnal behavior make them well-suited to hiding in vegetation or rocky terrain, making them challenging to spot in the wild.", R.drawable.viper);
        dataList.add(androidData);

        androidData = new DataClass("Bamboo Pit Viper", "The Indian Green Pit Viper knew as Bamboo Pit Viper is a toxic species endemic to the Western ghats of Maharashtra.", "Poisonous", "The Bamboo Pit Viper can be spotted on treks such as Harishchandragad, Kalsubai, Naneghat, Ajoba, Tung fort, Jivdhan fort, Ratangad fort, Bhairavgad, and Alang-Madan-Kulang Forts.",R.drawable.bamboo_pit_viper);
        dataList.add(androidData);

        androidData = new DataClass("Saw Scaled Viper", "The Indian Saw-Scaled Viper is a little viper among the eight types of small Viper poisonous snakes family. It is also the smallest member of the Big Four snakes in India. It is a coarse scaled snake with big eyes, a broader head than neck, and a thickset body. They are found in sandy and rocky regions as well as soft soil and shrublands. ", "Poisonous", "Found in rocky terrains, scrublands, and agricultural areas of Maharashtra.", R.drawable.saw_scaled_viper);
        dataList.add(androidData);

        androidData = new DataClass("Rat Snake", "Rat snakes are non-venomous constrictor snakes belonging to the family Colubridae", "Non-poisonous", "They are well-adapted to various terrestrial habitats such as forests, grasslands, agricultural areas, and urban environments. While they are not typically associated with aquatic environments, some species of rat snakes are known to swim and may occasionally enter water bodies such as rivers, streams, or ponds, especially when hunting for prey or seeking refuge. However, compared to truly aquatic or semi-aquatic species, rat snakes are not specialized for swimming or spending extended periods in water. Their primary habitat and behavior revolve around terrestrial ecosystems.", R.drawable.ratsnake);
        dataList.add(androidData);

        androidData = new DataClass("Indian Rock Python", " The Rock Python is a non-venomous snake with yellow and black colour, which differs depending on its territory. The rock python is one of the heaviest and longest snakes of India. ", "non-poisonous", "Found in agricultural lands, forests, and rural areas of Maharashtra.", R.drawable.indian_rock_python);
        dataList.add(androidData);

        androidData = new DataClass("Checkered keelback", " A non-venomous snake that lives in ditches along roads, streams, stagnant water in paddy fields, and holes and bushes near permanent sources of water. ", "non-poisonous", "Found in agricultural lands,wells,rivers, water, and permenent water areas of Maharashtra.", R.drawable.checkered_snake);
        dataList.add(androidData);

        androidData = new DataClass("Common Trinket Snake","Slender bodied,scales of hind body and tail sometimes keeled.Mostly tan or olive and chocolate brown wih two black stripes on neck,light bands and checks on forebody","non-poisonous","Found throughout India,Srilanka,Nepal,Pakistan",R.drawable.common_trinket_snake);
        dataList.add(androidData);

        androidData = new DataClass("Montane Trinket Snake","Slender bodied.scales of hind body and tail sometimes keeled.Tan olive and chocolate brown with a banded forebody.The bands consist of several rows of large pale yellow oval or round spots encircled with black.Hind body has lateral stripes","non-poisonous","Western Ghats(Tamil Nadu,Kerala,Karnataka)",R.drawable.montain_trinket_snake);
        dataList.add(androidData);

        androidData = new DataClass("Banded Racer","Scales smooth ,light or dark brown,snout tapered.Young with brilliant white head markings and bright white cross bands.Adults usually without bands,belly white or yellowish white","non-poisonous","Found throughout India,from Baroda to Gwalior",R.drawable.banded_racer);
        dataList.add(androidData);

        androidData = new DataClass("Slender Racer","Small ,slender,smooth scaled.head broader than neck,large eye with round pupil.Tail long and thin.Narrow white,black edged cross bars on the back,which widen on the sides to connect with adjacent bands","non-poisonous","Recorded only from Maharashtra and Madhya Pradesh",R.drawable.slender_racer);
        dataList.add(androidData);

        androidData = new DataClass("Russell's Kukri Snake","Scales smooth,head slightly or not broader than neck.Eye has round pupil,tail has pointed tip.Markings on head and back extremely variable. “^” shaped marks closely followed by a broad band or blotch on neck","non-poisonous","Found in hills and plains of peninsular and northern india.western Himalayas",R.drawable.russells_kukri_snake);
        dataList.add(androidData);

        androidData = new DataClass("Common Kukri Snake","Scales smooth ,head slightly or not broader than neck,with typical ^ shaped marks seen on most kukri snakes,eye has round pupil.Short tail has pointed tip,single scale at tip of snout extends well onto upper surface of head.10 – 20 distinct black or dark brown bands adn three ^ shaped marks of same color on head","non-poisonous","Found throughout India, except Northeast,Pakistan,Nepal,Bangladesh,Srilanka",R.drawable.common_kukri_snake);
        dataList.add(androidData);

        androidData = new DataClass("Common Bronzeback Tree Snake","Long,slender,smooth scaled,head distinctly broader than neck,snout bluntly rounded.Large eye has round pupil.Tail very long,thin and wire like .Bronze brown or purplish brown back with a dark brown or black stripe on either side of body","non-poisonous","Found in downward parts of India,northeast india to Darjeeling",R.drawable.common_bronzeback_tree_snake);
        dataList.add(androidData);

        androidData = new DataClass("Yellow Spotted Wolf Snake","Slender bodied,smooth scaled,flattened head slightly broader than neck,snout broad,projects beyond lower jaw. Eye entirely black,series of small bright yellow spots along the midback. Lips scales and underside white","non-poisonous","Only recorded from a few places in and around Western Ghats of Maharashtra",R.drawable.yellow_spotted_wolf_snake);
        dataList.add(androidData);
        
        adapter = new MyAdapter(MainActivity.this, dataList);
        recyclerView.setAdapter(adapter);


    }

    private void searchList(String text) {
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList) {
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()) {
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }


}
