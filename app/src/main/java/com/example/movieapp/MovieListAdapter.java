package com.example.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    Context context;
    ArrayList<ResultsItem> allMovieItems;
    OnMovieCallBack callBack;


    public MovieListAdapter(Context context, ArrayList<ResultsItem> allMovieItems,OnMovieCallBack callBack){
        this.context = context;
        this.allMovieItems = allMovieItems;
        this.callBack = callBack;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.movieTitle.setText(allMovieItems.get(position).getTitle());
        holder.movieLang.setText(allMovieItems.get(position).getOriginalLanguage());
        holder.date.setText(allMovieItems.get(position).getReleaseDate());
        holder.rating.setText(String.valueOf(allMovieItems.get(position).getVoteAverage()));
        holder.votes.setText(String.valueOf(allMovieItems.get(position).getVoteCount()));

        Glide
                .with(context)
                .load("https://image.tmdb.org/t/p/original/" + allMovieItems.get(position).getPosterPath())
                .placeholder(R.drawable.sr)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onItemClickListener(allMovieItems.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return allMovieItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle,movieLang,date,rating,votes;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieLang = itemView.findViewById(R.id.language);
            date = itemView.findViewById(R.id.date);
            rating = itemView.findViewById(R.id.rating);
            votes = itemView.findViewById(R.id.no_of_votes);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
