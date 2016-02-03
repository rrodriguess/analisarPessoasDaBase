package br.org.cap.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.org.cap.models.GrlPessoaJuridica;

public class GrlPessoaJuridicaDao {
	private EntityManager manager;
	
	public GrlPessoaJuridicaDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public GrlPessoaJuridica busca(Long id) {
		GrlPessoaJuridica pJuridica = new GrlPessoaJuridica();
		try{
			Query query = manager.createQuery(
					"select j from GrlPessoaJuridica j where j.idPessoa =:id");
			query.setParameter("id", id);
			
			pJuridica = (GrlPessoaJuridica) query.getSingleResult();
		} catch (Exception e) {
//			e.printStackTrace();
			System.err.println("IdPessoa: " + id + " não cadastrado em GrlPessoaJuridica");
		}
		
		return pJuridica;
	}
}
