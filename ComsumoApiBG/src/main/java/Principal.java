import java.sql.SQLOutput;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Principal {
	static MovieApiService movieApiService;
	static Retrofit retrofit;
	static String title;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 retrofit = new Retrofit.Builder().baseUrl("https://www.omdbapi.com/")
				.addConverterFactory(GsonConverterFactory.create()).build();

		 title = JOptionPane.showInputDialog("Digite o nome do filme:");

		searchMovies(retrofit, title);
		// searchMovies(retrofit);

	}

	private static void searchMovie(Retrofit retrofit, String title) {
		MovieApiService movieApiService = retrofit.create(MovieApiService.class);

		Call<MovieModel> callMovie = movieApiService.searchMovieByTitleAndYaer(title, "");

		callMovie.enqueue(new Callback<MovieModel>() {

			public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
				MovieModel movie = response.body();
				JOptionPane.showMessageDialog(null, movie);

			}

			public void onFailure(Call<MovieModel> call, Throwable t) {
				// TODO Auto-generated method stub

			}
		});
	}

	protected static String movieIndex = "";

	private static void searchMovies(final Retrofit retrofit, final String title) {
		movieApiService = retrofit.create(MovieApiService.class);
		Call<MoviesModel> callMovies = movieApiService.searchMoviesByTitle(title);
		callMovies.enqueue(new Callback<MoviesModel>() {	
			public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
				MoviesModel moviesModel = response.body();
				ArrayList<MovieModel> movies = moviesModel.movies;
				int i = 1;
				String mensagem = "";
				mensagem.concat("0 - Encerrar\n");
				for (MovieModel movie : movies) {
					mensagem += i + " - " + movie.Title + "\n";
					i++;
				}
				movieIndex = JOptionPane.showInputDialog(mensagem);
				if (movieIndex.equals("0")) {
					return; // encerra o método
				} else {
					int movieChoosedIndex = Integer.parseInt(movieIndex) - 1;
					MovieModel movieChoosed = movies.get(movieChoosedIndex);
					searchMovieById(movieChoosed.imdbID);
				}
				
			}
			
			public void onFailure(Call<MoviesModel> call, Throwable t) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
			

			private static void searchMovieById(String imdbID) {
				Call<MovieModel> call = movieApiService.searchMoviesById(imdbID);
				call.enqueue(new Callback<MovieModel>() {

					public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
						// TODO Auto-generated method stub
						MovieModel movie = response.body();
						JOptionPane.showMessageDialog(null, movie);
						searchMovies(retrofit, title); //para abrir de novo

					}

					public void onFailure(Call<MovieModel> call, Throwable t) {
						// TODO Auto-generated method stub

					}
				});

			}

		
	}

