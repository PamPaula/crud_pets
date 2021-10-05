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

import com.gft.pets.entities.Dono;
import com.gft.pets.services.AnimalService;
import com.gft.pets.services.DonoService;

@Controller
@RequestMapping("dono")
public class DonoController {
	
	@Autowired
	private DonoService donoService;
	
	@Autowired
	private AnimalService animalService;
	
	@RequestMapping(path = "editar")
	public ModelAndView editarDono(@RequestParam(required = false) Long id) {
		
		ModelAndView mv = new ModelAndView("dono/form.html");
		
		Dono dono;
		
		if(id == null) {
			dono = new Dono();
		}else {
			try {
				dono = donoService.obterDono(id);
			}catch(Exception e) {
				dono = new Dono();
				mv.addObject("mensagem", e.getMessage());
			}
		}
		
		mv.addObject("dono", dono);
		mv.addObject("listaAnimais", animalService.listarAnimal());
		
		return mv;
		
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "editar")
	public ModelAndView salvarDono(@Valid Dono dono, BindingResult bindingResult) {
		
		ModelAndView mv = new ModelAndView("dono/form.html");
		
		boolean novo = true;
		
		if(bindingResult.hasErrors()) {
			mv.addObject("dono", dono);
			return mv;
		}
		
		donoService.salvarDono(dono);
		
		if(novo) {
			mv.addObject("dono", new Dono());
		}else {
			mv.addObject("dono", dono);
		}
		
		mv.addObject("mensagem", "Dono cadastrado com sucesso!");
		
		return mv;
		
	}
	
	@RequestMapping
	public ModelAndView listarDonos() {
		
		ModelAndView mv = new ModelAndView("dono/listar.html");
		
		mv.addObject("lista", donoService.listarDonos());
		
		return mv;
		
	}
	
	@RequestMapping("/excluir")
	public ModelAndView excluirDono(@RequestParam Long id, RedirectAttributes redirectedAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/dono");
		
		try {
			donoService.excluirDono(id);
			redirectedAttributes.addFlashAttribute("mensagem", "Dono excluído com sucesso!");
		}catch(Exception e) {
			redirectedAttributes.addFlashAttribute("mensagem", "Erro ao excluir dono!" + e.getMessage());
		}
		
		return mv;
		
	}

}
