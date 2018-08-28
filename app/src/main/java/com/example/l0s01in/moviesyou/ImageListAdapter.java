package com.example.l0s01in.moviesyou;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by l0s01in on 8/23/18.
 */

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> {

    private String[] mImages;

    public ImageListAdapter(String[] mImages) {
        this.mImages = mImages;
    }
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("recyler", "render");
        Context context = parent.getContext();
        int layoutListItem = R.layout.list_image;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutListItem, parent, shouldAttachToParentImmediately);
        ImageViewHolder viewHolder = new ImageViewHolder(view);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {
        final String imgUrl = mImages[position];
        Log.d("position", position + "");
        holder.mTextView.setText(imgUrl);
        Picasso.with(holder.itemView.getContext())
                .load(imgUrl)
                .placeholder(R.drawable.place_holder)
                .into(holder.mImageView);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("movies", imgUrl);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mImages.length;
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.list_image) public ImageView mImageView;
        @BindView(R.id.list_text) public TextView mTextView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
