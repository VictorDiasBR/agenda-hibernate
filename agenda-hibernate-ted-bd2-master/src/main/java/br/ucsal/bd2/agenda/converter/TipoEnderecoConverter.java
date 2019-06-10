package br.ucsal.bd2.agenda.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.ucsal.bd2.agenda.enums.TipoEnderecoEnum;

public class TipoEnderecoConverter implements AttributeConverter<TipoEnderecoEnum, Integer> {

	public Integer convertToDatabaseColumn(TipoEnderecoEnum tipoEnderecoEnum) {
		return tipoEnderecoEnum != null ? tipoEnderecoEnum.getCodigo() : null;
	}

	public TipoEnderecoEnum convertToEntityAttribute(Integer dbData) {
		return dbData != null ? TipoEnderecoEnum.valueOfCodigo(dbData) : null;
	}

}
