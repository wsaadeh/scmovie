package com.saadeh.consultancy.SCMovie.services;

import com.saadeh.consultancy.SCMovie.dto.MovieDTO;
import com.saadeh.consultancy.SCMovie.entities.MovieEntity;
import com.saadeh.consultancy.SCMovie.repositories.MovieRepository;
import com.saadeh.consultancy.SCMovie.services.exceptions.DatabaseException;
import com.saadeh.consultancy.SCMovie.services.exceptions.ResourceNotFoundException;
import com.saadeh.consultancy.SCMovie.tests.MovieFactory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class MovieServiceTests {
	
	@InjectMocks
	private MovieService service;

	@Mock
	private MovieRepository repository;

	private MovieDTO dto;
	private MovieEntity entity;
	private Long existingMovieID;
	private Long nonExistingMovieID;
	private Long dependentMovieID;

	@BeforeEach
	void setUp() throws Exception{
		entity = MovieFactory.createMovieEntity();
		dto = MovieFactory.createMovieDTO();
		String title = "Test Movie";
		existingMovieID = 1L;
		nonExistingMovieID = 1000L;
		dependentMovieID = 2L;


		Pageable pageable = PageRequest.of(0,20);
		Page<MovieEntity> pageMovie = new PageImpl<>(List.of(entity),pageable,1);

		//findAll
		Mockito.when(repository.searchByTitle(title,pageable)).thenReturn(pageMovie);

		//findById
		Mockito.when(repository.findById(existingMovieID)).thenReturn(Optional.of(entity));
		Mockito.when(repository.findById(nonExistingMovieID)).thenReturn(Optional.empty());

		//insert
		Mockito.when(repository.save(any())).thenReturn(entity);

		//update
		Mockito.when(repository.getReferenceById(existingMovieID)).thenReturn(entity);
		Mockito.when(repository.getReferenceById(nonExistingMovieID)).thenThrow(EntityNotFoundException.class);

		//delete
		Mockito.when(repository.existsById(existingMovieID)).thenReturn(true);
		Mockito.when(repository.existsById(nonExistingMovieID)).thenReturn(false);
		Mockito.when(repository.existsById(dependentMovieID)).thenReturn(true);
		Mockito.doNothing().when(repository).deleteById(existingMovieID);
		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentMovieID);
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
		MovieDTO result = service.findById(existingMovieID);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getTitle(),result.getTitle());
	}
	
	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class,()->{ MovieDTO result = service.findById(nonExistingMovieID); });
	}
	
	@Test
	public void insertShouldReturnMovieDTO() {
		MovieDTO result = service.insert(dto);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getScore(),entity.getScore());
		Assertions.assertEquals(result.getImage(),entity.getImage());

	}
	
	@Test
	public void updateShouldReturnMovieDTOWhenIdExists() {
		MovieDTO result = service.update(existingMovieID,dto);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getTitle(),entity.getTitle());
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class,()->{ MovieDTO result = service.update(nonExistingMovieID,dto); });
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		Assertions.assertDoesNotThrow(()->{ service.delete(existingMovieID);});
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Assertions.assertThrows(ResourceNotFoundException.class,()->{ service.delete(nonExistingMovieID);});
	}
	
	@Test
	public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
		Assertions.assertThrows(DatabaseException.class,()-> service.delete(dependentMovieID));
	}
}
