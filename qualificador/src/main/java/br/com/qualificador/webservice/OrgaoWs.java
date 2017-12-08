package br.com.qualificador.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.qualificador.model.Orgao;
import br.com.qualificador.service.OrgaoService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/orgao")
public class OrgaoWs {

	@Autowired
	private OrgaoService orgaoService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/lista", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Orgao> listar(@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="size", defaultValue="10") Integer size) {
		System.out.println("passando");
		List<Orgao> list = (List<Orgao>) orgaoService.obterTodos();
		return list;
		
	}
	
}
