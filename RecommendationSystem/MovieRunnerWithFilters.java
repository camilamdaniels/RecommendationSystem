
import java.util.ArrayList;

public class MovieRunnerWithFilters {
	// copy the printAverageRatings method from the MovieRunnerAverage class into this class
	// make the following changes:
		// instead of creating a SecondRatings object, you will create a ThirdRatings object
		// note that this only has one parameter, the name of a file with ratings data
		// print the number of raters after creating a ThirdRatings object
		// you'll call the MovieDatabse initialize method with the moviefile to set up the movie database
		// print the number of movies in the database
		// you will call getAverageRatings with a minimal number of raters to return an ArrayList of type Rating
		// print out how many moives with ratings are returned, then sort them, and print out the rating and title of each movie
		// if you run the printAverageRatings method ont he files ratings_short.csv and ratedmovies_short.csv with a minimal rater of 1, 
			// check pdf for desired output
	
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
	
	public void printAverageRatings() {		
		String moviefile = "/Users/e144574/Desktop/Recommender/data/ratedmovies_short.csv";
		String ratingsfile = "/Users/e144574/Desktop/StepOneStarterProgram (1)/data/ratings_short.csv";
		ThirdRatings tr = new ThirdRatings(ratingsfile);
		
		System.out.println("# of raters: "+tr.getRaterSize());
		MovieDatabase md = new MovieDatabase();
		md.initialize(moviefile);
		
		System.out.println("# of movies: "+md.size());
		
		int minRatings = 1;
		ArrayList<Rating> ratings = tr.getAverageRatings(minRatings);
		System.out.println("there are "+ratings.size()+" movies with ratings");
		sortRatings(ratings);
		for (Rating rating: ratings) {
			// print the list of movies, one movie per line (print rating then title) in sorted order by ratings, lowest rating to highest rating
			System.out.println("rating: "+rating.getValue()+" | "+md.getTitle(rating.getItem()));
		}	
	}
	
	// create a void method named printAverageRatingsByYear that should be similar to printAverageRatings
	public void printAverageRatingsByYear() {
		int year = 2000;
		int minimalRaters = 1;
		String moviefile = "/Users/e144574/Desktop/Recommender/data/ratedmovies_short.csv";
		String ratingsfile = "/Users/e144574/Desktop/StepOneStarterProgram (1)/data/ratings_short.csv";
		ThirdRatings tr = new ThirdRatings(ratingsfile);
		
		System.out.println("# of raters: "+tr.getRaterSize());
		
		YearAfterFilter yaf = new YearAfterFilter(year);
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, yaf);
		
