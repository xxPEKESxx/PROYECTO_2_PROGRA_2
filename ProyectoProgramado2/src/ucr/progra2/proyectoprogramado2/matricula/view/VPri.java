package ucr.progra2.proyectoprogramado2.matricula.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class VPri implements Runnable {

	private JFrame jfra_ventanaPrincipal;

	private JLabel jlbl_fechaHora;
	private Calendar calendario;
	private String hora;

	public VPri() {
		initGui();
		new Thread(this).start();
	}

	private void initGui() {

		jfra_ventanaPrincipal = new JFrame();
		jfra_ventanaPrincipal.setTitle("Registro de Estudiantes");
		jfra_ventanaPrincipal
				.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jfra_ventanaPrincipal.setLayout(new BorderLayout());

		JPanel jpnl_informacion = new JPanel();
		jpnl_informacion.setBorder(BorderFactory.createLoweredBevelBorder());
		jpnl_informacion.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_informacion.add(new JLabel(
				"PROYECTO PROGRAMADO 2. UCR - PROGRAMACION 2 (IF-3000)"), gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_informacion.add(new JLabel(
				"RODOLFO SEQUEIRA R. - ALEXANDER CANALES S."), gbc);

		calendario = new GregorianCalendar();
		hora = calendario.getTime().toString();
		jlbl_fechaHora = new JLabel(hora);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jpnl_informacion.add(jlbl_fechaHora, gbc);

		jfra_ventanaPrincipal.getContentPane().add(jpnl_informacion,
				BorderLayout.SOUTH);

	}

	public Container getContenenedor() {
		return jfra_ventanaPrincipal.getContentPane();
	}

	public void setJMenuBar(MenuBarra mb) {
		jfra_ventanaPrincipal.setJMenuBar(mb);

	}

	public void Visible() {
		jfra_ventanaPrincipal.setPreferredSize(new Dimension(1000, 700));
		jfra_ventanaPrincipal.pack();
		jfra_ventanaPrincipal.setVisible(true);
		jfra_ventanaPrincipal.setLocationRelativeTo(null);
	}

	public void cerrar() {
		jfra_ventanaPrincipal.dispose();
	}

	public JFrame getFrame() {
		return jfra_ventanaPrincipal;
	}

	@Override
	public void run() {

		while (true) {
			try {
				Thread.sleep(1000);
				calendario = Calendar.getInstance();
				hora = calendario.getTime().toString().toUpperCase();
				jlbl_fechaHora.setText(hora);

			} catch (InterruptedException iex) {
				iex.printStackTrace();

			}
		}
	}
}