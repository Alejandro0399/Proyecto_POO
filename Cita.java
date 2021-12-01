package proyectoFinal;


public class Cita {
	protected Paciente p1;
	protected Doctor d1;
	protected DateTime fecha;
	
	public Cita(Paciente p1, Doctor d1, DateTime fecha){
		setPaciente(p1);
		setDoctor(d1);
		setFecha(fecha);
	}
	
	public void setPaciente(Paciente p1) {
		this.p1 = p1;
	}
	
	public void setDoctor(Doctor d1) {
		this.d1 = d1;
	}
	
	public void setFecha(DateTime d1) {
		this.fecha = d1;
	}
	
	public Doctor getDoctor() {
		return this.d1;
	}
	
	public Paciente getPaciente() {
		return this.p1;
	}
	
	public DateTime getFecha() {
		return this.fecha;
	}
	
}
