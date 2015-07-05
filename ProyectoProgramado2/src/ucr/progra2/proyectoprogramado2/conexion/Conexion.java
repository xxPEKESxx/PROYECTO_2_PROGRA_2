package ucr.progra2.proyectoprogramado2.conexion;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

	/**
	 * Retorna una conexion a la base de datos.
	 * 
	 * @param rutaDB
	 * @return
	 */
	public static Connection getConexion(String rutaDB) {
		Connection conexion = null;
		try {

			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:" + rutaDB);

		} catch (ClassNotFoundException cnfex) {
			System.err.print("Error # 1");
			System.out.println("Imposible abrir la clase");

		} catch (SQLException sqlex) {
			System.err.print("Error # 2");
			System.out.println("Imposible obtener la conexion");
		}

		return conexion;
	}

	// ********************************

	protected boolean tablaExiste(Connection conexion, String nombreTabla){
		
		boolean flag = false;
		
		try{
			
			DatabaseMetaData data = conexion.getMetaData();
			ResultSet tabla = data.getTables(null, null, nombreTabla, null);
			
			if (tabla.next())
				flag = true;
			
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
		
		return flag;
	
	}
	
}
