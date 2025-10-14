package com.saadeh.consultancy.SCMovie.dto;

import com.saadeh.consultancy.SCMovie.entities.ScoreEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ScoreDTO {
	
	private static final DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

	@NotNull(message = "Required field")
	private Long movieId;

	@PositiveOrZero(message = "Score should be greater than or equal to zero")
	@Max(value = 5, message = "Score should not be greater than five")
	private Double score;

	public ScoreDTO() {
	}

	public ScoreDTO(Long movieId, Double score) {
		this.movieId = movieId;
		this.score = Double.valueOf(df.format(score));
	}
	
	public ScoreDTO(ScoreEntity score) {
		this.movieId = score.getId().getMovie().getId();
		this.score = Double.valueOf(df.format(score.getValue()));
	}
	
	public Long getMovieId() {
		return movieId;
	}

	public Double getScore() {
		return score;
	}
}
