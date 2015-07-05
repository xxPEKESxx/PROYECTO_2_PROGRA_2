package ucr.progra2.proyectoprogramado2.matricula.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import ucr.progra2.proyectoprogramado2.matricula.cotrol.Control;

public class Reporte extends JInternalFrame {

	/**
	 * Generado por el IDE
	 */
	private static final long serialVersionUID = -9044349858950656963L;

	private JTabbedPane jtbp_tabs;

	private JPanel jpnl_estudiantes;
	private JTable jtbl_estudiantesReporte;
	
	private JPanel jpnl_cursos;
	private JTable jtbl_cursosReporte;
	
	private JPanel jpnl_cursosMatriculados;
	private JTable jtbl_cursosMatriculadosReporte;

	private JPanel jpnl_usuarios;
	private JTable jtbl_usuariosReporte;

	private Control control;
	
	public Reporte(Control c) {
		control = c;
		initGui();
	}

	private void initGui() {

		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("REPORTES");
		setFrameIcon(new ImageIcon(
				getClass()
						.getResource(
								"/ucr/progra2/proyectoprogramado2/recursos/iconos/reportes.png")));

		jtbp_tabs = new JTabbedPane();

		initEstudiantes();
		initCursos();
		initCursosMatriculados();
		initUsuarios();
		
		add(jtbp_tabs);
		
		pack();
		setLocation(100, 20);

	}

