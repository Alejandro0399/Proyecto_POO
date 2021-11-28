package proyectoFinal;

public class Paciente extends Persona{
	public String sangre;
	public String alergias;
	public String sintomas;
	
	Paciente(String nombre, String apellidos, int edad, String sangre, String alergias, String sintomas){
		super(nombre, apellidos, edad);
		setSangre(sangre);
		setAlergias(alergias);
		setSintomas(sintomas);
	}
	
	public void setSangre(String sangre) {
		this.sangre = sangre;
	}
	
	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}
	
	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}
}
