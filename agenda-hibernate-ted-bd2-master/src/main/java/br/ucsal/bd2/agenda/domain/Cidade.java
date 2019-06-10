package br.ucsal.bd2.agenda.domain;

import javax.persistence.*;
import javax.persistence.ManyToOne;


//String sigla	- char(3)	- not null
//String nome	- varchar(40)	- not null
//Estado estado	- char(2)	- not null

@Entity(name = "tab_cidade")
public class Cidade {

	@Id
	@Column(columnDefinition = "char(3)")
	private String sigla;

	@Column(length = 45, nullable = false)
	private String nome;
	@JoinColumn(foreignKey = @ForeignKey(name="fk_estado_cidade"))
	@ManyToOne
	private Estado estado; 


	public Cidade(String sigla, String nome, Estado estado) {
		super();
		this.sigla = sigla;
		this.nome = nome;
		this.estado = estado;
	}

	public Cidade() {

	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
