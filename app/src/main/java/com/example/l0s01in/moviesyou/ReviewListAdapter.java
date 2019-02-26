package com.example.l0s01in.moviesyou;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.l0s01in.moviesyou.Models.Review;


import java.util.List;

public class ReviewListAdapter extends ArrayAdapter<Review> {
    public ReviewListAdapter(@NonNull Context context, @NonNull List<Review> reviews) {
        super(context, R.layout.list_trailer, reviews);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Review review = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_trailer, parent, false);
        }

        TextView reviewName = (TextView) convertView.findViewById(R.id.trailer_name);
        reviewName.setText( "Review" + " " +Integer.toString(position + 1) );

        LinearLayout reviewButton = (LinearLayout) convertView.findViewById(R.id.trailer_button);
        reviewButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(getContext(), ReviewActivity.class);
                intent.putExtra("review", review.getContent());
                getContext().startActivity(intent);
                return false;
            }
        });
        return convertView;

    }
}

