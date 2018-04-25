package com.qa.business.service;

import javax.inject.Inject;

import com.qa.business.repository.MovieRepoInterface;

public class MovieService implements MovieServiceInterface {

	@Inject
	private MovieRepoInterface repo;
	
	
	public String getAllMovies() {
		return repo.getAllMovies();
	}
	
	public String create(String movie) {
		return repo.createNewMovie(movie);
	}
	
	public String update(Long id,String movie) {
		return repo.updateMovie(id, movie);
	}
	
	public String delete(Long id) {
		return repo.deleteMovie(id);
	}
	

}
