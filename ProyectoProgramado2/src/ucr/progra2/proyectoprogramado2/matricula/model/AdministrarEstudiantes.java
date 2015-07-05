package ucr.progra2.proyectoprogramado2.matricula.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ucr.progra2.proyectoprogramado2.conexion.Conexion;
import ucr.progra2.proyectoprogramado2.matricula.cotrol.Control;

/**
 * Modelo que administra la tabla estudiantes
 * 
 * @author Rodolfo
 * 
 */
public class AdministrarEstudiantes extends Conexion {

	private Connection conexion = null;
	private String rutaDB = System.getProperty("user.dir") + "/db/ucr.db";

	private PreparedStatement psInsertar = null;
	private PreparedStatement psConsulta = null;
	private PreparedStatement psEliminar = null;
	private PreparedStatement psModificar = null;
	private PreparedStatement psMostrar = null;
	private List<String> carnets = new ArrayList<String>();
	private Control control;

	public AdministrarEstudiantes(Control c) {
		control = c;
	}

	// ********************************

	public void insertar(String carnet, String nombre, String apellido1,
			String apellido2, String direccion, int edad) {

		try {
			conexion = getConexion(rutaDB);
			boolean crear = tablaExiste(conexion, "Estudiantes");
			if (!crear)
				crearTabla();

			if (!existeCarnet(carnet)) {
				conexion = getConexion(rutaDB);

				if (psInsertar == null)
					psInsertar = conexion
							.prepareStatement("INSERT OR IGNORE INTO Estudiantes (carnet, nombre, apellido1, apellido2, direccion, edad) "
									+ "VALUES (?, ?, ?, ?, ?, ?)");

				psInsertar.setString(1, carnet);
				psInsertar.setString(2, nombre);
				psInsertar.setString(3, apellido1);
				psInsertar.setString(4, apellido2);
				psInsertar.setString(5, direccion);
				psInsertar.setInt(6, edad);

				psInsertar.executeUpdate();
				JOptionPane.showMessageDialog(null, "Estudiante agregado");

				setCarnets();

			} else {

				JOptionPane.showMessageDialog(null, "El estudiante ya existe");
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

	// ********************************

	public void eliminar(String carnet) {

		try {

			if (existeCarnet(carnet)) {

				conexion = getConexion(rutaDB);

				psEliminar = conexion
						.prepareStatement("DELETE FROM Estudiantes WHERE carnet = ?");

				psEliminar.setString(1, carnet);

				psEliminar.executeUpdate();

				setCarnets();

				JOptionPane.showMessageDialog(null, "Eliminacion exitosa");

			} else {
				JOptionPane.showMessageDialog(null, "El estudiante no existe");

			}

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();

		} finally {

			try {
				if (psEliminar != null)
					psEliminar.close();
				if (conexion != null)
					conexion.close();

			} catch (SQLException sqlex) {
				sqlex.printStackTrace();

			}

		}

	}

	// ********************************

	public void modificar(String carnet, String nombre, String apellido1,
			String apellido2, String direccion, int edad) {

		try {

			conexion = getConexion(rutaDB);
			boolean crear = tablaExiste(conexion, "Estudiantes");
			if (!crear)
				crearTabla();

			if (existeCarnet(carnet)) {

				conexion = getConexion(rutaDB);
				psModificar = conexion
						.prepareStatement("UPDATE Estudiantes SET nombre = ?, apellido1 = ?, apellido2 = ?, direccion = ?, edad = ?"
								+ "WHERE carnet = ?;");

				psModificar.setString(1, nombre);
				psModificar.setString(2, apellido1);
				psModificar.setString(3, apellido2);
				psModificar.setString(4, direccion);
				psModificar.setInt(5, edad);
				psModificar.setString(6, carnet);

				psModificar.executeUpdate();

				JOptionPane.showMessageDialog(null,
						"Modificacion satisfactoria");

			} else {
				JOptionPane.showMessageDialog(null, "El estudiante no existe");
			}

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();

		} finally {
			try {
				if (psModificar != null)
					psModificar.close();
				if (conexion != null)
					conexion.close();

			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}

	}

	// ********************************

	public void consultarDatos(String carnet) {

		try {

			conexion = getConexion(rutaDB);

			psMostrar = conexion
					.prepareStatement("SELECT * FROM Estudiantes WHERE carnet = ?;");

			psMostrar.setString(1, carnet);

			ResultSet resultado = psMostrar.executeQuery();
			boolean encontrado = false;

			String nombre = "", apellido1 = "", apellido2 = "", direccion = "", edad = "";
			while (resultado.next()) {
				encontrado = true;
				nombre = resultado.getString("nombre");
				apellido1 = resultado.getString("apellido1");
				apellido2 = resultado.getString("apellido2");
				direccion = resultado.getString("direccion");
				edad = resultado.getString("edad");
			}

			if (encontrado)
				control.setDatosEstudiante(carnet, nombre, apellido1,
						apellido2, direccion, edad);
			else
				JOptionPane.showMessageDialog(null, "Estudiante no encontrado");

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

	}

	// ********************************

	private void consultarCarnets() {

		carnets.clear();

		try {
			conexion = getConexion(rutaDB);
			boolean crear = tablaExiste(conexion, "Estudiantes");
			if (!crear)
				crearTabla();

			conexion = getConexion(rutaDB);
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta
					.executeQuery("SELECT carnet FROM Estudiantes");

			while (resultado.next()) {
				carnets.add(resultado.getString("carnet"));
			}

			consulta.close();
			resultado.close();

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

	public void crearTabla() {

		conexion = getConexion(rutaDB);

		Statement crearTabla = null;

		String tabla = "CREATE TABLE Estudiantes ("
				+ "carnet VARCHAR PRIMARY KEY UNIQUE NOT NULL,"
				+ "nombre VARCHAR NOT NULL," + "apellido1 VARCHAR NOT NULL,"
				+ "apellido2 VARCHAR NOT NULL," + "direccion VARCHAR NOT NULL,"
				+ "edad INTEGER NOT NULL" + ");";

		try {

			crearTabla = conexion.createStatement();
			crearTabla.executeUpdate(tabla);
			crearTabla.close();

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

	private boolean existeCarnet(String carnet) {

		boolean flag = false;

		try {

			conexion = getConexion(rutaDB);

			psConsulta = conexion
					.prepareStatement("SELECT carnet FROM Estudiantes WHERE carnet = ?;");

			psConsulta.setString(1, carnet);

			ResultSet resultado = psConsulta.executeQuery();
			while (resultado.next()) {
				flag = true;
			}

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();

		} finally {
			try {
				if (psConsulta != null)
					psConsulta.close();
				if (conexion != null)
					conexion.close();

			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}
		}

		return flag;

	}

	// ********************************

	public void setCarnets() {
		consultarCarnets();
		control.setCarnets(carnets);
	}

	public List<String> getCarnets() {
		consultarCarnets();
		return carnets;
	}

	// ********************************

	public JTable generarReporte() {

		conexion = getConexion(rutaDB);

		DefaultTableModel modeloTabla = new DefaultTableModel();
		JTable jtbl_tabla = new JTable(modeloTabla);

		try {

			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery("SELECT * FROM Estudiantes");

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
				if (conexion != null)
					conexion.close();

			} catch (SQLException sqlex) {
				sqlex.printStackTrace();

			}

		}

		return jtbl_tabla;

	}

}
