package com.example.splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class PhotoArraFragment extends Fragment {

    public static PhotoArraFragment newInstance() {
        return new PhotoArraFragment();
    }

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_photo3d, container, false);
        return view;
    }

}
