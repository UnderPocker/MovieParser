package com.akr.course.movieparser;


import com.akr.course.movieparser.site_entities.MovieSuggestion;
import com.akr.course.movieparser.site_entities.MovieSuggestions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MovieParserApplication {

    public static void main(String[] args) throws IOException {
        ImageLoader.loadImage(
                "https://flim-1-0-2.s3.eu-central-1.amazonaws.com/thumbs/thumbnail/b85fecf2186211ebb16110ddb1aba44f.jpeg",
                Paths.get(""), 1);
    }
    
    public static List<String> getValidMovieIds() throws IOException {
        List<String> validMovies = new ArrayList<>();

        List<String> lines = Files.readAllLines(Paths.get("movies.txt"));
        for (String movie : lines) {
            MovieSuggestions suggestions = FlimApiRequest.getMovieSuggestions(movie);

            if (suggestions != null && suggestions.getSuggestions() != null) {
                for (MovieSuggestion suggestion : suggestions.getSuggestions()) {
                    if (movie.equalsIgnoreCase(suggestion.getName())){
                        validMovies.add(suggestion.getId());
                        break;
                    }
                }
            }


        }
        return validMovies;
    }

}
