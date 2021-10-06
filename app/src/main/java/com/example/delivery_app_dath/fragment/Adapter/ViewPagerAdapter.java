package com.example.delivery_app_dath.fragment.Adapter;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.delivery_app_dath.fragment.HomeFragment;
import com.example.delivery_app_dath.fragment.OrderFragment.CancelledFragment;
import com.example.delivery_app_dath.fragment.OrderFragment.CompletedFragment;
import com.example.delivery_app_dath.fragment.OrderFragment.OnGoingFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new OnGoingFragment();
            case 1:
                return new CompletedFragment();
            case 2:
                return new CancelledFragment();
            default:
                return new OnGoingFragment();
        }
    }
    // hàm getCoutn trả ra số lượng tab
    @Override
    public int getCount() {
        return 3;
    }

    // sửa lại title cho tab
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";

        switch(position){
            case 0:
                title = "Đã đặt";
                break;
            case 1:
                title = "Hoàn thành";
                break;
            case 2:
                title = "Đã hủy";
                break;

        }
        return title;
    }
}
