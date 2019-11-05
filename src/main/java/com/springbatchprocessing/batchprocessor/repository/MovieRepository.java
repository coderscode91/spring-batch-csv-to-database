package com.springbatchprocessing.batchprocessor.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.springbatchprocessing.batchprocessor.model.Movie;

@Repository
@Component
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	
}