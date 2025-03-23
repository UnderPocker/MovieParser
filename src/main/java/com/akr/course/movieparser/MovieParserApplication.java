package com.akr.course.movieparser;


import com.akr.course.movieparser.site_entities.Image;
import com.akr.course.movieparser.site_entities.MovieSuggestion;
import com.akr.course.movieparser.site_entities.MovieSuggestions;
import com.akr.course.movieparser.site_entities.QueryResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieParserApplication {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("E:/dataset/");
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        Map<String, String> validMovies = getValidMovieIds();
        System.out.println("Id фильмов получены.");

        int count = 0;
        for (String movieName : validMovies.keySet()) {
            saveMovieImages(path, movieName, validMovies.get(movieName));

            count++;
            if (count % 100 == 0) {
                System.out.println("Было скачено " + count + " фильмов");
            }
        }

    }

    public static void saveMovieImages(Path mainFolder, String movieName, String movieId) throws IOException {
        Path path = mainFolder.resolve(Paths.get(movieName + "/"));
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        QueryResponse response;
        int page = 0, result = 0;
        while ((response = FlimApiRequest.getQueryResponse(movieId, page++)).getNumberOfResults() > 0){
            for (Image image : response.getImages()) {
                String url = image.getUrl();
                ImageLoader.loadImage(url, path, ++result);
            }
        }
    }
    
    public static Map<String, String> getValidMovieIds() throws IOException {
        Map<String, String> validMovies = new HashMap<>();

        List<String> lines = Files.readAllLines(Paths.get("movies.txt"));
        for (String movie : lines) {
            MovieSuggestions suggestions = FlimApiRequest.getMovieSuggestions(movie);

            if (suggestions != null && suggestions.getSuggestions() != null) {
                for (MovieSuggestion suggestion : suggestions.getSuggestions()) {
                    if (movie.equalsIgnoreCase(suggestion.getName())){
                        validMovies.put(movie, suggestion.getId());
                        break;
                    }
                }
            }


        }
        return validMovies;
    }

}
