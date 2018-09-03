package com.example.l0s01in.moviesyou;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.l0s01in.moviesyou.Models.Movie;
import com.example.l0s01in.moviesyou.Utils.ModelUtils;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> {

    private final List<Movie> movies;

    public ImageListAdapter(List<Movie> movies) {
        this.movies = movies;
    }
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutListItem = R.layout.list_image;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutListItem, parent, false);
        return new ImageViewHolder(view);

    }


    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {
        final Movie movie = movies.get(position);
        Log.d("position", position + "");
        final String imgUrl = movie.getImgUrl();
        Picasso.with(holder.itemView.getContext())
                .load(imgUrl)
                .error(R.drawable.place_holder)
                .into(holder.mImageView);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("movies", ModelUtils.toString(movie, new TypeToken<Movie>(){}));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    class ImageViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.list_image) public ImageView mImageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
