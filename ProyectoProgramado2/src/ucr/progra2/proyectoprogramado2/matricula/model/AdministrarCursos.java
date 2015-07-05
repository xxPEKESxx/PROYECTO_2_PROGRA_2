package ucr.progra2.proyectoprogramado2.matricula.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ucr.progra2.proyectoprogramado2.conexion.Conexion;
import ucr.progra2.proyectoprogramado2.matricula.cotrol.Control;

public class AdministrarCursos extends Conexion {

	private Connection conexion = null;
	private String rutaDB = System.getProperty("user.dir") + "/db/ucr.db";

	private PreparedStatement psInsertar = null;
	private PreparedStatement psEliminar = null;
	private PreparedStatement psModificar = null;
	private PreparedStatement psConsultar = null;
	
	private List<String> codigos = new ArrayList<String>();

	private Control control;
	
	public AdministrarCursos(Control c){
		control = c;
	}
	
	public void insertar(String codigo, String nombre, int creditos){
		
		
		try{
			conexion = getConexion(rutaDB);
			if (!tablaExiste(conexion, "Cursos"))
				crearTabla();
			
			if (!cursoExiste(codigo)){
				
				conexion = getConexion(rutaDB);
				psInsertar = conexion.prepareStatement("INSERT INTO Cursos (codigo, nombre, creditos) "
						+ "VALUES (?, ?, ?);");
				psInsertar.setString(1, codigo);
				psInsertar.setString(2, nombre);
				psInsertar.setInt(3, creditos);
				
				psInsertar.executeUpdate();
				JOptionPane.showMessageDialog(null, "Curso agregado con exito!");
			
				setCodigos();
				
			} else {
				JOptionPane.showMessageDialog(null, "Ya existe un curso con ese codigo");
				
			}

		}catch(SQLException sqlex){
			sqlex.printStackTrace();
			
		} finally {
			try{
				if (psInsertar != null)
					psInsertar.close();
				if (conexion != null)
					conexion.close();

			}catch(SQLException sqlex){
				sqlex.printStackTrace();
			}
			
		}
		
	}
	
	// ********************************
	
	public void modificar(String codigo, String nombre, int creditos){
		
		try{
			conexion = getConexion(rutaDB);
			if (!tablaExiste(conexion, "Cursos"))
				crearTabla();
			
			if (cursoExiste(codigo)){
				
				conexion = getConexion(rutaDB);
				psModificar = conexion.prepareStatement("UPDATE Cursos SET nombre = ?, creditos = ? WHERE codigo = ?;");
				psModificar.setString(1, nombre);
				psModificar.setInt(2, creditos);
				psModificar.setString(3, codigo);
				
				psModificar.executeUpdate();
				JOptionPane.showMessageDialog(null, "Curso modificado con exito!");
			
			} else {
				JOptionPane.showMessageDialog(null, "No existe el curso a modificar");
				
			}

		}catch(SQLException sqlex){
			sqlex.printStackTrace();
			
		} finally {
			try{
				if (conexion != null)
					conexion.close();
				if (psInsertar != null)
					psInsertar.close();
			
			}catch(SQLException sqlex){
				sqlex.printStackTrace();
			}
			
		}
		
	
		
	}
	
	// ********************************
	
	public void eliminar(String codigo){
		
		try{
			
			conexion = getConexion(rutaDB);
			if (!tablaExiste(conexion, "Cursos"))
				crearTabla();
			
			if (cursoExiste(codigo)){
				
				conexion = getConexion(rutaDB);
				psEliminar = conexion.prepareStatement("DELETE FROM Cursos WHERE codigo = ?;");
				psEliminar.setString(1, codigo);
				psEliminar.executeUpdate();
				JOptionPane.showMessageDialog(null, "Curso eliminado con exito");
				
				setCodigos();
				
			} else {
				JOptionPane.showMessageDialog(null, "El curso no existe");
			}
			
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
			
		}finally {
			try{
				if (conexion != null)
					conexion.close();
				if (psInsertar != null)
					psInsertar.close();
			
			}catch(SQLException sqlex){
				sqlex.printStackTrace();
			}
			
		}
		
		
	}
	
	public void consultarDatos(String codigo){
		
		try{
			
			conexion = getConexion(rutaDB);
			if (!tablaExiste(conexion, "Cursos"))
				crearTabla();
			
			conexion = getConexion(rutaDB);
			psConsultar = conexion.prepareStatement("SELECT * FROM Cursos WHERE codigo = ?;");
			psConsultar.setString(1, codigo);
			
			ResultSet resultado = psConsultar.executeQuery();
			boolean encontrado = false;
			
			String nombre = "";
			int creditos = 0;
			while (resultado.next()){
				encontrado = true;
				nombre = resultado.getString("nombre");
				creditos = resultado.getInt("creditos");
			}
			if (encontrado)
				control.setDatosCurso(codigo, nombre, creditos);
			else
				JOptionPane.showMessageDialog(null, "Curso no encontrado");
			
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
			
		}finally {
			try{
				if (conexion != null)
					conexion.close();
				if (psInsertar != null)
					psInsertar.close();
			
			}catch(SQLException sqlex){
				sqlex.printStackTrace();
			}
			
		}
		
	}
	
	// ********************************
	
	private void consultarCodigos(){
		
		codigos.clear();
		
		try{
			
			conexion = getConexion(rutaDB);
			boolean crear = tablaExiste(conexion, "Cursos");
			if (!crear)
				crearTabla();
			
			conexion = getConexion(rutaDB);
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery("SELECT * FROM Cursos");
			
			while (resultado.next()){
				codigos.add(resultado.getString("codigo"));
			}
			
			consulta.close();
			resultado.close();
			
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
			
		} finally{
			try {
				if (conexion != null)
					conexion.close();

			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}
		
	}
	
	// ********************************
	
	public void setCodigos(){
		consultarCodigos();
		control.setCodigos(codigos);
	}
	public List<String> getCodigos(){
		consultarCodigos();
		return codigos;
	}
	
	// ********************************
	
	private boolean cursoExiste(String codigo){
		boolean flag = false;
		
		try{
			
			conexion = getConexion(rutaDB);
			
			psConsultar = conexion.prepareStatement("SELECT codigo FROM Cursos WHERE codigo = ?;");
			psConsultar.setString(1, codigo);
			ResultSet resultado = psConsultar.executeQuery();
			
			while (resultado.next()){
				flag = true;
			}
			
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
			
		} finally {
			try{
				if (conexion != null)
					conexion.close();
				if (psInsertar != null)
					psInsertar.close();
			
			}catch(SQLException sqlex){
				sqlex.printStackTrace();
			}
			
		}
		
		return flag;
	}
	
	// ********************************
	
	private void crearTabla(){
		try {

			conexion = getConexion(rutaDB);
			String tabla = "CREATE TABLE Cursos ("
					+ "codigo VARCAR PRIMARY KEY NOT NULL," + "nombre VARCHAR NOT NULL,"
					+ "creditos INTEGER NOT NULL"
					+ ");";

			Statement crear = conexion.createStatement();
			crear.executeUpdate(tabla);
			crear.close();

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();

		} finally {
			try {
				if (conexion != null)
					conexion.close();

			} catch (SQLException sqlex) {
				sqlex.printStackTrace();

			}

		}

	}
	
}
