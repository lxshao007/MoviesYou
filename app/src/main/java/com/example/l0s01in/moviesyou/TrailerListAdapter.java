package com.example.l0s01in.moviesyou;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.l0s01in.moviesyou.Models.Trailer;

import java.util.List;

public class TrailerListAdapter extends ArrayAdapter<Trailer> {
    public TrailerListAdapter(@NonNull Context context, @NonNull List<Trailer> trailers) {
        super(context, R.layout.list_trailer, trailers);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Trailer trailer = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_trailer, parent, false);
        }

        TextView trailerName = (TextView) convertView.findViewById(R.id.trailer_name);
        trailerName.setText( "Trailer" + " " +Integer.toString(position + 1) );

        LinearLayout trailerButton = (LinearLayout) convertView.findViewById(R.id.trailer_button);
        trailerButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + trailer.getKey()));
                getContext().startActivity(webIntent);
                return false;
            }
        });
        return convertView;

    }
}
