package com.gft.pets.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.pets.entities.Dono;
import com.gft.pets.repositories.DonoRepository;

@Service
public class DonoService {

	@Autowired
	private DonoRepository donoRepository;
	
	public Dono salvarDono(Dono dono) {
		
		return donoRepository.save(dono);
		
	}
	
	public List<Dono> listarDonos() {
		
		return donoRepository.findAll();
		
	}
	
	public Dono obterDono(Long id) throws Exception {
		
		Optional<Dono> dono = donoRepository.findById(id);
		
		if(dono.isEmpty()) {
			throw new Exception("Dono não encontrado!");
		}
		
		return dono.get();
		
	}
	
	public void excluirDono(Long id) {
		
		donoRepository.deleteById(id);
		
	}
	
}
