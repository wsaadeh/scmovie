package com.saadeh.consultancy.SCMovie.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_movie")
@NoArgsConstructor
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

	@OneToMany(mappedBy = "id.movie")
	@Setter(AccessLevel.PRIVATE)
	private Set<ScoreEntity> scores = new HashSet<>();

	public MovieEntity(Long id, String title, Double score, Integer count, String image) {
		this.id = id;
		this.title = title;
		this.score = score;
		this.count = count;
		this.image = image;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;

		MovieEntity that = (MovieEntity) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}