package ucr.progra2.proyectoprogramado2.matricula.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

import ucr.progra2.proyectoprogramado2.matricula.cotrol.Control;

public class Matricular extends JInternalFrame {

	/**
	 * Generado por el IDE
	 */
	private static final long serialVersionUID = -2124520549331172194L;

	private JLabel jlbl_cursos;
	private JComboBox<String> jcbx_cursos;
	private List<String> cursos = new ArrayList<String>();

	private JLabel jlbl_semestre;
	private JComboBox<String> jcbx_semestre;

	private JLabel jlbl_years;
	private JComboBox<String> jcbx_years;

	private JLabel jlbl_nota;
	private JTextField jtxt_nota;

	private Boton jbtn_matricularCurso;
	
	private JTable jtbl_cursosMatriculadosActualmente;
	private JScrollPane jscrl_tabla;
	private Boton jbtn_eliminar;

	private Control control;
	private String carnet;
	
	public Matricular(Control c, String carnet) {
		this.carnet = carnet;
		control = c;
		initGui();
	}

	private void initGui() {

		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		setTitle("MATRICULAR CURSO");
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		jlbl_cursos = new JLabel("Cursos:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.ipadx = 50;
		add(jlbl_cursos, gbc);

		jcbx_cursos = new JComboBox<String>();
		cursos = control.getCodigos();
		for (String cur : cursos)
			jcbx_cursos.addItem(cur);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.set(0, 4, 4, 4);
		add(jcbx_cursos, gbc);

		jlbl_semestre = new JLabel("Semestre:");
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.set(0, 0, 0, 0);
		add(jlbl_semestre, gbc);

		jcbx_semestre = new JComboBox<String>();
		jcbx_semestre.addItem("1");
		jcbx_semestre.addItem("2");
		jcbx_semestre.addItem("3");
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.set(0, 4, 4, 4);
		add(jcbx_semestre, gbc);

		jlbl_years = new JLabel("Año:");
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.set(0, 0, 0, 0);
		add(jlbl_years, gbc);

		jcbx_years = new JComboBox<String>();
		for (int x = 2020; x > 1990; x--)
			jcbx_years.addItem(String.valueOf(x));
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.set(0, 4, 4, 4);
		add(jcbx_years, gbc);

		jlbl_nota = new JLabel("Nota");
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.set(0, 0, 0, 0);
		add(jlbl_nota, gbc);

		jtxt_nota = new JTextField(5);
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.set(0, 4, 4, 4);
		add(jtxt_nota, gbc);

		jbtn_matricularCurso = new Boton("Matricular");
		jbtn_matricularCurso.tipoInsertar();
		jbtn_matricularCurso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				insertar();
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.ipadx = 0;
		add(jbtn_matricularCurso, gbc);
		
		// ****************************

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.ipady = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(new JSeparator(JSeparator.HORIZONTAL), gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.ipady = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(new JLabel("Cursos matriculados actualmente:"), gbc);
		
		jtbl_cursosMatriculadosActualmente = new JTable();
		jtbl_cursosMatriculadosActualmente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jtbl_cursosMatriculadosActualmente.setRowSelectionAllowed(true);
		
		jscrl_tabla = new JScrollPane(jtbl_cursosMatriculadosActualmente);
		jscrl_tabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jscrl_tabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jscrl_tabla.setPreferredSize(new Dimension(0, 150));
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 4;
		gbc.gridheight = 3;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		add(jscrl_tabla, gbc);
		
		jbtn_eliminar = new Boton("Eliminar");
		jbtn_eliminar.tipoEliminar();
		jbtn_eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				eliminar();
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		add(jbtn_eliminar, gbc);
		
		pack();
		setLocation(100,  20);
		
	}

	private void insertar() {

		String codigo = jcbx_cursos.getSelectedItem().toString();
		int semestre = Integer.parseInt(jcbx_semestre.getSelectedItem()
				.toString());
		int year = Integer.parseInt(jcbx_years.getSelectedItem().toString());
		String nota = jtxt_nota.getText();

		if (!(codigo.isEmpty()) && !(nota.isEmpty())) {
			control.insertarCursoMatriculado(carnet, codigo, semestre, year,
					nota);
		}
		
		getCursosMatriculados(this.carnet);
		
	}
	
	
	private void eliminar(){
		
		int cantidadColumnas = jtbl_cursosMatriculadosActualmente.getColumnCount();
		int fila = jtbl_cursosMatriculadosActualmente.getSelectedRow();
		int colum = 0;
		String [] datos = new String [cantidadColumnas];
		for (int x = 0; x < cantidadColumnas; x++){
			datos[x] = (String) jtbl_cursosMatriculadosActualmente.getValueAt(fila, colum);
			colum++;
		}
		
		String carnet = datos[0];
		String codigo = datos[1];
		int semestre = Integer.parseInt(datos[2]);
		int year = Integer.parseInt(datos[3]);
		control.eliminarCursoMatriculado(carnet, codigo, semestre, year);
		
		getCursosMatriculados(this.carnet);
		
	}
	
	public void setDatosTabla(JTable tabla) {
		jtbl_cursosMatriculadosActualmente.setModel(tabla.getModel());
	}

	
	public void getCursosMatriculados(String carnet){
		control.mostrarMatriculadosActualmente(carnet);

	}
	
	
}
