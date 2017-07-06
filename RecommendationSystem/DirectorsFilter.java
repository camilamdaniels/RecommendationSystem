
import java.util.*;

public class DirectorsFilter implements Filter{
	private String myDirectors;
	
	public DirectorsFilter(String directors) {
		myDirectors = directors;
		
	}
	
	public boolean satisfies(String id) {
		List<String> directorList = Arrays.asList(myDirectors.split(","));
		for (String director: directorList) {
			if (MovieDatabase.getDirector(id).contains(director)) {
				return true;
			}
		}
		return false;
	}
}
