package com.saadeh.consultancy.SCMovie.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class MovieEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String title;
	private Double score;
	private Integer count;
	private String image;

	@Setter(AccessLevel.PRIVATE)
	@OneToMany(mappedBy = "id.movie")
	private Set<ScoreEntity> scores = new HashSet<>();
	

}
