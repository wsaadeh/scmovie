package com.saadeh.consultancy.SCMovie.services;

import com.saadeh.consultancy.SCMovie.dto.MovieDTO;
import com.saadeh.consultancy.SCMovie.entities.MovieEntity;
import com.saadeh.consultancy.SCMovie.repositories.MovieRepository;
import com.saadeh.consultancy.SCMovie.tests.MovieFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class MovieServiceTests {
	
	@InjectMocks
	private MovieService service;

	@Mock
	private MovieRepository repository;

	private MovieDTO dto;
	private MovieEntity entity;

	@BeforeEach
	void setUp() throws Exception{
		entity = MovieFactory.createMovieEntity();
		dto = MovieFactory.createMovieDTO();
		String title = "Test Movie";

		Pageable pageable = PageRequest.of(0,20);
		Page<MovieEntity> pageMovie = new PageImpl<>(List.of(entity),pageable,1);

		Mockito.when(repository.searchByTitle(title,pageable)).thenReturn(pageMovie);

	}
	
	@Test
	public void findAllShouldReturnPagedMovieDTO() {
		String title = "Test Movie";
		Pageable pageable = PageRequest.of(0,20);

		Page<MovieDTO> result = service.findAll(title,pageable);

		Assertions.assertNotNull(result);
	}
	
	@Test
	public void findByIdShouldReturnMovieDTOWhenIdExists() {
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
	}
	
	@Test
	public void insertShouldReturnMovieDTO() {
	}
	
	@Test
	public void updateShouldReturnMovieDTOWhenIdExists() {
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
	}
	
	@Test
	public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
	}
}
