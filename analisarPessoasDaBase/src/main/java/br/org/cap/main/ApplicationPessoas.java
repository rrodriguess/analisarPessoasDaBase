package br.org.cap.main;

import java.util.List;

import br.org.cap.controllers.PessoasController;
import br.org.cap.models.Planilha;
import br.org.cap.utils.LeitorDeArquivoXlsx;

public class ApplicationPessoas {
	public static void main(String[] args) {
		LeitorDeArquivoXlsx leitor = new LeitorDeArquivoXlsx();
		List<Planilha> lista = leitor.geraLista();
		
	//	PessoasController pController = new PessoasController();
	//	pController.compararDadosDa(lista);
	}
}
