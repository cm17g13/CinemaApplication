package com.qa.business.repository;

import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;

public interface MovieRepoInterface  {
	
	public String getAllMovies();

	public String findAMovie(Long id);
	
	public String createNewMovie(String movie);

	public String updateMovie(Movie movie);

	public String deleteMovie(Long id);
	
	public Movie findMovie(Long id);
	
	public JSONUtil getConverter();

}
