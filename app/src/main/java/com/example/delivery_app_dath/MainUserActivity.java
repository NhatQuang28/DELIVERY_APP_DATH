package com.example.delivery_app_dath;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.delivery_app_dath.fragment.StartOrderFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainUserActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout ;
    // phân biệt giữa các fragment
//    private static final int FRAGMENT_HOME = 0;
//    private static final int FRAGMENT_ODER = 1;
//    private static final int FRAGMENT_WALLET = 2;
//    private static final int FRAGMENT_MYDRIVER = 3;
//    private static final int FRAGMENT_HELPCENTER = 4;
//    private static final int FRAGMENT_SETTING = 5;
//    private int mCurrentFragment = FRAGMENT_HOME;

    private ImageView img_avatarUser;
    private TextView txt_nameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        //tool bar của androidx
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //ánh xạ
        mDrawerLayout = findViewById(R.id.drawer_layout);
        unitIU();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainUserActivity.this,mDrawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //bắt sự kiện của navigation view
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new StartOrderFragment());
//        navigationView.getMenu().findItem(R.id.nav_home).setCheckable(true);

        img_avatarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainUserActivity.this,InformationActivity.class);
                startActivity(intent);
            }
        });

        txt_nameUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainUserActivity.this,InformationActivity.class);
                startActivity(intent);
            }
        });

    }

    private void unitIU(){
        NavigationView navigationView = findViewById(R.id.navigation_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        txt_nameUser = navigationView.getHeaderView(0).findViewById(R.id.tv_nameUser);
        img_avatarUser =  navigationView.getHeaderView(0).findViewById(R.id.img_avatarUser);
    }



    //relaceFragment vào cái activity main
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        int id = item.getItemId();
        if (id == R.id.nav_oder){
            intent = new Intent(MainUserActivity.this, OrderActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_wallet){
            intent = new Intent(MainUserActivity.this,WalletActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_mydrivers){
            intent = new Intent(MainUserActivity.this,MyDriverActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_helpcenter){
            intent = new Intent(MainUserActivity.this,HelpCenterActivity.class);
            startActivity(intent);
        }else if (id == R.id.nav_settings){
            intent = new Intent(MainUserActivity.this,SettingActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.nav_signout){
            FirebaseAuth.getInstance().signOut();
            intent = new Intent(MainUserActivity.this,LoginActivity.class);
            startActivity(intent);
        }

        // đóng gravity drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}