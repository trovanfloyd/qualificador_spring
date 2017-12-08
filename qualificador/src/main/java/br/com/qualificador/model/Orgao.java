package br.com.qualificador.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "vi_orgao_folha", schema = "db_entidade")
@Inheritance(strategy = InheritanceType.JOINED)
public class Orgao implements Serializable {

	private static final long serialVersionUID = -7081771926791930814L;

	@Id
	@Column(name = "isn_orgao")
	private Long isnOrgao;

	@Column(name = "isn_guardiao")
	private Integer codGuardiao;

	@Column(name = "flg_em_folha")
	private Boolean ativoEmFolha;

	@Column(name = "num_cep")
	private String cep;

	@Column(name = "num_cnae")
	private String cnae;

	@Column(name = "num_cnpj")
	private String cnpj;

	@Column(name = "num_telefone")
	private String telefoneDoContato;

	@Column(name = "cod_poder")
	private String codigoPoder;

	@Column(name = "isn_folha")
	private Long codOrgaoSige;

	@Column(name = "num_cpf_responsavel")
	private String cpfResponsavel;

	@Column(name = "dsc_email")
	private String emailPessoaContato;

	@Temporal(TemporalType.DATE)
	@Column(name = "dat_ini", insertable = false, updatable = false)
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "dat_fim")
	private Date dataTermino;

	@Column(name = "dsc_orgao")
	private String descricao;

	@Column(name = "dsc_endereco")
	private String endereco;

	@Column(name = "dsc_municipio")
	private String municipio;

	@Column(name = "num_fpas")
	private String fpas;

	@Column(name = "dsc_pessoa_contato")
	private String nomePessoaContato;

	@Column(name = "isn_regime")
	private Integer regimeJuridico;

	@Transient
	private Date dataUltimoAumentoVpcAutomatico;

	@Transient
	private BigDecimal percentualAumento;
	@Column(name = "dsc_sigla")
	private String sigla;

	public Orgao() {

	}

	public Long getIsnOrgao() {
		return isnOrgao;
	}

	public void setIsnOrgao(Long isnOrgao) {
		this.isnOrgao = isnOrgao;
	}

	public Boolean getAtivoEmFolha() {
		return ativoEmFolha;
	}

	public void setAtivoEmFolha(Boolean ativoEmFolha) {
		this.ativoEmFolha = ativoEmFolha;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCodigoPoder() {
		return codigoPoder;
	}

	public void setCodigoPoder(String codigoPoder) {
		this.codigoPoder = codigoPoder;
	}

	public Long getCodOrgaoSige() {
		return codOrgaoSige;
	}

	@Transient
	public String getCodOrgaoSigeFormatado() {
		return new DecimalFormat("000").format(codOrgaoSige);
	}

	public void setCodOrgaoSige(Long codOrgaoSige) {
		this.codOrgaoSige = codOrgaoSige;
	}

	public String getCpfResponsavel() {
		return cpfResponsavel;
	}

	public void setCpfResponsavel(String cpfResponsavel) {
		this.cpfResponsavel = cpfResponsavel;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getFpas() {
		return fpas;
	}

	public void setFpas(String fpas) {
		this.fpas = fpas;
	}

	public String getNomePessoaContato() {
		return nomePessoaContato;
	}

	public void setNomePessoaContato(String nomePessoaContato) {
		this.nomePessoaContato = nomePessoaContato;
	}

	public Integer getRegimeJuridico() {
		return regimeJuridico;
	}

	public void setRegimeJuridico(Integer regimeJuridico) {
		this.regimeJuridico = regimeJuridico;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getSigla() {
		return this.sigla;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricaoCustomizada() {
		if (codOrgaoSige == null) {
			return sigla;
		} else {
			String codOrgao = String.format("%03d", codOrgaoSige);
			return codOrgao + " - " + sigla;
		}
	}

	public String getDescricaoCustomizadaCompleta() {
		if (codOrgaoSige == null) {
			return sigla;
		} else {
			String codOrgao = String.format("%03d", codOrgaoSige);
			return codOrgao + " - " + descricao;
		}
	}

	public String getSiglaDescricaoCustomizadaCompleta() {
		if (codOrgaoSige == null) {
			return sigla;
		} else {
			String codOrgao = String.format("%03d", codOrgaoSige);
			return codOrgao + " - " + sigla + " - " + descricao;
		}
	}

	@Override
	public String toString() {
		return this.getDescricaoCustomizada();
	}

	public String getUf() {
		return "CE";
	}

	public String getTelefoneDoContato() {
		return telefoneDoContato;
	}

	public void setTelefoneDoContato(String telefoneDoContato) {
		this.telefoneDoContato = telefoneDoContato;
	}

	public String getEmailPessoaContato() {
		return emailPessoaContato;
	}

	public void setEmailPessoaContato(String emailPessoaContato) {
		this.emailPessoaContato = emailPessoaContato;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Integer getCodGuardiao() {
		return codGuardiao;
	}

	public void setCodGuardiao(Integer codGuardiao) {
		this.codGuardiao = codGuardiao;
	}

	public Date getDataUltimoAumentoVpcAutomatico() {
		return dataUltimoAumentoVpcAutomatico;
	}

	public void setDataUltimoAumentoVpcAutomatico(Date dataUltimoAumentoVpcAutomatico) {
		this.dataUltimoAumentoVpcAutomatico = dataUltimoAumentoVpcAutomatico;
	}

	public BigDecimal getPercentualAumento() {
		return percentualAumento;
	}

	public void setPercentualAumento(BigDecimal percentualAumento) {
		this.percentualAumento = percentualAumento;
	}

	public Long getId() {
		return isnOrgao;
	}

	public void setId(Long isnOrgao) {
		this.isnOrgao = isnOrgao;
	}

}
