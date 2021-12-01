package proyectoFinal;

public class Doctor extends Persona {
	protected String especialidad;
	
	Doctor(String nombre, String apellidos, int edad, String especialidad){
		super(nombre, apellidos, edad);
		setEspecialidad(especialidad);
	}
	
	Doctor(){
		super();
	}
	
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	
	public String getEspecialidad() {
		return this.especialidad;
	}
	
	public String toString() {
		return this.getNombre() + " " + this.getApellidos();
	}
	
	public String table_format() {
		return this.getNombre() + "_" + this.getApellidos().replace(" ", "_");
	}
	
}
