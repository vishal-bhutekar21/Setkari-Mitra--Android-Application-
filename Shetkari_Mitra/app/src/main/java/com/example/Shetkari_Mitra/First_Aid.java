package com.example.Shetkari_Mitra;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class First_Aid extends AppCompatActivity {

    private final String[] tabTitles = new String[]{
            "Steps (पायऱ्या)",
            "DOs & DON'Ts",
            "Symptoms (लक्षणे)",
            "Helplines (हेल्पलाईन)"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid);

        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        ViewPager2 viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.tablayout);

        viewPager.setAdapter(new FirstAidPagerAdapter(this));

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position < tabTitles.length) {
                tab.setText(tabTitles[position]);
            }
        }).attach();
    }

    private static class FirstAidPagerAdapter extends FragmentStateAdapter {

        public FirstAidPagerAdapter(@NonNull AppCompatActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new FragmentFirstAidSteps();
                case 1:
                    return new FragmentFirstAidDoDonts();
                case 2:
                    return new FragmentFirstAidSymptoms();
                case 3:
                    return new FragmentFirstAidHelplines();
                default:
                    return new FragmentFirstAidSteps();
            }
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }
}