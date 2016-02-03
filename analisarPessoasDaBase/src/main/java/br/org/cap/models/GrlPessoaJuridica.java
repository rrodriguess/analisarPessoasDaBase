package br.org.cap.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "GRL_PESSOA_JURIDICA", schema = "CAP")
public class GrlPessoaJuridica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_PESSOA")
    private Long idPessoa;   

    @Column(name = "CNPJ")
    private Long cnpj; 
    
	//bi-directional one-to-one association to GrlPessoa
	@OneToOne
	@JoinColumn(name="ID_PESSOA", nullable=false, insertable=false, updatable=false)
	private GrlPessoa grlPessoa;

    public GrlPessoaJuridica() {
    }

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public GrlPessoa getGrlPessoa() {
		return grlPessoa;
	}

	public void setGrlPessoa(GrlPessoa grlPessoa) {
		this.grlPessoa = grlPessoa;
	}

    
}
