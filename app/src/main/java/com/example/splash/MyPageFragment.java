package com.example.splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

    public class MyPageFragment extends Fragment {
        ViewGroup viewGroup;
        ImageButton btn_myPhoto;

        public static MyPageFragment newInstance(){
            return new MyPageFragment();
        }
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_my_page,container,false);


            btn_myPhoto = (ImageButton)viewGroup.findViewById(R.id.BtnMyPhoto);

            btn_myPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v){
                ((FragActivity)getActivity()).replaceFragment(ItemFragment.newInstance());
            }
            });
            return viewGroup;
        }

    }
