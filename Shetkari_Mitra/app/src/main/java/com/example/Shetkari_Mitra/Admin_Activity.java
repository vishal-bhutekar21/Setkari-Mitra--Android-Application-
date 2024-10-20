package com.example.Shetkari_Mitra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Admin_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ViewPager2 viewPager = findViewById(R.id.view_pager1);
        viewPager.setAdapter(new ViewPagerAdapter1(this));

        TabLayout tabLayout = findViewById(R.id.tab_layout1);
        new TabLayoutMediator(tabLayout, viewPager,

                (tab, position) -> tab.setText(position == 0 ? "Login" : "Signup")
        ).attach();
    }
}