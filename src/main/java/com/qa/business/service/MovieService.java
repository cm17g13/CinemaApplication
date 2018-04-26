package com.qa.business.service;

import javax.inject.Inject;
import com.qa.business.repository.MovieRepoInterface;
import com.qa.persistence.domain.Movie;

public class MovieService implements MovieServiceInterface {

	@Inject
	private MovieRepoInterface repo;
	
	
	public String getAllMovies() {
		return repo.getAllMovies();
	}
	
	public String findAMovie(Long id) {
		return repo.findAMovie(id);
	}
	
	public String createNewMovie(String movie) {
		return repo.createNewMovie(movie);
	}
	
	public String updateMovie(String movie) {
		return repo.updateMovie(movie);
	}
	
	public String deleteMovie(Long id) {
		return repo.deleteMovie(id);
	}

	@Override
	public Movie findMovie(Long id) {
		return repo.findMovie(id);
	}
	

}
