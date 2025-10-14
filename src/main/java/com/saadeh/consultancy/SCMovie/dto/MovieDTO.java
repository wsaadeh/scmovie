package com.saadeh.consultancy.SCMovie.dto;

import com.saadeh.consultancy.SCMovie.entities.MovieEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MovieDTO {

	private static final DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

	private Long id;
	
	@NotBlank(message = "Required field")
	@Size(min = 5, max = 80, message = "Title must be between 5 and 80 characters")
	private String title;
	
	@PositiveOrZero(message = "Score should be greater than or equal to zero")
	private Double score;
	
	@PositiveOrZero(message = "Count should be greater than or equal to zero")
	private Integer count;
	
	@NotBlank(message = "Required field")
	@URL(message = "Field must be a valid url")
	private String image;

	public MovieDTO(MovieEntity movie) {
		this(movie.getId(), movie.getTitle(), movie.getScore(), movie.getCount(), movie.getImage());
	}

	@Override
	public String toString() {
		return "MovieDTO [id=" + id + ", title=" + title + ", score=" + score + ", count=" + count + ", image=" + image
				+ "]";
	}
}
