package modelos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
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

	public void cargarArchivo(Workbook libroExcel) {
		Sheet hoja = libroExcel.getSheetAt(0);
		int numeroFilas=hoja.getLastRowNum()+1;
		// Borra el ArrayList de hojas de vida extraídas y de filas corruptas
		hojasVidaExtraidas.clear();
		filasNoExtraidas.clear();
		
		Long numeroIdentificacion;
		String nombrePersona, apellidoPersona;
		String tipoDocumento;
		Date fechaNacimiento;
		Long telefono;
		String correoElectronico;
		String profesion, especializacion;
		Date inicioUno, finalUno;
		Date inicioDos, finalDos;
		Date inicioTres, finalTres;
		
		Row fila;
		for (int i = 0; i < numeroFilas; i++) {
			fila=hoja.getRow(i);
			// Extracción de datos de la hoja de vida. Si alguno de los
			// campos contiene información no válida, la fila no se extrae.
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
				inicioUno=extraerFecha(fila.getCell(9));
				finalUno=extraerFecha(fila.getCell(10));
				inicioDos=extraerFecha(fila.getCell(11));
				finalDos=extraerFecha(fila.getCell(12));
				inicioTres=extraerFecha(fila.getCell(13));
				finalTres=extraerFecha(fila.getCell(14));
			} catch (Exception e) {
				filasNoExtraidas.add(i+1);
				e.printStackTrace();
				continue;
			}
			// Creación objeto HojaVida
			HojaVida hojaVida=new HojaVida();
			// Restricción: número de identificación y tipo de documento requeridos
			if(numeroIdentificacion==null || TipoDocumento.valueOf(tipoDocumento)==null){
				filasNoExtraidas.add(i+1);
				continue;
			}
			// Restricción: nombre requerido
			if(nombrePersona.length()==0){
				filasNoExtraidas.add(i+1);
				continue;
			}
			// Restricción: teléfono o correo electrónico requeridos
			if(telefono==null && correoElectronico.length()==0){
				filasNoExtraidas.add(i+1);
				continue;
			}
			// Inserción de campos al objeto HojaVida
			hojaVida.setNumeroIdentificacion(numeroIdentificacion);
			hojaVida.setNombrePersona(nombrePersona);
			hojaVida.setApellidoPersona(apellidoPersona);
			hojaVida.setTipoDocumento(TipoDocumento.valueOf(tipoDocumento));
			if(fechaNacimiento!=null){
				Calendar fecha=Calendar.getInstance();
				fecha.setTime(fechaNacimiento);
				hojaVida.setFechaNacimiento(fecha);
			} else {
				hojaVida.setFechaNacimiento(null);
			}
			hojaVida.setTelefono(telefono);
			hojaVida.setCorreoElectronico(correoElectronico);
			hojaVida.setProfesion(profesion);
			hojaVida.setEspecializacion(especializacion);
			// Extracción de los datos de las experiencias de la hoja de vida
			if(inicioUno!=null && finalUno!=null && inicioUno.before(finalUno)){
				Experiencia experiencia=new Experiencia();
				experiencia.establecerFechas(inicioUno, finalUno);
				experiencia.setHojaVida(hojaVida);
				hojaVida.getExperiencias().add(experiencia);
			}
			if(inicioDos!=null && finalDos!=null && inicioDos.before(finalDos)){
				Experiencia experiencia=new Experiencia();
				experiencia.establecerFechas(inicioDos, finalDos);
				experiencia.setHojaVida(hojaVida);
				hojaVida.getExperiencias().add(experiencia);
			}
			if(inicioTres!=null && finalTres!=null && inicioTres.before(finalTres)){
				Experiencia experiencia=new Experiencia();
				experiencia.establecerFechas(inicioTres, finalTres);
				experiencia.setHojaVida(hojaVida);
				hojaVida.getExperiencias().add(experiencia);
			}
			// Inserta la hoja de vida generada en el ArrayList
			hojasVidaExtraidas.add(hojaVida);
		}
	}
	
	/**
	 * Extrae un texto de una celda de tipo texto. Si la celda
	 * está vacía, el método devuelve null
	 * @param celda
	 * @return
	 * @throws Exception  
	 */
	private String extraerTexto(Cell celda) throws Exception {
		if (celda==null) {
			return "";
		} else if (celda.getCellType()==Cell.CELL_TYPE_STRING) {
			return celda.getRichStringCellValue().toString();
		} else {
			throw new Exception("Celda ("+celda.getRowIndex()+","+celda.getColumnIndex()+") con formato incorrecto");
		}
	}
	
	/**
	 * Extrae un número de una celda de tipo numérico. Si la celda
	 * está vacía, el método devuelve null, si la celda no está
	 * vacía y no contiene un número válido, se genera una excepción
	 * @param celda
	 * @return
	 * @throws Exception
	 */
	private Long extraerNumero(Cell celda) throws Exception {
		Long numero=null;
		if(celda==null){
			return null;
		} else if (celda.getCellType()==Cell.CELL_TYPE_NUMERIC) {
			numero=(long) celda.getNumericCellValue();
			return numero;
		} else {
			throw new Exception("Celda ("+celda.getRowIndex()+","+celda.getColumnIndex()+") con formato incorrecto");
		}
	}
	
	/**
	 * Extrae un Date de una celda de tipo fecha. Si la celda
	 * está vacía, el método devuelve null, si la celda no está
	 * vacía y no contiene un número válido, se genera una excepción
	 * @param celda
	 * @return
	 * @throws Exception
	 */
	private Date extraerFecha(Cell celda) throws Exception {
		Date fecha=null;
		if(celda==null){
			return null;
		} else if (celda.getCellType()==Cell.CELL_TYPE_NUMERIC && HSSFDateUtil.isCellDateFormatted(celda)) {
			fecha=celda.getDateCellValue();
			return fecha;
		} else {
			throw new Exception("Celda ("+celda.getRowIndex()+","+celda.getColumnIndex()+") con formato incorrecto");
		}
	}

}
