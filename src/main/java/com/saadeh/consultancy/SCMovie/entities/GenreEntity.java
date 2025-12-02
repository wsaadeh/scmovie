package com.saadeh.consultancy.SCMovie.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_genre")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "genre")
    @Setter(AccessLevel.PRIVATE)
    private List<MovieEntity> movies = new ArrayList<>();
}
