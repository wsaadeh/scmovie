package com.saadeh.consultancy.SCMovie.repositories;

import com.saadeh.consultancy.SCMovie.entities.ScoreEntity;
import com.saadeh.consultancy.SCMovie.entities.ScoreEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<ScoreEntity, ScoreEntityPK> {

}