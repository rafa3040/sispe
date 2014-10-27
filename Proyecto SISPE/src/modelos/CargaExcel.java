package modelos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Clase que se encarga de extraer las hojas de vida
 * presentes en una hoja de Excel
 * @author Solutions Developers
 *
 */
public class CargaExcel {
	
	private ArrayList<HojaVida> hojasVidaExtraidas;
	private ArrayList<Integer> filasNoExtraidas;
	
	public CargaExcel() {
		hojasVidaExtraidas=new ArrayList<HojaVida>();
		filasNoExtraidas=new ArrayList<Integer>();
	}
	
	public ArrayList<HojaVida> getHojasVidaExtraidas() {
		return hojasVidaExtraidas;
	}

	public ArrayList<Integer> getFilasNoExtraidas() {
		return filasNoExtraidas;
	}

	public void cargarArchivo(Workbook libroExcel) throws InvalidFormatException, IOException{
		Sheet hoja = libroExcel.getSheetAt(0);
		int numeroFilas=hoja.getLastRowNum()+1;
		// Borra el ArrayList de hojas de vida extraídas y de filas corruptas
		hojasVidaExtraidas.clear();
		filasNoExtraidas.clear();
		
		long numeroIdentificacion;
		String nombrePersona, apellidoPersona;
		String tipoDocumento;
		Date fechaNacimiento;
		long telefono;
		String correoElectronico;
		String profesion, especializacion;
		Date inicioUno, finalUno;
		Date inicioDos, finalDos;
		Date inicioTres, finalTres;
		
		Row fila;
		for (int i = 0; i < numeroFilas; i++) {
			fila=hoja.getRow(i);
			// Extracción de datos de la persona de la hoja de vida
			try {
				numeroIdentificacion=extraerNumero(fila.getCell(0));
				nombrePersona=extraerTexto(fila.getCell(1));
				apellidoPersona=extraerTexto(fila.getCell(2));
				tipoDocumento=extraerTexto(fila.getCell(3));
				fechaNacimiento=extraerFecha(fila.getCell(4));
				telefono=extraerNumero(fila.getCell(5));
				correoElectronico=extraerTexto(fila.getCell(6));
				profesion=extraerTexto(fila.getCell(7));
				especializacion=extraerTexto(fila.getCell(8));
			} catch (Exception e) {
				filasNoExtraidas.add(i+1);
				continue;
			}
			// Creación objeto HojaVida
			HojaVida hojaVida=new HojaVida();
			// Inserción número de identificación y teléfono
			hojaVida.setNumeroIdentificacion(numeroIdentificacion);
			hojaVida.setTelefono(telefono);
			// Inserción nombres, apellidos y profesión
			if(nombrePersona.length()>0 && apellidoPersona.length()>0 && profesion.length()>0){
				hojaVida.setNombrePersona(nombrePersona);
				hojaVida.setApellidoPersona(apellidoPersona);
				hojaVida.setProfesion(profesion);
			} else {
				filasNoExtraidas.add(i+1);
				continue;
			}
			// Inserción tipo de documento
			if(TipoDocumento.valueOf(tipoDocumento)!=null){
				hojaVida.setTipoDocumento(TipoDocumento.valueOf(tipoDocumento));
			} else {
				filasNoExtraidas.add(i+1);
				continue;
			}
			// Inserción fecha de nacimiento
			Calendar fecha=Calendar.getInstance();
			fecha.setTime(fechaNacimiento);
			hojaVida.setFechaNacimiento(fecha);
			// Inserción correo electrónico y especialización
			hojaVida.setCorreoElectronico(correoElectronico);
			hojaVida.setEspecializacion(especializacion);
			// Extracción de los datos de las experiencias de la hoja de vida
			try {
				inicioUno=extraerFecha(fila.getCell(9));
				finalUno=extraerFecha(fila.getCell(10));
				if(inicioUno.before(finalUno)){
					Experiencia experiencia=new Experiencia();
					experiencia.establecerFechas(inicioUno, finalUno);
					experiencia.setHojaVida(hojaVida);
					hojaVida.getExperiencias().add(experiencia);
				}
			} catch (Exception e) {

			}
			try {
				inicioDos=extraerFecha(fila.getCell(11));
				finalDos=extraerFecha(fila.getCell(12));
				if(inicioDos.before(finalDos)){
					Experiencia experiencia=new Experiencia();
					experiencia.establecerFechas(inicioDos, finalDos);
					experiencia.setHojaVida(hojaVida);
					hojaVida.getExperiencias().add(experiencia);
				}
			} catch (Exception e) {

			}
			try {
				inicioTres=extraerFecha(fila.getCell(13));
				finalTres=extraerFecha(fila.getCell(14));
				if(inicioTres.before(finalTres)){
					Experiencia experiencia=new Experiencia();
					experiencia.establecerFechas(inicioTres, finalTres);
					experiencia.setHojaVida(hojaVida);
					hojaVida.getExperiencias().add(experiencia);
				}
			} catch (Exception e) {

			}
			// Inserta la hoja de vida generada en el ArrayList
			hojasVidaExtraidas.add(hojaVida);
		}
	}
	
	private String extraerTexto(Cell celda){
		return celda.getRichStringCellValue().toString();
	}
	
	private Long extraerNumero(Cell celda) throws Exception {
		Long numero=null;
		if (celda.getCellType()==Cell.CELL_TYPE_NUMERIC) {
			numero=(long) celda.getNumericCellValue();
			return numero;
		} else {
			throw new Exception("Celda ("+celda.getRowIndex()+","+celda.getColumnIndex()+") con formato incorrecto");
		}
	}
	
	private Date extraerFecha(Cell celda) throws Exception {
		Date fecha=null;
		if (celda.getCellType()==Cell.CELL_TYPE_NUMERIC && HSSFDateUtil.isCellDateFormatted(celda)) {
			fecha=celda.getDateCellValue();
			return fecha;
		} else {
			throw new Exception("Celda ("+celda.getRowIndex()+","+celda.getColumnIndex()+") con formato incorrecto");
		}
	}

}
