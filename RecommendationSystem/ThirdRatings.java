 

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
    	FirstRatings fr = new FirstRatings();
    	myRaters = fr.loadRaters(ratingsfile);
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
    	ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
    	for (String movie: myMovies) {
    		// store each such rating in a rating object in which the movie ID and the average rating are used in creating the Rating object
    		Rating avgRating = new Rating(movie,getAverageByID(movie, minimalRaters)) ;
    		if (avgRating.getValue() > 0.0) {
    			retList.add(avgRating);
    		}
    	}
        
    	return retList;
        // the method getAverageRatings should return an ArrayList of all the Rating objects for movies that have at least the minimal number of raters supplying a rating
    }
    
    // write a public helper method named getAverageRatingsByFilter that has two parameters
 // an int named minimalRaters for the minimum number of ratings a movie must have and a Filter named filterCriteria
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
    	ArrayList<Rating> retList = new ArrayList<Rating>();
    	
    	// this method shuold create and return an ArrayList of type Rating of all the movies that have at least minimalRaters ratings and satisfies the filer criteria
    	MovieDatabase md = new MovieDatabase();
    	ArrayList<String> movies = md.filterBy(filterCriteria);
        // this method will need to create the ArrayList of type STring of movie IDs from the MovieDatabase using the filterBy method before calculating those averages
    	for (String movie: movies) {
    		// store each such rating in a rating object in which the movie ID and the average rating are used in creating the Rating object
    		Rating avgRating = new Rating(movie,getAverageByID(movie, minimalRaters)) ;
    		if (avgRating.getValue() > 0.0) {
    			retList.add(avgRating);
    		}
    	}
    	return retList;
    }
    	
    
}
