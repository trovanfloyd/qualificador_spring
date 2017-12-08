package br.com.qualificador.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.qualificador.model.ViQualificador;

public interface ViQualificadorRepository extends CrudRepository<ViQualificador, Long> {

	
	@Query("SELECT qual FROM ViQualificador qual WHERE (qual.codNomeInv = ?1 OR qual.codCpfNome <> ?2) "
	        + "AND (qual.codNisInv =?3 OR qual.codCnisNis = ?4 OR qual.codCnisObito =?5) AND (qual.codDnInv =?6 OR qual.codCnisDn = ?7 OR qual.codCpfDn=?8) "
	        + "AND (qual.codCpfInv =?9 OR qual.codCnisCpf =?10 OR qual.codCnisCpfNaoInf =?11 OR qual.codCpfNaoConsta =?12 OR qual.codCpfNulo =?13 OR qual.codCpfCancelado =?14 "
	        + "OR qual.codCpfSuspenso=?15) AND qual.isnOrgao = ?16 ORDER BY qual.txtNomFuncionario")
	Page<ViQualificador> findByFilters(String codNomeInv, String codCpfNome, String codNisInv, String codCnisNis, String codCnisObito, String codDnInv, String codCnisDn,
	        String codCpfDn, String codCpfInv, String codCnisCpf, String codCnisCpfNaoInf, String codCpfNaoConsta, String codCpfNulo, String codCpfCancelado, String codCpfSuspenso,
	        Long isnOrgao, Pageable pageable);
	
	Page<ViQualificador> findByIsnOrgaoAndStatus(Long isnOrgao, Integer status, Pageable pageable);
	
	Page<ViQualificador> findByIsnOrgao(Long isnOrgao, Pageable pageable);
	
	Page<ViQualificador> findByTxtNomFuncionarioAndIsnOrgao(String txtNomFuncionario, Long isnOrgao, Pageable pageable);
	
	Page<ViQualificador> findByTxtNomFuncionario(String txtNomFuncionario, Pageable pageable);
	
	Page<ViQualificador> findByNumCpfAndIsnOrgao(String numCpf, Long isnOrgao, Pageable pageable);
	
	Page<ViQualificador> findByNumCpf(String numCpf, Pageable pageable);
	
	@Query("SELECT count (distinct qual.numCpf) FROM ViQualificador qual")
	Long countTotalPessoas();

	@Query("SELECT count (distinct qual.numCpf) FROM ViQualificador qual WHERE qual.isnOrgao = ?1")
	Long countTotalPessoasByOrgao(Long isnOrgao);

	@Query("SELECT count (distinct qual.numCpf) FROM ViQualificador qual WHERE qual.status=?1 AND qual.tipoArquivo != 'REJEITADO'")
	Long countByStatus(Integer status);

	@Query("SELECT count (distinct qual.numCpf) FROM ViQualificador qual WHERE qual.isnOrgao = ?1 AND qual.status=?2  AND qual.tipoArquivo != 'REJEITADO'")
	Long countByStatusOrgao(Long isnOrgao, Integer status);
	
	@Query("SELECT count (qual.tipoArquivo) FROM ViQualificador qual WHERE qual.tipoArquivo = 'REJEITADO'")
	Long countTodosRejeitados();

	@Query("SELECT count (qual.tipoArquivo) FROM ViQualificador qual WHERE qual.tipoArquivo = 'REJEITADO' AND qual.isnOrgao = ?1")
	Long countRejeitadosByOrgao(Long isnOrgao);
	
	List<ViQualificador> findByIsnOrgaoOrderByTxtNomFuncionarioAsc(Long isnOrgao);
	
	List<ViQualificador> findByTipoArquivoOrderByTxtNomFuncionarioAsc(String tipoArquivo);
	
	List<ViQualificador> findByTipoArquivoAndIsnOrgaoOrderByTxtNomFuncionarioAsc(String tipoArquivo, Long isnOrgao);
	
	@Query("SELECT qual FROM ViQualificador qual WHERE (qual.codNomeInv = ?1 OR qual.codCpfNome <> ?2) "
	        + "AND (qual.codNisInv =?3 OR qual.codCnisNis = ?4 OR qual.codCnisObito =?5) AND (qual.codDnInv =?6 OR qual.codCnisDn = ?7 OR qual.codCpfDn=?8) "
	        + "AND (qual.codCpfInv =?9 OR qual.codCnisCpf =?10 OR qual.codCnisCpfNaoInf =?11 OR qual.codCpfNaoConsta =?12 OR qual.codCpfNulo =?13 OR qual.codCpfCancelado =?14 "
	        + "OR qual.codCpfSuspenso=?15) AND (qual.isnOrgao = ?16) AND qual.status = ?17 ORDER BY qual.txtNomFuncionario")
	List<ViQualificador> listaExportacaoByFilter(String codNomeInv, String codCpfNome, String codNisInv, String codCnisNis, String codCnisObito, String codDnInv, String codCnisDn,
	        String codCpfDn, String codCpfInv, String codCnisCpf, String codCnisCpfNaoInf, String codCpfNaoConsta, String codCpfNulo, String codCpfCancelado, String codCpfSuspenso,
	        Long isnOrgao, Integer status);
	
	List<ViQualificador> findByNumCpf(String numCpf);
	
	Page<ViQualificador> findByTipoArquivo(String tipoArquivo, Pageable pageable);
	
	Page<ViQualificador> findByTipoArquivoAndIsnOrgao(String tipoArquivo, Long isnOrgao, Pageable pageable);
	
}
