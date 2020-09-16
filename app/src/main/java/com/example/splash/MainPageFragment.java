package com.example.splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainPageFragment extends Fragment {

    ViewGroup viewGroup;
    private ImageView imageCloset;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.frag_mainpage,container,false);

        imageCloset = (ImageView)viewGroup.findViewById(R.id.closet_image);
        imageCloset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragActivity)getActivity()).replaceFragment(ClosetFragment.newInstance());
            }
        });

        return viewGroup;
    }
}
