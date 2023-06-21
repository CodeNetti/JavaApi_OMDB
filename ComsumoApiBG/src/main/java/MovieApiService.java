import retrofit2.http.Query;
import retrofit2.Call;
import retrofit2.http.GET;



public interface MovieApiService {
	
	final String KEY = "?apikey=8e8bd0de";
	
	@GET(KEY)
	Call<MovieModel> searchMovieByTitleAndYaer (@Query("t")String title,
			                              		@Query("y") String year);


	@GET(KEY)
	Call<MoviesModel> searchMoviesByTitle (@Query("s") String title);
	
	
	@GET(KEY)
	Call<MovieModel> searchMoviesById (@Query("i") String id);
 
	}
