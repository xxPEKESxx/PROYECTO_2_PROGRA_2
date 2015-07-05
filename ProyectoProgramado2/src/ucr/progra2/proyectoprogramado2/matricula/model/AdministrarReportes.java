package ucr.progra2.proyectoprogramado2.matricula.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ucr.progra2.proyectoprogramado2.conexion.Conexion;

public class AdministrarReportes extends Conexion {

	private Connection conexion = null;
	private String rutaDB = System.getProperty("user.dir") + "/db/ucr.db";

	public JTable reporteEstudiantes(int atributos) {

		DefaultTableModel modelo = new DefaultTableModel();
		JTable tabla = new JTable(modelo);

		try {
			String consu = "";
			switch (atributos) {

			case 1:
				consu = "SELECT carnet FROM Estudiantes;";
				break;

			case 2:
				consu = "SELECT carnet, nombre FROM Estudiantes;";
				break;

			case 3:
				consu = "SELECT carnet, nombre, apellido1 FROM Estudiantes;";
				break;

			case 4:
				consu = "SELECT carnet, nombre, apellido1, apellido2 FROM Estudiantes;";
				break;

			case 5:
				consu = "SELECT carnet, nombre, apellido1, apellido2, direccion FROM Estudiantes;";
				break;

			case 6:
				consu = "SELECT * FROM Estudiantes;";
				break;

			default:
				consu = "SELECT * FROM Estudiantes";
				break;
			}

			conexion = getConexion(rutaDB);
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery(consu);
			ResultSetMetaData meta = resultado.getMetaData();
			int columnas = meta.getColumnCount();
			String[] etiquetas = new String[columnas];

			for (int x = 0; x < columnas; x++)
				etiquetas[x] = meta.getColumnLabel(x + 1);

			modelo.setColumnIdentifiers(etiquetas);

			while (resultado.next()) {
				Object[] fila = new Object[columnas];
				for (int x = 0; x < columnas; x++)
					fila[x] = resultado.getObject(x + 1);
				modelo.addRow(fila);
			}

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		return tabla;

	}

	public JTable reporteUsuarios(int atributos) {

		DefaultTableModel modelo = new DefaultTableModel();
		JTable tabla = new JTable(modelo);

		try {
			String consu = "";
			switch (atributos) {

			case 1:
				consu = "SELECT idUsuario FROM Usuarios;";
				break;

			case 2:
				consu = "SELECT idUsuario, userID FROM Usuarios;";
				break;

			case 3:
				consu = "SELECT idUsuario, userID, pass FROM Usuarios;";
				break;

			case 4:
				consu = "SELECT idUsuario, userID, pass, nombre FROM Usuarios;";
				break;

			case 5:
				consu = "SELECT idUsuario, userID, pass, nombre, apellido1 FROM Usuarios;";
				break;

			case 6:
				consu = "SELECT idUsuario, userID, pass, nombre, apellido1, apellido2 FROM Usuarios;";
				break;

			default:
				consu = "SELECT * FROM Usuarios;";
				break;

			}

			conexion = getConexion(rutaDB);
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery(consu);
			ResultSetMetaData meta = resultado.getMetaData();
			int columnas = meta.getColumnCount();
			String[] etiquetas = new String[columnas];

			for (int x = 0; x < columnas; x++)
				etiquetas[x] = meta.getColumnLabel(x + 1);

			modelo.setColumnIdentifiers(etiquetas);

			while (resultado.next()) {
				Object[] fila = new Object[columnas];
				for (int x = 0; x < columnas; x++)
					fila[x] = resultado.getObject(x + 1);
				modelo.addRow(fila);
			}

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		return tabla;

	}

	public JTable reporteCursos(int atributos) {

		DefaultTableModel modelo = new DefaultTableModel();
		JTable tabla = new JTable(modelo);

		try {
			String consu = "";
			switch (atributos) {

			case 1:
				consu = "SELECT codigo FROM Cursos;";
				break;

			case 2:
				consu = "SELECT codigo, nombre FROM Cursos;";
				break;

			case 3:
				consu = "SELECT * FROM Cursos;";
				break;

			default:
				consu = "SELECT * FROM Cursos;";
				break;

			}

			conexion = getConexion(rutaDB);
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery(consu);
			ResultSetMetaData meta = resultado.getMetaData();
			int columnas = meta.getColumnCount();
			String[] etiquetas = new String[columnas];

			for (int x = 0; x < columnas; x++)
				etiquetas[x] = meta.getColumnLabel(x + 1);

			modelo.setColumnIdentifiers(etiquetas);

			while (resultado.next()) {
				Object[] fila = new Object[columnas];
				for (int x = 0; x < columnas; x++)
					fila[x] = resultado.getObject(x + 1);
				modelo.addRow(fila);
			}

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		return tabla;

	}

	public JTable reporteCursosMatriculados(int atributos) {

		DefaultTableModel modelo = new DefaultTableModel();
		JTable tabla = new JTable(modelo);

		try {
			String consu = "";
			switch (atributos) {

			case 1:
				consu = "SELECT carnet FROM CursosMatriculados;";
				break;

			case 2:
				consu = "SELECT carnet, codigo FROM CursosMatriculados;";
				break;

			case 3:
				consu = "SELECT carnet, codigo, semestre FROM CursosMatriculados;";
				break;

			case 4:
				consu = "SELECT carnet, codigo, semestre, año FROM CursosMatriculados;";
				break;

			case 5:
				consu = "SELECT * FROM CursosMatriculados;";
				break;

			default:
				consu = "SELECT * FROM Estudiantes;";
				break;

			}

			conexion = getConexion(rutaDB);
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery(consu);
			ResultSetMetaData meta = resultado.getMetaData();
			int columnas = meta.getColumnCount();
			String[] etiquetas = new String[columnas];

			for (int x = 0; x < columnas; x++)
				etiquetas[x] = meta.getColumnLabel(x + 1);

			modelo.setColumnIdentifiers(etiquetas);

			while (resultado.next()) {
				Object[] fila = new Object[columnas];
				for (int x = 0; x < columnas; x++)
					fila[x] = resultado.getObject(x + 1);
				modelo.addRow(fila);
			}

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		return tabla;

	}

}
