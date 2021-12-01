package proyectoFinal;

public enum Especialidades {
	Pediatra("Pediatria"), Neurologo("Neurologo"), Dermatologo("Dermatologo"), 
	Cardiologo("Cardiologo"), Rehabilitacion("Medicina de Rehabilitacion"), 
	Oftalmologia("Oftalmologia"), Ortopedia("Ortopedia"), Urologo("Urologo"), 
	General("Medico General");
	
	private final String nombre_especialidad;
	
	private Especialidades(String nombre) {
		this.nombre_especialidad = nombre;
	}
	
	public String toString() {
		return this.nombre_especialidad;
	}
	
}
