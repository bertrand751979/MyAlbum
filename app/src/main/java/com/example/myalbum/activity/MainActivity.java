package com.example.myalbum.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.myalbum.R;
import com.example.myalbum.api.AlbumApi;
import com.example.myalbum.model.Album;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText photoNumber;
    private Button btnDone;
    private TextView photoTitle;
    private ImageView photoAlbum;
    private Button done;
    public static final String ID_KEY = "ID_KEY";
    public static List<Album> myAlbumsList;
    public static String ALBUM_KEY = "albumKeys";
    private Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        done=findViewById(R.id.btnIntent);
        photoNumber=findViewById(R.id.edit_photo_id);
        btnDone=findViewById(R.id.btnSearch);
        photoTitle=findViewById(R.id.photo_title);
        photoAlbum=findViewById(R.id.photo_image);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();

            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this,DisplayAlbumActivity.class);
                intent.putExtra(ALBUM_KEY,album);
                startActivity(intent);

            }
        });

        if (getIdFromSharedPreferences() != 0) {
            photoNumber.setText(String.valueOf(getIdFromSharedPreferences()));
        }
    }

    private void search(){
        if(photoNumber.equals("")){
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setMessage("Veuillez saisir un numero");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
            return;
        }
        callService();
    }

    public void callService(){
        AlbumApi.AlbumService service = AlbumApi.getInstance().getClient().create(AlbumApi.AlbumService.class);
        Call<List<Album>> call = service.getBooks(Integer.valueOf(photoNumber.getText().toString()));
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                updateView(response);
                writeIdToSharedPreferences();
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
    private void updateView(Response<List<Album>> response) {
        if (response.body().size() > 0) {
            myAlbumsList =response.body();
            photoTitle.setText(myAlbumsList.get(0).getTitle());
            GlideUrl glideUrl = new GlideUrl(response.body().get(0).getThumbnailUrl(), new LazyHeaders.Builder()
                    .addHeader("User-Agent", WebSettings.getDefaultUserAgent(getApplicationContext()))
                    .build());


            Glide.with(getApplicationContext())
                    .load(glideUrl)
                    .placeholder(R.drawable.placeholder)
                    .into(photoAlbum);
        }
    }
        private void writeIdToSharedPreferences(){
            getPreferences(Context.MODE_PRIVATE)
                    .edit()
                    .putInt(ID_KEY, Integer.valueOf(photoNumber.getText().toString()))
                    .apply();
        }

    private int getIdFromSharedPreferences() {
        return getPreferences(Context.MODE_PRIVATE).getInt(ID_KEY, 0);
    }



}