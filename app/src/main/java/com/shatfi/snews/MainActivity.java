package com.shatfi.snews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shatfi.snews.adapter.MyAdapter;
import com.shatfi.snews.fragment.FragmentAbout;
import com.shatfi.snews.fragment.FragmentCategory;
import com.shatfi.snews.fragment.FragmentHome;
import com.shatfi.snews.retrofitconfig.GetJsonAll;
import com.shatfi.snews.retrofitconfig.RetrofitConfigToJson;
import com.shatfi.snews.retrofitjson.News;
import com.shatfi.snews.retrofitjson.NewsList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemReselectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::OnNavigationItemReselectedListener);
        fragmenClick(new FragmentHome());
    }

    public boolean OnNavigationItemReselectedListener(@NonNull MenuItem menuItem){
        int userId = menuItem.getItemId();
        Fragment fragment = null;

        switch (userId){
            case R.id.home:
                fragment = new FragmentHome();
                break;
            case R.id.category:
                fragment = new FragmentCategory();
                break;
            case R.id.about:
                fragment = new FragmentAbout();
                break;
        }
        return fragmenClick(fragment);
    }
    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {

    }
    private boolean fragmenClick(Fragment fragment){
        if (fragment != null){

            getSupportFragmentManager().beginTransaction().replace(R.id.ganti, fragment).commit();

        }
        return false;
    }
}