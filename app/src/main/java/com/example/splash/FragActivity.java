package com.example.splash;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragActivity extends AppCompatActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;
    private MainPageFragment menu1Fragment;
    private CommuActivity menu2Fragment;
    private ArrangementFragment menu3Fragment;
    private CommuActivity menu4Fragment;
    private MyPageFragment menu5Fragment;

    private BottomNavigationView mBottomNV;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);

        mBottomNV = findViewById(R.id.nav_view);

        fm =  getSupportFragmentManager();
        ft = fm.beginTransaction();
        menu1Fragment = new MainPageFragment();
        menu2Fragment = new CommuActivity();
        menu3Fragment = new ArrangementFragment();
        menu4Fragment = new CommuActivity();
        menu5Fragment = new MyPageFragment();

        ft.replace(R.id.frame_layout, menu1Fragment).commitAllowingStateLoss();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, menu1Fragment).commitAllowingStateLoss();

        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) { //menu_bottom.xml에서 지정해줬던 아이디 값을 받아와서 각 아이디값마다 다른 이벤트를 발생시킵니다.
                    case R.id.navigation_1: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, menu1Fragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.navigation_2: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, menu2Fragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.navigation_3: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, menu3Fragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.navigation_4: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, menu4Fragment).commitAllowingStateLoss();
                        return true;
                    }
                    case R.id.navigation_5: {
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, menu5Fragment).commitAllowingStateLoss();
                        return true;
                    }
                    default:
                        return false;
                }
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment).commit();      // Fragment로 사용할 Activity내의 layout공간을 선택합니다.
    }

}