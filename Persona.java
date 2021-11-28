package proyectoFinal;

public class Persona {
	protected String nombre;
	protected String apellidos;
	protected int edad;
	
	
	Persona(String nombre, String apellidos, int edad){
		setNombre(nombre);
		setApellidos(apellidos);
		setEdad(edad);
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
}
