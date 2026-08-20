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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid);

        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        String currentLang = LocaleHelper.getLanguage(this);
        String[] titles;
        if (LocaleHelper.LANGUAGE_MARATHI.equals(currentLang)) {
            titles = new String[]{"प्रथमोपचार पायऱ्या", "काय करावे / करू नये", "लक्षणे", "हेल्पलाईन"};
        } else if (LocaleHelper.LANGUAGE_HINDI.equals(currentLang)) {
            titles = new String[]{"प्राथमिक उपचार", "क्या करें / न करें", "लक्षण", "हेल्पलाइन"};
        } else {
            titles = new String[]{"First Aid Steps", "Dos & Don'ts", "Symptoms", "Helplines"};
        }

        ViewPager2 viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.tablayout);

        viewPager.setAdapter(new FirstAidPagerAdapter(this));

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position < titles.length) {
                tab.setText(titles[position]);
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