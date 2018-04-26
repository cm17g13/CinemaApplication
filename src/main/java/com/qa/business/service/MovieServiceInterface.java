package com.qa.business.service;

import com.qa.persistence.domain.Movie;

public interface MovieServiceInterface {

	public String getAllMovies();

	public String findAMovie(Long id);
	
	public String createNewMovie(String movie);

	public String updateMovie(String movie);

	public String deleteMovie(Long id);
	
	public Movie findMovie(Long id);
	
}
