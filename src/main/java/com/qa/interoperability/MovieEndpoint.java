package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.*;
import com.qa.business.service.MovieServiceInterface;

@Path("/movie")
public class MovieEndpoint {

	@Inject
	private MovieServiceInterface service;
	
	@GET
	@Path("/json")
	@Produces({"application/json"})
	public String getAllMovies() {
		return service.getAllMovies();
	}
}
