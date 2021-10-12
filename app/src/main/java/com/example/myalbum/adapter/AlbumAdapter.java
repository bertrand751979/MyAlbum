package com.example.myalbum.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myalbum.viewHolder.AlbumViewHolder;
import com.example.myalbum.R;
import com.example.myalbum.model.Album;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
    private ArrayList<Album>listAlbumAdapter;

    public AlbumAdapter(ArrayList<Album> listAlbumAdapter) {
        this.listAlbumAdapter = listAlbumAdapter;
    }

    public void setListAlbumAdapter(ArrayList<Album> listAlbumAdapter) {
        this.listAlbumAdapter = listAlbumAdapter;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view= inflater.inflate(R.layout.raw_album,parent,false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        holder.bind(listAlbumAdapter.get(position));
    }

    @Override
    public int getItemCount() {
        return listAlbumAdapter.size();
    }
}
