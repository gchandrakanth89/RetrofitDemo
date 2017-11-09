package com.gck.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.gck.retrofitdemo.adapters.MoviesAdapter;
import com.gck.retrofitdemo.model.Movie;
import com.gck.retrofitdemo.model.MovieResponse;
import com.gck.retrofitdemo.rest.ApiClient;
import com.gck.retrofitdemo.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String API_KEY = "f63880e16a5d4012106788dc7ef851e6";
    private static final String TAG = MainActivity.class.getSimpleName();

    private boolean isLoading = false;

    private static final int PAGE_START = 1;
    private int totalPages = 3;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private MoviesAdapter moviesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnScrollListener(onScrollListener);


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getTopRetedMovies(API_KEY, 1);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movieList = response.body().getMovieList();
                moviesAdapter = new MoviesAdapter(movieList, R.layout.list_item_movie, getApplicationContext());
                recyclerView.setAdapter(moviesAdapter);
                int totalResults = response.body().getTotalResults();
                totalPages = response.body().getPagesCount();
                Log.d(TAG, "onResponse: movieList.size() = " + movieList.size() + ", totalResults = " + totalResults);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString(), t);

            }
        });
    }

    PaginationScrollListener onScrollListener = new PaginationScrollListener() {
        @Override
        protected void loadMoreItems() {
            isLoading = true;
            currentPage += 1; //Increment page index to load the next one
            loadNextPage();
        }

        @Override
        public int getTotalPageCount() {
            return totalPages;
        }

        @Override
        public boolean isLastPage() {
            return isLastPage;
        }

        @Override
        public boolean isLoading() {
            return isLoading;
        }
    };

    private void loadFirstPage() {
        Log.d(TAG, "loadFirstPage: ");
    }

    private void loadNextPage() {


        Log.d(TAG, "loadNextPage: " + currentPage);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getTopRetedMovies(API_KEY, currentPage);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movieList = response.body().getMovieList();
                moviesAdapter.removeLoadingFooter();
                isLoading = false;
                moviesAdapter.addAll(movieList);

                if (currentPage != totalPages) {
                    moviesAdapter.addLoadingFooter();
                } else {
                    //isLastPage = true;
                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString(), t);
                isLoading = false;

            }
        });



        /*List<Movie> movies = Movie.createMovies(adapter.getItemCount());

        adapter.removeLoadingFooter();
        isLoading = false;

        adapter.addAll(movies);

        if (currentPage != totalPages) adapter.addLoadingFooter();
        else isLastPage = true;*/
    }
}
