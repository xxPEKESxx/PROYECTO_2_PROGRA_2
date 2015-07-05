package ucr.progra2.proyectoprogramado2.matricula.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ucr.progra2.proyectoprogramado2.matricula.cotrol.Control;

public class Usuarios extends JFrame implements FocusListener,
		ItemListener, ActionListener, DocumentListener {

	/**
	 * Generado por el IDE
	 */
	private static final long serialVersionUID = -7152365898793263694L;

	private JSplitPane jspl_paneles;
	private JPanel jpnl_acciones;
	private JPanel jpnl_reporte;
	private JScrollPane jscrl_tablaReporte;
	private JTable jtbl_tablaReporte;

	private JLabel jlbl_usuariosAgregados;
	private JComboBox<String> jcbx_usuariosAgregados;
	private Boton jbtn_mostrar;

	// Etiquetas
	private JLabel jlbl_nombre;
	private JLabel jlbl_apellido1;
	private JLabel jlbl_apellido2;
	private JLabel jlbl_email;
	private JLabel jlbl_idUsuario;
	private JLabel jlbl_userId;
	private JLabel jlbl_passO;
	private JLabel jlbl_passC;

	// Camps de texto
	private JTextField jtxt_nombre;
	private JTextField jtxt_apellido1;
	private JTextField jtxt_apellido2;
	private JTextField jtxt_email;
	private JTextField jtxt_idUsuario;
	private JTextField jtxt_userId;

	// Passwords
	private JPasswordField jpss_passOriginal;
	private JPasswordField jpss_passComprobante;
	private JCheckBox jcbx_mostrarPassOriginal;
	private JCheckBox jcbx_mostrarPassComprobante;
	private JCheckBox jcbx_administrador;

	// Botonera
	private Boton jbtn_insertar;
	private Boton jbtn_modificar;
	private Boton jbtn_buscar;
	private Boton jbtn_eliminar;
	private Boton jbtn_limpiar;
	private Boton jbtn_reporte;

	private Control control;
	private boolean admin;

	public Usuarios(Control c, boolean ad) {
		control = c;
		admin = ad;
		initGui();
		setUsuariosAgregados(control.getUsuarios());
	}

	private void initGui() {

		
		setResizable(true);
		
		setTitle("ADMINISTRADOR DE USUARIOS");
		                         

		jspl_paneles = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jspl_paneles.setOneTouchExpandable(true);

		jpnl_acciones = new JPanel(new GridBagLayout());
		jspl_paneles.setLeftComponent(jpnl_acciones);

		GridBagConstraints gbc = new GridBagConstraints();
		jlbl_usuariosAgregados = new JLabel("Usuarios agregados (idUsuarios)");
		jlbl_usuariosAgregados.setForeground(new Color(165, 42, 42));
		jlbl_usuariosAgregados.setFont(new Font("Castellar", Font.BOLD, 11));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.insets.set(4, 4, 4, 4);
		jpnl_acciones.add(jlbl_usuariosAgregados, gbc);

		jcbx_usuariosAgregados = new JComboBox<String>();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		jpnl_acciones.add(jcbx_usuariosAgregados, gbc);

		jbtn_mostrar = new Boton("Mostrar");
		jbtn_mostrar.tipoBuscar();
		jbtn_mostrar.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		jpnl_acciones.add(jbtn_mostrar, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.ipady = 3;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		jpnl_acciones.add(new JSeparator(JSeparator.HORIZONTAL), gbc);

		jlbl_nombre = new JLabel("Nombre:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.ipady = 0;

		jpnl_acciones.add(jlbl_nombre, gbc);

		jtxt_nombre = new JTextField(10);
		jtxt_nombre.addFocusListener(this);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jtxt_nombre, gbc);

		jlbl_apellido1 = new JLabel("1re Apellido:");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jlbl_apellido1, gbc);

		jtxt_apellido1 = new JTextField(10);
		jtxt_apellido1.addFocusListener(this);
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jtxt_apellido1, gbc);

		jlbl_apellido2 = new JLabel("2do Apellido:");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jlbl_apellido2, gbc);

		jtxt_apellido2 = new JTextField(10);
		jtxt_apellido2.addFocusListener(this);
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jtxt_apellido2, gbc);

		jlbl_email = new JLabel("Email:");
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jlbl_email, gbc);

		jtxt_email = new JTextField(10);
		jtxt_email.addFocusListener(this);
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jtxt_email, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.ipady = 3;
		jpnl_acciones.add(new JSeparator(JSeparator.HORIZONTAL), gbc);

		jlbl_idUsuario = new JLabel("idUsuario:");
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.ipady = 0;
		jpnl_acciones.add(jlbl_idUsuario, gbc);

		jtxt_idUsuario = new JTextField(10);
		jtxt_idUsuario.addFocusListener(this);
		gbc.gridx = 1;
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jtxt_idUsuario, gbc);

		jlbl_userId = new JLabel("UserID:");
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jlbl_userId, gbc);

		jtxt_userId = new JTextField(10);
		jtxt_userId.addFocusListener(this);
		gbc.gridx = 1;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jtxt_userId, gbc);

		jlbl_passO = new JLabel("PassWord:");
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jlbl_passO, gbc);

		jpss_passOriginal = new JPasswordField(10);
		jpss_passOriginal.addFocusListener(this);
		jpss_passOriginal.getDocument().addDocumentListener(this);
		gbc.gridx = 1;
		gbc.gridy = 10;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jpss_passOriginal, gbc);

		jcbx_mostrarPassOriginal = new JCheckBox();
		jcbx_mostrarPassOriginal.addItemListener(this);
		gbc.gridx = 2;
		gbc.gridy = 10;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jcbx_mostrarPassOriginal, gbc);

		jlbl_passC = new JLabel("Confirme Pass:");
		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jlbl_passC, gbc);

		jpss_passComprobante = new JPasswordField(10);
		jpss_passComprobante.addFocusListener(this);
		jpss_passComprobante.getDocument().addDocumentListener(this);
		gbc.gridx = 1;
		gbc.gridy = 11;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jpss_passComprobante, gbc);

		jcbx_mostrarPassComprobante = new JCheckBox();
		jcbx_mostrarPassComprobante.setEnabled(admin);
		jcbx_mostrarPassComprobante.addItemListener(this);
		gbc.gridx = 2;
		gbc.gridy = 11;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jcbx_mostrarPassComprobante, gbc);

		jcbx_administrador = new JCheckBox("Administrador");
		jcbx_administrador.setEnabled(admin);
		gbc.gridx = 0;
		gbc.gridy = 12;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		jpnl_acciones.add(jcbx_administrador, gbc);

		// ****************************

		jbtn_insertar = new Boton("Insertar");
		jbtn_insertar.tipoInsertar();
		jbtn_insertar.setEnabled(false);
		jbtn_insertar.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 13;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		jpnl_acciones.add(jbtn_insertar, gbc);

		jbtn_modificar = new Boton("Modificar");
		jbtn_modificar.tipoModificar();
		jbtn_modificar.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 13;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jbtn_modificar, gbc);

		jbtn_eliminar = new Boton("Eliminar");
		jbtn_eliminar.tipoEliminar();
		jbtn_eliminar.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 14;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jbtn_eliminar, gbc);

		jbtn_buscar = new Boton("Buscar");
		jbtn_buscar.tipoBuscar();
		jbtn_buscar.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 14;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jbtn_buscar, gbc);

		jbtn_limpiar = new Boton("Limpiar");
		jbtn_limpiar.tipoLimpiar();
		jbtn_limpiar.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 15;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jbtn_limpiar, gbc);

		jbtn_reporte = new Boton("Reporte");
		jbtn_reporte.tipoReporte();
		jbtn_reporte.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 15;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jbtn_reporte, gbc);

		// ****************************

		jpnl_reporte = new JPanel();

		jtbl_tablaReporte = new JTable();
		jtbl_tablaReporte.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		jscrl_tablaReporte = new JScrollPane(jtbl_tablaReporte);
		jscrl_tablaReporte
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jscrl_tablaReporte
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jpnl_reporte.add(jscrl_tablaReporte);

		jspl_paneles.setRightComponent(jpnl_reporte);

		// ****************************

		getContentPane().add(jspl_paneles);
		pack();
		setLocation(100,  20);
		
	}

	/**
	 * Inserta un usuario nuevo a la tabla
	 */
	private void insertar() {

		String idUsuario, userId, pass, nombre, apellido1, apellido2, email;
		idUsuario = jtxt_idUsuario.getText();
		userId = jtxt_userId.getText();
		pass = String.copyValueOf(jpss_passOriginal.getPassword());
		nombre = jtxt_nombre.getText();
		apellido1 = jtxt_apellido1.getText();
		apellido2 = jtxt_apellido2.getText();
		email = jtxt_email.getText();
		int admin = 0;
		if (jcbx_administrador.isSelected()) {
			admin = 1;
		}

		if (!(idUsuario.isEmpty())) {

			control.insertarUsuario(idUsuario, admin, userId, pass, nombre,
					apellido1, apellido2, email);
		}

	}

	private void eliminar() {

		String idUsuario = jtxt_idUsuario.getText();

		if (!idUsuario.isEmpty()) {

			control.eliminarUsuario(idUsuario);

		}

	}

	/**
	 * Muestra los datos del usuario seleccionado en el combo box Tambien
	 * utlizado para buscar al usuario por el campo de texto idUsuario
	 * 
	 * @param idUsuario
	 */
	private void mostrar(String idUsuario) {
		if (!(idUsuario.isEmpty())) {
			control.consultarUsuario(idUsuario);

		} else {
			JOptionPane.showMessageDialog(null, "Ingrese un idUsuario");
		}
	}

	/**
	 * Setea los datos del usuario en los campos de texto
	 * 
	 * @param admin
	 *            - int
	 * @param userId
	 *            - String
	 * @param pass
	 *            - String
	 * @param nombre
	 *            - Stirng
	 * @param apellido1
	 *            - String
	 * @param apellido2
	 *            - String
	 * @param email
	 *            - String
	 */
	public void setDatosUsuario(String idUsuario, int admin, String userId,
			String pass, String nombre, String apellido1, String apellido2,
			String email) {

		if (admin == 1)
			jcbx_administrador.setSelected(true);
		else
			jcbx_administrador.setSelected(false);

		jtxt_nombre.setText(nombre);
		jtxt_apellido1.setText(apellido1);
		jtxt_apellido2.setText(apellido2);
		jtxt_email.setText(email);

		jtxt_idUsuario.setText(idUsuario);
		jtxt_userId.setText(userId);
		jpss_passOriginal.setText(pass);
		jpss_passComprobante.setText(pass);

	}

	/**
	 * Setea los los idUsuarios que existen actualmente en la tabla en el
	 * JComboBox
	 * 
	 * @param usuarios
	 *            - List<Sting>
	 */
	public void setUsuariosAgregados(List<String> usuarios) {
		jcbx_usuariosAgregados.removeAllItems();
		for (String idUsuario : usuarios) {
			jcbx_usuariosAgregados.addItem(idUsuario);
		}
	}

	private void modificar() {

		String idUsuario, userId, pass, nombre, apellido1, apellido2, email;
		idUsuario = jtxt_idUsuario.getText();
		userId = jtxt_userId.getText();
		pass = String.copyValueOf(jpss_passOriginal.getPassword());
		nombre = jtxt_nombre.getText();
		apellido1 = jtxt_apellido1.getText();
		apellido2 = jtxt_apellido2.getText();
		email = jtxt_email.getText();
		int admin = 0;
		if (jcbx_administrador.isSelected()) {
			admin = 1;
		}

		if (!(idUsuario.isEmpty())) {

			control.modificarUsuario(idUsuario, admin, userId, pass, nombre,
					apellido1, apellido2, email);
		}

	}

	/**
	 * Limpia los campos de texto y deselecciona los check box
	 */
	private void limpiar() {
		jtxt_idUsuario.setText("");
		jtxt_userId.setText("");
		jtxt_nombre.setText("");
		jtxt_apellido1.setText("");
		jtxt_apellido2.setText("");
		jtxt_email.setText("");
		jpss_passOriginal.setText("");
		jpss_passComprobante.setText("");
		jcbx_mostrarPassComprobante.setSelected(false);
		jcbx_mostrarPassOriginal.setSelected(false);
		jcbx_administrador.setSelected(false);
	}

	private void reporte() {
		control.generarReporteUsuarios();
	}

	public void setReporte(JTable tabla) {
		jtbl_tablaReporte.setModel(tabla.getModel());
	}

	@Override
	public void focusGained(FocusEvent fe) {
		if (fe.getSource() == jtxt_nombre) {
			jtxt_nombre.setSelectionStart(0);

		} else if (fe.getSource() == jtxt_apellido1) {
			jtxt_apellido1.setSelectionStart(0);

		} else if (fe.getSource() == jtxt_apellido2) {
			jtxt_apellido2.setSelectionStart(0);

		} else if (fe.getSource() == jtxt_email) {
			jtxt_email.setSelectionStart(0);

		} else if (fe.getSource() == jtxt_idUsuario) {
			jtxt_idUsuario.setSelectionStart(0);

		} else if (fe.getSource() == jtxt_userId) {
			jtxt_userId.setSelectionStart(0);

		} else if (fe.getSource() == jpss_passOriginal) {
			jpss_passOriginal.setSelectionStart(0);

		} else if (fe.getSource() == jpss_passComprobante) {
			jpss_passComprobante.setSelectionStart(0);

		}
	}

	@Override
	public void focusLost(FocusEvent fe) {
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		if (ie.getSource() == jcbx_mostrarPassOriginal) {
			if (ie.getStateChange() == ItemEvent.SELECTED)
				jpss_passOriginal.setEchoChar((char) 0);
			else
				jpss_passOriginal.setEchoChar('*');

		} else if (ie.getSource() == jcbx_mostrarPassComprobante) {
			if (ie.getStateChange() == ItemEvent.SELECTED)
				jpss_passComprobante.setEchoChar((char) 0);
			else
				jpss_passComprobante.setEchoChar('*');

		}

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jbtn_insertar) {
			insertar();

		} else if (ae.getSource() == jbtn_eliminar) {
			eliminar();

		} else if (ae.getSource() == jbtn_buscar) {
			mostrar(jtxt_idUsuario.getText());

		} else if (ae.getSource() == jbtn_mostrar) {
			if (jcbx_usuariosAgregados.getItemCount() > 0)
				mostrar(jcbx_usuariosAgregados.getSelectedItem().toString());

		} else if (ae.getSource() == jbtn_modificar) {
			modificar();

		} else if (ae.getSource() == jbtn_limpiar) {
			limpiar();

		} else if (ae.getSource() == jbtn_reporte) {
			reporte();

		}
	}

	@Override
	public void insertUpdate(DocumentEvent de) {
		char[] passO = jpss_passOriginal.getPassword();
		char[] passC = jpss_passComprobante.getPassword();
		if (Arrays.equals(passO, passC)
				&& (jpss_passOriginal.getPassword().length > 0)
				&& (jpss_passComprobante.getPassword().length > 0)) {
			jbtn_insertar.setEnabled(true);
		} else {
			jbtn_insertar.setEnabled(false);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent de) {
		char[] passO = jpss_passOriginal.getPassword();
		char[] passC = jpss_passComprobante.getPassword();
		if (Arrays.equals(passO, passC)
				&& (jpss_passOriginal.getPassword().length > 0)
				&& (jpss_passComprobante.getPassword().length > 0)) {
			jbtn_insertar.setEnabled(true);
		} else {
			jbtn_insertar.setEnabled(false);
		}
	}

	@Override
	public void changedUpdate(DocumentEvent de) {
		char[] passO = jpss_passOriginal.getPassword();
		char[] passC = jpss_passComprobante.getPassword();
		if (Arrays.equals(passO, passC)
				&& (jpss_passOriginal.getPassword().length > 0)
				&& (jpss_passComprobante.getPassword().length > 0)) {
			jbtn_insertar.setEnabled(true);
		} else {
			jbtn_insertar.setEnabled(false);
		}
	}

}