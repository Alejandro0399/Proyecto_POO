package proyectoFinal;

public class Paciente extends Persona{
	public String sangre;
	public String alergias;
	public String sintomas;
	public String telefono;
	public Date fecha;
	
	Paciente(String nombre, String apellidos, int edad, String sangre, String alergias, String sintomas, String telefono, Date fecha){
		super(nombre, apellidos, edad);
		setSangre(sangre);
		setAlergias(alergias);
		setSintomas(sintomas);
		setTelefono(telefono);
		setFecha(fecha);
	}
	
	Paciente(){
		super();
	}
	
	public String getTelefono() {
		return this.telefono;
	}
	
	public Date getFecha() {
		return this.fecha;
	}
	
	public String getSangre() {
		return this.sangre;
	}
	
	public String getAlergias() {
		return this.alergias;
	}
	
	public String getSintomas() {
		return this.sintomas;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
