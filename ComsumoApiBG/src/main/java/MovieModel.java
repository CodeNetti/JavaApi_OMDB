
public class MovieModel {
	
	String Title;
	String Year;
	String Type;
	String imdbRating;
	String imdbID;
	String Poster;
	String Plot;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Title + "\n" + Year + "\n" + Type + "\n" + imdbRating
					 + "\n" + Poster + "\n" + Plot; 
	}
}
