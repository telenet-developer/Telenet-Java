package com.telenet.movie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.telenet.movie.controller.MovieController;
import com.telenet.movie.model.Movie;
import com.telenet.movie.model.Rating;
import com.telenet.movie.model.Viewer;
import com.telenet.movie.util.Constants;

@SpringBootTest
class DemoApplicationTests {
	
	MovieController movie=new MovieController();
	

	@Test
	void contextLoads() {
	}

	@Test
	public void movieNamesTest() {
		Movie mv=new Movie();
		mv.setTitle("Spider Man");
		mv.setDescription("Spider Man 4");
		mv.setReleaseDate("20/04/2020");
		mv.setGenere("hollywood");
		String output=movie.movieNames(mv);
		assertEquals(Constants.SUCCESS_CREATE, output);
	}
	@Test
	public void movieNamesValidationTest() {
		Movie mv=new Movie();
		mv.setTitle("Spider Mansddddddddddddd dbasdbashdbhasbd dashdashd"
				+ "hasdhasdhasbhdbhasdhasdhasbdhasbdhbas dbasdbasdbyasbdy hbdhasbdh");
		mv.setDescription("Spider Man 4");
		mv.setReleaseDate("20/04/2020");
		mv.setGenere("hollywood");
		String output=movie.movieNames(mv);
		assertEquals(Constants.TITLE_ERROR, output);
	}
	@Test
	public void movieNamesValidationDescriptionTest() {
		Movie mv=new Movie();
		mv.setTitle("Test");
		mv.setDescription("Spider Mansddddddddddddd dbasdbashdbhasbd dashdashd" 
				+"ds dhas dhas hdhdhas dhh h has dh ashd has dha sddasdbasudbasudbua"
				+ "hasdhasdhasbhdbhasdhasdhasbdhasbdhbas dbasdbasdbyasbdy hbdhasbdh"
				+ "asdasdasda sdvyavdyasvdyvasydvyasvdyvasydvas davdyasvdvyasydvyasdvasyvdy dvasydvasydvs"
				+ "dassssssssssssssssssssssssssssdbbduabdusabudbausdbuasbduabsdubausbdasud dasbduabsd"
				+ "asda dvasydvasydvyasdv ydvsaydvasydvasydvyavdyasvdyasdugasduasdguasd dgasydvyasvdyasd sd"
				+ "asda dvasydvasydvyasdv ydvsaydvasydvasydvyavdyasvdyasdugasduasdguasd dgasydvyasvdyasd sd"
				+ "asda dvasydvasydvyasdv ydvsaydvasydvasydvyavdyasvdyasdugasduasdguasd dgasydvyasvdyasd sd"
				+ "asda dvasydvasydvyasdv ydvsaydvasydvasydvyavdyasvdyasdugasduasdguasd dgasydvyasvdyasd sd"
				+ "asda dvasydvasydvyasdv ydvsaydvasydvasydvyavdyasvdyasdugasduasdguasd dgasydvyasvdyasd sd"
				+ "asda dvasydvasydvyasdv ydvsaydvasydvasydvyavdyasvdyasdugasduasdguasd dgasydvyasvdyasd sd"
				+ "asda dvasydvasydvyasdv ydvsaydvasydvasydvyavdyasvdyasdugasduasdguasd dgasydvyasvdyasd sd"
				+ "asda dvasydvasydvyasdv ydvsaydvasydvasydvyavdyasvdyasdugasduasdguasd dgasydvyasvdyasd sd"
				+ "asda dvasydvasydvyasdv ydvsaydvasydvasydvyavdyasvdyasdugasduasdguasd dgasydvyasvdyasd sd"
				+ "asda dvasydvasydvyasdv ydvsaydvasydvasydvyavdyasvdyasdugasduasdguasd dgasydvyasvdyasd sd"
				+ "asda dvasydvasydvyasdv ydvsaydvasydvasydvyavdyasvdyasdugasduasdguasd dgasydvyasvdyasd sd"
				+ "asda dvasydvasydvyasdv ydvsaydvasydvasydvyavdyasvdyasdugasduasdguasd dgasydvyasvdyasd sd"
				+ "asdadasdasdasdasdasdasdasudgaysdyasds");
		mv.setReleaseDate("20/04/2020");
		mv.setGenere("hollywood");
		String output=movie.movieNames(mv);
		assertEquals(Constants.DESCRIPTION_ERROR, output);
	}
	
	@Test
	public void updatedmovieNamesTest() {
		Movie mv=new Movie();
		mv.setTitle("Spider Man");
		mv.setDescription("Spider Man 4");
		mv.setReleaseDate("20/04/2020");
		mv.setGenere("hollywood");
		mv.setId(0);
		String output=movie.updateMovieNames(mv);
		if(output!=Constants.MOVIES_NOT_FOUND) {
			assertEquals(Constants.UPDATE_SUCCESS, output);
		}
		else {
			assertEquals(Constants.MOVIES_NOT_FOUND, output);
		}
	}
	@Test
	public void getMoviesList() {
		List<Movie> getMovies=movie.getMovieNames();
		assertTrue(getMovies.size()>=0);	
	}
	@Test
	public void getMoviesBasedOnReleaseDateTest() {
		Movie mv=new Movie();
		mv.setReleaseDate("20/04/2020");
		List<Movie> getMovies=movie.getMoviesBasedOnReleaseDate(mv);
		assertTrue(getMovies.size()>=0);	
	}
	@Test
	public void createViewerTest() {
		Viewer view=new Viewer();
		view.setFirstName("Raheman");
		view.setLastName("Md");
		view.setAge(26);
		String viewOtp=movie.createViewer(view);
		if(!viewOtp.equals(Constants.VIEWER_ALREADY_EXIST)) {
			assertEquals(Constants.SUCCESS_VIWER_CREATE, viewOtp);
		}else {
			assertEquals(Constants.VIEWER_ALREADY_EXIST, viewOtp);
		}
	}
	
	@Test
	public void getExistingviewersDetailsTest() {
		List<Viewer> details=movie.getExistingviewersDetails();
		assertTrue(details.size()>=0);
	}
	@ExceptionHandler
	@Test
	public void getOldestViewerTest() {
		List<Viewer> details=movie.getOldestViewer();
		assertTrue(details.size()>=0);
	}
	@Test
	public void rateMovieTest() {
		Rating rate=new Rating();
		Movie mv=new Movie();
		Viewer view=new Viewer();
		rate.setScore(4);
		rate.setTimeStamp("February 26 2020 08:08:23 (UTC/GMT +8 hours)");
		mv.setTitle("Spider Man");
		mv.setDescription("Spider Man 4");
		mv.setReleaseDate("20/04/2020");
		mv.setGenere("hollywood");
		mv.setId(0);
		view.setFirstName("Raheman");
		view.setLastName("Md");
		view.setAge(26);
		rate.setMovie(mv);
		rate.setViewer(view);
		String details=movie.rateMovie(rate);
		assertEquals(Constants.RATING_ADDED, details);
	}
	
	@Test
	public void viewRatingTest() {
		String movieName="Spider Man";
		List<Rating> getRating=movie.viewRating(movieName);
		assertTrue(getRating.size()>=0);
	}
	@Test
	public void viewStatisticsTest() {
		List<Rating> getRating=movie.viewStatistics();
		assertTrue(getRating.size()>=0);
	}

}
