package com.gck.retrofitdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gck.retrofitdemo.R;
import com.gck.retrofitdemo.model.Movie;

import java.util.List;

/**
 * Created by Pervacio on 07-11-2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder> {

    private List<Movie> movies;
    private int rowLayout;
    private Context context;
    private boolean isLoadingAdded;

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MovieHolder movieHolder = null;
        switch (viewType) {
            case ITEM:
                View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
                movieHolder = new NormalVH(view);
                break;
            case LOADING:

                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
                movieHolder = new LoadingVH(view2);

                break;
        }

        return movieHolder;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        //Movie movie = movies.get(position);
        switch (getItemViewType(position)) {
            case ITEM:
                NormalVH nHolder = (NormalVH) holder;
                nHolder.movieTitle.setText(movies.get(position).getTitle());
                nHolder.data.setText(movies.get(position).getReleaseDate());
                nHolder.movieDescription.setText(movies.get(position).getOverview());
                nHolder.rating.setText(movies.get(position).getCount() + "");
                break;
            case LOADING:
                //Do nothing
                break;
        }

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position == movies.size() - 1 && isLoadingAdded ? LOADING : ITEM;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {


        public MovieHolder(View v) {
            super(v);

        }
    }

    public static class NormalVH extends MovieHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;

        public NormalVH(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);
        }
    }

    public static class LoadingVH extends MovieHolder {

        public LoadingVH(View itemView) {
            super(itemView);
        }
    }

    public void add(Movie mc) {
        movies.add(mc);
        notifyItemInserted(movies.size() - 1);
    }

    public void addAll(List<Movie> mcList) {
        for (Movie mc : mcList) {
            add(mc);
        }
    }

    public void remove(Movie city) {
        int position = movies.indexOf(city);
        if (position > -1) {
            movies.remove(position);
            notifyItemRemoved(position);
        }
    }

    public Movie getItem(int position) {
        return movies.get(position);
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        //Add dummy object
        add(new Movie(0, "", 0, "", ""));
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = movies.size() - 1;
        Movie item = getItem(position);

        if (item != null) {
            movies.remove(position);
            notifyItemRemoved(position);
        }
    }
}
