package com.gck.retrofitdemo.rest;

import com.gck.retrofitdemo.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Pervacio on 06-11-2017.
 */

public interface ApiInterface {

    //http://api.themoviedb.org/3/movie/top_rated?api_key=f63880e16a5d4012106788dc7ef851e6
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRetedMovies(@Query("api_key") String apiKey);

    //http://api.themoviedb.org/3/movie/19404?api_key=f63880e16a5d4012106788dc7ef851e6
    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
