package br.com.qualificador.util;

import br.com.qualificador.model.ViQualificador;

public class OrientacoesCadastrais {

	public static String Inconsistencias(ViQualificador qualificacao) {

		String incosistencias = "";
		if (qualificacao.getCodNisInv() != null && qualificacao.getCodNisInv().equals("1")) {
			incosistencias += "NIS inválido/";
		}

		if (qualificacao.getCodCpfInv() != null && qualificacao.getCodCpfInv().equals("1")) {
			incosistencias += "CPF inválido/";
		}

		if (qualificacao.getCodNomeInv() != null && qualificacao.getCodNomeInv().equals("1")) {
			incosistencias += "NOME inválido/";
		}

		if (qualificacao.getCodDnInv() != null && qualificacao.getCodDnInv().equals("1")) {
			incosistencias += "DATA NASCIMENTO inválida/";
		}

		if (qualificacao.getCodCnisNis() != null && qualificacao.getCodCnisNis().equals("1")) {
			incosistencias += "NIS inconsistente/";
		}

		if (qualificacao.getCodCnisDn() != null && qualificacao.getCodCnisDn().equals("1")) {
			incosistencias += "Data de Nascimento informada diverge da existente no CNIS/";
		}

		if (qualificacao.getCodCnisObito() != null && qualificacao.getCodCnisObito().equals("1")) {
			incosistencias += "NIS com óbito no CNIS/";
		}

		if (qualificacao.getCodCnisCpf() != null && qualificacao.getCodCnisCpf().equals("1")) {
			incosistencias += "CPF informado diverge do existente no CNIS/";
		}

		if (qualificacao.getCodCnisCpfNaoInf() != null && qualificacao.getCodCnisCpfNaoInf().equals("1")) {
			incosistencias += "CPF não preenchido no CNIS/";
		}

		if (qualificacao.getCodCpfNaoConsta() != null && qualificacao.getCodCpfNaoConsta().equals("1")) {
			incosistencias += "CPF informado não consta no Cadastro CPF/";
		}

		if (qualificacao.getCodCpfNulo() != null && qualificacao.getCodCpfNulo().equals("1")) {
			incosistencias += "CPF informado NULO no Cadastro CPF/";
		}

		if (qualificacao.getCodCpfCancelado() != null && qualificacao.getCodCpfCancelado().equals("1")) {
			incosistencias += "CPF informado CANCELADO no Cadastro CPF/";
		}

		if (qualificacao.getCodCpfSuspenso() != null && qualificacao.getCodCpfSuspenso().equals("1")) {
			incosistencias += "CPF informado SUSPENSO no Cadastro CPF/";
		}

		if (qualificacao.getCodCpfDn() != null && qualificacao.getCodCpfDn().equals("1")) {
			incosistencias += "Data de Nascimento informada diverge da existente no Cadastro CPF/";
		}

		if (qualificacao.getCodCpfNome() != null && qualificacao.getCodCpfNome().startsWith("1")) {
			incosistencias += "NOME informado diverge do existente no Cadastro CPF" + qualificacao.getCodCpfNome().substring(2) + "/";
		}

		while (incosistencias.endsWith("/")) {
			incosistencias = incosistencias.substring(0, incosistencias.length() - 1);
		}
		return incosistencias;
	}

	public static String orientacaoCPF(String input) {
		switch (input) {
			case "0":
				return "";
			case "1":
				return "Procurar Conveniadas da RFB: Correios, Banco do Brasil";
			default:
				return "";
		}
	}

	public static String orientacaoNIS(String input) {
		switch (input) {
			case "0":
				return "";
			case "1":
				return "Atualizar NIS no INSS - Ligar 135 para agendar atendimento";
			case "2":
				return "Atualizar o Cadastro NIS da CAIXA - Utilizar Cadastro NIS Empresa pelo Conectividade Social ou uma agência da Caixa";
			case "3":
				return "Atualizar o Cadastro NIS em uma agência do Banco do Brasil";
			default:
				return "";
		}
	}

}
