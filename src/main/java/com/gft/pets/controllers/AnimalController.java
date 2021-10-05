package com.gft.pets.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.pets.entities.Animal;
import com.gft.pets.services.AnimalService;

@Controller
@RequestMapping("animal")
public class AnimalController {
	
	@Autowired
	private AnimalService animalService;
	
	@RequestMapping(path = "novo") // http://localhost:8080/animal/novo
	public ModelAndView novoAnimal() {
		
		ModelAndView mv = new ModelAndView("animal/form.html");
		mv.addObject("animal", new Animal());
		
		return mv;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "novo")
	public ModelAndView salvarAnimal(@Valid Animal animal, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("animal/form.html");
		
		if(bindingResult.hasErrors()) {
			mv.addObject("animal", animal);
			return mv;
		}
		
		Animal animalSalvo = animalService.salvarAnimal(animal);
		
		if(animal.getId() == null) {
			mv.addObject("animal", new Animal());
		}else {
			mv.addObject("animal", animalSalvo);
		}
		
		mv.addObject("mensagem", "Pet cadastrado com sucesso!");
		
		return mv;
		
	}
	
	@RequestMapping // http://localhost:8080/animal
	public ModelAndView listarAnimais() {
		
		ModelAndView mv = new ModelAndView("animal/listar.html");
		mv.addObject("lista", animalService.listarAnimal());
		
		return mv;
		
	}
	
	@RequestMapping("/editar")
	public ModelAndView editarAnimal(@RequestParam Long id) {
		
		ModelAndView mv = new ModelAndView("animal/form.html");
		Animal animal;
		
		try {
			animal = animalService.obterAnimal(id);
		}catch(Exception e) {
			animal = new Animal();
			mv.addObject("mensagem", e.getMessage());
		}
		
		mv.addObject("animal", animal);
		
		return mv;
		
	}

	@RequestMapping("/excluir")
	public ModelAndView excluirAnimal(@RequestParam Long id, RedirectAttributes redirectedAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/animal");
		
		try {
			animalService.excluirAnimal(id);
			redirectedAttributes.addFlashAttribute("mensagem", "Pet excluído com sucesso!");
		}catch(Exception e) {
			redirectedAttributes.addFlashAttribute("mensagem", "Erro ao excluir pet!" + e.getMessage());
		}
		
		return mv;
		
	}
	
}
