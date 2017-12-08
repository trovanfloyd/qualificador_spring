package br.com.qualificador.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import br.com.qualificador.constants.StatusQualificacaoCadastral;
import br.com.qualificador.exception.ClientError;
import br.com.qualificador.exception.QualificadorApplicationException;
import br.com.qualificador.model.ViQualificador;
import br.com.qualificador.repository.ViQualificadorRepository;
import br.com.qualificador.util.OrientacoesCadastrais;
import br.com.qualificador.util.Util;

@Service
public class ViQualificadorService {

	@Autowired
	private ViQualificadorRepository viQualificadorRepository;
	
	public ViQualificador atualizar(ViQualificador qualificacaoCadastral) {
		return viQualificadorRepository.save(qualificacaoCadastral);
	}

	

	public Page<ViQualificador> findByFilters(String nome, String cpf, String nis, String data, Long isnOrgao, PageRequest pageRequest) {

		ViQualificador qual = Util.setUpFiltersQuery(nome, cpf, nis, data);
		
		isnOrgao = getIsnOrgaoByAuthenticatedUser(isnOrgao);

		return viQualificadorRepository.findByFilters(qual.getCodNomeInv(), qual.getCodCpfNome(), qual.getCodNisInv(), qual.getCodCnisNis(), qual.getCodCnisObito(), qual.getCodDnInv(),
		        qual.getCodCnisDn(), qual.getCodCpfDn(), qual.getCodCpfInv(), qual.getCodCnisCpf(), qual.getCodCnisCpfNaoInf(), qual.getCodCpfNaoConsta(), qual.getCodCpfNulo(),
		        qual.getCodCpfCancelado(), qual.getCodCpfSuspenso(), isnOrgao, pageRequest);
	}

	public Page<ViQualificador> listarQualificacoesByStatus(Long isnOrgao, Integer status, PageRequest pageRequest) {
		isnOrgao = getIsnOrgaoByAuthenticatedUser(isnOrgao);
		
		return viQualificadorRepository.findByIsnOrgaoAndStatus(isnOrgao, status, pageRequest);
	}

	public Page<ViQualificador> listarQualificacoesOrgao(Long isnOrgao, PageRequest pageRequest) {
		isnOrgao = getIsnOrgaoByAuthenticatedUser(isnOrgao);
		
		return viQualificadorRepository.findByIsnOrgao(isnOrgao, pageRequest);
	}

	public Page<ViQualificador> findByNome(String nome, Long isnOrgao, PageRequest pageRequest) {
		
		isnOrgao = getIsnOrgaoByAuthenticatedUser(isnOrgao);
		
		return viQualificadorRepository.findByTxtNomFuncionarioAndIsnOrgao("%" + nome + "%", isnOrgao, pageRequest);
	}

	public Page<ViQualificador> findByNomeTodosOrgaos(String nome, PageRequest pageRequest) {
		return viQualificadorRepository.findByTxtNomFuncionario("%" + nome + "%", pageRequest);
	}

	public Page<ViQualificador> findByNumCpfAndIsnOrgao(String cpf, Long isnOrgao, PageRequest pageRequest) {
		
		isnOrgao = getIsnOrgaoByAuthenticatedUser(isnOrgao);
		
		return viQualificadorRepository.findByNumCpfAndIsnOrgao(cpf, isnOrgao, pageRequest);
	}

	public Page<ViQualificador> findByCpfTodosOrgaos(String cpf, PageRequest pageRequest) {
		
		return viQualificadorRepository.findByNumCpf(cpf, pageRequest);
	}

	public List<ViQualificador> listaExportacaoTudo(Long isnOrgao) {
		isnOrgao = getIsnOrgaoByAuthenticatedUser(isnOrgao);
		
		return viQualificadorRepository.findByIsnOrgaoOrderByTxtNomFuncionarioAsc(isnOrgao);
	}

	public List<ViQualificador> listaExportacaoRejeitados() {
		
		return viQualificadorRepository.findByTipoArquivoOrderByTxtNomFuncionarioAsc("REJEITADO");
	}

	public List<ViQualificador> listaExportacaoRejeitadosByOrgao(Long isnOrgao) {
		isnOrgao = getIsnOrgaoByAuthenticatedUser(isnOrgao);
		
		return viQualificadorRepository.findByTipoArquivoAndIsnOrgaoOrderByTxtNomFuncionarioAsc("REJEITADO", isnOrgao);
	}
	
