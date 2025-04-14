package com.nhnacademy;

import java.util.List;

public class MovieMain {
    public static void main(String[] args) {
        MovieParser parser = new ApacheCommonsCsvMovieParser();

        try {
            List<Movie> movieList = parser.parse();
            for (Movie movie : movieList) {
                System.out.println(movie);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}