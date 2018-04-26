package com.qa.business.repository;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.transaction.Transactional.TxType.*;
import javax.persistence.TypedQuery;
import javax.transaction.*;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class MovieDBRepo implements MovieRepoInterface {

	private static final Logger logger = Logger.getLogger(MovieDBRepo.class);
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil jsonConverter;
	
	
	public String getAllMovies() {
		logger.info("MovieDBRepo getAllMovies");
		TypedQuery<Movie> query = manager.createQuery("SELECT m FROM Movie m", Movie.class);
		Collection<Movie> movies = query.getResultList();
		return jsonConverter.getJSONForObject(movies);
	}
	
	public String findAMovie(Long id) {
		Movie movie = findMovie(id);
		if(movie != null) {
			return jsonConverter.getJSONForObject(movie);
		} 
		return "{\"message\": \"movie was not found\"}";
		
	}
	
	@Transactional(REQUIRED)
	public String createNewMovie(String movie) {
		Movie newMovie = jsonConverter.getObjectForJSON(movie, Movie.class);
		manager.persist(newMovie);
		return "{\"message\": \"movie sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String updateMovie(String movie) {
		Movie updateMovie = jsonConverter.getObjectForJSON(movie, Movie.class);
		if (findMovie(updateMovie.getId()) != null) {
			manager.merge(updateMovie);
			return"{\"message\": \"movie sucessfully updated\"}";
		}
		return "{\"message\": \"movie doesn't exist, could not updated\"}";
	}

	@Transactional(REQUIRED)
	public String deleteMovie(Long id) {
		Movie movieToDelete = findMovie(id);
		if (movieToDelete != null) {
			manager.remove(movieToDelete);
			return "{\"message\": \"movie sucessfully deleted\"}";
		}
		return "{\"message\": \"movie was not found, could not be deleted\"}";
		
	}

	public Movie findMovie(Long id) {
		return manager.find(Movie.class, id);
	}

}
	
