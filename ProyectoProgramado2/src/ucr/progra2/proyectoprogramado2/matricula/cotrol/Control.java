package ucr.progra2.proyectoprogramado2.matricula.cotrol;

import java.awt.BorderLayout;
import java.util.List;

import ucr.progra2.proyectoprogramado2.login.Login;
import ucr.progra2.proyectoprogramado2.matricula.model.AdministrarCursos;
import ucr.progra2.proyectoprogramado2.matricula.model.AdministrarCursosMatriculados;
import ucr.progra2.proyectoprogramado2.matricula.model.AdministrarEstudiantes;
import ucr.progra2.proyectoprogramado2.matricula.model.AdministrarReportes;
import ucr.progra2.proyectoprogramado2.matricula.model.AdministrarUsuarios;
import ucr.progra2.proyectoprogramado2.matricula.view.Config;
import ucr.progra2.proyectoprogramado2.matricula.view.Cursos;
import ucr.progra2.proyectoprogramado2.matricula.view.Escritorio;
import ucr.progra2.proyectoprogramado2.matricula.view.Estudiantes;
import ucr.progra2.proyectoprogramado2.matricula.view.Matricular;
import ucr.progra2.proyectoprogramado2.matricula.view.Menu;
import ucr.progra2.proyectoprogramado2.matricula.view.MenuBarra;
import ucr.progra2.proyectoprogramado2.matricula.view.Reporte;
import ucr.progra2.proyectoprogramado2.matricula.view.Usuarios;
import ucr.progra2.proyectoprogramado2.matricula.view.VPri;

public class Control {

	private VPri principal;
	private Menu menu;
	private MenuBarra menuBarra;
	private Escritorio escritorio;

	private Estudiantes estudiantes;
	private Config config;
	private Usuarios usuarios;
	private Cursos cursos;
	private Matricular matricular;
	private Reporte reporte;

	private AdministrarEstudiantes adminEstudiantes;
	private AdministrarUsuarios adminUsuarios;
	private AdministrarCursos adminCursos;
	private AdministrarCursosMatriculados adminCursosMatriculados;
	private AdministrarReportes adminReportes;

	private boolean administrador = false;

	private Login login;

	public Control(boolean ad, Login log) {
		administrador = ad;
		login = log;

		principal = new VPri();
		menu = new Menu(this);
		principal.getContenenedor().add(menu, BorderLayout.NORTH);
		escritorio = new Escritorio();
		principal.getContenenedor().add(escritorio, BorderLayout.CENTER);
		menuBarra = new MenuBarra(this);
		principal.setJMenuBar(menuBarra);
		principal.Visible();

		// ****************************

		adminEstudiantes = new AdministrarEstudiantes(this);
		adminUsuarios = new AdministrarUsuarios(this);
		adminCursos = new AdministrarCursos(this);
		adminCursosMatriculados = new AdministrarCursosMatriculados();
		adminReportes = new AdministrarReportes();

	}

	public void restart() {

		principal.cerrar();
		principal = new VPri();
		escritorio = null;
		escritorio = new Escritorio();
		principal.getContenenedor().add(menu, BorderLayout.NORTH);
		principal.getContenenedor().add(escritorio, BorderLayout.CENTER);
		principal.Visible();

	}

	public void cerrarSesion() {
		principal.cerrar();
		login.visibleLogin();
	}

	public void addConfig() {
		config = new Config(this, principal.getFrame(), administrador);
		escritorio.addVentana(config);
	}

	public void addEstudiantes() {
		estudiantes = new Estudiantes(this);
		escritorio.addVentana(estudiantes);
	}

	public void addUsuarios() {
		usuarios = new Usuarios(this, administrador);
		escritorio.addVentana(usuarios);
	}

	public void addCursos() {
		cursos = new Cursos(this);
		escritorio.addVentana(cursos);
	}

	public void addMatricular(String carnet) {
		matricular = new Matricular(this, carnet);
		matricular.setDatosTabla(adminCursosMatriculados
				.getCursosMatriculadosPorEstudiante(carnet));
		escritorio.addVentana(matricular);
	}

	public void addReporte() {
		reporte = new Reporte(this);
		escritorio.addVentana(reporte);
	}

	// ********************************

	public void guardarConfiguracion(boolean detener, long segundos) {
		escritorio.detenerCambioImagen(detener);
		escritorio.setSegundos(segundos);
	}

	// ********************************

	public void insertarEstudiante(String carnet, String nombre,
			String apellido1, String apellido2, String direccion, int edad) {
		adminEstudiantes.insertar(carnet, nombre, apellido1, apellido2,
				direccion, edad);
	}

	public void eliminarEstudiante(String carnet) {
		adminEstudiantes.eliminar(carnet);
	}

	public void modificarEstudiante(String carnet, String nombre,
			String apellido1, String apellido2, String direccion, int edad) {
		adminEstudiantes.modificar(carnet, nombre, apellido1, apellido2,
				direccion, edad);
	}

