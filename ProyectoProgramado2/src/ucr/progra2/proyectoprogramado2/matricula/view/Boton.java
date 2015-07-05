package ucr.progra2.proyectoprogramado2.matricula.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Boton extends JButton {
	
	/**
	 * Generado por el IDE
	 */
	private static final long serialVersionUID = 9086306696471127173L;
	
	public Boton(String leyenda){
		setText(leyenda);
	}
	
	public void tipoInsertar(){
		ImageIcon imagen1 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/agregar.png"));
		ImageIcon imagen2 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/agregar1.png"));
		setIcon(imagen1);
		setRolloverIcon(imagen2);
	}
	
	public void tipoEliminar(){
		ImageIcon imagen1 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/eliminar.png"));
		ImageIcon imagen2 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/eliminar1.png"));
		setIcon(imagen1);
		setRolloverIcon(imagen2);
	}
	
	public void tipoModificar(){
		ImageIcon imagen1 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/modificar.png"));
		ImageIcon imagen2 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/modificar1.png"));
		setIcon(imagen1);
		setRolloverIcon(imagen2);
	}
	public void tipoBuscar(){
		ImageIcon imagen1 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/buscar.png"));
		ImageIcon imagen2 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/buscar1.png"));
		setIcon(imagen1);
		setRolloverIcon(imagen2);
	}
	public void tipoLimpiar(){
		ImageIcon imagen1 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/limpiar.png"));
		ImageIcon imagen2 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/limpiar1.png"));
		setIcon(imagen1);
		setRolloverIcon(imagen2);
	}
	public void tipoReporte(){
		ImageIcon imagen1 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/reporte.png"));
		ImageIcon imagen2 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/reporte1.png"));
		setIcon(imagen1);
		setRolloverIcon(imagen2);
	}
	public void tipoMatricular(){
		ImageIcon imagen1 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/matricular.png"));
		ImageIcon imagen2 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/matricular1.png"));
		setIcon(imagen1);
		setRolloverIcon(imagen2);
	}
	public void tipoGuardar(){
		ImageIcon imagen1 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/guardar.png"));
		ImageIcon imagen2 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/guardar1.png"));
		setIcon(imagen1);
		setRolloverIcon(imagen2);
	}
	public void tipoMostrarMatriculados(){
		ImageIcon imagen1 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/mostrarMatriculados.png"));
		ImageIcon imagen2 = new ImageIcon(getClass().getResource("/ucr/progra2/proyectoprogramado2/recursos/botones/mostrarMatriculados1.png"));
		setIcon(imagen1);
		setRolloverIcon(imagen2);
	}
	
	
}
