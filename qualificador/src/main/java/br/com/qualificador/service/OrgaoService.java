package br.com.qualificador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qualificador.model.Orgao;
import br.com.qualificador.repository.OrgaoRepository;

@Service
public class OrgaoService {
	
	@Autowired
	private OrgaoRepository orgaoRepository;
	
	 public Iterable<Orgao> obterTodos(){
	        Iterable<Orgao> orgaos = orgaoRepository.findAll();
	        return orgaos;
	    }

}
