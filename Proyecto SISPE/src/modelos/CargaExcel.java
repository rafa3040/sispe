package modelos;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class CargaExcel {
	
	private String[][] datosExtraidos;
	
	public CargaExcel() {

	}
	
	public void cargarArchivo(Workbook libroExcel) throws InvalidFormatException, IOException{
		Sheet hoja = libroExcel.getSheetAt(0);
		datosExtraidos=new String[hoja.getLastRowNum()][15];
		Row fila;
		Cell celda;
		for (int i = 0; i < datosExtraidos.length; i++) {
			fila=hoja.getRow(i);
			for (int j = 0; j < datosExtraidos[0].length; j++) {
				celda=fila.getCell(j);
				datosExtraidos[i][j]=celda.getStringCellValue();
			}
		}
		for (String[] strings : datosExtraidos) {
			for (String string : strings) {
				System.out.println(string);
			}
			System.out.println();
		}
	}

}
