package br.com.qualificador.util;

import br.com.qualificador.model.ViQualificador;

public class Util {

	public static ViQualificador setUpFiltersQuery(String nome, String cpf, String nis, String data) {

		ViQualificador qualificacao = new ViQualificador();

		if (nome.equals("1")) {
			qualificacao.setCodNomeInv("1");
			qualificacao.setCodCpfNome("0");

		} else {
			qualificacao.setCodNomeInv("0");
			qualificacao.setCodCpfNome("1");

		}

		if (nis.equals("1")) {
			qualificacao.setCodNisInv("1");
			qualificacao.setCodCnisNis("1");
			qualificacao.setCodCnisObito("1");

		} else {
			qualificacao.setCodNisInv("0");
			qualificacao.setCodCnisNis("0");
			qualificacao.setCodCnisObito("0");

		}

		if (data.equals("1")) {
			qualificacao.setCodDnInv("1");
			qualificacao.setCodCnisDn("1");
			qualificacao.setCodCpfDn("1");

		} else {
			qualificacao.setCodDnInv("0");
			qualificacao.setCodCnisDn("0");
			qualificacao.setCodCpfDn("0");

		}

		if (cpf.equals("1")) {
			qualificacao.setCodCpfInv("1");
			qualificacao.setCodCnisCpf("1");
			qualificacao.setCodCnisCpfNaoInf("1");
			qualificacao.setCodCpfNaoConsta("1");
			qualificacao.setCodCpfNulo("1");
			qualificacao.setCodCpfCancelado("1");
			qualificacao.setCodCpfSuspenso("1");

		} else {
			qualificacao.setCodCpfInv("0");
			qualificacao.setCodCnisCpf("0");
			qualificacao.setCodCnisCpfNaoInf("0");
			qualificacao.setCodCpfNaoConsta("0");
			qualificacao.setCodCpfNulo("0");
			qualificacao.setCodCpfCancelado("0");
			qualificacao.setCodCpfSuspenso("0");

		}
		return qualificacao;
	}

}
