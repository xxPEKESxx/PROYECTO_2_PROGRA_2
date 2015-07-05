package ucr.progra2.proyectoprogramado2.matricula.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import ucr.progra2.proyectoprogramado2.matricula.cotrol.Control;

public class Cursos extends JInternalFrame implements ActionListener,
		FocusListener {

	/**
	 * Generado por el IDE
	 */
	private static final long serialVersionUID = 9209838632735047867L;

	private JTabbedPane jtbs_tabs;

	private JPanel jpnl_cursos;
	private JPanel jpnl_cursosMatriculados;

	private JLabel jlbl_cursosAgregados;
	private JComboBox<String> jcbx_cursoAagregados;
	private Boton jbtn_mostrar;

	// ********************************

	private JLabel jlbl_codigo;
	private JLabel jlbl_nombre;
	private JLabel jlbl_cantidadCreditos;
	private JTextField jtxt_codigo;
	private JTextField jtxt_nombre;
	private JTextField jtxt_cantidadCreditos;

	// ********************************

	private Boton jbtn_insertar;
	private Boton jbtn_eliminar;
	private Boton jbtn_buscar;
	private Boton jbtn_modificar;
	private Boton jbtn_limpiar;

	private Control control;

	public Cursos(Control c) {
		control = c;
		initGui();
		setCodigos(control.getCodigos());
	}

	private void initGui() {

		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setTitle("ADMINISTRADOR DE CURSOS");
		setFrameIcon(new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/iconos/cursos.png")));

		jtbs_tabs = new JTabbedPane();

		jpnl_cursos = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		jlbl_cursosAgregados = new JLabel("Cursos Agregados:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.insets = new Insets(4, 4, 4, 4);
		jpnl_cursos.add(jlbl_cursosAgregados);

		jcbx_cursoAagregados = new JComboBox<String>();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		jpnl_cursos.add(jcbx_cursoAagregados, gbc);

		jbtn_mostrar = new Boton("Mostrar");
		jbtn_mostrar.tipoBuscar();
		jbtn_mostrar.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_cursos.add(jbtn_mostrar, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.ipady = 3;
		jpnl_cursos.add(new JSeparator(JSeparator.HORIZONTAL), gbc);

		jlbl_codigo = new JLabel("Codigo:");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.ipady = 0;
		jpnl_cursos.add(jlbl_codigo, gbc);

		jtxt_codigo = new JTextField(10);
		jtxt_codigo.addFocusListener(this);
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_cursos.add(jtxt_codigo, gbc);

		jlbl_nombre = new JLabel("Nombre:");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_cursos.add(jlbl_nombre, gbc);

		jtxt_nombre = new JTextField(10);
		jtxt_nombre.addFocusListener(this);
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_cursos.add(jtxt_nombre, gbc);

		jlbl_cantidadCreditos = new JLabel("Creditos");
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_cursos.add(jlbl_cantidadCreditos, gbc);

		jtxt_cantidadCreditos = new JTextField(10);
		jtxt_cantidadCreditos.addFocusListener(this);
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_cursos.add(jtxt_cantidadCreditos, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.ipady = 3;
		jpnl_cursos.add(new JSeparator(JSeparator.HORIZONTAL), gbc);

		jbtn_insertar = new Boton("Insertar");
		jbtn_insertar.tipoInsertar();
		jbtn_insertar.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.ipady = 0;
		jpnl_cursos.add(jbtn_insertar, gbc);

		jbtn_modificar = new Boton("Modificar");
		jbtn_modificar.tipoModificar();
		jbtn_modificar.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_cursos.add(jbtn_modificar, gbc);

		jbtn_eliminar = new Boton("Eliminar");
		jbtn_eliminar.tipoEliminar();
		jbtn_eliminar.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_cursos.add(jbtn_eliminar, gbc);

		jbtn_buscar = new Boton("Buscar");
		jbtn_buscar.tipoBuscar();
		jbtn_buscar.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_cursos.add(jbtn_buscar, gbc);

		jbtn_limpiar = new Boton("Limpiar");
		jbtn_limpiar.tipoLimpiar();
		jbtn_limpiar.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_cursos.add(jbtn_limpiar, gbc);

		jtbs_tabs.addTab("Cursos", jpnl_cursos);

		// ****************************

		jpnl_cursosMatriculados = new JPanel();

		jtbs_tabs.addTab("Cursos Matriculados", jpnl_cursosMatriculados);

		// ****************************

		getContentPane().add(jtbs_tabs);

		// ****************************

		pack();
		setLocation(100,  20);
		toBack();
	}

	private void insertar() {

		String codigo = jtxt_codigo.getText();
		String nombre = jtxt_nombre.getText();
		int creditos = 0;
		if (!jtxt_cantidadCreditos.getText().isEmpty()) {

			creditos = Integer.parseInt(jtxt_cantidadCreditos.getText());
			if (!(codigo.isEmpty()) && !(nombre.isEmpty()) && (creditos > 0)) {
				control.insertarCurso(codigo, nombre, creditos);

			} else {
				JOptionPane.showMessageDialog(null,
						"Ingrese el codgo y nombre de curso");

			}

		} else {
			JOptionPane.showMessageDialog(null,
					"Ingrese la cantidad de creditos");
		}
	}

	private void eliminar() {
		String codigo = jtxt_codigo.getText();

		if (!(codigo.isEmpty())) {
			control.eliminarCurso(codigo);

		} else {
			JOptionPane.showMessageDialog(null, "Ingrese el codigo de curso");
		}
	}

	private void modificar() {
		String codigo = jtxt_codigo.getText();
		String nombre = jtxt_nombre.getText();
		int creditos = 0;
		if (!jtxt_cantidadCreditos.getText().isEmpty()) {

			creditos = Integer.parseInt(jtxt_cantidadCreditos.getText());

			if (!(codigo.isEmpty()) && !(nombre.isEmpty()) && (creditos > 0)) {
				control.modificarCurso(codigo, nombre, creditos);
			} else {
				JOptionPane.showMessageDialog(null,
						"Ingrese el codgo y nombre de curso");
			}

		} else {
			JOptionPane.showMessageDialog(null,
					"Ingrese la cantidad de creditos");
		}

	}

	private void consultar(String codigo) {
		control.consultarCurso(codigo);
	}

	public void setDatosCurso(String codigo, String nombre, int creditos) {

		jtxt_codigo.setText(codigo);
		jtxt_nombre.setText(nombre);
		jtxt_cantidadCreditos.setText(String.valueOf(creditos));

	}

	private void limpiar() {
		jtxt_codigo.setText("");
		jtxt_nombre.setText("");
		jtxt_cantidadCreditos.setText("");
	}

	public void setCodigos(List<String> carnets) {
		jcbx_cursoAagregados.removeAllItems();

		for (String carnet : carnets) {
			jcbx_cursoAagregados.addItem(carnet);
		}

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jbtn_insertar) {
			insertar();

		} else if (ae.getSource() == jbtn_eliminar) {
			eliminar();

		} else if (ae.getSource() == jbtn_modificar) {
			modificar();

		} else if (ae.getSource() == jbtn_buscar) {
			consultar(jtxt_codigo.getText());

		} else if (ae.getSource() == jbtn_mostrar) {
			if (jcbx_cursoAagregados.getItemCount() > 0)
				consultar(jcbx_cursoAagregados.getSelectedItem().toString());

		} else if (ae.getSource() == jbtn_limpiar) {
			limpiar();

		}
	}

	@Override
	public void focusGained(FocusEvent fe) {
		if (fe.getSource() == jtxt_codigo){
			jtxt_codigo.setSelectionStart(0);
			
		} else if (fe.getSource() == jtxt_nombre){
			jtxt_nombre.setSelectionStart(0);
			
		} else if (fe.getSource() == jtxt_cantidadCreditos){
			jtxt_cantidadCreditos.setSelectionStart(0);
			
		}
	}

	@Override
	public void focusLost(FocusEvent fe) {
	}
}