package com.example.Shetkari_Mitra;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class fragmentset1 extends Fragment {

    View do_and_dont;
    Context f1context;

    ExpandableListView expandableListView;
    ExpandableListView expandableListView2;

    ExpandableListAdapter expandableListAdapter;
    ExpandableListAdapter2 expandableListAdapter2;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        f1context = getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        do_and_dont = inflater.inflate(R.layout.fragment_fragmentset1, container, false);

        expandableListView = do_and_dont.findViewById(R.id.expandableListView);
        expandableListDetail = yourDataSource(); // Load your data here
        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListAdapter(requireContext(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);

//        // Optional: Add listener to handle item clicks
//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                // Handle child item click here
//                return true;
//            }
//        });

        expandableListView2 = do_and_dont.findViewById(R.id.expandableListView2);

        // Set up your data sources
        List<String> listDataHeader = new ArrayList<>();
        HashMap<String, List<ChildItem>> listHashMap = new HashMap<>();

        // Add group data
        listDataHeader.add("Do's");
        listDataHeader.add("Don'ts");

        // Add child data
        List<ChildItem> group1Items = new ArrayList<>();
        group1Items.add(new ChildItem(R.drawable.do1, "One end of the stick should be tied in " +
                "such way that it is above the nearest joint and other end will be below the" +
                "bitten part. This way the affected limb will be immobilized ideally."));
        group1Items.add(new ChildItem(R.drawable.do2, "Wash the bite area with soap and water if possible, " +
                "but do not attempt to suck out the venom or apply ice to the wound."));
        group1Items.add(new ChildItem(R.drawable.do3, "Do not delay seeking medical attention or wait for " +
                "symptoms to appear before seeking help. "));
        group1Items.add(new ChildItem(R.drawable.do4, "Get a antivenom fastly"));

        listHashMap.put(listDataHeader.get(0), group1Items);

        List<ChildItem> group2Items = new ArrayList<>();
        group2Items.add(new ChildItem(R.drawable.dont2, "Do not suck the wound"));
        group2Items.add(new ChildItem(R.drawable.dont3, "Do not cut the wound or bitten area"));
        group2Items.add(new ChildItem(R.drawable.dont4, "Do not tie ligatures around the wound, " +
                "it wound rather make the situation worse"));
        group2Items.add(new ChildItem(R.drawable.dont5, "Do not burn the wound"));
        group2Items.add(new ChildItem(R.drawable.dont6, "Do not apply any herbal pastes over the wound"));
        listHashMap.put(listDataHeader.get(1), group2Items);

        // Set up the ExpandableListView and adapter
        ExpandableListAdapter2 expandableListAdapter2 = new ExpandableListAdapter2(requireContext(), listDataHeader, listHashMap);
        expandableListView2.setAdapter(expandableListAdapter2);

        return do_and_dont;
    }
    private HashMap<String, List<String>> yourDataSource() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<>();

        // Add data for Group 1
        List<String> group1 = new ArrayList<>();
        group1.add("Do not take a patient any tantric or ojha or snake charmer" +
                "for a treatment. A venomous snakebite can be treated only in a" +
                "hospitals by administration os anti-snake venom[ASV]. ");
        group1.add("Do not Panic, all snakes are not poisonous.");

        expandableListDetail.put("Important Information", group1);




        return expandableListDetail;
    }

}
