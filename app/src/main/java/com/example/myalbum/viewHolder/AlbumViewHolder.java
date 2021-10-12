package com.example.myalbum.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myalbum.R;
import com.example.myalbum.model.Album;


public class AlbumViewHolder extends RecyclerView.ViewHolder {
    private TextView vhTitle;
    private ImageView vhImage;

    public AlbumViewHolder(@NonNull View view) {
        super(view);
        vhTitle=view.findViewById(R.id.rawTitle);
        vhImage=view.findViewById(R.id.rawImage);
    }

    public TextView getVhTitle() {
        return vhTitle;
    }

    public void setVhTitle(TextView vhTitle) {
        this.vhTitle = vhTitle;
    }

    public ImageView getVhImage() {
        return vhImage;
    }

    public void setVhImage(ImageView vhImage) {
        this.vhImage = vhImage;
    }

    public void bind(Album album){
    vhTitle.setText(album.getTitle());

    }
}
