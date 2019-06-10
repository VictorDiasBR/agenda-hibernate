package br.ucsal.bd2.agenda.test;

import br.ucsal.bd2.agenda.domain.Contato;

public class ContatosCidadeBDto {

	private Contato contato;

	private String telefones;

	public String getTelefones() {
		return telefones;
	}

	public void setTelefones(String telefones) {
		this.telefones = telefones;
	}

	public ContatosCidadeBDto(Contato contato, String telefones) {
		super();
		this.contato = contato;
		this.telefones = telefones;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

}
