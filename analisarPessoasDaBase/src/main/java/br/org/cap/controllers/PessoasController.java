package br.org.cap.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import br.org.cap.dao.GrlPessoaDao;
import br.org.cap.dao.GrlPessoaFisicaDao;
import br.org.cap.dao.GrlPessoaJuridicaDao;
import br.org.cap.models.GrlPessoa;
import br.org.cap.models.GrlPessoaFisica;
import br.org.cap.models.GrlPessoaJuridica;
import br.org.cap.models.Planilha;
import br.org.cap.utils.JPAUtil;

public class PessoasController {
	EntityManager manager = new JPAUtil().getEntityManager();
	GrlPessoaFisicaDao pessoaFisicaDao = new GrlPessoaFisicaDao(manager);
	GrlPessoaJuridicaDao pessoaJuridicaDao = new GrlPessoaJuridicaDao(manager);
	GrlPessoaDao pessoaDao = new GrlPessoaDao(manager);

	public void compararDadosDa(List<Planilha> lista) {
		resetLog();
		criaCabecalho();

		for (Planilha planilha : lista) {
			GrlPessoaFisica pFisica = pessoaFisicaDao.busca(Long.parseLong(planilha.getIdPessoa()));
			GrlPessoaJuridica pJuridica = new GrlPessoaJuridica();
			GrlPessoa pessoa = new GrlPessoa();

			if (pFisica.getIdPessoa() != null) {
				if (pFisica.getCpf() == null) {
					planilha.setMotivo("CPF não cadastrado");
					gravaLog(planilha);
				} else {
					comparaPessoaFisica(planilha, pFisica);
				}
			} else {
				pJuridica = pessoaJuridicaDao.busca(Long.parseLong(planilha.getIdPessoa()));

				if (pJuridica.getIdPessoa() != null) {
					if (pJuridica.getCnpj() != null) {
						comparaPessoaJuridica(planilha, pJuridica);
					} else {
						planilha.setMotivo("CNPJ não cadastrado");
						gravaLog(planilha);
					}
				} else {
					pessoa = pessoaDao.busca(Long.parseLong(planilha.getIdPessoa()));

					if (pessoa.getIdPessoa() != null) {
						planilha.setMotivo("Sem cadastro em GrlPessoaFisica ou GrlPessoaJuridica");
						gravaLog(planilha);
					} else {
						planilha.setMotivo("IdPessoa não encontrado");
						gravaLog(planilha);
					}
				}
			}
		}
	}

	private void criaCabecalho() {
		String path = "../analisarPessoasDaBase/arquivo/dadosDivergentes.txt";

		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter(path, true));
			bf.append("ID_PESSOA;NOME_PESQUISA;CPF/CNPJ;MOTIVO");
			bf.newLine();
			bf.close();
			System.out.println("Criado arquivo de Log");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Não gravou arquivo");
		}
	}

	private void gravaLog(Planilha planilha) {
		String path = "../analisarPessoasDaBase/arquivo/dadosDivergentes.txt";

		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter(path, true));
			bf.append(planilha.getIdPessoa() + ";" + planilha.getNome() + ";" + planilha.getDocumento() + ";"
					+ planilha.getMotivo());
			bf.newLine();
			bf.close();
			System.out.println("Gravado dado em arquivo");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Não gravou arquivo");
		}
	}

	private void comparaPessoaFisica(Planilha planilha, GrlPessoaFisica pFisica) {
		Long cpf = Long.parseLong(planilha.getDocumento());

		if (!planilha.getNome().equals(pFisica.getGrlPessoa().getNomePesquisa())) {
			planilha.setMotivo("Nomes divergentes");
			gravaLog(planilha);
		} else if (!cpf.equals(pFisica.getCpf())) {
			planilha.setMotivo("Cpf's diferentes");
			gravaLog(planilha);
		} else if (!planilha.getTipoPesquisa().equals(pFisica.getGrlPessoa().getIdCategJde())) {
			planilha.setMotivo("Categorias diferentes");
			gravaLog(planilha);
		} else {
			System.out.println("Cadastro ok!");
		}
	}

	private void comparaPessoaJuridica(Planilha planilha, GrlPessoaJuridica pJuridica) {
		Long cnpj = Long.parseLong(planilha.getDocumento());
		
		if (!planilha.getNome().equals(pJuridica.getGrlPessoa().getNomePesquisa())) {
			planilha.setMotivo("Nomes divergentes");
			gravaLog(planilha);
		} else if (!cnpj.equals(pJuridica.getCnpj())) {
			planilha.setMotivo("Cnpj's diferentes");
			gravaLog(planilha);
		} else if (!planilha.getTipoPesquisa().equals(pJuridica.getGrlPessoa().getIdCategJde())) {
			planilha.setMotivo("Categorias diferentes");
			gravaLog(planilha);
		} else {
			System.out.println("Cadastro ok!");
		}
	}

	private void resetLog() {
		File file = new File("../analisarPessoasDaBase/arquivo/dadosDivergentes.txt");

		try {
			FileReader fr = new FileReader(file);
			BufferedReader bf = new BufferedReader(fr);
			bf.close();
			FileWriter fw = new FileWriter(file);
			fw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
