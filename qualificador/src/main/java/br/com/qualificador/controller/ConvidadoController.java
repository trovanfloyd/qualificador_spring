package br.com.qualificador.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConvidadoController {
	
	

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("novo")
	public String novo(Model model) {
		
		return "novo";
	}
	
	
}
