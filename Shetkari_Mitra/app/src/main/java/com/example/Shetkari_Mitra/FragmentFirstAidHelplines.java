package com.example.Shetkari_Mitra;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentFirstAidHelplines extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_aid_helplines, container, false);

        View card108 = view.findViewById(R.id.cardCall108);
        View card112 = view.findViewById(R.id.cardCall112);
        View card1926 = view.findViewById(R.id.cardCall1926);
        View cardPoison = view.findViewById(R.id.cardCallPoison);
        View cardJalnaCivil = view.findViewById(R.id.cardCallJalnaCivil);

        if (card108 != null) card108.setOnClickListener(v -> dialNumber("108"));
        if (card112 != null) card112.setOnClickListener(v -> dialNumber("112"));
        if (card1926 != null) card1926.setOnClickListener(v -> dialNumber("1926"));
        if (cardPoison != null) cardPoison.setOnClickListener(v -> dialNumber("1800116117"));
        if (cardJalnaCivil != null) cardJalnaCivil.setOnClickListener(v -> dialNumber("02482225600"));

        return view;
    }

    private void dialNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }
}
