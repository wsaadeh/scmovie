package com.saadeh.consultancy.SCMovie.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class ScoreEntityPK {

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private MovieEntity movie;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;


}