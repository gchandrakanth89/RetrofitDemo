package com.gck.retrofitdemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pervacio on 24-10-2017.
 */
//http://api.themoviedb.org/3/movie/top_rated?api_key=f63880e16a5d4012106788dc7ef851e6
public class Movie {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("vote_count")
    private int count;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("overview")
    private String overview;


    public Movie(int id, String title, int count, String releaseDate, String overview) {
        this.id = id;
        this.title = title;
        this.count = count;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCount() {
        return count;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }
}
