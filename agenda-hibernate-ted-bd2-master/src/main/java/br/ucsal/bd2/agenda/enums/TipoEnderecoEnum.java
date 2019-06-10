package br.ucsal.bd2.agenda.enums;


public enum TipoEnderecoEnum {

	COMERCIAL(1), RESIDENCIAL(2);

	private Integer codigo;

	private TipoEnderecoEnum(Integer codTipoEnum) {
		this.codigo = codTipoEnum;
	}

	public Integer getCodigo() {
		return codigo;
	}
	
	public static TipoEnderecoEnum valueOfCodigo(Integer codigo) {
		for (TipoEnderecoEnum tipoEnderecoEnum : values()) {
			if (tipoEnderecoEnum.getCodigo().equals(codigo)) {
				return tipoEnderecoEnum;
			}
		}
		throw new IllegalArgumentException("Codigo inválido" + codigo);
	}

}
