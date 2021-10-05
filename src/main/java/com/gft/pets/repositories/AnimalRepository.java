package com.gft.pets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.pets.entities.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>{

}
