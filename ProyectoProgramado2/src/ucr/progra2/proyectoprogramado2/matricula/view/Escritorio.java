package ucr.progra2.proyectoprogramado2.matricula.view;

import java.awt.Component;
import java.awt.Graphics;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class Escritorio extends JDesktopPane implements Runnable, Serializable {

	/**
	 * Generado por el IDE
	 */
	private static final long serialVersionUID = 2237506934904658287L;
	private ArrayList<ImageIcon> imagenes = new ArrayList<ImageIcon>();
	private boolean detenido = false;
	private int contador = 0;
	private long segundos = 5000;

	private File directorio;

	public Escritorio() {
		initImagenes();
		new Thread(this).start();
	}

	private void initImagenes() {
		
		try{

			String os = System.getProperty("os.name").toLowerCase();
			if (os.indexOf("win") >= 0) {

				directorio = new File(System.getProperty("user.dir") + "\\fondos");
				directorio.mkdirs();

			} else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0
					|| os.indexOf("aix") >= 0) {

				directorio = new File(System.getenv("user.dir") + "/fondos");
				directorio.mkdirs();
			}

			File archivos[] = directorio.listFiles();
			
			if (archivos.length > 0) {

				int index;
				String ruta, extension;
				for (File arch : archivos) {

					ruta = arch.getAbsolutePath();
					index = ruta.lastIndexOf('.');
					if (index != -1) {
						extension = ruta.substring(index + 1);
						if (extension.equals("jpg") || extension.endsWith("png")) {
							imagenes.add(new ImageIcon(arch.getAbsolutePath()));
						}
					}
				}
				if (imagenes.size() == 0)
					initImagenesDefecto();

			} else {
				
				initImagenesDefecto();
				
			}
		
			
		}catch(Exception ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al cargar el directorio de imagenes personalizadas");
			initImagenesDefecto();
		}
		
	}

	private void initImagenesDefecto(){
		imagenes.add(new ImageIcon(
				getClass()
						.getResource(
								"/ucr/progra2/proyectoprogramado2/recursos/fondos/fondo1.jpg")));
		imagenes.add(new ImageIcon(
				getClass()
						.getResource(
								"/ucr/progra2/proyectoprogramado2/recursos/fondos/fondo2.jpg")));
		imagenes.add(new ImageIcon(
				getClass()
						.getResource(
								"/ucr/progra2/proyectoprogramado2/recursos/fondos/fondo3.jpg")));

	}
	
	/**
	 * Agrega un imagen nueva
	 * 
	 * @param img
	 */
	public void addImagen(ImageIcon img) {
		imagenes.add(img);
		initImagenes();
	}

	/**
	 * Elimina una imagen
	 * 
	 * @param indice
	 */
	public void elimiarImagen(int indice) {
		imagenes.remove(indice);
	}

	/**
	 * Agrega un JInternalFrame
	 * 
	 * @param ven
	 */
	public void addVentana(Object ven) {
		Component[] com = getComponents();
		boolean existe = false;
		for (short x = 0; x < com.length; x++) {
			if (com[x].getClass() == ven.getClass()) {
				existe = true;
			}
		}

		if (!existe){
			JInternalFrame vens = (JInternalFrame)ven;
			add((JInternalFrame) vens);
			vens.setVisible(true);
		}

	}

	/**
	 * Cambia el contador. Llama al metodo repaint
	 */
	private void cambiarImagen() {
		if (contador == (imagenes.size() - 1)) {
			contador = 0;
		} else
			contador++;
		repaint();
	}

	/**
	 * Detiene o continua con el cambio de la imagenes
	 * 
	 * @param cambiar
	 *            - boolean
	 */
	public void detenerCambioImagen(boolean d) {
		detenido = !d;
	}

	/**
	 * Indica los segundos para el cambio de imagenes
	 * 
	 * @param s
	 *            - long
	 */
	public void setSegundos(long s) {
		segundos = (s * 1000);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(imagenes.get(contador).getImage(), 0, 0, getWidth(),
				getHeight(), this);
		setOpaque(false);
		super.paint(g);
	}

	@Override
	public void run() {
		while (true) {

			try {
				Thread.sleep(segundos);
				while (detenido) {
					System.out.println();
				}
				cambiarImagen();

			} catch (InterruptedException iex) {

			}
		}
	}

}
