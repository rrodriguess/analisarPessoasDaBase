package br.org.cap.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.org.cap.models.GrlPessoa;

public class GrlPessoaDao {
	private EntityManager manager;

	public GrlPessoaDao(EntityManager manager) {
		this.manager = manager;
	}

	public GrlPessoa busca(Long id) {
		GrlPessoa pessoa = new GrlPessoa();
		try {
			Query query = manager.createQuery("select f from GrlPessoa f where f.idPessoa =:id");

			query.setParameter("id", id);

			pessoa = (GrlPessoa) query.getSingleResult();
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("IdPessoa: " + id + " não cadastrado em GrlPessoa");
		}

		return pessoa;
	}
}
