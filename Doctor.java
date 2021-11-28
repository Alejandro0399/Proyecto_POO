package proyectoFinal;

public class Doctor extends Persona {
	protected String especialidad;
	protected String direccion;
	
	Doctor(String nombre, String apellidos, int edad, String especialidad, String direccion){
		super(nombre, apellidos, edad);
		setEspecialidad(especialidad);
		setDireccion(direccion);
	}
	
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
