package com.example.splash;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import me.relex.circleindicator.CircleIndicator;

public class ArrangementFragment extends Fragment {


    public ArrangementFragment() {
        // Required empty public constructor
    }

    public static ArrangementFragment newInstance() {
        ArrangementFragment fragment = new ArrangementFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    Context context;
    Activity activity;
    ViewPager vPager;
    Arr_pagerAdapter adapter;
    CircleIndicator indicator;

    ImageButton button3d;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_arrangement, container, false);
        activity = getActivity();
        context = getContext();
        vPager = view.findViewById(R.id.viewPager);
        adapter = new Arr_pagerAdapter(context);
        vPager.setAdapter(adapter);

        indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(vPager);


        button3d = (ImageButton)view.findViewById(R.id.BtnStart3d);

        button3d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* 유니티 연결 (버튼클릭시)
                Intent titleIntent = new Intent(activity.this, Unity.class);
                activity.this.startActivity(titleIntent);
                */

            }

        });
         return view;
    }

    public class Arr_pagerAdapter extends PagerAdapter {

        private int[] images = {R.drawable.facebook_logo, R.drawable.splash_logo,R.drawable.facebook_logo}; //설명그림

        private LayoutInflater inflater;
        private Context context;

        public Arr_pagerAdapter(Context context){
            super();
            this.context = context;
        }
        @Override
        public int getCount() {
            return images.length;
        }
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == ((View)object);
        }
        @Override
        public Object instantiateItem(ViewGroup container, int pos){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.fragment_arr_image, container, false);
            ImageView imageView = view.findViewById(R.id.image);
            imageView.setImageResource(images[pos]);
            container.addView(view);

            return view;
        }
        @Override
        public void destroyItem(ViewGroup container, int pos, Object object){
            container.invalidate();
        }

    }


}
