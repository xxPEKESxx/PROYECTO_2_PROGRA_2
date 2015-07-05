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

public class AdministrarUsuarios extends Conexion {

	private Connection conexion = null;
	private String rutaDB = System.getProperty("user.dir") + "/db/ucr.db";

	private PreparedStatement psInsertar = null;
	private PreparedStatement psEliminar = null;
	private PreparedStatement psModificar = null;
	private PreparedStatement psMostrar = null;
	private PreparedStatement psConsulta = null;

	private List<String> usuarios = new ArrayList<String>();

	private Control control;

	public AdministrarUsuarios(Control c) {
		control = c;
	}

	public void insertar(String idUsuario, int admin, String userId, String pass,
			String nombre, String apellido1, String apellido2, String email) {

		try {

			conexion = getConexion(rutaDB);
			boolean crear = tablaExiste(conexion, "Usuarios");
			if (!crear)
				crearTabla();

			if (!existeIdUsuario(idUsuario)) {

				if (!existeUserID(userId)) {

					conexion = getConexion(rutaDB);
					psInsertar = conexion
							.prepareStatement("INSERT INTO Usuarios (admin, userId, pass, nombre, apellido1, apellido2, email)"
									+ "VALUES (?, ?, ?, ?, ?, ?, ?);");
					psInsertar.setInt(1, admin);
					psInsertar.setString(2, userId);
					psInsertar.setString(3, pass);
					psInsertar.setString(4, nombre);
					psInsertar.setString(5, apellido1);
					psInsertar.setString(6, apellido2);
					psInsertar.setString(7, email);

					psInsertar.executeUpdate();

					setUsuarios();

					JOptionPane.showMessageDialog(null, "Usuario agregado");

				} else {
					JOptionPane.showMessageDialog(null,
							"El nombre de usuario ya esta en uso");
				}

			} else {
				JOptionPane.showMessageDialog(null, "El usuario ya existe");

			}

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();

		} finally {

			try {

				if (psInsertar != null)
					psInsertar.close();

				if (conexion != null)
					conexion.close();

			} catch (SQLException sqlex) {
				sqlex.printStackTrace();
			}

		}

	}

	// ********************************

