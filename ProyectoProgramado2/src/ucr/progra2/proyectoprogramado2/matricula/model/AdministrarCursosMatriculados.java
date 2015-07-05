package ucr.progra2.proyectoprogramado2.matricula.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ucr.progra2.proyectoprogramado2.conexion.Conexion;

public class AdministrarCursosMatriculados extends Conexion {

	private Connection conexion = null;
	private String rutaDB = System.getProperty("user.dir") + "/db/ucr.db";

	private PreparedStatement psInsertar = null;
	private PreparedStatement psEliminar = null;
	private PreparedStatement psConsultar = null;

	public void insertar(String carnet, String codigo, int semestre, int year,
			String nota) {

		conexion = getConexion(rutaDB);

		try {

			if (!tablaExiste(conexion, "CursosMatriculados"))
				crearTabla();

			if (!existeCursoMatriculado(carnet, codigo, semestre, year)) {

				conexion = getConexion(rutaDB);
				if (psInsertar == null)
					psInsertar = conexion
							.prepareStatement("INSERT INTO CursosMatriculados (carnet, codigo, semestre, año, nota) VALUES (?, ?, ?, ?, ?)");
				
				psInsertar.setString(1, carnet);
				psInsertar.setString(2, codigo);
				psInsertar.setInt(3, semestre);
				psInsertar.setInt(4, year);
				psInsertar.setString(5, nota);

				psInsertar.executeUpdate();
				JOptionPane.showMessageDialog(null,
						"Curso matriculado con exito");

			} else {
				JOptionPane
						.showMessageDialog(null,
								"Ya existe un curso matriculado con estos mismos datos");

			}

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

	public void eliminar(String carnet, String codigo, int semestre, int year) {

		try{
			System.out.println("pe");
			conexion = getConexion(rutaDB);
			if (!tablaExiste(conexion, "CursosMatriculados"))
				crearTabla();
			
			if (existeCursoMatriculado(carnet, codigo, semestre, year)) {
				
				conexion = getConexion(rutaDB);
				if (psEliminar == null)
					psEliminar = conexion.prepareStatement("DELETE FROM CursosMatriculados WHERE carnet = ? AND codigo = ? AND semestre = ? AND año = ?;");
			
				psEliminar.setString(1, carnet);
				psEliminar.setString(2, codigo);
				psEliminar.setInt(3, semestre);
				psEliminar.setInt(4, year);
				
				psEliminar.executeUpdate();
				JOptionPane.showMessageDialog(null, "El curso se ha desmatriculado del estudiante");
				
			} else {
				JOptionPane.showMessageDialog(null, "Esta combinacion dee estudiante-curso no existe!");
			}
			
		}catch(SQLException sqlex){
			sqlex.printStackTrace();
			
		} finally {
			try{
				if (conexion != null)
					conexion.close();
				
			}catch(SQLException sqlex){
				sqlex.printStackTrace();
			}
		}
		
	}

	// ********************************

	private boolean existeCursoMatriculado(String carnet, String codigo,
			int semestre, int year) {

		boolean flag = false;

		try {
			conexion = getConexion(rutaDB);
			if (!tablaExiste(conexion, "CursosMatriculados"))
				crearTabla();

			conexion = getConexion(rutaDB);
			psConsultar = conexion
					.prepareStatement("SELECT * FROM CursosMatriculados WHERE carnet = ? AND codigo = ? AND semestre = ? AND año = ?;");
			psConsultar.setString(1, carnet);
			psConsultar.setString(2, codigo);
			psConsultar.setInt(3, semestre);
			psConsultar.setInt(4, year);

			ResultSet resultado = psConsultar.executeQuery();
			while (resultado.next()) {
				flag = true;
			}

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();

		} finally {
			try {
				if (psConsultar != null)
					psConsultar.close();
				if (conexion != null)
					conexion.close();

			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}

		}

		return flag;
	}

	// ********************************

	public JTable getCursosMatriculadosPorEstudiante(String carnet) {

		conexion = getConexion(rutaDB);

		DefaultTableModel modeloTabla = new DefaultTableModel();
		JTable jtbl_tabla = new JTable(modeloTabla);

		try {

			psConsultar = conexion
					.prepareStatement("SELECT * FROM CursosMatriculados WHERE carnet = ?");
			psConsultar.setString(1, carnet);
			ResultSet resultado = psConsultar.executeQuery();

			ResultSetMetaData metaDatos = resultado.getMetaData();

			int columnas = metaDatos.getColumnCount();
			String[] etiquetas = new String[columnas];

			for (int x = 0; x < columnas; x++) {
				etiquetas[x] = metaDatos.getColumnLabel(x + 1);
			}

			modeloTabla.setColumnIdentifiers(etiquetas);

			while (resultado.next()) {

				Object[] fila = new Object[columnas];
				for (int x = 0; x < columnas; x++) {
					fila[x] = resultado.getObject(x + 1);
				}
				modeloTabla.addRow(fila);

			}

			resultado.close();

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();

		} finally {

			try {
				if (psConsultar != null)
					psConsultar.close();
				if (conexion != null)
					conexion.close();

			} catch (SQLException sqlex) {
				sqlex.printStackTrace();

			}

		}

		return jtbl_tabla;

	}

	// ********************************

	private void crearTabla() {

		try {

			conexion = getConexion(rutaDB);
			String tabla = "CREATE TABLE CursosMatriculados ("
					+ "carnet VARCHAR NOT NULL,"
					+ "codigo VARCHAR NOT NULL,"
					+ "semestre VARCHAR NOT NULL,"
					+ "año VARCHAR NOT NULL,"
					+ "nota VARCHAR NOT NULL, PRIMARY KEY(carnet, codigo, semestre, año));";

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
