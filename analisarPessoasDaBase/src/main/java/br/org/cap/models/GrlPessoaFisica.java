package br.org.cap.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="GRL_PESSOA_FISICA", schema="CAP")
public class GrlPessoaFisica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PESSOA", unique=true, nullable=false, precision=10)
	private Long idPessoa;

	@Column(precision=11)
	private Long cpf;	

	//bi-directional one-to-one association to GrlPessoa
	@OneToOne
	@JoinColumn(name="ID_PESSOA", nullable=false, insertable=false, updatable=false)
	private GrlPessoa grlPessoa;

	public GrlPessoaFisica() {
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public GrlPessoa getGrlPessoa() {
		return grlPessoa;
	}

	public void setGrlPessoa(GrlPessoa grlPessoa) {
		this.grlPessoa = grlPessoa;
	}

	

}
