package com.example.Shetkari_Mitra;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class ViewPagerAdapter1 extends FragmentStateAdapter {

    public ViewPagerAdapter1(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Fragment_Admin_login();
            case 1:
                return new Fragment_Admin_Signup();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Number of tabs
    }
}
