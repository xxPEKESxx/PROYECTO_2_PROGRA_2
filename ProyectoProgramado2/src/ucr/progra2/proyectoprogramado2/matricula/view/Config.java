package ucr.progra2.proyectoprogramado2.matricula.view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ucr.progra2.proyectoprogramado2.matricula.cotrol.Control;

public class Config extends JInternalFrame {

	/**
	 * Generado por el IDE
	 */
	private static final long serialVersionUID = -6830268909613447221L;

	private JCheckBox jcbx_cambiarFondo;

	private JLabel jlbl_segundosCambio;
	private JTextField jtxt_segundosCambio;

	private JComboBox<String> jcbx_lookAndFeels;
	private JButton jbtn_cambiar;

	private JButton jbtn_cargarImagen;
	private JFileChooser jfch_cargador;

	private Boton jbtn_guardar;

	private Control control;
	private JFrame principal;
	private boolean admin = false;

	public Config(Control c, JFrame pri, boolean ad) {
		control = c;
		principal = pri;
		admin = ad;
		initGui();
	}

	private void initGui() {

		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setTitle("CONFIGURACION");
		setFrameIcon(new ImageIcon(getClass().getResource(
				"/ucr/progra2/proyectoprogramado2/recursos/iconos/config.png")));
		toFront();
		try {
			setSelected(true);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Container con = getContentPane();
		con.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		jcbx_cambiarFondo = new JCheckBox("Cambiar fondo dinamicante");
		jcbx_cambiarFondo.setEnabled(admin);
		jcbx_cambiarFondo.setSelected(true);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.insets.set(4, 4, 4, 4);
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		con.add(jcbx_cambiarFondo, gbc);

		jlbl_segundosCambio = new JLabel("Segundos:");
		jlbl_segundosCambio.setEnabled(admin);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		con.add(jlbl_segundosCambio, gbc);

		jtxt_segundosCambio = new JTextField(3);
		jtxt_segundosCambio.setText("5");
		jtxt_segundosCambio.setEnabled(admin);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		con.add(jtxt_segundosCambio, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.ipady = 3;
		con.add(new JSeparator(JSeparator.HORIZONTAL), gbc);

		jbtn_cargarImagen = new JButton("Cargar imagen de fondo");
		jbtn_cargarImagen.setIcon(new ImageIcon(
						getClass()
								.getResource(
										"/ucr/progra2/proyectoprogramado2/recursos/botones/cargar.png")));
		jbtn_cargarImagen.setRolloverIcon(new ImageIcon(
						getClass()
								.getResource(
										"/ucr/progra2/proyectoprogramado2/recursos/botones/cargar1.png")));
		jbtn_cargarImagen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				cargarFondo();
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.ipady = 0;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		con.add(jbtn_cargarImagen, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.ipady = 3;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		con.add(new JSeparator(JSeparator.HORIZONTAL), gbc);

		jcbx_lookAndFeels = new JComboBox<String>();
		jcbx_lookAndFeels
				.addItem("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		jcbx_lookAndFeels.addItem("com.jtattoo.plaf.aero.AeroLookAndFeel");
		jcbx_lookAndFeels.addItem("com.jtattoo.plaf.luna.LunaLookAndFeel");
		jcbx_lookAndFeels.addItem("com.jtattoo.plaf.noire.NoireLookAndFeel");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		con.add(jcbx_lookAndFeels, gbc);

		jbtn_cambiar = new JButton("Cambiar Aspecto");
		jbtn_cambiar
				.setIcon(new ImageIcon(
						getClass()
								.getResource(
										"/ucr/progra2/proyectoprogramado2/recursos/botones/recargar.png")));
		jbtn_cambiar
				.setRolloverIcon(new ImageIcon(
						getClass()
								.getResource(
										"/ucr/progra2/proyectoprogramado2/recursos/botones/recargar1.png")));
		jbtn_cambiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				cambiarLookAndFeel();
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		con.add(jbtn_cambiar, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.ipady = 3;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		con.add(new JSeparator(JSeparator.HORIZONTAL), gbc);

		jbtn_guardar = new Boton("Guardar");
		jbtn_guardar.tipoGuardar();
		jbtn_guardar.setEnabled(admin);
		jbtn_guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				guardarConfig();
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.ipady = 0;
		gbc.weightx = 0;
		con.add(jbtn_guardar, gbc);

		pack();
		setLocation(100,  20);
	}

	/**
	 * Guarda la configuracion de la aplicacion
	 */
	private void guardarConfig() {

		boolean detener = jcbx_cambiarFondo.isSelected();
		long segundos = 5;

		if (!jtxt_segundosCambio.getText().isEmpty()) {

			segundos = Long.parseLong(jtxt_segundosCambio.getText());

			if (segundos < 1) {
				segundos = 1;
				jtxt_segundosCambio.setText("1");
			}

		}

		control.guardarConfiguracion(detener, segundos);
		dispose();

	}

	private void cargarFondo() {

		File fondoOrigen;
		jfch_cargador = new JFileChooser();

		if (jfch_cargador.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

			fondoOrigen = jfch_cargador.getSelectedFile();
			int ix = fondoOrigen.getAbsolutePath().lastIndexOf(".");
			String extension = fondoOrigen.getAbsolutePath().substring(ix + 1);
			int numero = cantidadFicheros();

			String os = System.getProperty("os.name").toLowerCase();
			File fondoDestino = null;
			if (os.indexOf("win") >= 0) {

				fondoDestino = new File(System.getProperty("user.dir")
						+ "\\fondos\\" + (numero + 1) + "." + extension);

			} else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0
					|| os.indexOf("aix") >= 0) {
				fondoDestino = new File(System.getProperty("user.dir")
						+ "/fondos/" + (numero + 1) + "." + extension);

			}

			ImageInputStream input = null;
			ImageOutputStream output = null;
			try {
				input = new FileImageInputStream(fondoOrigen);
				output = new FileImageOutputStream(fondoDestino);
				byte[] buffer = new byte[3072];
				int bytesLectura;
				while ((bytesLectura = input.read(buffer)) > 0) {
					output.write(buffer, 0, bytesLectura);
				}

				if (JOptionPane
						.showConfirmDialog(
								null,
								"El fondo ha sido cargado con exito.\nPara que surta efecto ",
								"", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.restart();
				}

			} catch (IOException ioex) {
				ioex.printStackTrace();

			} finally {
				try {
					if (input != null)
						input.close();
					if (output != null)
						output.close();

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}

	private int cantidadFicheros() {
		int cantidad = 0;
		String os = System.getProperty("os.name").toLowerCase();
		File directorio = null;
		if (os.indexOf("win") >= 0) {

			directorio = new File(System.getProperty("user.dir") + "\\fondos");

		} else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0
				|| os.indexOf("aix") >= 0) {

			directorio = new File(System.getenv("user.dir") + "/fondos");

		}

		String[] cantidadFondos = directorio.list();

		if (cantidadFondos.length > 0) {
			cantidad = cantidadFondos.length;

		} else {
			cantidad = 0;
		}

		return cantidad;
	}

	private void cambiarLookAndFeel() {

		try {

			String laf = jcbx_lookAndFeels.getSelectedItem().toString();
			UIManager.setLookAndFeel(laf);
			SwingUtilities.updateComponentTreeUI(principal);

		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

	}

}
