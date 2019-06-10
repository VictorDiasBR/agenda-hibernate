package br.ucsal.bd2.agenda.domain;

import javax.persistence.Column;

import javax.persistence.*;

import br.ucsal.bd2.agenda.converter.TipoEnderecoConverter;
import br.ucsal.bd2.agenda.enums.TipoEnderecoEnum;

// TipoEndereco 	- char(3)	- not null
// Estado estado	- char(2)	- not null
// String sigla	- char(2)	- not null
// String nome	- varchar(40)	- not null

@Entity(name="tab_endereco")
public class Endereco {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(length = 120, nullable = false)
	private String logradouro;
	@JoinColumn(name = "cidade", referencedColumnName = "sigla", nullable = false, foreignKey = @ForeignKey(name="fk_endereco_cidade"))
	@OneToOne
	private Cidade cidade; 
	@Column(name = "tipo_endereco",nullable = false)
	@Convert(converter = TipoEnderecoConverter.class)
	private TipoEnderecoEnum tipoEndereco;

	public Endereco( String logradouro, Cidade cidade, TipoEnderecoEnum tipoEndereco) {
		super();
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.tipoEndereco = tipoEndereco;
	}
	
	public Endereco() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}


	public TipoEnderecoEnum getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEnderecoEnum tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

}
