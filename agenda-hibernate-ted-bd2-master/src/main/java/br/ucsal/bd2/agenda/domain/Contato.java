package br.ucsal.bd2.agenda.domain;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//String nome		-varchar(40) 	not null
//String telefones 	-char(11)	not null
//Endereco enderecos	-String		not null

@Entity(name = "tab_contato")
@Table
public class Contato {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(columnDefinition = "varchar", length = 40, nullable = false)
	private String nome;

	@ElementCollection
	@CollectionTable(name = "tab_contato_telefone", joinColumns = @JoinColumn(name = "id"))
	private List<String> telefones;

	@ElementCollection
	@CollectionTable(name = "tab_contato_emails", joinColumns = @JoinColumn(name = "id"))
	private List<String> emails;

	@ManyToOne
	private Endereco endereco;

	public Contato(String nome, List<String> telefones, List<String> emails, Endereco endereco) {
		super();
		this.nome = nome;
		this.telefones = telefones;
		this.endereco = endereco;
		this.emails = emails;
	}

	public Contato() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	@Override
	public String toString() {
		return "Contato [id=" + id + ", nome=" + nome + ", telefones=" + telefones + ", emails=" + emails
				+ ", endereco=" + endereco + "]";
	}
	
	
}
