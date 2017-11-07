package com.gck.retrofitdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Pervacio on 06-11-2017.
 */

public class MovieResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int pagesCount;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("results")
    private List<Movie> movieList;

    public MovieResponse(int page, int pagesCount, int totalResults, List<Movie> movieList) {
        this.page = page;
        this.pagesCount = pagesCount;
        this.totalResults = totalResults;
        this.movieList = movieList;
    }

    public int getPage() {
        return page;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }
}
