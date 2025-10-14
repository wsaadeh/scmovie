package com.saadeh.consultancy.SCMovie.tests;

import com.saadeh.consultancy.SCMovie.dto.ScoreDTO;
import com.saadeh.consultancy.SCMovie.entities.MovieEntity;
import com.saadeh.consultancy.SCMovie.entities.ScoreEntity;
import com.saadeh.consultancy.SCMovie.entities.UserEntity;

public class ScoreFactory {
	
	public static Double scoreValue = 4.5;
	
	public static ScoreEntity createScoreEntity() {
		MovieEntity movie = MovieFactory.createMovieEntity();
		UserEntity user = UserFactory.createUserEntity();
		ScoreEntity score = new ScoreEntity();
		
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(scoreValue);
		movie.getScores().add(score);
		return score;
	}
	
	public static ScoreDTO createScoreDTO() {
		ScoreEntity score = createScoreEntity();
		return new ScoreDTO(score.getId().getMovie().getId(), score.getValue());
	}
}
