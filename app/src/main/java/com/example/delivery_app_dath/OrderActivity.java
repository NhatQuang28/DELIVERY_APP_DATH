package com.example.delivery_app_dath;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.delivery_app_dath.fragment.Adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class OrderActivity extends AppCompatActivity {

    private TabLayout mTablayout;
    private ViewPager mViewPager;

    private void unitIU(){
        mTablayout = findViewById(R.id.tab_layout);
        mViewPager =  findViewById(R.id.view_pager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        unitIU();

        //Chỉnh action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //nhúng fragment vào activity
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);
        mTablayout.setupWithViewPager(mViewPager);


    }
}