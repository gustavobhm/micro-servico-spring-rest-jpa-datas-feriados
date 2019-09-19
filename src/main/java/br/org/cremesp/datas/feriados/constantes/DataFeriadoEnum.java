package br.org.cremesp.datas.feriados.constantes;

public enum DataFeriadoEnum {

	MSG_FERIADO_FIND_ERRO("Feriado não encontrado!"), //
	;

	private String texto;

	private DataFeriadoEnum(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}
}
