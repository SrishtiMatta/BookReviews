package com.example.hp.bookreviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.bookreviews.Adapters.GenreListAdapter;
import com.example.hp.bookreviews.Models.GenreListItemClass;
import com.example.hp.bookreviews.Network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenreActivity extends AppCompatActivity {

    public static final String access_token = "c89eb2059db08d579dd7dd523cff918c503e82d3";
    ListView genreLV;
    GenreListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        setTitle("Books by genre");
        genreLV = (ListView) findViewById(R.id.genreListView);
        final String genreName = getIntent().getStringExtra("genreName");
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://idreambooks.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service2 = retrofit2.create(ApiInterface.class);
      //  Log.i("Genre", genreName);

        Call<ArrayList<GenreListItemClass>> genreListClassCall =  service2.getGenreList(access_token, genreName);

        genreListClassCall.enqueue(new Callback<ArrayList<GenreListItemClass>>() {
            @Override
            public void onResponse(Call<ArrayList<GenreListItemClass>> call, Response<ArrayList<GenreListItemClass>> response) {
                if(response.isSuccessful())
                {
                    ArrayList<GenreListItemClass> Object = response.body();

                 //   Toast.makeText(GenreActivity.this, Object.get(1).bName, Toast.LENGTH_SHORT).show();

                    adapter = new GenreListAdapter(GenreActivity.this, Object);
                    genreLV.setAdapter(adapter);
                }
                else{
                    Log.i("Errr",response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GenreListItemClass>>call, Throwable t) {

                Log.i("Errr", t.getMessage());

            }
        });

        genreLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bookName = adapter.getItem(position).bName;
                Intent i = new Intent();
                i.setClass(GenreActivity.this, BookDetails.class);
                i.putExtra("bookName", bookName);
                startActivity(i);
            }
        });

    }
}
