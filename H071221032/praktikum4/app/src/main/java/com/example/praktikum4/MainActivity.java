package com.example.praktikum4;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity{
    private BottomNavigationView bottomNavigationView;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        FragmentManager fragmentManager = getSupportFragmentManager();

        HomeFragment homeFragment = new HomeFragment();
        AddPostFragment addPostFragment = new AddPostFragment();
        ProfileFragment profileFragment =  new ProfileFragment();

        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());

        if (!(fragment instanceof HomeFragment)){
            fragmentManager
                    .beginTransaction()
                    .add(R.id.mainView, homeFragment)
                    .commit();
        }

        bottomNavigationView = findViewById(R.id.botnavbar);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item) {
                int itemID = item.getItemId();
                if (itemID==R.id.menu_home) {
                    homeFragment.setArguments(bundle);
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.mainView, homeFragment)
                            .commit();
                    return true;
                } else if (itemID==R.id.menu_add) {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.mainView, addPostFragment)
                            .commit();
                    return true;
                } else if (itemID==R.id.menu_profile) {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.mainView, profileFragment)
                            .commit();
                    return true;
                } else {
                    return false;
                }
            }
        });

    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}