		System.out.println("found "+ratings.size()+" movies");
		for (Rating rating: ratings) {
			System.out.println("rating: "+rating.getValue()+" "+MovieDatabase.getYear(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
		}
	}
	
	// create a void method named printAverageRatingsByGenre that shuold create a GenreFilter 
	public void printAverageRatingsByGenre() {
		int minimalRaters = 1;
		String genre = "Crime";
		String moviefile = "/Users/e144574/Desktop/Recommender/data/ratedmovies_short.csv";
		String ratingsfile = "/Users/e144574/Desktop/StepOneStarterProgram (1)/data/ratings_short.csv";
		ThirdRatings tr = new ThirdRatings(ratingsfile);
		
		System.out.println("# of raters: "+tr.getRaterSize());
		
		GenreFilter gf = new GenreFilter(genre);
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, gf);
		
		System.out.println("found "+ratings.size()+" movies");
		// and call getAverageRatingsByFilter to get an AraryList of type Rating of all the movies that have a specified number of minimal ratings and include a specified genre
		// print the number of movies found, and for each movie, print its rating and its title on one line and its genres on the next line
		for (Rating rating: ratings) {
			System.out.println("rating: "+rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem())+" | genre: "+MovieDatabase.getGenres(rating.getItem()));
		}
		// test with a minimal rater of 1 and the genre crime
	}
	
	// create a void method named printAverageRatingsByMinutes that should create a 
	public void printAverageRatingsByMinutes() {
		//MinutesFilter and call getAVerageRatingsByFilter to get an 
		int minimalRaters = 1;
		int min = 110;
		int max = 170;
		
		String moviefile = "/Users/e144574/Desktop/Recommender/data/ratedmovies_short.csv";
		String ratingsfile = "/Users/e144574/Desktop/StepOneStarterProgram (1)/data/ratings_short.csv";
		ThirdRatings tr = new ThirdRatings(ratingsfile);
		
		System.out.println("# of raters: "+tr.getRaterSize());
		// ArrayList of type Rating of all the movies that have 
		// a specified number of minimal ratings and their running time is at least a minimum number of minutes and no more than a maximum number of minutes
		MinutesFilter mf = new MinutesFilter(min, max);
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, mf);
		// print the number of movies found, and for each movie,
		System.out.println("# of movies found: "+ratings.size());
		for (Rating rating: ratings) {
			System.out.println("rating: "+rating.getValue()+" | running time: "+MovieDatabase.getMinutes(rating.getItem()+" | title: "+MovieDatabase.getTitle(rating.getItem())));
		}
		//print its rating, its running time, and its title on one line
		// test with minimal rater of 1, minimum minutes of 110 and maximum minutes of 170
	}
	
	// create a void method named printAverageRatingsByDirectors that should create a DirectorsFilter 
	// test on short files with minimal rater of 1 and the directors set to "Charles Chaplin,Michael Mann,Spike Jonze"
	public void printAverageRatingsByDirectors() {
		String directors = "Charles Chaplin,Michael Mann,Spike Jonze";
		int minimalRaters = 1;
		String moviefile = "/Users/e144574/Desktop/Recommender/data/ratedmovies_short.csv";
		String ratingsfile = "/Users/e144574/Desktop/StepOneStarterProgram (1)/data/ratings_short.csv";
		ThirdRatings tr = new ThirdRatings(ratingsfile);
		
		System.out.println("# of raters: "+tr.getRaterSize());
		// then call getAverageRatingsByFilter to get an ArrayList of type Rating of all the movies that ahve a specified number of minimal ratings
		// and include at least one of the directors specified
		DirectorsFilter df = new DirectorsFilter(directors);
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, df);
		System.out.println(ratings.size());
		// print the number of movies found, and for each movie print its rating and its title on one line
		// and all its directors on the next line
		for (Rating rating: ratings) {
			System.out.println("rating: "+rating.getValue()+" | title: "+MovieDatabase.getTitle(rating.getItem()));
			System.out.println(MovieDatabase.getDirector(rating.getItem()));
		}
	}
		
	// create a void method named printAverageRatingsByYearAfterAndGenre tht should creae an AllFilters object
	// test with minimal rater of 1, year set to 1980, and genre set to Romance
	
	public void printAverageRatingsByYearAfterAndGenre() {
		int minimalRaters = 1;
		String genre = "Romance";
		int year = 1980;
		String moviefile = "/Users/e144574/Desktop/Recommender/data/ratedmovies_short.csv";
		String ratingsfile = "/Users/e144574/Desktop/StepOneStarterProgram (1)/data/ratings_short.csv";
		ThirdRatings tr = new ThirdRatings(ratingsfile);
		
		System.out.println("# of raters: "+tr.getRaterSize());
		AllFilters af = new AllFilters();
		YearAfterFilter yaf = new YearAfterFilter(year);
		af.addFilter(yaf);
		GenreFilter gf = new GenreFilter(genre);
		af.addFilter(gf);
		
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, af);
		System.out.println(ratings.size());
		// this method should call getAverageRatingsByFilter to get an ArrayList of type Rating of
					// all the movies that have a specified number of minimal ratings and the two criteria based on year and genre
				// print the number of movies found, and for each movie, print its rating, its year, and its title on one line
					// and all its genres on the next line
		for (Rating rating: ratings) {
			System.out.println("rating: "+rating.getValue()+" | time: "+MovieDatabase.getMinutes(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
			System.out.println(MovieDatabase.getDirector(rating.getItem()));
		}
	}
		
	// create a void method named printAverageRatingsByDirectorsAndMinutes that should create an AllFilters object that includes criteria based on
		// running time and directors
	public void printAverageRatingsByDirectorsAndMinutes() {
		// this method shuould call getAverageRatingsByfilter to get an ArrayList of type Rating of all the movies that have a speicifed number of minimal ratings 
		// and the two criteria based on minutes and directors
		int minimalRaters = 1;
		String directors = "Spike Jonze,Michael Mann, Charles Chaplin, Francis Ford Coppola";
		int min = 30;
		int max = 170;
		
		String moviefile = "/Users/e144574/Desktop/Recommender/data/ratedmovies_short.csv";
		String ratingsfile = "/Users/e144574/Desktop/StepOneStarterProgram (1)/data/ratings_short.csv";
		ThirdRatings tr = new ThirdRatings(ratingsfile);
		
		System.out.println("# of raters: "+tr.getRaterSize());
		AllFilters af = new AllFilters();
		DirectorsFilter df = new DirectorsFilter(directors);
		af.addFilter(df);
		MinutesFilter mf = new MinutesFilter(min, max);
		af.addFilter(mf);
		
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, af);
		System.out.println(ratings.size());
		
		for (Rating rating: ratings) {
			System.out.println("rating: "+rating.getValue()+" | time: "+MovieDatabase.getMinutes(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
			System.out.println(MovieDatabase.getDirector(rating.getItem()));
		}
		// print the number of movies found, and for each movie, print its rating, its time length, and its title on one line, and all its directors on the next line
			// test with minimal rater of 1, minimum minutes set to 30, maximum minutes set to 170, and the directors set to 
				// "Spkie Jonze,Michael Mann, Charles Chaplin, Francis Ford Coppola"

	}
}
