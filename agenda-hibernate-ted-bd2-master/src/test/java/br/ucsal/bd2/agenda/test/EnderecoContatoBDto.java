package br.ucsal.bd2.agenda.test;

import br.ucsal.bd2.agenda.domain.Cidade;
import br.ucsal.bd2.agenda.domain.Estado;

public class EnderecoContatoBDto {

	private String logradouro;
	private Estado estado;
	private Cidade cidade;

	public EnderecoContatoBDto(String logradouro, Estado estado, Cidade cidade) {
		super();
		this.logradouro = logradouro;
		this.estado = estado;
		this.cidade = cidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
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
