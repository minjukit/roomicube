package com.example.splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class ItemFragment extends Fragment {


    public ItemFragment() {
    }

    public static ItemFragment newInstance() {
        return new ItemFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_myphoto, container, false);


        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tabLayout);
        replaceFragment2(PhotoFragment.newInstance()); //기본설정 사진
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition() ;


                if (pos == 0) { // 첫 번째 탭 선택.
                    replaceFragment2(PhotoFragment.newInstance());
                }else if(pos == 1){ //두번째 탭
                    replaceFragment2(PhotoArraFragment.newInstance());
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        return view;
    }
    public void replaceFragment2(Fragment fragment) {
        FragmentManager fragmentManager = ((FragActivity)getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_photo, fragment).commit();      // Fragment로 사용할 Activity내의 layout공간을 선택합니다.
    }

}