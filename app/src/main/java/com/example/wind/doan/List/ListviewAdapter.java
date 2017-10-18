package com.example.wind.doan.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.wind.doan.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by wind on 9/23/2017.
 */

public class ListviewAdapter extends ArrayAdapter<ListView_player> {


    public ListviewAdapter(Context context, int resource, List<ListView_player> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.activity_dong_dl, null);
        }
        ListView_player p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txtTen = (TextView) view.findViewById(R.id.namePlaces);
            txtTen.setText(p.ten);
            TextView txtDiachi = (TextView) view.findViewById(R.id.addressPlaces);
            txtDiachi.setText(p.diachi);
            //ImageView imgHinh = (ImageView) view.findViewById(R.id.imagePlaces);
        }

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.amin_listview);
        view.startAnimation(animation);

        return view;
    }
}