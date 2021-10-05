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

import com.gft.pets.entities.Veterinario;
import com.gft.pets.services.AnimalService;
import com.gft.pets.services.VeterinarioService;

@Controller
@RequestMapping("veterinario")
public class VeterinarioController {

	@Autowired
	private VeterinarioService veterinarioService;
	
	@Autowired
	private AnimalService animalService;
	
	@RequestMapping(path = "editar")
	public ModelAndView editarVeterinario(@RequestParam(required = false) Long id) {
		
		ModelAndView mv = new ModelAndView("veterinario/form.html");
		
		Veterinario veterinario;
		
		if(id == null) {
			veterinario = new Veterinario();
		}else {
			try {
				veterinario = veterinarioService.obterVeterinario(id);
			}catch(Exception e) {
				veterinario = new Veterinario();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		
		mv.addObject("veterinario", veterinario);
		mv.addObject("listaAnimais", animalService.listarAnimal());
		
		return mv;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarVeterinario(@Valid Veterinario veterinario, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("veterinario/form.html");
		
		boolean novo = true;
		
		if(bindingResult.hasErrors()) {
			mv.addObject("veterinario", veterinario);
			return mv;
		}
		
		veterinarioService.salvarVeterinario(veterinario);
		
		if(novo) {
			mv.addObject("veterinario", new Veterinario());
		}else {
			mv.addObject("veterinario", veterinario);
		}
		
		mv.addObject("mensagem", "Veterinário cadastrado com sucesso!");
		
		return mv;
		
	}
	
	@RequestMapping
	public ModelAndView listarVeterinarios() {
		
		ModelAndView mv = new ModelAndView("veterinario/listar.html");
		
		mv.addObject("lista", veterinarioService.listarVeterinarios());
		
		return mv;
		
	}

	@RequestMapping("/excluir")
	public ModelAndView excluirVeterinario(@RequestParam Long id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/veterinario");
		
		try {
			veterinarioService.excluirVeterinario(id);
			redirectAttributes.addFlashAttribute("mensagem", "Veterinário excluído com sucesso!");
		}catch(Exception e) {
			redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir veterinário!" + e.getMessage());
		}
		
		return mv;
		
	}
	
}
