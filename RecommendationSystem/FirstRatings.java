import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
	// write a method named loadMovies that has one parameter, a String named filename
	// this method should 
	public ArrayList<Movie> loadMovies(String filename) {
		ArrayList<Movie> retList = new ArrayList<Movie>();
		// process every record from the CSV file whose name is filename, and
		FileResource fr = new FileResource(filename);
		CSVParser parser = fr.getCSVParser();
		for (CSVRecord rec: parser) {
			String anID = rec.get("id");
			String aTitle = rec.get("title"); 
			String aYear = rec.get("year"); 
			String theGenres = rec.get("genre"); 
			String aDirector = rec.get("director");
		    String aCountry = rec.get("country"); 
		    String aPoster = rec.get("poster"); 
		    int theMinutes = Integer.parseInt(rec.get("minutes"));
			Movie curr = new Movie(anID, aTitle, aYear, theGenres, aDirector, aCountry, aPoster, theMinutes);
			retList.add(curr);
		}
				// return an ArrayList of type Movie will all of the movie data from the file
		return retList;
	}
	
	// write a method named testLoadMovies that should do several things
	public void testLoadMovies() {
		// call the method loadMovies on the file ratedmovies_short.csv and store the result in an ArrayList local variable
		String shortFile = "/Users/e144574/Desktop/Recommender/data/ratedmovies_short.csv";
		String longFile = "/Users/e144574/Desktop/StepOneStarterProgram/data/ratedmoviesfull.csv";
		
		ArrayList<Movie> movieList = loadMovies(shortFile);
		// print the number of movies, and print each movie
		int listSize = movieList.size();
		System.out.println(listSize);
		//for (Movie movie:movieList) System.out.println(movie.toString());
		
		// you should see there are five movies in this file
		// after this works you should comment out the printing of the movies
		// if you run your program on the file ratedmoviesfull.csv, you should see there are 3143 movies in the file
		movieList = loadMovies(longFile);
		listSize = movieList.size();
		System.out.println(listSize);
		
		// add code to determine how many movies include the Comedy genre
		// in the file ratedmovies_short.csv, there is only one
		movieList = loadMovies(shortFile);
		int comedyCount = 0;
		for (Movie movie: movieList) {
			if (movie.getGenres().contains("Comedy")) {
				comedyCount++;
			}
		}
		System.out.println("there are "+comedyCount+" comedies");		
		
		// add code to determine how many movies are greater than 150 minutes in length
		// in the file ratedmovies_short.csv, there are two
		int longMovies = 0;
		for (Movie movie: movieList) {
			if (movie.getMinutes() > 150) {
				longMovies++;
			}
		}
		System.out.println("there are "+longMovies+" movies longer than 150 minutes");
		
		// add code to determine the maximum number of movies by any director, and who the directors are that directed that many movies
		// remember that some movies may have more than one director
		// the the file ratedmovies_short.csv the maximum number of movies by any director is one, and there are five directors that directed one such movie
		ArrayList<String> directorList = new ArrayList<String>();
		for (Movie movie: movieList) {
			String directors = movie.getDirector();
			List<String> dirs = Arrays.asList(directors.split(","));
			for (String dir: dirs) {
				if (!directorList.contains(dir)) {
					directorList.add(dir);
				}
			}
		}
		
		HashMap<String, Integer> directorMap = new HashMap<String, Integer>();
		int directorCount = 0;
		for (String director: directorList) {
		    //int directorCount = 0;
			for (Movie movie: movieList) {
				if (movie.getDirector().contains(director)) {
					directorCount++;
				}
			}
			directorMap.put(director, directorCount);
			directorCount = 0;
		}
		
		int max = 0;
		for (int val: directorMap.values()) {
			if (val > max) {
				max = val;
			}
		}
		
		System.out.println("max # of movies by any director "+max);
		ArrayList<String> directors = new ArrayList<String>();
		for (String dir: directorMap.keySet()) {
			if (directorMap.get(dir) == max) {
				System.out.println(dir);
			}
		}
	}
		
	// write a method named loadRaters that has one parameter named filename
	public ArrayList<Rater> loadRaters(String filename) {
		ArrayList<Rater> retList = new ArrayList<Rater>();
		ArrayList<String> raterIDs = new ArrayList<String>();
		
		// this method should process every record fromt he CSV file whose name is file
		FileResource fr = new FileResource(filename);
		CSVParser parser = fr.getCSVParser();
		Rater rater = new EfficientRater("");
		String raterID = "";
		for (CSVRecord rec: parser) {
		    if (!raterID.equals(rec.get("rater_id"))) {
		      rater = new EfficientRater(rec.get("rater_id"));
		      }
			
			rater.addRating(rec.get("movie_id"), Double.parseDouble(rec.get("rating")));
			if (!raterIDs.contains(rater.getID())) {
			    retList.add(rater);
			 }
			raterIDs.add(rater.getID());
			raterID = rater.getID();
		}
		
		for (CSVRecord rec: parser) {
			String id = rec.get("rater_id");
			for (Rater rat: retList) {
				if (id.contains(rat.getID())) {
					rat.addRating(rec.get("movie_id"), Double.parseDouble(rec.get("rating")));
				}
			}
		}
		// return an arraylist of type rater with all the rater data from that file
		return retList;
	}
	
	public void testLoadRaters() {
	    // call the method loadRaters on the file ratings_short.csv and store the result in a local ArrayList variable
	    String shortFile = "/Users/e144574/Desktop/StepOneStarterProgram (1)/data/ratings_short.csv";
	    ArrayList<Rater> raterList = loadRaters(shortFile);
	    // print the total number of raters
	    System.out.println(raterList.size());
	    // then, for each rater, print the rater's ID and the number of ratings they did on one line, followed by each rating (both the movie ID and the rating given) on a seprate line
	    /* for (Rater rater: raterList) {
	        System.out.println("rater ID: "+rater.getID()+" | # of ratings: "+rater.numRatings());
	    } */
	    // if you run your program on the file ratings_short.csv you will see there are five raters
	    // after it looks like it works, you might want to comment out the printing of each rater and their ratings
	    // if you run your program on the file ratings.csv, you shoulf get 1048 raters
	    String longFile = "/Users/e144574/Desktop/StepOneStarterProgram (1)/data/ratings.csv";
	    raterList = loadRaters(longFile);
	    System.out.println(raterList.size());
	
	    // add code to find the number of ratings for a particular rater you specify in your code
	    raterList = loadRaters(shortFile);
	    String raterID = "2";
	    // for example, if you run this code on the rater whose rater_id is 2 for the file ratings_short.csv, you will see they have three ratings
	    for (Rater rater: raterList) {
	        if (rater.getID().equals(raterID)) {
	            System.out.println("rater "+raterID+" has "+rater.numRatings()+" ratings");
	        }
	    }
	    
	
	    // add code to find the maximum number of ratings by any rater
	    int max = 0;
	    for (Rater rater: raterList) {
	        if (rater.numRatings() > max) {
	            max = rater.numRatings();
	           }
	    }
	    // determine how many raters have this maximum number of ratings and who those raters are
	    // if you run this code on the file ratings_short.csv, you will see rater 2 has three ratings, the maximum number of ratings of all the raters, and that there is only one rater with three ratings
	    int count = 0;
	    for (Rater rater: raterList) {
	       if (rater.numRatings() == max) {
	           //System.out.println(rater.getID());
	           count++;
	       }
	       }
	    System.out.println(count); 
	
	    // add code to find the number of ratings a particular movie has
	    String movieID = "1798709";
	    int ratingCount = 0;
	    for (Rater rater: raterList) {
	       if (rater.hasRating(movieID)) {
	           ratingCount++;
	       }
	    }
	    // if you run this code on the file ratings_short.csv for the movie "1798709" you will see it was rated by four raters
	    System.out.println(ratingCount);
	    
	    // add code to determine how many different movies have been rated by all these raters
	    // if you run this code on the file ratings_short.csv, you will see there were four movies rated
	    ArrayList<String> movieList = new ArrayList<String>();
	    for (Rater rater: raterList) {
	       ArrayList<String> movies = rater.getItemsRated();
	       for (String movie : movies) {
	           if (!movieList.contains(movie)) {
	               movieList.add(movie);
	           }
	       }
	    }
	    System.out.println(movieList.size());
	  }
	
}