	public Response exportQualificacaoRejeitadosByOrgao(Long isnOrgao) {

		List<ViQualificador> list = listaExportacaoRejeitadosByOrgao(isnOrgao);

		JSONObject response = new JSONObject();
		Optional<byte[]> opt = this.gerarPlanilha(list);
		response.append("content", StringUtils.newStringUtf8(Base64.encodeBase64(opt.orElse(new byte[0]))));
		
		GenericEntity<String> entity = new GenericEntity<>(response.toString(), String.class);
		
		return Response.status(Status.OK).entity(entity).build();
	}

	public Page<ViQualificador> listarTodosRejeitados(PageRequest page) {
		return viQualificadorRepository.findByTipoArquivo("REJEITADO", page);
	}

	public Page<ViQualificador> listarRejeitadosByOrgao(Long isnOrgao, PageRequest page) {

		isnOrgao = getIsnOrgaoByAuthenticatedUser(isnOrgao);
		
		return viQualificadorRepository.findByTipoArquivoAndIsnOrgao("REJEITADO",isnOrgao, page);
	}

	public List<ViQualificador> listaExportacaoByFilter(String nome, String cpf, String nis, String data, Long isnOrgao, Integer status) {
		ViQualificador qual = Util.setUpFiltersQuery(nome, cpf, nis, data);
		
		isnOrgao = getIsnOrgaoByAuthenticatedUser(isnOrgao);

		return viQualificadorRepository.listaExportacaoByFilter(qual.getCodNomeInv(), qual.getCodCpfNome(), qual.getCodNisInv(), qual.getCodCnisNis(), qual.getCodCnisObito(),
		        qual.getCodDnInv(), qual.getCodCnisDn(), qual.getCodCpfDn(), qual.getCodCpfInv(), qual.getCodCnisCpf(), qual.getCodCnisCpfNaoInf(), qual.getCodCpfNaoConsta(),
		        qual.getCodCpfNulo(), qual.getCodCpfCancelado(), qual.getCodCpfSuspenso(), isnOrgao, status);
	}

	public ViQualificador findByCPF(String cpf) {
		return viQualificadorRepository.findByNumCpf(cpf).stream().filter(qual -> qual.getIsnQualificacaoCadastral() > 0).findFirst().orElse(null);
	}

	public Long countTotalPessoas() {
		return viQualificadorRepository.countTotalPessoas();
	}

	public Long countTotalPessoasByOrgao(Long isnOrgao) {
		isnOrgao = getIsnOrgaoByAuthenticatedUser(isnOrgao);
		
		return viQualificadorRepository.countTotalPessoasByOrgao(isnOrgao);
	}

	public Long countByStatus(Integer status) {
		
		return viQualificadorRepository.countByStatus(status);
	}

	public Long countByStatusOrgao(Long isnOrgao, Integer status) {
		isnOrgao = getIsnOrgaoByAuthenticatedUser(isnOrgao);
		
		return viQualificadorRepository.countByStatusOrgao(isnOrgao, status);
	}

	public Long countRejeitadosByOrgao(Long isnOrgao) {
		isnOrgao = getIsnOrgaoByAuthenticatedUser(isnOrgao);
		
		return viQualificadorRepository.countRejeitadosByOrgao(isnOrgao);
	}

	public Long countTodosRejeitados() {
		return viQualificadorRepository.countTodosRejeitados();
	}

	public Long countTodosRejeitados(Long isnOrgao) {
		return viQualificadorRepository.countRejeitadosByOrgao(isnOrgao);
	}
	
	
	
