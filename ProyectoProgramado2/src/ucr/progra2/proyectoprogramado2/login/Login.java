package ucr.progra2.proyectoprogramado2.login;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ucr.progra2.proyectoprogramado2.conexion.Conexion;
import ucr.progra2.proyectoprogramado2.matricula.cotrol.Control;

public class Login implements ActionListener {

	private JFrame jfra_login;
	private JTextField jtxt_user;
	private JPasswordField jpss_pass;
	private JButton jbtn_ingresar;
	private JButton jbtn_salir;

	private Connection conexion = null;
	private PreparedStatement psConsultar = null;

	private boolean administrador = false;

	public Login() {
		initGui();
	}

	private void initGui() {

		jfra_login = new JFrame("Login");
		jfra_login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Container con = jfra_login.getContentPane();
		con.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		jtxt_user = new JTextField(10);
		jtxt_user.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.insets.set(3, 3, 3, 3);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		con.add(jtxt_user, gbc);

		jpss_pass = new JPasswordField(10);
		jpss_pass.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		con.add(jpss_pass, gbc);

		jbtn_ingresar = new JButton("Ingresar");
		jbtn_ingresar
				.setIcon(new ImageIcon(
						getClass()
								.getResource(
										"/ucr/progra2/proyectoprogramado2/recursos/botones/entrar.png")));
		jbtn_ingresar
				.setRolloverIcon(new ImageIcon(
						getClass()
								.getResource(
										"/ucr/progra2/proyectoprogramado2/recursos/botones/entrar1.png")));
		jbtn_ingresar.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.NONE;
		con.add(jbtn_ingresar, gbc);

		jbtn_salir = new JButton("Salir");
		jbtn_salir
				.setIcon(new ImageIcon(
						getClass()
								.getResource(
										"/ucr/progra2/proyectoprogramado2/recursos/botones/cancelar.png")));
		jbtn_salir
				.setRolloverIcon(new ImageIcon(
						getClass()
								.getResource(
										"/ucr/progra2/proyectoprogramado2/recursos/botones/cancelar1.png")));
		jbtn_salir.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		con.add(jbtn_salir, gbc);

		jfra_login.pack();
		jfra_login.setVisible(true);
		jfra_login.setLocationRelativeTo(null);

	}

	// ********************************

	public void insertar() {

		Statement insertar = null;
		String insert = "INSERT INTO Usuarios "
				+ "(admin, userID, pass, nombre, apellido1, apellido2, email)"
				+ "VALUES (" + "1," + "'admin'," + "'123'," + "'admin',"
				+ "'apellido1'," + "'apellido2'," + "'email'" + ");";

		try {

			initConexion();

			insertar = conexion.createStatement();
			insertar.executeUpdate(insert);
			insertar.close();

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

	public void consultar() {

		String user = jtxt_user.getText();

		try {

			if (!(jtxt_user.getText().isEmpty())
					&& (jpss_pass.getPassword().length > 0)) {

				boolean crear = tablaExiste();
				if (!crear)
					crearTabla();

				initConexion();

				psConsultar = conexion
						.prepareStatement("SELECT * FROM Usuarios WHERE userId = ?;");

				psConsultar.setString(1, user);

				ResultSet resultado = psConsultar.executeQuery();

				String idUser = "", pass = "", admin = "";
				boolean usuarioEncontrado = false;
				while (resultado.next()) {
					usuarioEncontrado = true;
					idUser = resultado.getString("userID");
					pass = resultado.getString("pass");
					admin = resultado.getString("admin");
				}

				if (usuarioEncontrado)
					verificarUser(idUser, pass, admin);
				else
					JOptionPane.showMessageDialog(null,
							"No se ha encontrado el usuario");

				resultado.close();

			} else {
				JOptionPane.showMessageDialog(null, "Rellene los campos");
			}

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			System.out.println("Error consultar Login");

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

	}

	// ********************************

	public void crearTabla() {

		initConexion();

		Statement crearTabla = null;

		String tabla = "CREATE TABLE Usuarios ("
				+ "idUsuario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
				+ "admin INTEGER," + "userID VARCHAR NOT NULL,"
				+ "pass VARCHAR NOT NULL," + "nombre VARCHAR NOT NULL,"
				+ "apellido1 VARCHAR NOT NULL," + "apellido2 VARCHAR NOT NULL,"
				+ "email VARCHAR NOT NULL" + ");";

		try {

			crearTabla = conexion.createStatement();
			crearTabla.executeUpdate(tabla);
			insertar();

			crearTabla.close();

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			System.err.println("Error # 3");
			System.out.println("Imposible crear la tabla");

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

	public boolean tablaExiste() {

		initConexion();
		boolean flag = false;

		try {
			DatabaseMetaData data = conexion.getMetaData();
			ResultSet tabla = data.getTables(null, null, "Usuarios", null);
			if (tabla.next()) {
				flag = true;
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

		return flag;

	}

	// ********************************

	public void initConexion() {

		directorioExiste();
		conexion = null;
		String ruta = System.getProperty("user.dir") + "/db/ucr.db";
		conexion = Conexion.getConexion(ruta);
	}

	private void directorioExiste() {

		try {
			File directorio;
			String os = System.getProperty("os.name").toLowerCase();
			if (os.indexOf("win") >= 0) {

				directorio = new File(System.getProperty("user.dir") + "\\db");
				directorio.mkdirs();

			} else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0
					|| os.indexOf("aix") >= 0) {

				directorio = new File(System.getenv("user.dir") + "/db");
				directorio.mkdirs();

			}

		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	// ********************************

	private void verificarUser(String user, String pass, String admin) {

		if (!(user.isEmpty()) && !(pass.isEmpty()) && !(admin.isEmpty())) {

			String usuario = jtxt_user.getText();
			char[] contraIngresada = jpss_pass.getPassword();
			char[] contra = pass.toCharArray();
			int admini = Integer.parseInt(admin);

			if ((usuario.equals(user))
					&& (Arrays.equals(contraIngresada, contra))) {

				if (admini == 1)
					administrador = true;

				mostrarPrincipal();

			} else {
				JOptionPane
						.showMessageDialog(null, "Datos de ingreso erroneos");
			}

		}
	}

	// ********************************

	private void mostrarPrincipal() {
		new Control(administrador, this);
		jfra_login.setVisible(false);
	}

	public void visibleLogin() {
		jtxt_user.setText("");
		jpss_pass.setText("");
		jfra_login.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jbtn_ingresar) {
			consultar();

		} else if (ae.getSource() == jtxt_user) {
			consultar();

		} else if (ae.getSource() == jpss_pass){
			consultar();
			
		} else if (ae.getSource() == jbtn_salir){
			System.exit(0);
			
		}
		
	}

}
