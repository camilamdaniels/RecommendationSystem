public class MinutesFilter implements Filter{
    private int minMinutes;
    private int maxMinutes;
    
    public MinutesFilter(int min, int max) {
        minMinutes = min;
        maxMinutes = max;
    }
    
    public boolean satisfies(String id) {
        if (MovieDatabase.getMinutes(id) >= minMinutes) {
          if (MovieDatabase.getMinutes(id) <= maxMinutes) {
              return true;
          }
        } 
        return false;
    }
}
