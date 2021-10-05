package com.gft.pets.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.pets.entities.Veterinario;
import com.gft.pets.repositories.VeterinarioRepository;

@Service
public class VeterinarioService {

	@Autowired
	private VeterinarioRepository veterinarioRepository;
	
	public Veterinario salvarVeterinario(Veterinario veterinario) {
		
		return veterinarioRepository.save(veterinario);
		
	}
	
	public List<Veterinario> listarVeterinarios() {
		
		return veterinarioRepository.findAll();
		
	}
	
	public Veterinario obterVeterinario(Long id) throws Exception {
		
	Optional<Veterinario> veterinario = veterinarioRepository.findById(id);
		
		if(veterinario.isEmpty()) {
			throw new Exception("Veterinario não encontrado!");
		}
		
		return veterinario.get();
		
	}
	
	public void excluirVeterinario(Long id) {
		
		veterinarioRepository.deleteById(id);
		
	}
}
