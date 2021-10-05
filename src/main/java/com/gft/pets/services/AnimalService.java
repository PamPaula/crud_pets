package com.gft.pets.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.pets.entities.Animal;
import com.gft.pets.repositories.AnimalRepository;

@Service
public class AnimalService {

	@Autowired
	private AnimalRepository animalRepository;
	
	public Animal salvarAnimal(Animal animal) {
		
		return animalRepository.save(animal);
		
	}
	
	public List<Animal> listarAnimal() {
		
		return animalRepository.findAll();
		
	}
	
	public Animal obterAnimal(Long id) throws Exception {
		
		Optional<Animal> animal = animalRepository.findById(id);
		
		if(animal.isEmpty()) {
			throw new Exception("Pet não encontrado!");
		}
		
		return animal.get();
		
	}

	public void excluirAnimal(Long id) {
		animalRepository.deleteById(id);
	}
	
}
