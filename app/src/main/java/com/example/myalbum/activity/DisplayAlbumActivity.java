package com.example.myalbum.activity;

import static com.example.myalbum.activity.MainActivity.ALBUM_KEY;
import static com.example.myalbum.activity.MainActivity.myAlbumsList;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myalbum.adapter.AlbumAdapter;
import com.example.myalbum.R;
import com.example.myalbum.model.Album;
import java.util.ArrayList;
import java.util.List;


public class DisplayAlbumActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AlbumAdapter adapterAlbum;
    private Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_album);
        album=(Album) getIntent().getSerializableExtra(ALBUM_KEY);
        recyclerView = findViewById(R.id.recyclerViewlistAlbum);
        setViewItem();
    }

    private void setViewItem(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterAlbum= new AlbumAdapter((ArrayList<Album>) myAlbumsList);
        recyclerView.setAdapter(adapterAlbum);
    }


}//fin
