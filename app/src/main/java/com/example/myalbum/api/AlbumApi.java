package com.example.myalbum.api;

import com.example.myalbum.model.Album;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class AlbumApi {
    public interface AlbumService{
      @GET("photos")
        Call<List<Album>>getBooks(@Query("albumId") Integer id);
    }

    private final static String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static AlbumApi INSTANCE = null;
    private AlbumApi(){}
    public static AlbumApi getInstance(){
        if (INSTANCE == null){
            INSTANCE = new AlbumApi();
        }
        return INSTANCE;
    }
//configuration
    public Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }





}
