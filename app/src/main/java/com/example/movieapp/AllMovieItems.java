package com.example.movieapp;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AllMovieItems{

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private ArrayList<ResultsItem> results;

	@SerializedName("total_results")
	private int totalResults;

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public ArrayList<ResultsItem> getResults(){
		return results;
	}

	public int getTotalResults(){
		return totalResults;
	}
}