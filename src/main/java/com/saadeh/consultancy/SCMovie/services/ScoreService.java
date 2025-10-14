package com.saadeh.consultancy.SCMovie.services;

import com.saadeh.consultancy.SCMovie.dto.MovieDTO;
import com.saadeh.consultancy.SCMovie.dto.ScoreDTO;
import com.saadeh.consultancy.SCMovie.entities.MovieEntity;
import com.saadeh.consultancy.SCMovie.entities.ScoreEntity;
import com.saadeh.consultancy.SCMovie.entities.UserEntity;
import com.saadeh.consultancy.SCMovie.repositories.MovieRepository;
import com.saadeh.consultancy.SCMovie.repositories.ScoreRepository;
import com.saadeh.consultancy.SCMovie.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	@Transactional
	public MovieDTO saveScore(ScoreDTO dto) {
		
		UserEntity user = userService.authenticated();
		
		MovieEntity movie = movieRepository.findById(dto.getMovieId())
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));		
		
		ScoreEntity score = new ScoreEntity();
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(dto.getScore());
		
		score = scoreRepository.saveAndFlush(score);
		
		double sum = 0.0;
		for (ScoreEntity s : movie.getScores()) {
			sum = sum + s.getValue();
		}
			
		double avg = sum / movie.getScores().size();
		
		movie.setScore(avg);
		movie.setCount(movie.getScores().size());
		
		movie = movieRepository.save(movie);
		
		return new MovieDTO(movie);
	}
}
