package br.com.qualificador.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.qualificador.constants.TipoVinculo;
import br.com.qualificador.model.ViQualificador.PrimaryKey;
import br.com.qualificador.util.LocalDatePersistenceConverter;

@Entity
@Table(name = "tb_qualificador", schema = "db_esocial")
@IdClass(PrimaryKey.class)
public class ViQualificador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "isn_qualificacao_cadastral", nullable = false)
	private Long isnQualificacaoCadastral;

	@Column(name = "txt_nom_funcionario")
	private String txtNomFuncionario;

	@Column(name = "txt_pis_pasep")
	private String txtPisPasep;

	@Column(name = "num_cpf")
	private String numCpf;

	@Column(name = "data_nascimento")
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate dataNascimento;

	@Column(name = "agrupamento")
	private String agrupamento;

	@Column(name = "cod_cnis_cpf")
	private String codCnisCpf;

	@Column(name = "cod_cnis_cpf_nao_inf")
	private String codCnisCpfNaoInf;

	@Column(name = "cod_cnis_dn")
	private String codCnisDn;

	@Column(name = "cod_cnis_nis")
	private String codCnisNis;

	@Column(name = "cod_cnis_obito")
	private String codCnisObito;

	@Column(name = "cod_cpf_cancelado")
	private String codCpfCancelado;

	@Column(name = "cod_cpf_dn")
	private String codCpfDn;

	@Column(name = "cod_cpf_inv")
	private String codCpfInv;

	@Column(name = "cod_cpf_nao_consta")
	private String codCpfNaoConsta;

	@Column(name = "cod_cpf_nome")
	private String codCpfNome;

	@Column(name = "cod_cpf_nulo")
	private String codCpfNulo;

	@Column(name = "cod_cpf_suspenso")
	private String codCpfSuspenso;

	@Column(name = "cod_dn_inv")
	private String codDnInv;

	@Column(name = "cod_nis_inv")
	private String codNisInv;

	@Column(name = "cod_nome_inv")
	private String codNomeInv;

	@Column(name = "cod_orientacao_cpf")
	private String codOrientacaoCpf;

	@Column(name = "cod_orientacao_nis")
	private String codOrientacaoNis;

	@Column(name = "dat_atualizacao")
	@Temporal(TemporalType.TIMESTAMP)
	private Date datAtualizacao;

	@Column(name = "dsc_municipio")
	private String dscMunicipio;

	@Column(name = "flg_arquivo_gerado")
	private Boolean flgArquivoGerado;

	@Column(name = "tipo_arquivo")
	private String tipoArquivo;

	@Id
	@Column(name = "isn_orgao")
	private Long isnOrgao;

	@Column(name = "isn_pessoa")
	private Long isnPessoa;

	@Column(name = "nom_unidade_exercicio")
	private String nomUnidadeExercicio;

	@Column(name = "status")
	private Integer status;

	@Id
	@Column(name = "tipo_vinculo")
	private String tipoVinculo;

	public Long getIsnQualificacaoCadastral() {
		return isnQualificacaoCadastral;
	}

	public void setIsnQualificacaoCadastral(Long isnQualificacaoCadastral) {
		this.isnQualificacaoCadastral = isnQualificacaoCadastral;
	}

	public String getAgrupamento() {
		return agrupamento;
	}

	public void setAgrupamento(String agrupamento) {
		this.agrupamento = agrupamento;
	}

	public String getCodCnisCpf() {
		return codCnisCpf;
	}

	public void setCodCnisCpf(String codCnisCpf) {
		this.codCnisCpf = codCnisCpf;
	}

	public String getCodCnisCpfNaoInf() {
		return codCnisCpfNaoInf;
	}

	public void setCodCnisCpfNaoInf(String codCnisCpfNaoInf) {
		this.codCnisCpfNaoInf = codCnisCpfNaoInf;
	}

	public String getCodCnisDn() {
		return codCnisDn;
	}

	public void setCodCnisDn(String codCnisDn) {
		this.codCnisDn = codCnisDn;
	}

	public String getCodCnisNis() {
		return codCnisNis;
	}

	public void setCodCnisNis(String codCnisNis) {
		this.codCnisNis = codCnisNis;
	}

	public String getCodCnisObito() {
		return codCnisObito;
	}

	public void setCodCnisObito(String codCnisObito) {
		this.codCnisObito = codCnisObito;
	}

	public String getCodCpfCancelado() {
		return codCpfCancelado;
	}

	public void setCodCpfCancelado(String codCpfCancelado) {
		this.codCpfCancelado = codCpfCancelado;
	}

	public String getCodCpfDn() {
		return codCpfDn;
	}

	public void setCodCpfDn(String codCpfDn) {
		this.codCpfDn = codCpfDn;
	}

	public String getCodCpfInv() {
		return codCpfInv;
	}

	public void setCodCpfInv(String codCpfInv) {
		this.codCpfInv = codCpfInv;
	}

	public String getCodCpfNaoConsta() {
		return codCpfNaoConsta;
	}

	public void setCodCpfNaoConsta(String codCpfNaoConsta) {
		this.codCpfNaoConsta = codCpfNaoConsta;
	}

	public String getCodCpfNome() {
		return codCpfNome;
	}

	public void setCodCpfNome(String codCpfNome) {
		this.codCpfNome = codCpfNome;
	}

	public String getCodCpfNulo() {
		return codCpfNulo;
	}

	public void setCodCpfNulo(String codCpfNulo) {
		this.codCpfNulo = codCpfNulo;
	}

	public String getCodCpfSuspenso() {
		return codCpfSuspenso;
	}

	public void setCodCpfSuspenso(String codCpfSuspenso) {
		this.codCpfSuspenso = codCpfSuspenso;
	}

	public String getCodDnInv() {
		return codDnInv;
	}

	public void setCodDnInv(String codDnInv) {
		this.codDnInv = codDnInv;
	}

	public String getCodNisInv() {
		return codNisInv;
	}

	public void setCodNisInv(String codNisInv) {
		this.codNisInv = codNisInv;
	}

	public String getCodNomeInv() {
		return codNomeInv;
	}

	public void setCodNomeInv(String codNomeInv) {
		this.codNomeInv = codNomeInv;
	}

	public String getCodOrientacaoCpf() {
		return codOrientacaoCpf;
	}

	public void setCodOrientacaoCpf(String codOrientacaoCpf) {
		this.codOrientacaoCpf = codOrientacaoCpf;
	}

	public String getCodOrientacaoNis() {
		return codOrientacaoNis;
	}

	public void setCodOrientacaoNis(String codOrientacaoNis) {
		this.codOrientacaoNis = codOrientacaoNis;
	}

	public Date getDatAtualizacao() {
		return datAtualizacao;
	}

	public void setDatAtualizacao(Date datAtualizacao) {
		this.datAtualizacao = datAtualizacao;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getDscMunicipio() {
		return dscMunicipio;
	}

	public void setDscMunicipio(String dscMunicipio) {
		this.dscMunicipio = dscMunicipio;
	}

	public Boolean getFlgArquivoGerado() {
		return flgArquivoGerado;
	}

	public void setFlgArquivoGerado(Boolean flgArquivoGerado) {
		this.flgArquivoGerado = flgArquivoGerado;
	}

	public String getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public Long getIsnOrgao() {
		return isnOrgao;
	}

	public void setIsnOrgao(Long isnOrgao) {
		this.isnOrgao = isnOrgao;
	}

	public Long getIsnPessoa() {
		return isnPessoa;
	}

	public void setIsnPessoa(Long isnPessoa) {
		this.isnPessoa = isnPessoa;
	}

	public String getNomUnidadeExercicio() {
		return nomUnidadeExercicio;
	}

	public void setNomUnidadeExercicio(String nomUnidadeExercicio) {
		this.nomUnidadeExercicio = nomUnidadeExercicio;
	}

	public String getNumCpf() {
		return numCpf;
	}

	public void setNumCpf(String numCpf) {
		this.numCpf = numCpf;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTipoVinculo() {
		return tipoVinculo;
	}

	public void setTipoVinculo(String tipoVinculo) {
		this.tipoVinculo = tipoVinculo;
	}

	public String getTxtNomFuncionario() {
		return txtNomFuncionario;
	}

	public void setTxtNomFuncionario(String txtNomFuncionario) {
		this.txtNomFuncionario = txtNomFuncionario;
	}

	public String getTxtPisPasep() {
		return txtPisPasep;
	}

	public void setTxtPisPasep(String txtPisPasep) {
		this.txtPisPasep = txtPisPasep;
	}

	public Long getId() {
		return isnQualificacaoCadastral + isnOrgao;
	}

	public void setId(Long isnQualificacaoCadastral) {
		this.isnQualificacaoCadastral = isnQualificacaoCadastral + isnOrgao + TipoVinculo.valueOf(tipoVinculo).toString().length();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ViQualificador) {
			return ((ViQualificador) obj).getNumCpf().equals(numCpf);
		}
		return false;
	}

	public static class PrimaryKey implements Serializable {

		private Long isnOrgao;
		private Long isnQualificacaoCadastral;
		private String tipoVinculo;

		public PrimaryKey() {
		}

		public PrimaryKey(Long isnQualificacaoCadastral, Long isnOrgao, String tipoVinculo) {
			this.isnQualificacaoCadastral = isnQualificacaoCadastral;
			this.isnOrgao = isnOrgao;
			this.tipoVinculo = tipoVinculo;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof PrimaryKey) {
				PrimaryKey pk = (PrimaryKey) obj;
				return pk.getIsnQualificacaoCadastral().equals(isnQualificacaoCadastral) && pk.getIsnOrgao().equals(isnOrgao) && pk.getTipoVinculo().equals(tipoVinculo);
			}
			return false;
		}

		/*
		 * @Override public int hashCode() { return super.hashCode(); }
		 */

		public Long getIsnOrgao() {
			return isnOrgao;
		}

		public void setIsnOrgao(Long isnOrgao) {
			this.isnOrgao = isnOrgao;
		}

		public Long getIsnQualificacaoCadastral() {
			return isnQualificacaoCadastral;
		}

		public void setIsnQualificacaoCadastral(Long isnQualificacaoCadastral) {
			this.isnQualificacaoCadastral = isnQualificacaoCadastral;
		}

		public String getTipoVinculo() {
			return tipoVinculo;
		}

		public void setTipoVinculo(String tipoVinculo) {
			this.tipoVinculo = tipoVinculo;
		}

	}

}
