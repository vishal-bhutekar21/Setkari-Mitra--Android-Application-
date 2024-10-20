package com.example.Shetkari_Mitra;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class fragmentset2 extends Fragment {

    View prevention_methods;
    Context f1context;
    ExpandableListView expandableListView3;
    ExpandableListView expandableListView4;
    private TextView InfoLink;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        f1context = getActivity().getApplicationContext();
        InfoLink = view.findViewById(R.id.infoLink);
        InfoLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String youtubeVideoLink = "https://youtu.be/ovyG5t-R9Zw?si=d-LZz1CPmRt3IZ7G";
                Intent ylink = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeVideoLink));
                startActivity(ylink);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        prevention_methods = inflater.inflate(R.layout.fragment_fragmentset2, container, false);

        expandableListView4 = prevention_methods.findViewById(R.id.expandableListView4);
        expandableListView3 = prevention_methods.findViewById(R.id.expandableListView3);

        // Set up your data sources for expandableListView3
        List<String> listDataHeader = new ArrayList<>();
        HashMap<String, List<ChildItem>> listHashMap = new HashMap<>();

        // Add group data
        listDataHeader.add("Snakebite Prevention Tips");

        // Add child data
        List<ChildItem> group1Items = new ArrayList<>();
        group1Items.add(new ChildItem(R.drawable.use_stick, "Use the stick to move the grass or leaves" +
                "first when going farm. Give the resting snake a chance move to away."));
        group1Items.add(new ChildItem(R.drawable.use_torch, "Use at night time to going at farm flashlight is swiched on"));
        group1Items.add(new ChildItem(R.drawable.were_costume, "Walk at grass area or night time with closed type footware (eg.shoes or boots) "));
        group1Items.add(new ChildItem(R.drawable.camping, "Dont keep camping in forest area"));

        listHashMap.put(listDataHeader.get(0), group1Items);

        // Set up the ExpandableListView and adapter
        ExpandableListAdapter3 expandableListAdapter3 = new ExpandableListAdapter3(requireContext(), listDataHeader, listHashMap);
        expandableListView3.setAdapter(expandableListAdapter3);


        List<String> listDataHeader1 = new ArrayList<>();
        HashMap<String, List<ChildItem4>> listHashMap1 = new HashMap<>();

        // Add group data
        listDataHeader1.add("links");

        // Add child data
        List<ChildItem4> group1Items1 = new ArrayList<>();
        group1Items1.add(new ChildItem4("https://www.youtube.com/watch?v=dZubwJkZCpo&pp=ygUdc25ha2UgYml0ZSB0cmVhdG1lbnQgbWFyYXRoaSA%3D", "Snake Bite Symptoms (Marathi)"));
        group1Items1.add(new ChildItem4("https://www.youtube.com/watch?v=hJAzNtRD5rw&pp=ygUdc25ha2UgYml0ZSB0cmVhdG1lbnQgbWFyYXRoaSA%3D", "Snake bite Treatment in marathi"));
        group1Items1.add(new ChildItem4("https://www.youtube.com/watch?v=l5hNxW3vhFk&pp=ygUdc25ha2UgYml0ZSB0cmVhdG1lbnQgbWFyYXRoaSA%3D","First Aid and Emengency Treatment in English"));
        group1Items1.add(new ChildItem4("https://www.youtube.com/watch?v=pwWoQ1EXQE4&pp=ygUlcHJlY2F1dGlvbnMgZm9yIHNuYWtlIGJpdGUgaW4gbWFyYXRoaQ%3D%3D","Snake Bite Sefty (Marathi)"));
        listHashMap1.put(listDataHeader1.get(0), group1Items1);

        // Set up the ExpandableListView and adapter
        MyExpandableListAdapter myExpandableListAdapter = new MyExpandableListAdapter(requireContext(), listDataHeader1, listHashMap1);
        expandableListView4.setAdapter(myExpandableListAdapter);

        // Set click listener for child items
        expandableListView4.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // Get the clicked child item
                ChildItem4 childItem4 = listHashMap1.get(listDataHeader1.get(groupPosition)).get(childPosition);

                // Open the YouTube video link in a web browser or the YouTube app
                openYoutubeVideo(childItem4.getLinks());
                return true;

            }

            private void openYoutubeVideo(String videoUrl) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.google.android.youtube");
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    // If the YouTube app is not installed, open the link in a web browser
                    intent.setPackage(null);
                    startActivity(intent);
                }
            }
        });
        return prevention_methods;
    }



}
