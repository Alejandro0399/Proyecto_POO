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
	
	
}
