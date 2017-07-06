 
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    // write an additional secondratings constructor that has two string parameters named moviefile and ratingsfile
    public SecondRatings(String moviefile, String ratingsfile) {
    	// the constructor should create a firstratings object
    	FirstRatings fr = new FirstRatings();
    	// and then call the loadMovies and loadRaters methods
    	myMovies = fr.loadMovies(moviefile);
    	myRaters = fr.loadRaters(ratingsfile);
    	//  in FirstRatings to read in all the move and ratings data 
    	// and store them in the two private ArrayList variables
    	// of the SecondRatings class, myMovies and myRaters
    }
   
    // write a public method named getMovieSize, which 
    public int getMovieSize() {
    	// returns the number of movies that
    	// were read in and stored in the ArrayList of type Movie
    	return myMovies.size();
    }
    
    // write a public method named getRaterSize, which returns the number of raters that were read in and stored in the ArrayList of type Rater
    public int getRaterSize() {
    	return myRaters.size();
    }
    
    // write a helper method named getAverageById that has two parameters
    // a String named id representing a movie ID and an integer named minimalRaters
    private double getAverageByID(String id, int minimalRaters) {
    	// this method returns a double representing the average movie rating for this ID if there are at least minimalRaters ratings
    	int ratingCount = 0;
    	for (Rater rater: myRaters) {
    		if (rater.hasRating(id)) {
    			ratingCount ++;
    		}
    	}
    	
    	int numRaters = 0;
    	double ratingTotal = 0;
    	if (ratingCount >= minimalRaters) {
    		for (Rater rater: myRaters) {
    			if (rater.hasRating(id)) {
    				ratingTotal += rater.getRating(id);
        			numRaters++;
    			}
    		}
    	}
    
    	double averageRating = ratingTotal/ numRaters;
    	// if there are not minimalRaters ratings, then it returns 0.0
    	if (ratingCount < minimalRaters) {
    		averageRating = 0.0;
    	}
        return averageRating;
    }
    	
    // write a public method named getAverageRatings, which has one int parameter named minimalRaters
    // for example, if minimalRaters has the value 10, then this method returns rating information (the movie ID and its average rating) for each movie that has at least 10 ratings
    // consider calling the getAverageByID method
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
    	// this method should find the average rating for every movie that has been rated by at least minimalRaters raters
    	ArrayList<Rating> retList = new ArrayList<Rating>();
    	for (Movie movie: myMovies) {
    		// store each such rating in a rating object in which the movie ID and the average rating are used in creating the Rating object
    		Rating avgRating = new Rating(movie.getTitle(),getAverageByID(movie.getID(), minimalRaters)) ;
    		if (avgRating.getValue() > 0.0) {
    			retList.add(avgRating);
    		}
    	}
        
    	return retList;
        // the method getAverageRatings should return an ArrayList of all the Rating objects for movies that have at least the minimal number of raters supplying a rating
    }
    
    // write a method named getTitle that has oen String parameter named id, representing the ID of a movie
    public String getTitle(String id) {
    	// this method returns the title of the movie with that ID
    	// if the movie does not exist, then this method should return a String indicating the ID was not found
    	String ret = "ID NOT FOUND";
    	for (Movie movie: myMovies) {
    		if (movie.getID().equals(id)) {
    			ret = movie.getTitle();
    		}
    	}
    	return ret;
    }
    
    // write a method getID that has one String parameter named title representing the title of aa movie
    // this method returns the movie ID of this movie
    // if the title is not found, return an appropriate message such as "NO SUCH TITLE"
    // note that the movie title must be spelled exactly as it apperas in the movie data files
    public String getID(String title) {
    	for (Movie movie: myMovies) {
    		if (movie.getTitle().equals(title)) {
    			return movie.getID();
    		}
    	}
    	return "NO SUCH TITLE";
    }
}
