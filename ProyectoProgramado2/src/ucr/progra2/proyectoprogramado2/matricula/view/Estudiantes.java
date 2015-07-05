package ucr.progra2.proyectoprogramado2.matricula.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import ucr.progra2.proyectoprogramado2.matricula.cotrol.Control;

public class Estudiantes extends JInternalFrame implements ActionListener,
		FocusListener {

	/**
	 * Generado por el IDE
	 */
	private static final long serialVersionUID = 6847963993090524519L;

	private JSplitPane jspl_paneles;
	private JPanel jpnl_acciones;
	private JPanel jpnl_reporte;
	private JTable jtbl_tablaReporte;
	private JScrollPane jscrll_tablaReporte;

	private JLabel jlbl_estudiantesAgregados;
	private JComboBox<String> jcbx_estudiantesAgregados;
	private Boton jbtn_mostrar;
	private Boton jbtn_matricular;
	private Boton jbtn_mostrarMatriculados;

	// Labels indicadores
	private JLabel jlbl_carnet;
	private JLabel jlbl_nombre;
	private JLabel jlbl_apellido1;
	private JLabel jlbl_apellido2;
	private JLabel jlbl_direccion;
	private JLabel jlbl_edad;

	// Cajas de texto para mostrar e ingresar valores
	private JTextField jtxt_carnet;
	private JTextField jtxt_nombre;
	private JTextField jtxt_apellido1;
	private JTextField jtxt_apellido2;
	private JTextField jtxt_direccion;
	private JTextField jtxt_edad;

	// Botonera de accion
	private Boton jbtn_insertar;
	private Boton jbtn_eliminar;
	private Boton jbtn_buscar;
	private Boton jbtn_modificar;
	private Boton jbtn_limpiar;
	private Boton jbtn_reporte;

	private Control control;

	public Estudiantes(Control c) {
		control = c;
		initGui();
		setCarnets(control.getCarnets());
	}

	private void initGui() {

		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setTitle("ADMINISTRADOR DE ESTUDIANTES");
		setFrameIcon(new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/iconos/estudiantes.png")));

		jspl_paneles = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jspl_paneles.setOneTouchExpandable(true);

		jpnl_acciones = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		jlbl_estudiantesAgregados = new JLabel("Estudiantes agregados:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.insets.set(4, 4, 4, 4);
		jpnl_acciones.add(jlbl_estudiantesAgregados, gbc);

		jcbx_estudiantesAgregados = new JComboBox<String>();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 2;
		jpnl_acciones.add(jcbx_estudiantesAgregados, gbc);

		jbtn_mostrar = new Boton("Mostrar");
		jbtn_mostrar.tipoBuscar();
		jbtn_mostrar.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jbtn_mostrar, gbc);

		jbtn_matricular = new Boton("Matricular");
		jbtn_matricular.tipoMatricular();
		jbtn_matricular.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jbtn_matricular, gbc);

		jbtn_mostrarMatriculados = new Boton("Mostrar cursos matriculados");
		jbtn_mostrarMatriculados.tipoMostrarMatriculados();
		jbtn_mostrarMatriculados.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		jpnl_acciones.add(jbtn_mostrarMatriculados, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.ipady = 5;
		jpnl_acciones.add(new JSeparator(JSeparator.HORIZONTAL), gbc);

		jlbl_carnet = new JLabel("Carnet");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.ipady = 0;
		jpnl_acciones.add(jlbl_carnet, gbc);

		jtxt_carnet = new JTextField(10);
		jtxt_carnet.addFocusListener(this);
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jtxt_carnet, gbc);

		jlbl_nombre = new JLabel("Nombre:");
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jlbl_nombre, gbc);

		jtxt_nombre = new JTextField(10);
		jtxt_nombre.addFocusListener(this);
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jtxt_nombre, gbc);

		jlbl_apellido1 = new JLabel("1re Apellido:");
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jlbl_apellido1, gbc);

		jtxt_apellido1 = new JTextField(10);
		jtxt_apellido1.addFocusListener(this);
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jtxt_apellido1, gbc);

		jlbl_apellido2 = new JLabel("2do Apellido:");
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jlbl_apellido2, gbc);

		jtxt_apellido2 = new JTextField(10);
		jtxt_apellido2.addFocusListener(this);
		gbc.gridx = 1;
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jtxt_apellido2, gbc);

		jlbl_direccion = new JLabel("Direccion:");
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jlbl_direccion, gbc);

		jtxt_direccion = new JTextField(10);
		jtxt_direccion.addFocusListener(this);
		gbc.gridx = 1;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jtxt_direccion, gbc);

		jlbl_edad = new JLabel("Edad:");
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jlbl_edad, gbc);

		jtxt_edad = new JTextField(10);
		jtxt_edad.addFocusListener(this);
		gbc.gridx = 1;
		gbc.gridy = 10;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jtxt_edad, gbc);

		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.ipady = 3;
		jpnl_acciones.add(new JSeparator(JSeparator.HORIZONTAL), gbc);

		jbtn_insertar = new Boton("Insertar");
		jbtn_insertar.tipoInsertar();
		jbtn_insertar.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 12;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		jpnl_acciones.add(jbtn_insertar, gbc);

		jbtn_modificar = new Boton("Modificar");
		jbtn_modificar.tipoModificar();
		jbtn_modificar.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 12;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jbtn_modificar, gbc);

		jbtn_eliminar = new Boton("Eliminar");
		jbtn_eliminar.tipoEliminar();
		jbtn_eliminar.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 13;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jbtn_eliminar, gbc);

		jbtn_buscar = new Boton("Buscar");
		jbtn_buscar.tipoBuscar();
		jbtn_buscar.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 13;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jbtn_buscar, gbc);

		jbtn_limpiar = new Boton("Limpiar");
		jbtn_limpiar.tipoLimpiar();
		jbtn_limpiar.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 14;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jbtn_limpiar, gbc);

		jbtn_reporte = new Boton("Reporte");
		jbtn_reporte.tipoReporte();
		jbtn_reporte.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 14;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_acciones.add(jbtn_reporte, gbc);

		jspl_paneles.setLeftComponent(jpnl_acciones);

		// ****************************

		jpnl_reporte = new JPanel();

		jtbl_tablaReporte = new JTable();
		jtbl_tablaReporte.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		jscrll_tablaReporte = new JScrollPane(jtbl_tablaReporte);
		jscrll_tablaReporte
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jscrll_tablaReporte
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		jpnl_reporte.add(jscrll_tablaReporte);

		jspl_paneles.setRightComponent(jpnl_reporte);

		// ****************************

		getContentPane().add(jspl_paneles);
		pack();
		setLocation(100,  20);

	}

	// ********************************

	private void insertar() {

		String carnet = jtxt_carnet.getText();
		String nombre = jtxt_nombre.getText();
		String apellido1 = jtxt_apellido1.getText();
		String apellido2 = jtxt_apellido2.getText();
		String direccion = jtxt_direccion.getText();
		String eda = jtxt_edad.getText();

		if (!(carnet.isEmpty()) && !(nombre.isEmpty())
				&& !(apellido1.isEmpty()) && !(apellido2.isEmpty())
				&& !(direccion.isEmpty()) && !(eda.isEmpty())) {

			int edad = Integer.parseInt(eda);
			control.insertarEstudiante(carnet, nombre, apellido1, apellido2,
					direccion, edad);

		} else {
			JOptionPane.showMessageDialog(null, "Llene todos los campos");
		}

	}

	// ********************************

	private void eliminar() {

		String carnet = jtxt_carnet.getText();

		if (!(carnet.isEmpty())) {

			control.eliminarEstudiante(carnet);

		} else {

			String seleccionado = jcbx_estudiantesAgregados.getSelectedItem()
					.toString();
			control.eliminarEstudiante(seleccionado);

		}

	}

	// ********************************

	private void modificar() {

		String carnet = jtxt_carnet.getText();
		String nombre = jtxt_nombre.getText();
		String apellido1 = jtxt_apellido1.getText();
		String apellido2 = jtxt_apellido2.getText();
		String direccion = jtxt_direccion.getText();
		String eda = jtxt_edad.getText();

		if (!(carnet.isEmpty())) {

			if (!(carnet.isEmpty()) && !(nombre.isEmpty())
					&& !(apellido1.isEmpty()) && !(apellido2.isEmpty())
					&& !(direccion.isEmpty()) && !(eda.isEmpty())) {

				int edad = Integer.parseInt(eda);
				control.modificarEstudiante(carnet, nombre, apellido1,
						apellido2, direccion, edad);

			} else {
				JOptionPane.showMessageDialog(null, "Llene todos los campos");
			}

		} else {

			String seleccionado = jcbx_estudiantesAgregados.getSelectedItem()
					.toString();

			if (!(nombre.isEmpty()) && !(apellido1.isEmpty())
					&& !(apellido2.isEmpty()) && !(direccion.isEmpty())
					&& !(eda.isEmpty())) {

				int edad = Integer.parseInt(eda);
				control.modificarEstudiante(seleccionado, nombre, apellido1,
						apellido2, direccion, edad);

			} else {
				JOptionPane.showMessageDialog(null, "Llene todos los campos");
			}

		}

	}

	// ********************************

	private void mostrar(String carnet) {

		if (!(carnet.isEmpty())) {
			control.consultarEstudiante(carnet);

		}

	}

	public void setConsulta(String carnet, String nombre, String apellido1,
			String apellido2, String direccion, String edad) {
		jtxt_carnet.setText(carnet);
		jtxt_nombre.setText(nombre);
		jtxt_apellido1.setText(apellido1);
		jtxt_apellido2.setText(apellido2);
		jtxt_direccion.setText(direccion);
		jtxt_edad.setText(edad);

	}

	// ********************************

	public void setCarnets(List<String> carnets) {
		jcbx_estudiantesAgregados.removeAllItems();

		for (String carnet : carnets) {
			jcbx_estudiantesAgregados.addItem(carnet);
		}

	}

	// ********************************

	private void limpiar() {
		jtxt_nombre.setText("");
		jtxt_apellido1.setText("");
		jtxt_apellido2.setText("");
		jtxt_carnet.setText("");
		jtxt_direccion.setText("");
		jtxt_edad.setText("");
	}

	private void reporte() {
		control.generarReporteEstudiantes();
	}

	public void setDatosTabla(JTable tabla) {
		jtbl_tablaReporte.setModel(tabla.getModel());
	}

	// ********************************

	private void matricular() {
		String carnet = jcbx_estudiantesAgregados.getSelectedItem().toString();
		if (!carnet.isEmpty())
			control.addMatricular(carnet);
	}

	// ********************************

	private void mostrarMatriculados() {
		String carnet = jcbx_estudiantesAgregados.getSelectedItem().toString();
		if (!carnet.isEmpty())
			control.mostrarMatriculados(carnet);

	}

	// ********************************

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jbtn_insertar) {
			insertar();

		} else if (ae.getSource() == jbtn_eliminar) {
			eliminar();

		} else if (ae.getSource() == jbtn_modificar) {
			modificar();

		} else if (ae.getSource() == jbtn_mostrar) {
			if (jcbx_estudiantesAgregados.getItemCount() > 0)
				mostrar(jcbx_estudiantesAgregados.getSelectedItem().toString());

		} else if (ae.getSource() == jbtn_buscar) {
			mostrar(jtxt_carnet.getText());

		} else if (ae.getSource() == jbtn_limpiar) {
			limpiar();

		} else if (ae.getSource() == jbtn_reporte) {
			reporte();

		} else if (ae.getSource() == jbtn_matricular) {
			matricular();

		} else if (ae.getSource() == jbtn_mostrarMatriculados) {
			mostrarMatriculados();

		}
	}

	// ********************************

	@Override
	public void focusGained(FocusEvent fe) {
		if (fe.getSource() == jtxt_carnet)
			jtxt_carnet.setSelectionStart(0);

		else if (fe.getSource() == jtxt_nombre)
			jtxt_nombre.setSelectionStart(0);

		else if (fe.getSource() == jtxt_nombre)
			jtxt_nombre.setSelectionStart(0);

		else if (fe.getSource() == jtxt_apellido1)
			jtxt_apellido1.setSelectionStart(0);

		else if (fe.getSource() == jtxt_apellido2)
			jtxt_apellido2.setSelectionStart(0);

		else if (fe.getSource() == jtxt_direccion)
			jtxt_direccion.setSelectionStart(0);

		else if (fe.getSource() == jtxt_edad)
			jtxt_edad.setSelectionStart(0);
	}

	@Override
	public void focusLost(FocusEvent fe) {
	}
	
	public void toFront(){
		try{
			setSelected(true);
		}catch(PropertyVetoException pvex){
			pvex.printStackTrace();
			
		}
	}

	

}
