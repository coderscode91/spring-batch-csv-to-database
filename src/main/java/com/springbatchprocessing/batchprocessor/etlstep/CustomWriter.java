package com.springbatchprocessing.batchprocessor.etlstep;

import java.util.List;
import com.springbatchprocessing.batchprocessor.model.Movie;
import com.springbatchprocessing.batchprocessor.repository.MovieRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
 public class CustomWriter implements ItemWriter<Movie> {
	 
	 @Autowired
	 MovieRepository repo;
	 
	@Override
	public void write(List<? extends Movie> movies) throws Exception {
		for (Movie movie : movies) {
			System.out.println("Writing data " + movie);
			repo.save(movie);
		
			
			
		}
	}

}