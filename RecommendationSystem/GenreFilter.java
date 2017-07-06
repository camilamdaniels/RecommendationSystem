public class GenreFilter implements Filter{
	// constructor should have one parameter named genre representing one genre, and 
	// the satisfies method should return true if a movie has this genre
	// note that movies may have several genres
	private String myGenre;
	
	public GenreFilter(String genre) {
		
		myGenre = genre;
	} 
	
	public boolean satisfies(String id) {
		if (MovieDatabase.getGenres(id).contains(myGenre)) {
			return true;
		}
		return false;
	}
}
