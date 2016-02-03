package br.org.cap.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="GRL_PESSOA", schema="CAP")
public class GrlPessoa implements Serializable {
	private static final Long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GrlPessoaSequence")
	@SequenceGenerator(name = "GrlPessoaSequence", sequenceName = "CAP.GRL_PESSOA_SEQ", allocationSize = 1)
	@Id
	@Column(name="ID_PESSOA")
	private Long idPessoa;

	@Column(name="ID_CATEG_JDE")
	private String idCategJde;

	@Column(name="NOME_PESQUISA")
	private String nomePesquisa;
	
	@OneToOne(mappedBy="grlPessoa")
	private GrlPessoaFisica grlPessoaFisica;

	public GrlPessoa() {
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getIdCategJde() {
		return idCategJde;
	}

	public void setIdCategJde(String idCategJde) {
		this.idCategJde = idCategJde;
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public GrlPessoaFisica getGrlPessoaFisica() {
		return grlPessoaFisica;
	}

	public void setGrlPessoaFisica(GrlPessoaFisica grlPessoaFisica) {
		this.grlPessoaFisica = grlPessoaFisica;
	}
	

}	
