package br.com.qualificador.webservice;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.qualificador.model.Orgao;
import br.com.qualificador.service.OrgaoService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
@ConfigurationProperties(prefix = "helloapp")
public class RestController {
	
	@Autowired
	private OrgaoService orgaoService;
	
	private String saying;

	@RequestMapping(method = RequestMethod.GET, value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Orgao> helloRest() {
		System.out.println("passando");
		List<Orgao> list = (List<Orgao>) orgaoService.obterTodos();
		return list;
		
	}

	public String getSaying() {
		return saying;
	}

	public void setSaying(String saying) {
		this.saying = saying;
	}
	
	
	
}
