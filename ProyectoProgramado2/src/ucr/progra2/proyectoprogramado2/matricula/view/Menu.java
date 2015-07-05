package ucr.progra2.proyectoprogramado2.matricula.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ucr.progra2.proyectoprogramado2.matricula.cotrol.Control;

public class Menu extends JPanel implements ActionListener {

	/**
	 * Generado por el IDE
	 */
	private static final long serialVersionUID = -245012548598190231L;
	private JButton jbtn_estudiantes;
	private JButton jbtn_cursos;
	private JButton jbtn_usuarios;
	private JButton jbtn_config;
	private JButton jbtn_reportes;
	private JButton jbtn_cerrarSesion;

	private Control control;

	public Menu(Control c) {
		control = c;
		initGui();
	}

	private void initGui() {

		FlowLayout flow = new FlowLayout();
		flow.setAlignment(FlowLayout.LEFT);
		setLayout(flow);

		setBorder(BorderFactory.createEtchedBorder());

		jbtn_estudiantes = new JButton("Estudiantes");
		jbtn_estudiantes.setHorizontalTextPosition(SwingConstants.LEFT);
		jbtn_estudiantes.setContentAreaFilled(false);
		jbtn_estudiantes.setBorderPainted(false);
		jbtn_estudiantes
				.setIcon(new ImageIcon(
						getClass()
								.getResource(
										"/ucr/progra2/proyectoprogramado2/recursos/menu/estudiantes.png")));
		jbtn_estudiantes
				.setRolloverIcon(new ImageIcon(
						getClass()
								.getResource(
										"/ucr/progra2/proyectoprogramado2/recursos/menu/estudiantes1.png")));
		jbtn_estudiantes.addActionListener(this);
		add(jbtn_estudiantes);

		jbtn_cursos = new JButton("Cursos");
		jbtn_cursos.setHorizontalTextPosition(SwingConstants.LEFT);
		jbtn_cursos.setContentAreaFilled(false);
		jbtn_cursos.setBorderPainted(false);
		jbtn_cursos.setIcon(new ImageIcon(getClass().getResource(
				"/ucr/progra2/proyectoprogramado2/recursos/menu/cursos.png")));
		jbtn_cursos.setRolloverIcon(new ImageIcon(getClass().getResource(
				"/ucr/progra2/proyectoprogramado2/recursos/menu/cursos1.png")));
		jbtn_cursos.addActionListener(this);
		add(jbtn_cursos);

		jbtn_usuarios = new JButton("Usuarios");
		jbtn_usuarios.setHorizontalTextPosition(SwingConstants.LEFT);
		jbtn_usuarios.setContentAreaFilled(false);
		jbtn_usuarios.setBorderPainted(false);
		jbtn_usuarios
				.setIcon(new ImageIcon(
						getClass()
								.getResource(
										"/ucr/progra2/proyectoprogramado2/recursos/menu/usuarios.png")));
		jbtn_usuarios
				.setRolloverIcon(new ImageIcon(
						getClass()
								.getResource(
										"/ucr/progra2/proyectoprogramado2/recursos/menu/usuarios1.png")));
		jbtn_usuarios.addActionListener(this);
		add(jbtn_usuarios);

		jbtn_reportes = new JButton("Reportes");
		jbtn_reportes.setHorizontalTextPosition(SwingConstants.LEFT);
		jbtn_reportes.setContentAreaFilled(false);
		jbtn_reportes.setBorderPainted(false);
		jbtn_reportes.setIcon(new ImageIcon(getClass().getResource(
				"/ucr/progra2/proyectoprogramado2/recursos/menu/reporte.png")));
		jbtn_reportes
				.setRolloverIcon(new ImageIcon(
						getClass()
								.getResource(
										"/ucr/progra2/proyectoprogramado2/recursos/menu/reporte1.png")));
		jbtn_reportes.addActionListener(this);
		add(jbtn_reportes);

		jbtn_config = new JButton("Config");
		jbtn_config.setHorizontalTextPosition(SwingConstants.LEFT);
		jbtn_config.setContentAreaFilled(false);
		jbtn_config.setBorderPainted(false);
		jbtn_config.setIcon(new ImageIcon(getClass().getResource(
				"/ucr/progra2/proyectoprogramado2/recursos/menu/config.png")));
		jbtn_config.setRolloverIcon(new ImageIcon(getClass().getResource(
				"/ucr/progra2/proyectoprogramado2/recursos/menu/config1.png")));
		jbtn_config.addActionListener(this);
		add(jbtn_config);
		
		jbtn_cerrarSesion = new JButton("LogOut");
		jbtn_cerrarSesion.setHorizontalTextPosition(SwingConstants.LEFT);
		jbtn_cerrarSesion.setContentAreaFilled(false);
		jbtn_cerrarSesion.setBorderPainted(false);
		jbtn_cerrarSesion
				.setIcon(new ImageIcon(
						getClass()
								.getResource(
										"/ucr/progra2/proyectoprogramado2/recursos/menu/cerrarSesion.png")));
		jbtn_cerrarSesion
				.setRolloverIcon(new ImageIcon(
						getClass()
								.getResource(
										"/ucr/progra2/proyectoprogramado2/recursos/menu/cerrarSesion1.png")));
		jbtn_cerrarSesion.addActionListener(this);
		add(jbtn_cerrarSesion);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jbtn_estudiantes) {
			control.addEstudiantes();

		} else if (ae.getSource() == jbtn_cursos) {
			control.addCursos();
			
		} else if (ae.getSource() == jbtn_usuarios) {
			control.addUsuarios();

		} else if (ae.getSource() == jbtn_config) {
			control.addConfig();

		} else if (ae.getSource() == jbtn_cerrarSesion){
			control.cerrarSesion();
			
		} else if (ae.getSource() == jbtn_reportes){
			control.addReporte();
			
		}
	}

}
