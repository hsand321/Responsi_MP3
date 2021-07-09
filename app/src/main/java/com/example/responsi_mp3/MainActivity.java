package com.example.responsi_mp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.responsi_mp3.fragment.HistoryFragment;
import com.example.responsi_mp3.fragment.HomeFragment;
import com.example.responsi_mp3.fragment.PaymentFragment;
import com.example.responsi_mp3.fragment.SettingsFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment = null;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChipNavigationBar chipNavigationBar = findViewById(R.id.chipNavigation);

        chipNavigationBar.setItemSelected(R.id.home, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

        chipNavigationBar.setOnItemSelectedListener(i -> {
            switch (i) {
                case R.id.home:
                    fragment = new HomeFragment();
                    break;
                case R.id.payment:
                    fragment = new PaymentFragment();
                    break;
                case R.id.history:
                    fragment = new HistoryFragment();
                    break;
                case R.id.settings:
                    fragment = new SettingsFragment();
                    break;
            }

            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            }
        });
    }
}