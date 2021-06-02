package com.example.android.moviewappsingelton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.movieviewholder> {

    private Context context;
    private List<Movie> movieList;
    public MovieAdapter(Context context, List<Movie> movies){
        movieList=movies;
        this.context=context;
    }

    @NonNull
    @Override
    public movieviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item,parent,false);

        return new movieviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull movieviewholder holder, int position) {
        Movie movie=movieList.get(position);
        holder.overview.setText(movie.getOverview());
        holder.rating.setText(movie.getRating().toString());
        holder.title.setText(movie.getTitle());
        Glide.with(context).load(movie.getPoster()).into(holder.imageView);



    }

    @Override
    public int getItemCount() {

        return movieList.size();

    }

    public class movieviewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView rating;
        TextView title;
        TextView overview;

        public movieviewholder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            rating=itemView.findViewById(R.id.rating);
            title=itemView.findViewById(R.id.titl);
            overview=itemView.findViewById(R.id.overview);



        }
    }
}
