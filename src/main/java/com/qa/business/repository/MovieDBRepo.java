package com.qa.business.repository;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.transaction.Transactional.TxType.*;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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
	
	@Transactional(REQUIRED)
	public String createNewMovie(String movie) {
		Movie newMovie = jsonConverter.getObjectForJSON(movie, Movie.class);
		manager.persist(newMovie);
		return "{\"message\": \"movie sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String updateMovie(Long movieId, String movie) {
		Movie updateMovie = jsonConverter.getObjectForJSON(movie, Movie.class);
		Movie movieInDB = findMovie(new Long(movieId));
		if (movieInDB != null) {
			movieInDB = updateMovie;
			manager.merge(movie);
		}
		return "{\"message\": \"movie sucessfully updated\"}";
	}

	@Transactional(REQUIRED)
	public String deleteMovie(Long movieId) {
		Movie movieInDB = findMovie(new Long(movieId));
		if (movieInDB != null) {
			manager.remove(movieInDB);
		}
		return "{\"message\": \"movie sucessfully deleted\"}";
	}

	private Movie findMovie(Long id) {
		return manager.find(Movie.class, id);
	}

}
	
