package br.com.qualificador.constants;

public enum StatusQualificacaoCadastral {

	QUALIFICADO("Qualificado"), AGUARDANDO_QUALIFICACAO("Aguardando Qualificação"), NAO_QUALIFICADO("Não Qualificado");

	private String descricao;

	private StatusQualificacaoCadastral(String descricao) {
		this.descricao = descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static String getStatusById(int id) {
		switch (id) {
			case 0:
				return "QUALIFICADO";
			case 1:
				return "AGUARDANDO QUALIFICAÇÃO";
			case 2:
				return "NÃO QUALIFICADO";
			default:
				throw new IllegalArgumentException();
		}
	}
}
