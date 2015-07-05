package ucr.progra2.proyectoprogramado2.matricula.view;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ucr.progra2.proyectoprogramado2.matricula.cotrol.Control;

public class MenuBarra extends JMenuBar implements ActionListener {

	/**
	 * Generado por el IDE
	 */
	private static final long serialVersionUID = -2074430918963872769L;

	private JMenu jmnu_archivo;
	private JMenu jmnu_documentacion;
	private JMenuItem jmit_externa;
	private JMenuItem jmit_manual;
	private JMenuItem jmit_salir;

	private JMenu jmnu_administracion;
	private JMenuItem jmit_estudiantes;
	private JMenuItem jmit_cursos;
	private JMenuItem jmit_usuarios;
	private JMenuItem jmit_reporte;
	private JMenuItem jmit_config;

	private Control control;

	public MenuBarra(Control c) {
		control = c;
		initGui();
	}

	private void initGui() {

		jmnu_archivo = new JMenu("Archivo");
		jmnu_documentacion = new JMenu("Documentacion");
		jmnu_archivo.add(jmnu_documentacion);

		jmit_externa = new JMenuItem("Documentacion externa");
		jmit_externa.addActionListener(this);
		jmnu_documentacion.add(jmit_externa);
		jmit_manual = new JMenuItem("Manual de usuario");
		jmit_manual.addActionListener(this);
		jmnu_documentacion.add(jmit_manual);

		jmit_salir = new JMenuItem("Salir");
		jmnu_archivo.add(jmit_salir);

		add(jmnu_archivo);

		jmnu_administracion = new JMenu("Administracion");

		jmit_estudiantes = new JMenuItem("Estudiantes");
		jmit_estudiantes.addActionListener(this);
		jmnu_administracion.add(jmit_estudiantes);

		jmit_cursos = new JMenuItem("Cursos");
		jmit_cursos.addActionListener(this);
		jmnu_administracion.add(jmit_cursos);

		jmit_usuarios = new JMenuItem("Usuarios");
		jmit_usuarios.addActionListener(this);
		jmnu_administracion.add(jmit_usuarios);

		jmit_reporte = new JMenuItem("Reporte");
		jmit_reporte.addActionListener(this);
		jmnu_administracion.add(jmit_reporte);

		jmit_config = new JMenuItem("Configuracion");
		jmit_config.addActionListener(this);
		jmnu_administracion.add(jmit_config);

		add(jmnu_administracion);

	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == jmit_externa) {
			abrirExterna();

		} else if (ae.getSource() == jmit_manual) {
			abrirManual();

		} else if (ae.getSource() == jmit_estudiantes) {
			control.addEstudiantes();

		} else if (ae.getSource() == jmit_cursos) {
			control.addCursos();

		} else if (ae.getSource() == jmit_usuarios) {
			control.addUsuarios();

		} else if (ae.getSource() == jmit_config) {
			control.addConfig();

		} else if (ae.getSource() == jmit_reporte) {
			control.addReporte();

		}
	}

	private void abrirExterna() {

		try {

			String ruta = System.getProperty("user.dir") + "/db/Manual.pdf";
			File externa = new File(ruta);
			if (!externa.exists()) {

				InputStream input = getClass().getResourceAsStream(
						"/ucr/progra2/proyectoprogramado2/libs/Manual.pdf");
				FileOutputStream output;
				int lectorBytes;
				byte[] buffer = new byte[3000];

				output = new FileOutputStream(new File(ruta));
				while ((lectorBytes = input.read(buffer)) > 0) {
					output.write(buffer, 0, lectorBytes);
				}

				output.close();

				input.close();

			}

			Desktop.getDesktop().open(externa);

		} catch (IOException ioex) {
			ioex.printStackTrace();

		}

	}

	private void abrirManual() {

		try {

			String ruta = System.getProperty("user.dir") + "/db/Manual.pdf";
			File externa = new File(ruta);
			if (!externa.exists()) {

				InputStream input = getClass().getResourceAsStream(
						"/ucr/progra2/proyectoprogramado2/libs/Manual.pdf");
				FileOutputStream output;
				int lectorBytes;
				byte[] buffer = new byte[3000];

				output = new FileOutputStream(new File(ruta));
				while ((lectorBytes = input.read(buffer)) > 0) {
					output.write(buffer, 0, lectorBytes);
				}

				output.close();

				input.close();

			}

			Desktop.getDesktop().open(externa);

		} catch (IOException ioex) {
			ioex.printStackTrace();

		}

	}
}
