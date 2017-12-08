package br.com.qualificador.constants;

public enum TipoVinculo {

	PENSIONISTA("PENSIONISTA"), PENSAO_ALIMENTO("PENSAO ALIMENTO"), BOLSISTA("BOLSISTA"), APOSENTADO("APOSENTADO"), ATIVO("ATIVO");

	private String vinculo;

	private TipoVinculo(String vinculo) {
		this.vinculo = vinculo;
	}

	@Override
	public String toString() {
		return this.vinculo;
	}

}
