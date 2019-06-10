package br.ucsal.bd2.agenda.test;

import br.ucsal.bd2.agenda.domain.Cidade;
import br.ucsal.bd2.agenda.domain.Estado;

public class CidadesEstadoBDto {

	private Estado estado;
	private Cidade cidade;

	public CidadesEstadoBDto(Estado estado, Cidade cidade) {
		super();
		this.estado = estado;
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
