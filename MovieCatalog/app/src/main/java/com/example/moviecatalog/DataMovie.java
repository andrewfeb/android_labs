package com.example.moviecatalog;

import java.util.ArrayList;

public class DataMovie {
    private static String[] movieTitle = {
      "Batman",
      "Ambulance",
      "Persuit"
    };

    private static String[] movieDescription = {
      "movie batman",
      "movie ambulance",
      "movie persuit"
    };

    private static String[] releaseDate = {
      "2022",
      "2021",
      "2022"
    };

    private static int[] movieCover = {
      R.drawable.batman,
      R.drawable.ambulance,
      R.drawable.persuit
    };

    static ArrayList<Movie> getListData() {
        ArrayList<Movie> list = new ArrayList<>();
        for(int i=0; i < movieTitle.length; i++) {
            Movie movie = new Movie();
            movie.setTitle(movieTitle[i]);
            movie.setDescription(movieDescription[i]);
            movie.setReleaseYear(releaseDate[i]);
            movie.setCover(movieCover[i]);
            list.add(movie);
        }
        return list;
    }

    static Movie getDataMovie(int index) {
        Movie movie = new Movie();
        movie.setTitle(movieTitle[index]);
        movie.setReleaseYear(releaseDate[index]);
        movie.setDescription(movieDescription[index]);
        movie.setCover(movieCover[index]);

        return movie;
    }
}