	private Long getIsnOrgaoByAuthenticatedUser(Long isnOrgao) {
		/*Integer idCod = -1;
		try {
			idCod = credential.getAuthenticatedUser().getIdEntidade();
		}
		catch (JoseException | InvalidJwtException e) {
			e.printStackTrace();
		}

		isnOrgao = isnOrgao == -1 ? orgaoRepo.findByOrgaoByIdEntidade(idCod).getIsnOrgao() : isnOrgao;*/
		return isnOrgao;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	public Response exportQualificacao(String nome, String cpf, String nis, String data, Long isnOrgao, Integer status) {

		List<ViQualificador> list;
		if (!nome.equals("0") || !cpf.equals("0") || !nis.equals("0") || !data.equals("0") || status != -1) {
			list = listaExportacaoByFilter(nome, cpf, nis, data, isnOrgao, status);
		} else {
			list = listaExportacaoTudo(isnOrgao);
		}

		JSONObject response = new JSONObject();
		response.append("content", StringUtils.newStringUtf8(Base64.encodeBase64(this.gerarPlanilha(list).orElse(new byte[0]))));
		GenericEntity<String> entity = new GenericEntity<>(response.toString(), String.class);
		return Response.status(Status.OK).entity(entity).build();

	}


	public Response exportQualificacaoRejeitados() {

		List<ViQualificador> list = listaExportacaoRejeitados();

		JSONObject response = new JSONObject();
		Optional<byte[]> opt = this.gerarPlanilha(list);
		response.append("content", StringUtils.newStringUtf8(Base64.encodeBase64(opt.orElse(new byte[0]))));
		GenericEntity<String> entity = new GenericEntity<>(response.toString(), String.class);
		return Response.status(Status.OK).entity(entity).build();
	}

	public Response uploadResultadoESocial(String fileName, HttpServletRequest request) {

		Gson gson = new Gson();
		Map<String, Double> map = new HashedMap<>();
		try {
			map = gson.fromJson(request.getReader(), Map.class);
		}
		catch (JsonSyntaxException | JsonIOException | IOException e1) {
			e1.printStackTrace();
		}

		byte[] array = new byte[map.size()];
		int i = 0;
		for (Double d : map.values()) {
			array[i++] = Byte.parseByte(String.valueOf(d.intValue()));
		}

		try {
			File tempFile = File.createTempFile("file", ".txt");
			tempFile.deleteOnExit();
			OutputStream os = new FileOutputStream(tempFile);
			os.write(array);
			os.flush();
			os.close();
			BufferedReader buffer = new BufferedReader(new FileReader(tempFile));
			this.convertLines2Qualificacoes(buffer.lines().collect(Collectors.toList())).forEach(this::atualizar);
			buffer.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new QualificadorApplicationException(new ClientError(Arrays.asList(String.format("Upload de %s executado com falha!", fileName))));
		}

		JSONObject response = new JSONObject();
		response.append("content", String.format("Upload de %s foi realizado com sucesso!", fileName));
		return Response.status(Status.OK).entity(new GenericEntity<>(response.toString(), String.class)).build();
	}
	
	private Optional<byte[]> gerarPlanilha(List<ViQualificador> listViQualificador) {

		try (HSSFWorkbook workbook = new HSSFWorkbook(); ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			HSSFSheet sheet = workbook.createSheet("sheet");
			HSSFRow rowhead = sheet.createRow((short) 0);

			rowhead.createCell((short) 0).setCellValue("NOME");
			rowhead.createCell((short) 1).setCellValue("CPF");
			rowhead.createCell((short) 2).setCellValue("NIS");
			rowhead.createCell((short) 3).setCellValue("DATA NASCIMENTO");
			rowhead.createCell((short) 4).setCellValue("MUNICÍPIO");
			rowhead.createCell((short) 5).setCellValue("SITUAÇÃO");
			rowhead.createCell((short) 6).setCellValue("LOTAÇÃO");
			rowhead.createCell((short) 7).setCellValue("AGRUPAMENTO");
			rowhead.createCell((short) 8).setCellValue("INCONSISTÊNCIAS");
			rowhead.createCell((short) 9).setCellValue("ORIENTAÇÕES");
			rowhead.createCell((short) 10).setCellValue("STATUS");

			int rowNumber = 1;

			for (ViQualificador qualificacao : listViQualificador) {
				HSSFRow row = sheet.createRow((short) rowNumber++);
				row.createCell((short) 0).setCellValue(qualificacao.getTxtNomFuncionario());
				row.createCell((short) 1).setCellValue(qualificacao.getNumCpf());
				row.createCell((short) 2).setCellValue(qualificacao.getTxtPisPasep());
				row.createCell((short) 3).setCellValue(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(qualificacao.getDataNascimento()));
				row.createCell((short) 4).setCellValue(qualificacao.getDscMunicipio());
				row.createCell((short) 5).setCellValue(qualificacao.getTipoVinculo());
				row.createCell((short) 6).setCellValue(qualificacao.getNomUnidadeExercicio());
				row.createCell((short) 7).setCellValue(qualificacao.getAgrupamento());
				row.createCell((short) 8).setCellValue(OrientacoesCadastrais.Inconsistencias(qualificacao));

				String orientacao = qualificacao.getCodOrientacaoCpf() != null ? OrientacoesCadastrais.orientacaoCPF(qualificacao.getCodOrientacaoCpf()) + "\\" : "";
				orientacao = orientacao.startsWith("\\") ? orientacao.substring(1) : orientacao;
				orientacao += qualificacao.getCodOrientacaoNis() != null ? OrientacoesCadastrais.orientacaoNIS(qualificacao.getCodOrientacaoNis()) : "";

				if (orientacao.equals("") && qualificacao.getStatus().equals("2")) {
					orientacao = "Efetuar correção no SGP";
				}
				row.createCell((short) 9).setCellValue(orientacao);
				if (qualificacao.getTipoArquivo().equals("REJEITADO")) {
					row.createCell((short) 10).setCellValue("REJEITADO");
				} else {
					row.createCell((short) 10).setCellValue(StatusQualificacaoCadastral.getStatusById(qualificacao.getStatus()));
				}
			}
			// IntStream.rangeClosed(0, 10).forEach(i ->
			// sheet.autoSizeColumn((short) i));
			workbook.write(bos);
			return Optional.of(bos.toByteArray());
		}
		catch (IOException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	private List<ViQualificador> convertLines2Qualificacoes(List<String> listString) {
		List<ViQualificador> listQualificacaoCad = new ArrayList<>();
		Date CURRENT_TIME = Calendar.getInstance().getTime();
		listString.remove(0);// remoção do cabeçalho
		for (String linha : listString) {
			ViQualificador qc = null;
			String[] fields = linha.split(";");

			if (fields.length == 21) {

				qc = findByCPF(fields[0]);
				if (qc != null) {
					qc.setCodNisInv(fields[4]);
					qc.setCodCpfInv(fields[5]);
					qc.setCodNomeInv(fields[6]);
					qc.setCodDnInv(fields[7]);
					qc.setCodCnisNis(fields[8]);
					qc.setCodCnisDn(fields[9]);
					qc.setCodCnisObito(fields[10]);
					qc.setCodCnisCpf(fields[11]);
					qc.setCodCnisCpfNaoInf(fields[12]);
					qc.setCodCpfNaoConsta(fields[13]);
					qc.setCodCpfNulo(fields[14]);
					qc.setCodCpfCancelado(fields[15]);
					qc.setCodCpfSuspenso(fields[16]);
					qc.setCodCpfDn(fields[17]);
					qc.setCodCpfNome(fields[18]);
					qc.setCodOrientacaoCpf(fields[19]);
					qc.setCodOrientacaoNis(fields[20]);
					qc.setDatAtualizacao(CURRENT_TIME);
					qc.setTipoArquivo("PROCESSADO");
					if (this.verificarInconsistencia(qc)) {
						qc.setStatus(2);
					} else {
						qc.setStatus(0);
					}
					listQualificacaoCad.add(qc);
				}
			} else if (fields.length == 10) {

				qc = findByCPF(fields[0]);
				if (qc != null) {
					qc.setCodCpfInv(fields[4]);
					qc.setCodNisInv(fields[5]);
					qc.setCodNomeInv(fields[6]);
					qc.setCodDnInv(fields[7]);
					qc.setDatAtualizacao(CURRENT_TIME);
					qc.setStatus(2);
					qc.setTipoArquivo("REJEITADO");
					listQualificacaoCad.add(qc);
				}
			}

		}

		return listQualificacaoCad;
	}

	private boolean verificarInconsistencia(ViQualificador qc) {
		return !qc.getCodNisInv().equals("0") || !qc.getCodCpfInv().equals("0") || !qc.getCodNomeInv().equals("0") || !qc.getCodDnInv().equals("0")
		        || !qc.getCodCnisNis().equals("0") || !qc.getCodCnisDn().equals("0") || !qc.getCodCnisObito().equals("0") || !qc.getCodCnisCpf().equals("0")
		        || !qc.getCodCnisCpfNaoInf().equals("0") || !qc.getCodCpfNaoConsta().equals("0") || !qc.getCodCpfNulo().equals("0") || !qc.getCodCpfCancelado().equals("0")
		        || !qc.getCodCpfSuspenso().equals("0") || !qc.getCodCpfDn().equals("0") || !qc.getCodCpfNome().equals("0") || !qc.getCodOrientacaoCpf().equals("0")
		        || !qc.getCodOrientacaoNis().equals("0");
	}
}
