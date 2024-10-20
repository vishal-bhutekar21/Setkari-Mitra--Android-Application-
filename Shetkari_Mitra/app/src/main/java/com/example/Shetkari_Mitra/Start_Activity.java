package com.example.Shetkari_Mitra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Start_Activity extends AppCompatActivity {

    ViewPager2 viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        if (!isInternetConnected()) {
            showInternetDialog();
        } else {
            setupViewPager();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (viewPager.getAdapter() == null && isInternetConnected()) {
            setupViewPager();
        }
    }

    private void setupViewPager() {
        viewPager.setAdapter(new ViewPagerAdapter(this));
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(position == 0 ? "Login" : "Signup")
        ).attach();
    }

    private void showInternetDialog() {
        EnableInternetDialogFragment dialogFragment = new EnableInternetDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "EnableInternetDialogFragment");
    }

    private boolean isInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }
}
