package com.springbatchprocessing.batchprocessor.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Movie {
	@Id
	Integer id;
	String title;
	String genres;
	
	public Movie(Integer id, String title, String genres) {
		super();
		this.id = id;
		this.title = title;
		this.genres = genres;
	}
	

	public Movie() {
		
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", genres=" + genres + "]";
	}
	
	
	
}
