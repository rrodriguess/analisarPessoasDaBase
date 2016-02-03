package br.org.cap.models;

public class Planilha {

	private String idPessoa;
	private String documento;
	private String nome;
	private String tipoPesquisa;
	private String motivo;
	
	public Planilha() {}

	public Planilha(String idPessoa, String documento, String nome, String tipoPesquisa) {
		this.idPessoa = idPessoa;
		this.documento = documento;
		this.nome = nome;
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(String idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}	

}
