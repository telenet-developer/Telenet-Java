package com.telenet.movie.util;

import java.util.List;
import java.util.stream.Collectors;

import com.telenet.movie.model.Movie;
import com.telenet.movie.model.Viewer;

public class BusinessLogics {
	
	public static String validateMovie(Movie request) {
		if(request.getTitle().length()>100) {
			return Constants.TITLE_ERROR;
		}
		if(request.getDescription().length()>500) {
			return Constants.DESCRIPTION_ERROR;
		}
		return "";
	}
	
	public static String validateViewer(List<Viewer> viewerList,Viewer view) {
		List<Viewer> validateViewer= viewerList.stream()
				.filter(item -> item.getFirstName().equals(view.getFirstName())
						&& item.getLastName().equals(view.getLastName())
						).collect(Collectors.toList());
		
		if(validateViewer.size()>0) {
			return Constants.VIEWER_ALREADY_EXIST;
		}
		return "";
		
	}
	
	

}
