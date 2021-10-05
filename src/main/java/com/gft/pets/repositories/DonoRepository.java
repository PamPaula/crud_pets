package com.gft.pets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.pets.entities.Dono;

@Repository
public interface DonoRepository extends JpaRepository<Dono, Long> {

}
