package com.example.Shetkari_Mitra;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class fragmentset3 extends Fragment {

    View symptoms;
    Context f1context;

    private List<Snakeitem> snakeitemList;
    private RecyclerView f3recyclerView;
    private SnakeAdapter snakeAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        f1context = getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        symptoms = inflater.inflate(R.layout.fragment_fragmentset3, container, false);

        snakeitemList = createSnakeItems();
        f3recyclerView = symptoms.findViewById(R.id.f3recyclerView); // Make sure you use the correct ID
        f3recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        snakeAdapter = new SnakeAdapter(snakeitemList);
        f3recyclerView.setAdapter(snakeAdapter);

        return symptoms;
    }

    private List<Snakeitem> createSnakeItems() {
        List<Snakeitem> snakeitemList = new ArrayList<>();
        snakeitemList.add(new Snakeitem("Bleeding","Bledding", R.drawable.bleeding2));
        snakeitemList.add(new Snakeitem("Blister", "Blister formation",R.drawable.blister));
        snakeitemList.add(new Snakeitem("Blurred Vision","Blurring in the Vision", R.drawable.blurred_vision));
        snakeitemList.add(new Snakeitem("Drop EyeLids","Droping of the eye Lids", R.drawable.dropping_eyelids));
        snakeitemList.add(new Snakeitem("Headache","Headache and Vertigo", R.drawable.headache_vertigo));
        snakeitemList.add(new Snakeitem("Swallow","Inability to swallow", R.drawable.swallow));
        snakeitemList.add(new Snakeitem("Swelling","Swilling of the local site and adjioning part of bite", R.drawable.swelling2));
        snakeitemList.add(new Snakeitem("Vomiting","Unexpalined abdominal pain with vomiting", R.drawable.vomiting));

        return snakeitemList;
    }
}
