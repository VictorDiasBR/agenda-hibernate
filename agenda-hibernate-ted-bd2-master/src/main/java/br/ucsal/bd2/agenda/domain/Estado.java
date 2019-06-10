package br.ucsal.bd2.agenda.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//String sigla	- char(2)	- not null
//String nome	- varchar(40)	- not null

@Entity(name = "tab_estado")
public class Estado {

	@Id
	@Column(columnDefinition = "char(2)", nullable = false)
	private String sigla;
	
	@Column(length = 45, nullable = false)
	private String nome;
	
	@OneToMany(mappedBy = "estado")
	private List<Cidade> cidades;

	public Estado(String sigla, String nome) {
		super();
		this.sigla = sigla;
		this.nome = nome;
	}

	public Estado() {

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

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

}