	/**
	 * Consulta la tabla por el carnet para mostrar los datos del estudiante
	 * 
	 * @param carnet
	 */
	public void consultarEstudiante(String carnet) {
		adminEstudiantes.consultarDatos(carnet);
	}

	/**
	 * Setea los datos del estudiante en los campos de texto
	 * 
	 * @param carnet
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 * @param direccion
	 * @param edad
	 */
	public void setDatosEstudiante(String carnet, String nombre,
			String apellido1, String apellido2, String direccion, String edad) {
		estudiantes.setConsulta(carnet, nombre, apellido1, apellido2,
				direccion, edad);
	}

	public void setCarnets(List<String> carnets) {
		estudiantes.setCarnets(carnets);
	}

	public List<String> getCarnets() {
		List<String> carnets = adminEstudiantes.getCarnets();
		return carnets;
	}

	public void generarReporteEstudiantes() {
		estudiantes.setDatosTabla(adminEstudiantes.generarReporte());
	}

	// ********************************

	public void insertarUsuario(String idUsuario, int admin, String userId,
			String pass, String nombre, String apellido1, String apellido2,
			String email) {

		adminUsuarios.insertar(idUsuario, admin, userId, pass, nombre,
				apellido1, apellido2, email);
	}

	public void eliminarUsuario(String nomUser) {
		adminUsuarios.eliminar(nomUser);
	}

	public void modificarUsuario(String idUsuario, int admin, String userId,
			String pass, String nombre, String apellido1, String apellido2,
			String email) {
		adminUsuarios.modificar(idUsuario, admin, userId, pass, nombre,
				apellido1, apellido2, email);
	}

	public void consultarUsuario(String idUsuario) {
		adminUsuarios.consultarDatos(idUsuario);
	}

	public void setDatosUsuario(String idUsuario, int admin, String userId,
			String pass, String nombre, String apellido1, String apellido2,
			String email) {
		usuarios.setDatosUsuario(idUsuario, admin, userId, pass, nombre,
				apellido1, apellido2, email);
	}

	public void setUsuarios(List<String> users) {
		usuarios.setUsuariosAgregados(users);
	}

	public List<String> getUsuarios() {
		List<String> usuarios = adminUsuarios.getUsuarios();
		return usuarios;
	}

	public void generarReporteUsuarios() {
		usuarios.setReporte(adminUsuarios.generarReporte());
	}

	/**********************************
	 * 
	 * 
	 * 
	 * ********************************/

	public void insertarCurso(String codigo, String nombre, int creditos) {
		adminCursos.insertar(codigo, nombre, creditos);
	}

	public void eliminarCurso(String codigo) {
		adminCursos.eliminar(codigo);
	}

	public void modificarCurso(String codigo, String nombre, int creditos) {
		adminCursos.modificar(codigo, nombre, creditos);
	}

	public void consultarCurso(String codigo) {
		adminCursos.consultarDatos(codigo);
	}

	public void setDatosCurso(String codigo, String nombre, int creditos) {
		cursos.setDatosCurso(codigo, nombre, creditos);
	}

	public void setCodigos(List<String> codigos) {
		cursos.setCodigos(codigos);
	}

	public List<String> getCodigos() {
		List<String> codigos = adminCursos.getCodigos();
		return codigos;
	}

	/**********************************
	 * 
	 * Metodos de administración de la tabla CursosMatriculados
	 * 
	 * ********************************/

	public void insertarCursoMatriculado(String carnet, String codigo,
			int semestre, int year, String nota) {
		adminCursosMatriculados.insertar(carnet, codigo, semestre, year, nota);
	}

	public void eliminarCursoMatriculado(String carnet, String codigo,
			int semestre, int year) {
		adminCursosMatriculados.eliminar(carnet, codigo, semestre, year);
	}

	public void mostrarMatriculados(String carnet) {
		estudiantes.setDatosTabla(adminCursosMatriculados
				.getCursosMatriculadosPorEstudiante(carnet));
	}

	public void mostrarMatriculadosActualmente(String carnet) {
		matricular.setDatosTabla(adminCursosMatriculados
				.getCursosMatriculadosPorEstudiante(carnet));
	}

	/**********************************
	 * 
	 * Metodos de la generacion de reportes
	 * 
	 * ********************************/

	public void reporteEstudiantes(int atributos) {
		reporte.setTablaReporteEstudiante(adminReportes
				.reporteEstudiantes(atributos));
	}

	public void reporteUsuarios(int atributos) {
		reporte.setTablaReporteUsuarios(adminReportes.reporteUsuarios(atributos));
	}

	public void reporteCursos(int atributos) {
		reporte.setTablaReporteCursos(adminReportes.reporteCursos(atributos));
	}

	public void reporteCursosMatriculados(int atributos) {
		reporte.setTablaReporteCursosMatriculados(adminReportes
				.reporteCursosMatriculados(atributos));
	}

}
