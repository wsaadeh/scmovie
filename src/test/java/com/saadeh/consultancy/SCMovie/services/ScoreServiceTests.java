package com.saadeh.consultancy.SCMovie.services;

import com.saadeh.consultancy.SCMovie.dto.MovieDTO;
import com.saadeh.consultancy.SCMovie.dto.ScoreDTO;
import com.saadeh.consultancy.SCMovie.entities.MovieEntity;
import com.saadeh.consultancy.SCMovie.entities.ScoreEntity;
import com.saadeh.consultancy.SCMovie.entities.UserEntity;
import com.saadeh.consultancy.SCMovie.repositories.MovieRepository;
import com.saadeh.consultancy.SCMovie.repositories.ScoreRepository;
import com.saadeh.consultancy.SCMovie.services.exceptions.ResourceNotFoundException;
import com.saadeh.consultancy.SCMovie.tests.MovieFactory;
import com.saadeh.consultancy.SCMovie.tests.ScoreFactory;
import com.saadeh.consultancy.SCMovie.tests.UserFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class ScoreServiceTests {
	
	@InjectMocks
	private ScoreService service;

	@Mock
	private UserService userService;

	@Mock
	private MovieRepository movieRepository;

	@Mock
	private ScoreRepository repository;

	private UserEntity user;
	private ScoreDTO dto;
	private ScoreEntity entity;
	private MovieEntity movieEntity;
	private Long existingMovieId;
	private Long nonExistingMovieId;
	private MovieDTO movieDTO;

	@BeforeEach
	void setUp(){
		//User
		user = UserFactory.createUserEntity();

		//Movie
		movieDTO = MovieFactory.createMovieDTO();
		movieEntity = MovieFactory.createMovieEntity();
		existingMovieId = movieDTO.getId();
		nonExistingMovieId = 2L;

		//Score
		entity = ScoreFactory.createScoreEntity();
		dto = ScoreFactory.createScoreDTO();

		//Adjusting movieEntity
		movieEntity.getScores().add(entity);

		//saveScore

		//User
		Mockito.when(userService.authenticated()).thenReturn(user);

		//Movie
		Mockito.when(movieRepository.save(any())).thenReturn(movieEntity);

		//Score
		Mockito.when(repository.saveAndFlush(any())).thenReturn(entity);


	}

	@Test
	public void saveScoreShouldReturnMovieDTO() {


		Mockito.when(movieRepository.findById(existingMovieId)).thenReturn(Optional.of(movieEntity));

		MovieDTO result = service.saveScore(dto);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(),movieEntity.getId());
	}
	
	@Test
	public void saveScoreShouldThrowResourceNotFoundExceptionWhenNonExistingMovieId() {
		Mockito.when(movieRepository.findById(any())).thenReturn(Optional.empty());
		movieEntity.setId(nonExistingMovieId);
		entity.setMovie(movieEntity);
		dto = ScoreFactory.createScoreDTO();

		Assertions.assertThrows(ResourceNotFoundException.class,()->{
			MovieDTO result = service.saveScore(dto);
		});
	}
}
