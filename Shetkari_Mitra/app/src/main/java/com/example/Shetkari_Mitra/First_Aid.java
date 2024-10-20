package com.example.Shetkari_Mitra;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class First_Aid extends AppCompatActivity {


    private final String[] Pages_title= new String[]
            {
                    "Do's And Don't",
                    "Preventation methods",
                    "Symptoms"

            };

    private final Fragment[] Pages=new Fragment[]
            {
                    new fragmentset1(),
                    new fragmentset2(),
                    new fragmentset3()
            };

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid);


        mViewPager=findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout=findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(mViewPager);

    }



    public class MyPagerAdapter extends FragmentPagerAdapter
    {

        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return Pages[position];
        }

        @Override
        public int getCount() {
            return Pages.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return Pages_title[position];
        }
    }
}