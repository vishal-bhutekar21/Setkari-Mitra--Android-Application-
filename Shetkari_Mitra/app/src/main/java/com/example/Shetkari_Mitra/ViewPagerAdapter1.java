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
        if (position == 1) {
            return new Fragment_Admin_Signup();
        }
        return new Fragment_Admin_login();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
