package com.qa.business.repository;


public interface MovieRepoInterface {

	
	public String getAllMovies();

	public String createNewMovie(String movie);

	public String updateMovie(Long id, String movie);

	public String deleteMovie(Long id);
	

}
