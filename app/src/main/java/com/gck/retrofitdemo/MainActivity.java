package com.gck.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        Call<MovieResponse> call = apiService.getTopRetedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movieList = response.body().getMovieList();
                int totalResults = response.body().getTotalResults();
                Log.d(TAG, "onResponse: movieList.size() = " + movieList.size() + ", totalResults = " + totalResults);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString(), t);
            }
        });
    }
}
