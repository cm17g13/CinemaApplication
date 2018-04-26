package com.qa.business.service;

import javax.inject.Inject;
import com.qa.business.repository.MovieRepoInterface;
import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;

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
		Movie updateMovie = repo.getConverter().getObjectForJSON(movie, Movie.class);
		Movie newMovie = updateFields(updateMovie);
		return repo.updateMovie(newMovie);
	}
	
	public String deleteMovie(Long id) {
		return repo.deleteMovie(id);
	}

	@Override
	public Movie findMovie(Long id) {
		return repo.findMovie(id);
	}
	
	public Movie updateFields(Movie updateMovie) {
		Movie existingMovie =  repo.findMovie(updateMovie.getId());
		if(existingMovie != null) {
			if(!updateMovie.getTitle().trim().equals("")) {
				existingMovie.setTitle(updateMovie.getTitle());
			}
			if(!updateMovie.getRating().trim().equals("")) {
				existingMovie.setRating(updateMovie.getRating());
			}
			if(!updateMovie.getGenre().trim().equals("")) {
				existingMovie.setGenre(updateMovie.getGenre());
			}
		}
		return existingMovie;
		
	}
	

}
