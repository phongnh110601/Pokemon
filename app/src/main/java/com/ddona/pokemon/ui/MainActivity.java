package com.ddona.pokemon.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.ddona.pokemon.R;
import com.ddona.pokemon.adpater.PokemonPagerAdapter;
import com.ddona.pokemon.di_demo.Car;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private TabLayout tabLayout;
    private ViewPager vpPokemon;
    @Inject
    public Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        testDI();
    }

    private void testDI() {
        car.run();
    }

    private void initViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        drawerLayout = findViewById(R.id.drawer);
        vpPokemon = findViewById(R.id.vp_pokemon);
        vpPokemon.setAdapter(new PokemonPagerAdapter(getSupportFragmentManager()));
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(vpPokemon);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            if (!drawerLayout.isDrawerOpen(GravityCompat.START)){
                drawerLayout.openDrawer(GravityCompat.START);
            } else {
                drawerLayout.close();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}