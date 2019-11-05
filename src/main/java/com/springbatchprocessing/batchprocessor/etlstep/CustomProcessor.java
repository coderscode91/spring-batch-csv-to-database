package com.springbatchprocessing.batchprocessor.etlstep;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.springbatchprocessing.batchprocessor.model.Movie;

@Component
public class CustomProcessor implements ItemProcessor<Movie, Movie> {

	@Override
	public Movie process(Movie movie) throws Exception {
		
		movie.setTitle(movie.getTitle().toUpperCase());
		
		String[] genres = movie.getGenres().split("|");
		movie.setGenres(genres[0]);
		return movie;
	}

}