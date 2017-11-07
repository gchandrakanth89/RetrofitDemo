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


    public Movie(int id, String title, int count) {
        this.id = id;
        this.title = title;
        this.count = count;
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
}
