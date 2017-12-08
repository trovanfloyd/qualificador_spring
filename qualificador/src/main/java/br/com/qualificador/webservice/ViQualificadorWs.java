package br.com.qualificador.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.qualificador.model.ViQualificador;
import br.com.qualificador.service.ViQualificadorService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/qualificacao")
public class ViQualificadorWs {

	@Autowired
	private ViQualificadorService viQualificadorService;
	
	/*@RequestMapping(method = RequestMethod.GET, value = "/cpf/{cpf}/{isnOrgao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ViQualificador> listar(@RequestParam(value="cpf") String cpf, 
			@RequestParam(value="isnOrgao") Long isnOrgao, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="size", defaultValue="10") Integer size) {
		
		return viQualificadorService.findByNumCpfAndIsnOrgao(cpf, isnOrgao, new PageRequest(page, size));
		
	}*/
	
	/*@RequestMapping(method = RequestMethod.GET, value = "/cpf/{cpf}/{isnOrgao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ViQualificador> listar(@PathVariable String cpf, 
			@PathVariable Long isnOrgao, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="size", defaultValue="10") Integer size) {
		
		return viQualificadorService.findByNumCpfAndIsnOrgao(cpf, isnOrgao, new PageRequest(page, size));
		
	}*/

	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/{isnOrgao}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ViQualificador> listarQualificacoesByStatus( 
			@PathVariable Long isnOrgao,
			@PathVariable Integer status,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="size", defaultValue="10") Integer size) {
		
		return viQualificadorService.listarQualificacoesByStatus(isnOrgao, status, new PageRequest(page, size));
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/contar-total", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long countTotalPessoas() {
		
		return viQualificadorService.countTotalPessoas();
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/contar-total-orgao/{isnOrgao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long countTotalPessoasOrgao(
			@PathVariable Long isnOrgao) {
		
		return viQualificadorService.countTotalPessoasByOrgao(isnOrgao);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/contar-status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long countByStatus(
			@PathVariable Integer status) {
		
		return viQualificadorService.countByStatus(status);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/contar-status-orgao/{isnOrgao}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long countByStatusOrgao(
			@PathVariable Long isnOrgao,
			@PathVariable Integer status) {
		
		return viQualificadorService.countByStatusOrgao(isnOrgao, status);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/contar-rejeitados/{isnOrgao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long countRejeitadosByOrgao(
			@PathVariable Long isnOrgao) {
		
		return viQualificadorService.countRejeitadosByOrgao(isnOrgao);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listar-todos-rejeitados/", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ViQualificador> listarTodosRejeitados( 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="size", defaultValue="10") Integer size) {
		
		return viQualificadorService.listarTodosRejeitados(new PageRequest(page, size));
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listar-rejeitados/{isnOrgao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ViQualificador> listarRejeitadosByOrgao(
			@PathVariable Long isnOrgao,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="size", defaultValue="10") Integer size) {
		
		return viQualificadorService.listarRejeitadosByOrgao(isnOrgao, new PageRequest(page, size));
		
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/contar-todos-rejeitados", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long countTodosRejeitados(
			@PathVariable Long isnOrgao) {
		
		return viQualificadorService.countTodosRejeitados();
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{nome}/{cpf}/{nis}/{data}/{isnOrgao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ViQualificador> findByFilters(
			@PathVariable String nome,
			@PathVariable String cpf,
			@PathVariable String nis,
			@PathVariable String data,
			@PathVariable Long isnOrgao,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="size", defaultValue="10") Integer size) {
		
		return viQualificadorService.findByFilters(nome, cpf, nis, data, isnOrgao, new PageRequest(page, size));
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cpf/{cpf}/{isnOrgao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ViQualificador> findByNumCpfAndIsnOrgao(
			@PathVariable String cpf,
			@PathVariable Long isnOrgao,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="size", defaultValue="10") Integer size) {
		
		return viQualificadorService.findByNumCpfAndIsnOrgao(cpf, isnOrgao, new PageRequest(page, size));
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/cpf/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ViQualificador> findByCPFTodosOrgaos(
			@PathVariable String cpf,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="size", defaultValue="10") Integer size) {
		
		return viQualificadorService.findByCpfTodosOrgaos(cpf, new PageRequest(page, size));
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/nome/{nome}/{isnOrgao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ViQualificador> findByNome(
			@PathVariable String nome,
			@PathVariable Long isnOrgao,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="size", defaultValue="10") Integer size) {
		
		return viQualificadorService.findByNome(nome, isnOrgao, new PageRequest(page, size));
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/nome/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<ViQualificador> findByNomeTodosOrgaos (
			@PathVariable String nome,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="size", defaultValue="10") Integer size) {
		
		return viQualificadorService.findByNomeTodosOrgaos(nome, new PageRequest(page, size));
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/export-planilha-rejeitados", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response exportQualificacaoRejeitados () {
		
		return viQualificadorService.exportQualificacaoRejeitados();
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/export-planilha-rejeitados/{isnOrgao}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response exportQualificacaoRejeitadosByOrgao (
			@PathVariable Long isnOrgao) {
		
		return viQualificadorService.exportQualificacaoRejeitadosByOrgao(isnOrgao);
		
	}


	@RequestMapping(method = RequestMethod.GET, value = "/export-planilha/{nome}/{cpf}/{nis}/{data}/{isnOrgao}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response exportQualificacao(
			@PathVariable String nome,
			@PathVariable String cpf,
			@PathVariable String nis,
			@PathVariable String data,
			@PathVariable Long isnOrgao,
			@PathVariable Integer status) {
		
		return viQualificadorService.exportQualificacao(nome, cpf, nis, data, isnOrgao, status);
		
	}

	@RequestMapping(method = RequestMethod.POST, value = "/upload-arquivo/{fileName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response uploadResultadoESocial (
			@PathVariable String fileName,
			@Context HttpServletRequest request) {
		
		return viQualificadorService.uploadResultadoESocial(fileName, request);
		
	}


	
	
}
