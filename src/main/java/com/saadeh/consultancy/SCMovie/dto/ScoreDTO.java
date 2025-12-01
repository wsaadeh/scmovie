package com.saadeh.consultancy.SCMovie.dto;

import com.saadeh.consultancy.SCMovie.entities.ScoreEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ScoreDTO {
	
	private static final DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

	@NotNull(message = "Required field")
	@Schema(description = "Movie ID")
	private Long movieId;

	@PositiveOrZero(message = "Score should be greater than or equal to zero")
	@Max(value = 5, message = "Score should not be greater than five")
	@Schema(description = "Movie Score")
	private Double score;

	public ScoreDTO(ScoreEntity score) {
		this.movieId = score.getId().getMovie().getId();
		this.score = Double.valueOf(df.format(score.getValue()));
	}

}
