package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnMovieCallBack{
    RecyclerView recyclerView;
    MovieListAdapter adapter;
    ArrayList<ResultsItem> allMovieItems;
    ApiCall apiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allMovieItems = new ArrayList<>();
        apiCall = ApiClient.getRetrofit().create(ApiCall.class);

        recyclerView = findViewById(R.id.recycler);

        getMovieList();
    }


    public void getMovieList() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("api_key", "7fe9259e35319e8fa792151eb5451b73");
        params.put("language", "en-US");
        params.put("page", 1);


        Call<AllMovieItems> movieCall = apiCall.getMovieList(params);
        movieCall.enqueue(new Callback<AllMovieItems>() {
            @Override
            public void onResponse(Call<AllMovieItems> call, Response<AllMovieItems> response) {

                allMovieItems = response.body().getResults();
                setupAdapter();


            }

            @Override
            public void onFailure(Call<AllMovieItems> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void setupAdapter(){
        adapter = new MovieListAdapter(getApplicationContext(), allMovieItems,this::onItemClickListener);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClickListener(ResultsItem items) {
        Intent intent=new Intent(getApplicationContext(), MovieDetails.class);
        intent.putExtra("data", (Serializable) items);
        startActivity(intent);
    }
}