package com.akr.course.movieparser;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MovieParserApplication {

    public static void main(String[] args) throws IOException {
        int validMovies = 0;

        List<String> lines = Files.readAllLines(Paths.get("movies.txt"));
        for (String line : lines) {
            FlimApiRequest.
        }
    }

}
