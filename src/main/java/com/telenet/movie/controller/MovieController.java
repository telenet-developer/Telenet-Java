package com.telenet.movie.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.telenet.movie.model.Movie;



@RestController
public class MovieController {
	
	
	@PostMapping(value="/createMovieList", consumes="application/json")
	public List<Movie> movieNames(@RequestBody Movie request){
		List <Movie> movieList=new ArrayList<>();
		return movieList;
	}
	@GetMapping(value="/test")
	public String test(ModelMap map){
				
		return "test";
	}

}
