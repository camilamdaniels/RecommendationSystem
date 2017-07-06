 

import java.util.ArrayList;

public class MovieRunnerAverage {
    // create a void method named printAverageRatings that has no parameters
    // test your program to make sure it is reading in all the data from the two files
            // eg if you run your program on the files ratedmovies_short.csv and ratings_short.csv, you should see 5 raters and 5 movies
    private int findMindex(ArrayList<Rating> ratings) {
        double min = Double.MAX_VALUE;
        int mindex = 0;
        for (int i = 0; i<ratings.size(); i++) {
            Rating rating = ratings.get(i);
            double num = rating.getValue();
            if (num < min) {
                min = num;
                mindex = i;
            }
        }
        return mindex;
    }
    
    private void sortRatings(ArrayList<Rating> ratings) {
        ArrayList<Rating> retList = new ArrayList<Rating>();
        int mindex = findMindex(ratings);
        
        while (!ratings.isEmpty()) {
            retList.add(ratings.get(mindex));
            ratings.remove(ratings.get(mindex));
            mindex = findMindex(ratings);
        }
        
        for (Rating rating: retList) {
          ratings.add(rating);
          }
    }
    
    // for example, if printAverageRatins is called on the files ratings_short.csv and ratedmovies_short.csv with the argument 3, then the output will display two movies
    public void printAverageRatings() {
        // create a SecondRatings object and use the CSV filenames of movie information and ratings information from the first assignment when calling the constructor
        String moviefile = "/Users/e144574/Desktop/Recommender/data/ratedmovies_short.csv";
        String ratingsfile = "/Users/e144574/Desktop/StepOneStarterProgram (1)/data/ratings_short.csv";
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
        // print the number of movies and number of raters from the two files by calling the appropriate methods in the SecondRatings class
        System.out.println("# of movies: "+sr.getMovieSize()+ " | # of raters: "+sr.getRaterSize());
        
        // add code to print a list of movies and their average ratings, for all those movies that have at least a specified number of ratings, sorted by averages
        int minRatings = 3;
        ArrayList<Rating> ratings = sr.getAverageRatings(minRatings);
        sortRatings(ratings);
        for (Rating rating: ratings) {
            // print the list of movies, one movie per line (print rating then title) in sorted order by ratings, lowest rating to highest rating
            System.out.println("rating: "+rating.getValue()+" | "+rating.getItem());
        }
            
    }
        
    // write the void method getAverageRatingOneMovie, which has no parameters
    // if the moviefile is set to the file named ratedmovies_short.csv, and the ratingsfile is set to the file ratings_short.csv, then the average for the movie "The Godfather" would be 9.0
    public void getAverageRatingOneMovie() {
        
        // this method should first create a SecondRatings object, reading in the data from the movie and ratings data files
        String moviefile = "/Users/e144574/Desktop/Recommender/data/ratedmovies_short.csv";
        String ratingsfile = "/Users/e144574/Desktop/StepOneStarterProgram (1)/data/ratings_short.csv";
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
        // then this method should print out the average ratings for a specific movie title, such as the movie "The Godfather"
        String title = "The Godfather";
        
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> myMovies = fr.loadMovies(moviefile);
        ArrayList<Rater> myRaters = fr.loadRaters(ratingsfile);
        
        String id = "";
        for (Movie movie: myMovies) {
          if (movie.getTitle().equals(title)) {
              id = movie.getID();
            }
          }
        
        int numRaters = 0;
        double ratingTotal = 0;
        for (Rater rater: myRaters) {
            if (rater.hasRating(id)) {
                ratingTotal += rater.getRating(id);
                numRaters++;
            }
        }
        double avgRating = ratingTotal/numRaters;
        
        System.out.println(title+" "+avgRating);
        
    }
}
