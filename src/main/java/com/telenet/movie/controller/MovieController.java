package com.telenet.movie.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telenet.movie.model.Movie;
import com.telenet.movie.model.Rating;
import com.telenet.movie.model.Viewer;
import com.telenet.movie.util.BusinessLogics;
import com.telenet.movie.util.Constants;



@RestController
public class MovieController {
	
	List <Movie> movieList=new ArrayList<>();
	List <Viewer> viewerList=new ArrayList<>();
	List<Rating> ratingList=new ArrayList<>();
	int id=0;
	int viewerId=0;

	// Method to create movie list
	@PostMapping(value="/createMovieList", consumes="application/json")
	public String movieNames(@RequestBody Movie request){
		String validate=BusinessLogics.validateMovie(request);
		if(validate.length()>0) {
			return validate;
		}
		request.setId(id++);
		movieList.add(request);
		return Constants.SUCCESS_CREATE;
	}
	
	//  Method to update movie list
	@PostMapping(value="/updateMovieList", consumes="application/json")
	public String updateMovieNames(@RequestBody Movie request){
		if(movieList.size()==0) {
			return Constants.MOVIES_NOT_FOUND;
		}
		BusinessLogics.validateMovie(request);
		movieList.set(request.getId(),request);
		return Constants.UPDATE_SUCCESS;
	}
	
	//  Method to get movie list
	@GetMapping(value="/getmovieList")
	public List<Movie> getMovieNames(){
		return movieList;
	}
	
	//  Method to get movie based on release date
	@PostMapping(value="/getMoviesByReleaseDate",consumes="application/json")
	public List<Movie> getMoviesBasedOnReleaseDate(@RequestBody Movie request){
		List<Movie> finalList=movieList.stream()
				.filter(item -> item.getReleaseDate().equals(request.getReleaseDate())
						).collect(Collectors.toList());
			
		return finalList;
	}
	
		// Method to create a viewer
		@PostMapping(value="/createViewer", consumes="application/json")
		public String createViewer(@RequestBody Viewer request){
			String validate=BusinessLogics.validateViewer(viewerList,request);
			if(validate.length()>0) {
				return validate;
			}
			request.setId(viewerId++);
			viewerList.add(request);
			return Constants.SUCCESS_VIWER_CREATE;
		}
		
		// Method to get existing viewers
		@GetMapping(value = "/getExistingViewer")
		public List<Viewer> getExistingviewersDetails(){
			return viewerList;
		}
		// Method to get oldest viewer
		@GetMapping(value = "/getOldestViewer")
		public List<Viewer> getOldestViewer(){
			List<Viewer> oldesViewers=new ArrayList<>();
			if(oldesViewers.size()>0) {
				oldesViewers.add(viewerList.get(0));
			}
			return oldesViewers;
		}
		// Method to create a rating
		@PostMapping(value = "/rateMovie", consumes="application/json")
		public String rateMovie(@RequestBody Rating request){
			ratingList.add(request);
			return Constants.RATING_ADDED;
		} 
		
		// Method to view ratings for specific movie
		@GetMapping(value="/viewRatings")
		public List<Rating> viewRating(@RequestParam String movieName){
			List<Rating> viewRatings=ratingList.stream().
					filter(item->item.getMovie().getTitle().equals(movieName))
					.collect(Collectors.toList());
			return viewRatings;
		}
		// Method to view statistics
		@GetMapping(value="/viewStatistics")
		public List<Rating> viewStatistics(){
			return ratingList;
		}
		
	
	

}
