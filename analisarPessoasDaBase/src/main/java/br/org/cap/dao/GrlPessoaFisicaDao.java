package br.org.cap.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.org.cap.models.GrlPessoaFisica;

public class GrlPessoaFisicaDao {
	
	private EntityManager manager;
	
	public GrlPessoaFisicaDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public GrlPessoaFisica busca(Long id) {
		GrlPessoaFisica pfisica = new GrlPessoaFisica();
		try{
			Query query = manager.createQuery(
					"select f from GrlPessoaFisica f where f.idPessoa =:id");
					
			query.setParameter("id", id);
			
			pfisica = (GrlPessoaFisica) query.getSingleResult();
		} catch (Exception e) {
//			e.printStackTrace();
			System.err.println("IdPessoa: " + id + " não cadastrado em GrlPessoaFisica");
		}
		
		return pfisica;
	}
	
}