	private void initEstudiantes() {
		jpnl_estudiantes = new JPanel();

		// ****************************
		
		jpnl_estudiantes.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.set(4, 4, 4, 4);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		jpnl_estudiantes.add(new JLabel("Atributos:"), gbc);
		
		final JComboBox<String> jcbx_atributos = new JComboBox<String>();
		for (int x = 1; x < 7; x++)
			jcbx_atributos.addItem(String.valueOf(x));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_estudiantes.add(jcbx_atributos, gbc);
		
		JButton jbtn_crearReporte = new JButton("Crear Reporte");
		jbtn_crearReporte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				reporteEstudiantes(Integer.parseInt(jcbx_atributos.getSelectedItem().toString()));				
			}
		});
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_estudiantes.add(jbtn_crearReporte, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.ipady = 3;
		jpnl_estudiantes.add(new JSeparator(JSeparator.HORIZONTAL), gbc);
		
		jtbl_estudiantesReporte = new JTable();
		jtbl_estudiantesReporte.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane jscrl_reporte = new JScrollPane(jtbl_estudiantesReporte);
		jscrl_reporte.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jscrl_reporte.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jscrl_reporte.setPreferredSize(new Dimension(400, 200));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.gridheight = 3;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		jpnl_estudiantes.add(jscrl_reporte, gbc);
		
		
		// ****************************
		
		ImageIcon icon = new ImageIcon(
				getClass()
						.getResource(
								"/ucr/progra2/proyectoprogramado2/recursos/iconos/estudiantes.png"));
		jtbp_tabs.addTab("Estudiantes", icon, jpnl_estudiantes);
	}

	private void initCursos() {

		jpnl_cursos = new JPanel();
		
		jpnl_cursos.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.set(4, 4, 4, 4);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		jpnl_cursos.add(new JLabel("Atributos:"), gbc);
		
		final JComboBox<String> jcbx_atributos = new JComboBox<String>();
		for (int x = 1; x < 4; x++)
			jcbx_atributos.addItem(String.valueOf(x));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_cursos.add(jcbx_atributos, gbc);
		
		JButton jbtn_crearReporte = new JButton("Crear Reporte");
		jbtn_crearReporte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				reporteCursos(Integer.parseInt(jcbx_atributos.getSelectedItem().toString()));				
			}
		});
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_cursos.add(jbtn_crearReporte, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.ipady = 3;
		jpnl_cursos.add(new JSeparator(JSeparator.HORIZONTAL), gbc);
		
		jtbl_cursosReporte = new JTable();
		jtbl_cursosReporte.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane jscrl_reporte = new JScrollPane(jtbl_cursosReporte);
		jscrl_reporte.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jscrl_reporte.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jscrl_reporte.setPreferredSize(new Dimension(400, 200));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.gridheight = 3;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		jpnl_cursos.add(jscrl_reporte, gbc);
		
		ImageIcon icon = new ImageIcon(
				getClass()
				.getResource(
						"/ucr/progra2/proyectoprogramado2/recursos/iconos/cursos.png"));
		jtbp_tabs.addTab("Cursos", icon, jpnl_cursos);

		
	}

	private void initCursosMatriculados() {
		jpnl_cursosMatriculados = new JPanel();
		
		jpnl_cursosMatriculados.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.set(4, 4, 4, 4);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		jpnl_cursosMatriculados.add(new JLabel("Atributos:"), gbc);
		
		final JComboBox<String> jcbx_atributos = new JComboBox<String>();
		for (int x = 1; x < 6; x++)
			jcbx_atributos.addItem(String.valueOf(x));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_cursosMatriculados.add(jcbx_atributos, gbc);
		
		JButton jbtn_crearReporte = new JButton("Crear Reporte");
		jbtn_crearReporte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				reporteCursosMatriculados(Integer.parseInt(jcbx_atributos.getSelectedItem().toString()));				
			}
		});
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_cursosMatriculados.add(jbtn_crearReporte, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.ipady = 3;
		jpnl_cursosMatriculados.add(new JSeparator(JSeparator.HORIZONTAL), gbc);
		
		jtbl_cursosMatriculadosReporte = new JTable();
		jtbl_cursosMatriculadosReporte.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane jscrl_reporte = new JScrollPane(jtbl_cursosMatriculadosReporte);
		jscrl_reporte.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jscrl_reporte.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jscrl_reporte.setPreferredSize(new Dimension(400, 200));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.gridheight = 3;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		jpnl_cursosMatriculados.add(jscrl_reporte, gbc);
		
		
		ImageIcon icon = new ImageIcon(
				getClass()
				.getResource(
						"/ucr/progra2/proyectoprogramado2/recursos/botones/matricular.png"));
		jtbp_tabs.addTab("Cursos Matriculados", icon, jpnl_cursosMatriculados);
	}

	private void initUsuarios() {
		jpnl_usuarios = new JPanel();
		
		jpnl_usuarios.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets.set(4, 4, 4, 4);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		jpnl_usuarios.add(new JLabel("Atributos:"), gbc);
		
		final JComboBox<String> jcbx_atributos = new JComboBox<String>();
		for (int x = 1; x < 7; x++)
			jcbx_atributos.addItem(String.valueOf(x));
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_usuarios.add(jcbx_atributos, gbc);
		
		JButton jbtn_crearReporte = new JButton("Crear Reporte");
		jbtn_crearReporte.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				reporteUsuarios(Integer.parseInt(jcbx_atributos.getSelectedItem().toString()));				
			}
		});
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_usuarios.add(jbtn_crearReporte, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.ipady = 3;
		jpnl_usuarios.add(new JSeparator(JSeparator.HORIZONTAL), gbc);
		
		jtbl_usuariosReporte = new JTable();
		jtbl_usuariosReporte.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane jscrl_reporte = new JScrollPane(jtbl_usuariosReporte);
		jscrl_reporte.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jscrl_reporte.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jscrl_reporte.setPreferredSize(new Dimension(400, 200));
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.gridheight = 3;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		jpnl_usuarios.add(jscrl_reporte, gbc);
		
		ImageIcon icon = new ImageIcon(
				getClass()
				.getResource(
						"/ucr/progra2/proyectoprogramado2/recursos/iconos/usuarios.png"));
		jtbp_tabs.addTab("Usuarios", icon, jpnl_usuarios);
	}
	
	
	/**********************************
	 * 
	 * Re
	 * 
	 * *******************************/
	
	public void reporteEstudiantes(int atributos){
		control.reporteEstudiantes(atributos);
	}
	public void setTablaReporteEstudiante(JTable tabla){
		jtbl_estudiantesReporte.setModel(tabla.getModel());
	}
	
	public void reporteUsuarios(int atributos){
		control.reporteUsuarios(atributos);
	}
	public void setTablaReporteUsuarios(JTable tabla){
		jtbl_usuariosReporte.setModel(tabla.getModel());
	}
	
	public void reporteCursos(int atributos){
		control.reporteCursos(atributos);
	}
	public void setTablaReporteCursos(JTable tabla){
		jtbl_cursosReporte.setModel(tabla.getModel());
	}
	public void reporteCursosMatriculados(int atributos){
		control.reporteCursosMatriculados(atributos);
	}
	public void setTablaReporteCursosMatriculados(JTable tabla){
		jtbl_cursosMatriculadosReporte.setModel(tabla.getModel());
	}
}