	public void eliminar(String idUsuario) {

		try {

			boolean crear = tablaExiste(conexion, "Usuarios");
			if (!crear)
				crearTabla();
			
			if (existeIdUsuario(idUsuario)) {

				conexion = getConexion(rutaDB);
				psEliminar = conexion
						.prepareStatement("DELETE FROM Usuarios WHERE idUsuario = ?");

				psEliminar.setString(1, idUsuario);
				psEliminar.executeUpdate();

				JOptionPane.showMessageDialog(null, "Eliminacion exitosa");

				setUsuarios();

			} else {
				JOptionPane.showMessageDialog(null, "El usuario no existe");

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

	public void modificar(String idUsuario, int admin, String userId, String pass,
			String nombre, String apellido1, String apellido2, String email) {

		try {
			
			conexion = getConexion(rutaDB);
			boolean crear = tablaExiste(conexion, "Usuarios");
			if (!crear)
				crearTabla();

			if (existeIdUsuario(idUsuario)) {
				
				conexion = getConexion(rutaDB);
				psModificar = conexion
						.prepareStatement("UPDATE Usuarios SET admin = ?, userId = ?, pass = ?, nombre = ?, apellido1 = ?, apellido2 = ?, email = ?"
								+ "WHERE idUsuario = ?;");

				psModificar.setInt(1, admin);
				psModificar.setString(2, userId);
				psModificar.setString(3, pass);
				psModificar.setString(4, nombre);
				psModificar.setString(5, apellido1);
				psModificar.setString(6, apellido2);
				psModificar.setString(7, email);
				psModificar.setString(8, idUsuario);

				psModificar.executeUpdate();

				setUsuarios();

				JOptionPane.showMessageDialog(null, "Modificacion exitosa");

			} else {
				JOptionPane.showMessageDialog(null, "No existe el idUsuario");
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

	public void consultarDatos(String idUsuario) {

		try {
			
			boolean crear = tablaExiste(conexion = getConexion(rutaDB), "Usuarios");
			if (!crear)
				crearTabla();
			
			conexion = getConexion(rutaDB);

			psMostrar = conexion
					.prepareStatement("SELECT * FROM Usuarios WHERE idUsuario  = ?;");
			psMostrar.setString(1, idUsuario);

			ResultSet resultado = psMostrar.executeQuery();
			boolean encontrado = false;

			String user_Id = "", pass = "", nombre = "", apellido1 = "", apellido2 = "", email = "";
			int admin = 0;
			while (resultado.next()) {
				encontrado = true;
				admin = resultado.getInt("admin");
				user_Id = resultado.getString("userId");
				pass = resultado.getString("pass");
				nombre = resultado.getString("nombre");
				apellido1 = resultado.getString("apellido1");
				apellido2 = resultado.getString("apellido2");
				email = resultado.getString("email");
			}

			if (encontrado) {
				control.setDatosUsuario(idUsuario, admin, user_Id, pass, nombre, apellido1,
						apellido2, email);
			}

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();

		} finally {

		}

	}

	/**
	 * Verifica si existe el idUsuario (Numerico)
	 * 
	 * @param userId
	 * @return
	 */
	private boolean existeIdUsuario(String idUsuario) {

		boolean flag = false;

		try {

			conexion = getConexion(rutaDB);
			psConsulta = conexion
					.prepareStatement("SELECT idUsuario FROM Usuarios WHERE idUsuario = ?");
			psConsulta.setString(1, idUsuario);

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

	/**
	 * Verifica si existe el nombre de usuario
	 * 
	 * @return - boolean
	 */
	private boolean existeUserID(String userId) {
		boolean flag = false;

		try {

			conexion = getConexion(rutaDB);
			psConsulta = conexion
					.prepareStatement("SELECT userID FROM Usuarios WHERE userID = ?;");
			psConsulta.setString(1, userId);
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

	protected void crearTabla() {

		try {

			conexion = getConexion(rutaDB);
			String tabla = "CREATE TABLE Usuarios ("
					+ "idUsuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + "admin INTEGER,"
					+ "userID VARCHAR NOT NULL," + "pass VARCHAR NOT NULL,"
					+ "nombre VARCHAR NOT NULL," + "apellido1 VARCHAR NOT NULL,"
					+ "apellido2 VARCHAR NOT NULL," + "email VARCHAR NOT NULL"
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

	private void consultarUsuarios() {

		usuarios.clear();
		try {

			conexion = getConexion(rutaDB);
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta
					.executeQuery("SELECT idUsuario FROM Usuarios");

			while (resultado.next()) {
				usuarios.add(resultado.getString("idUsuario"));
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

	public List<String> getUsuarios() {
		consultarUsuarios();
		return usuarios;
	}

	private void setUsuarios() {
		consultarUsuarios();
		control.setUsuarios(usuarios);
	}
	
	// ********************************
	
	public JTable generarReporte(){
		
		conexion = getConexion(rutaDB);
		
		DefaultTableModel modeloTabla = new DefaultTableModel();
		JTable jtbl_tabla = new JTable(modeloTabla);
		
		try{
			
			Statement consulta = conexion.createStatement();
			ResultSet resultado = consulta.executeQuery("SELECT * FROM Usuarios");
			
			ResultSetMetaData metaDatos = resultado.getMetaData();
			
			int columnas = metaDatos.getColumnCount();
			String [] etiquetas = new String[columnas];
			
			for (int x = 0; x < columnas; x++){
				etiquetas[x] = metaDatos.getColumnLabel(x + 1);
			}
			
			modeloTabla.setColumnIdentifiers(etiquetas);
			
			while (resultado.next()){
				
				Object [] fila = new Object[columnas];
				for (int x = 0; x < columnas; x++){
					fila[x] = resultado.getObject(x + 1);
				}
				modeloTabla.addRow(fila);
				
			}
			
			resultado.close();
			
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
		
		return jtbl_tabla;
		
	}
	
	
}
