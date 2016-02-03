package br.org.cap.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.org.cap.models.Planilha;

public class LeitorDeArquivoXlsx {
	
	public List<Planilha> geraLista() {
		
		List<Planilha> lista = new ArrayList<>();
		
		try{			
			FileInputStream fis = new FileInputStream(new File("../analisarPessoasDaBase/arquivo/Tabela_de_AB _JDE_ PD.xlsx"));
//			FileInputStream fis = new FileInputStream(new File("../analisarPessoasDaBase/arquivo/Tabela.xlsx"));
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator();
			
			for (Row row : sheet) {				
				verificaCelulasSemDados(row);				
				if (row.getRowNum() > 0) {
					Planilha planilha = new Planilha();	
					for(Cell cell : row) {						
						validaCelulas(formulaEvaluator, planilha, cell);
						if (cell.getColumnIndex() > 8) {
							break;						
						}
					}
					lista.add(planilha);
				}
			}
//			wb.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao acessar arquivo");
		}
		
		return lista;
	}

	private void validaCelulas(FormulaEvaluator formulaEvaluator, Planilha planilha, Cell cell) {
		if(cell.getColumnIndex() == 0) {
			planilha.setIdPessoa(verificaTipoDaCelula(formulaEvaluator, cell));							
		}						
		if(cell.getColumnIndex() == 2) {
			planilha.setDocumento(verificaTipoDaCelula(formulaEvaluator, cell));						
		}						
		if(cell.getColumnIndex() == 3) {
			planilha.setNome(verificaTipoDaCelula(formulaEvaluator, cell));						
		}						
		if(cell.getColumnIndex() == 8) {
			planilha.setTipoPesquisa(verificaTipoDaCelula(formulaEvaluator, cell));	
		}
	}

	private void verificaCelulasSemDados(Row row) {
		for (int i = 0; i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
			if (i > 8) break;
		}
	}
	
	private String verificaTipoDaCelula(FormulaEvaluator formulaEvaluator, Cell cell) {
        String valorCelula = " ";
        if(cell.toString() != "") {
            switch (formulaEvaluator.evaluate(cell).getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    Long valorLong = (long) cell.getNumericCellValue();
                    valorCelula = valorLong.toString();
                    break;
                case Cell.CELL_TYPE_STRING:
                    valorCelula = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    valorCelula = cell.getStringCellValue();
                    break;
            }

            return valorCelula;
        } else
            return valorCelula;
    }

}
