package com.bosonit.trainig.mongodb.repository;

import com.bosonit.trainig.mongodb.entity.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaDao extends MongoRepository<Persona,String> {

    Page<Persona> findAll(Pageable pageable);
}
