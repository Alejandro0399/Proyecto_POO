package proyectoFinal;

public enum Especialidades {
	Pediatra("Pediatía"), Neurologo("Neurología"), Dermatologo("Dermatología"), 
	Cardiologo("Cardiología"), Rehabilitacion("Medicina de Rehabilitación"), 
	Oftalmologia("Oftalmología"), Ortopedia("Ortopedia"), Urologo("Urología"), 
	General("Médico General");
	
	private final String nombre_especialidad;
	
	private Especialidades(String nombre) {
		this.nombre_especialidad = nombre;
	}
	
	public String toString() {
		return this.nombre_especialidad;
	}
	
}
