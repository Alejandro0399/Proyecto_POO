package proyectoFinal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Gui{
	
	private JTable tblPaciente, tblDoctor, tblCita;
	private JScrollPane jspPaciente, jspDoctor, jspCita;
	private DefaultTableModel dtmPaciente, dtmDoctor, dtmCita;
	private static JTextField drNombre, drApellidos, drEdad, txtNombre, txtApellidos, txtTelefono, txtFecha, txtEdad, txtSangre, txtAlergias, txtSintomas, txtHora;
	private JLabel lblAlergias, lblEspecialidad, lblSeleccion, lblSintomas, lblEdad, lblNombre, lblApellidos, lblDoctores, lblPacientes, lblHora, lblTelefono, lblFecha, lblSangre;

	
	private JButton btnUpdate, btnGuardar, btnNuevo, btnDelete, btnGuardarDr, btnDeleteDr, btnDoctor, btnPaciente, btnRegresar;
	private JFrame frame1, frame2, frame3, frame4, frame5;
	private Doctor doctor;
	private static Date fecha_referencia = Date.previousDate(new Date());
	private static JComboBox<String> combo1;
	private static String especialidad;
	
	/**
	 * Constructor de la GUI, genera todos los componentes que se van a usar en la GUI
	 */
	public Gui() {
		frame1 = new JFrame();
		frame2 = new JFrame();
		frame3 = new JFrame();
		frame4 = new JFrame();
		frame5 = new JFrame();
		frame1.setVisible(false);
		frame2.setVisible(false);
		frame3.setVisible(false);
		frame4.setVisible(false);
		frame5.setVisible(false);
		
		//Combo box para mostrar especialidades
		combo1 = new JComboBox<String>();
		
		//Text Fields
		drNombre = new JTextField();
		drApellidos = new JTextField();
		drEdad = new JTextField();;
		txtNombre = new JTextField();
		txtApellidos = new JTextField();
		txtTelefono = new JTextField();
		txtFecha = new JTextField();
		txtSangre = new JTextField();
		txtAlergias = new JTextField();
		txtSintomas = new JTextField();
		txtEdad = new JTextField();
		txtHora = new JTextField();
		
		//Botones
		btnGuardarDr = new JButton("Guardar");
		btnGuardar = new JButton("Guardar");
		btnPaciente = new JButton("Control Citas");
		btnDoctor = new JButton("Dar de alta a Doctor");
		btnGuardar = new JButton("Guardar");
		btnNuevo = new JButton("Limpiar");
		btnDelete = new JButton("Eliminar");
		btnDeleteDr = new JButton("Eliminar Doctor");
		btnRegresar = new JButton("Regresar");
		btnUpdate = new JButton("Actualizar");
		
		//Labels
		lblNombre = new JLabel("Nombre:");
		lblApellidos = new JLabel("Apellidos:");
		lblEdad = new JLabel("Edad:");
		lblEspecialidad = new JLabel("Especialidad:");
		lblTelefono = new JLabel("Telefono:");
		lblSangre = new JLabel("Tipo de Sangre:");
		lblAlergias = new JLabel("Alergias:");
		lblSintomas = new JLabel("Sintomas:");
		lblFecha = new JLabel("Fecha(dd/mm/yyyy):");
		lblDoctores = new JLabel("DOCTORES");
		lblPacientes = new JLabel("PACIENTES");
		lblHora = new JLabel("Hora(formato 24h):");
		lblSeleccion = new JLabel("Selecciona a un doctor para generar tu cita");
		
		//Tablas
		dtmPaciente = new DefaultTableModel(null, ConexionAccess.TITULOS_PACIENTES);
		tblPaciente = new JTable(dtmPaciente);
		jspPaciente = new JScrollPane(tblPaciente);
		dtmDoctor = new DefaultTableModel(null, ConexionAccess.TITULOS_DOCTOR);
		tblDoctor = new JTable(dtmDoctor);
		jspDoctor = new JScrollPane(tblDoctor);
		dtmCita = new DefaultTableModel(null, ConexionAccess.TITULOS_DOCTOR);
		tblCita = new JTable(dtmCita);
		jspCita = new JScrollPane(tblCita);
		
		
		//Callbacks para acciones
		btnPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				frame1.setVisible(false);
				btnPacienteEvent(evt);
			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnUpdateEvent(evt, doctor);
			}
		});
		
		btnDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				frame1.setVisible(false);
				btnDoctorEvent(evt);
			}
		});
		
		btnGuardarDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				crear_doctor(frame2);
				frame2.setVisible(false);
			}
		});
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				crear_cita(frame3, doctor);
			}
		});
		
		btnDeleteDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				frame1.setVisible(false);
				btnDeleteDrEvent(evt);
				
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnDeleteEvent(evt);
			}
		});
		
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				limpiar_pantalla_paciente();
			}
		});
		
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				frame2.setVisible(false);
				frame3.setVisible(false);
				frame4.setVisible(false);
				frame5.setVisible(false);
				empezar_gui();
			}
		});
		
		tblDoctor.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				tblDoctorMouse(evt);
			}
		});
		
		tblPaciente.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				tblPacienteMouse(evt);
			}
		});
		
		tblCita.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				doctor = tblDoctorCitas(evt);
				frame5.setVisible(false);
				guardar_paciente();
			}
		});
		combo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				select_especialidad();
			}
		});
		
	}
	
	/**
	 * Metodo que se usa para comenzar a correr la GUI
	 */
	public void empezar_gui() {
		bienvenida_layout();
	}
	
	/**
	 * Metodo que se usa para generar la ventana de bienvenida
	 */
	public void bienvenida_layout() {
		frame1.setLayout(null);
		frame1.setTitle("Bienvenido");
		frame1.setSize(450, 350);
		frame1.setVisible(true);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame1.add(btnPaciente);
		frame1.add(btnDoctor);
		frame1.add(lblDoctores);
		frame1.add(lblPacientes);
		frame1.add(btnDeleteDr);
		
		lblDoctores.setBounds(130, 50, 100, 25);
		btnDoctor.setBounds(130, 80, 150, 25);
		btnDeleteDr.setBounds(130, 120, 150, 25);
		lblPacientes.setBounds(130, 170, 100, 25);
		btnPaciente.setBounds(130, 200, 150, 25);
		
		int status= ConexionAccess.citas_previas(fecha_referencia);
		if(status > 0) {
			JOptionPane.showMessageDialog(frame1.getRootPane(), "Citas del dia anterior eliminadas");
		}
	}
	
	/**
	 * Metodo que genera la ventana para dar de alta doctores
	 */
	public void alta_doctor() {
		frame2.setLayout(null);
		frame2.setTitle("Doctores");
		frame2.setSize(400, 300);
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		limpiar_pantalla_doctor();

		frame2.add(drNombre);
		frame2.add(lblNombre);
		
		frame2.add(drApellidos);
		frame2.add(lblApellidos);
		
		frame2.add(drEdad);
		frame2.add(lblEdad);
		
		frame2.add(lblEspecialidad);
		
		frame2.add(combo1);
		for(int i = 0; i < Especialidades.values().length; i++) {
			combo1.addItem(Especialidades.values()[i].toString());
		}
		
		
		frame2.add(btnGuardarDr);
		frame2.add(btnRegresar);
		
		lblNombre.setBounds(20,50,100,20);
		drNombre.setBounds(120,50,200,20);
		
		lblApellidos.setBounds(20, 80, 100, 20);	
		drApellidos.setBounds(120, 80, 200, 20);
		
		lblEdad.setBounds(20,110,100,20);
		drEdad.setBounds(120,110,200,20);
		
		lblEspecialidad.setBounds(20,140,100,20);
		combo1.setBounds(120,140,200,20);
		
		
		btnGuardarDr.setBounds(150, 170, 100, 25);
		btnRegresar.setBounds(150, 210, 100, 25);
	}
	
	/**
	 * Metodo usado para generar la ventana para generar las citas
	 */
	public void guardar_paciente() {
		frame3.setLayout(null);
		frame3.setTitle("Registrar Paciente");
		frame3.setSize(600, 600);
		frame3.setVisible(true);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		
		frame3.add(lblNombre);
		frame3.add(txtNombre);
		
		frame3.add(lblHora);
		frame3.add(txtHora);
		
		frame3.add(lblApellidos);
		frame3.add(txtApellidos);
		
		frame3.add(lblTelefono);
		frame3.add(txtTelefono);
		
		frame3.add(lblFecha);
		frame3.add(txtFecha);
		
		frame3.add(lblSangre);
		frame3.add(txtSangre);
		
		frame3.add(lblAlergias);
		frame3.add(txtAlergias);
		
		frame3.add(lblSintomas);
		frame3.add(txtSintomas);
		
		frame3.add(lblEdad);
		frame3.add(txtEdad);
		
		frame3.add(btnGuardar);
		frame3.add(btnNuevo);
		frame3.add(btnDelete);
		frame3.add(btnRegresar);
		frame3.add(btnUpdate);
		
		frame3.add(jspPaciente);
		
		
		lblNombre.setBounds(20,20,100,20);
		txtNombre.setBounds(150,20,200,20);
		
		lblApellidos.setBounds(20, 50, 100, 20);	
		txtApellidos.setBounds(150, 50, 200, 20);
		
		lblTelefono.setBounds(20,80,100,20);
		txtTelefono.setBounds(150,80,200,20);
		
		lblEdad.setBounds(20,110,100,20);
		txtEdad.setBounds(150,110,200,20);
		
		lblSangre.setBounds(20,140,100,20);
		txtSangre.setBounds(150,140,200,20);
		
		lblAlergias.setBounds(20,170,100,20);
		txtAlergias.setBounds(150,170,200,20);
		
		lblSintomas.setBounds(20,200,100,20);
		txtSintomas.setBounds(150,200,200,20);
		
		lblFecha.setBounds(20,230,130,20);
		txtFecha.setBounds(150,230,200,20);
		
		lblHora.setBounds(20, 260, 130, 20);
		txtHora.setBounds(150, 260, 200, 20);
		
		btnGuardar.setBounds(390, 100, 100, 25);
		btnNuevo.setBounds(390, 140, 100, 25);
		btnDelete.setBounds(390, 60, 100, 25);
		btnRegresar.setBounds(390, 180, 100, 25);
		btnUpdate.setBounds(390, 220, 100, 25);
		
		jspPaciente.setBounds(20,300,550,200);
		
		mostrar_tablaPacientes();
		
	}
	
	/**
	 * Metodo usado para generar la ventana para eliminar el registro de doctores
	 */
	public void eliminar_doctor() {
		frame4.setLayout(null);
		frame4.setTitle("Eliminar Doctor");
		frame4.setSize(600, 600);
		frame4.setVisible(true);
		frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame4.add(jspDoctor);
		frame4.add(btnRegresar);
		
		jspDoctor.setBounds(20, 20, 550, 400);
		btnRegresar.setBounds(250, 450, 100, 25);
		mostrar_tablaDr(tblDoctor);
	}
	
	/**
	 * Metodo usado para generar la ventana para seleccionar al doctor
	 * para generar citas
	 */
	public void seleccionar_doctor() {
		frame5.setLayout(null);
		frame5.setTitle("Registrar Paciente");
		frame5.setSize(600, 600);
		frame5.setVisible(true);
		frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		frame5.add(jspCita);
		frame5.add(lblSeleccion);
		frame5.add(btnRegresar);
		
		jspCita.setBounds(20, 50, 550, 400);
		lblSeleccion.setBounds(180, 20, 300, 20);
		btnRegresar.setBounds(250, 480, 100, 25);
		mostrar_tablaDr(tblCita);
	}
	
	/**
	 * Metodo utilizado para registrar al doctor en la base de datos
	 */
	public void crear_doctor(JFrame frame) {
		Doctor d1 = new Doctor(drNombre.getText(), drApellidos.getText(), Integer.parseInt(drEdad.getText().strip()), especialidad);
		int status = ConexionAccess.guardar_datos(d1);
		if(status > 0) {
			JOptionPane.showMessageDialog(frame.getRootPane(), "Datos del doctor guardados");
		}
		else if(status == -2) {
			JOptionPane.showMessageDialog(frame.getRootPane(), "Datos ya se encuentran en la base de datos");
		}
		bienvenida_layout();
	}
	
	/**
	 * Metodo utilizado para generar la cita
	 * @param d1, doctor con el que se quiere hacer la cita
	 */
	public void crear_cita(JFrame frame, Doctor d1) {
		String array [] = txtFecha.getText().split("/");
		Date fecha = new Date(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]));
		int hora = Integer.parseInt(txtHora.getText().split(":")[0]);
		int minuto = Integer.parseInt(txtHora.getText().split(":")[1]);
		Paciente p1 = new Paciente(txtNombre.getText(),
								   txtApellidos.getText(),
								   Integer.parseInt(txtEdad.getText()),
								   txtSangre.getText(),
								   txtAlergias.getText(),
								   txtSintomas.getText(),
								   txtTelefono.getText());
		DateTime fecha_cita = new DateTime(fecha.get_day(), fecha.get_month(), fecha.get_year(), hora, minuto, 0);
		Cita cita = new Cita(p1, d1, fecha_cita);
		int status = ConexionAccess.guardar_datos(cita);
		if(status > 0) {
			JOptionPane.showMessageDialog(frame.getRootPane(), "Cita generada");
			limpiar_pantalla_paciente();
		}
		else if(status == -2) {
			JOptionPane.showMessageDialog(frame.getRootPane(), "Paciente ya tiene cita");
			cargar_datos(cita);
		}
		else if(status == -3) {
			JOptionPane.showMessageDialog(frame.getRootPane(), "Hora no disponible");
		}
		mostrar_tablaPacientes();
	}
	
	public static void select_especialidad() {
		especialidad = (String) combo1.getSelectedItem();
	}
	
	/**
	 * Metodo que se usa para poner en la gui la una tabla
	 * @param tabla, se especifica que tabla se quiere mostrar
	 */
	public void mostrar_tablaDr(JTable tabla) {
		ConexionAccess.read_drtbl(tabla);
	}
	
	/**
	 * Metodo que se usa para mostrar la informacion de los pacientes en una tabla
	 */
	public void mostrar_tablaPacientes() {
		ConexionAccess.read_pttbl(tblPaciente, doctor);
	}
	
	/**
	 * Metodo que se usa para limpiar todos los campos en la pantalla de
	 * crear citas
	 */
	public static void limpiar_pantalla_paciente() {
		txtNombre.setText("");
		txtApellidos.setText("");
		txtEdad.setText("");
		txtSangre.setText("");
		txtAlergias.setText("");
		txtSintomas.setText("");
		txtTelefono.setText("");
		txtFecha.setText("");
		txtHora.setText("");
	}
	
	/**
	 * Metodo que se usa para limpiar todos los campos en la pantalla de
	 * crear doctor
	 */
	public static void limpiar_pantalla_doctor() {
		drNombre.setText("");
		drApellidos.setText("");
		drEdad.setText("");
	}
	
	/**
	 * Metodo que se usa para cargar los datos en pantalla de la cita seleccionada
	 * @param c1, cita seleccionada
	 */
	public static void cargar_datos(Cita c1) {
		Paciente p1 = c1.getPaciente();
		txtNombre.setText(p1.getNombre());
		txtApellidos.setText(p1.getApellidos());
		txtTelefono.setText(p1.getTelefono());
		txtFecha.setText(c1.getFecha().toString());
		txtHora.setText(c1.getFecha().formato());
		txtEdad.setText(String.valueOf(p1.getEdad()));
		txtSangre.setText(p1.getSangre());
		txtAlergias.setText(p1.getAlergias());
		txtSintomas.setText(p1.getSintomas());
	}
	
	//Eventos de botones
	protected void btnPacienteEvent(ActionEvent evt) {
		seleccionar_doctor();
	}
	
	protected void btnDoctorEvent(ActionEvent evt) {
		alta_doctor();
	}
	
	protected void btnDeleteDrEvent(ActionEvent evt) {
		eliminar_doctor();
	}

	protected void btnDeleteEvent(ActionEvent evt) {
		if(txtNombre.getText().compareTo("") == 0 || txtApellidos.getText().compareTo("") == 0) {
			JOptionPane.showMessageDialog(frame3.getRootPane(), "Ingresa Nombre y Apellidos para eliminar la cita");
		}
		Paciente p1 = new Paciente();
		p1.setNombre(txtNombre.getText());
		p1.setApellidos(txtApellidos.getText());
		Cita c1 = new Cita(p1, doctor, null);
		int status = ConexionAccess.delete_data(c1);
		if(status > 0) {
			JOptionPane.showMessageDialog(frame3.getRootPane(), "La cita del paciente " +p1.toString() + " fue eliminada");
		}
		else {
			JOptionPane.showMessageDialog(frame3.getRootPane(), "El paciente " + p1.toString() + " no tiene cita");
		}
		limpiar_pantalla_paciente();
		mostrar_tablaPacientes();
	}
	
	protected void btnUpdateEvent(ActionEvent evt, Doctor d1) {
		if(txtNombre.getText().compareTo("") == 0 && txtApellidos.getText().compareTo("") == 0) {
			JOptionPane.showMessageDialog(frame3.getRootPane(), "Ingresa el nombre y apellidos");
			return;
		}
		String array [] = txtFecha.getText().split("/");
		Date fecha = new Date(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]));
		int hora = Integer.parseInt(txtHora.getText().split(":")[0]);
		int minuto = Integer.parseInt(txtHora.getText().split(":")[1]);
		Paciente p1 = new Paciente(txtNombre.getText(),
								   txtApellidos.getText(),
								   Integer.parseInt(txtEdad.getText()),
								   txtSangre.getText(),
								   txtAlergias.getText(),
								   txtSintomas.getText(),
								   txtTelefono.getText());
		DateTime fecha_cita = new DateTime(fecha.get_day(), fecha.get_month(), fecha.get_year(), hora, minuto, 0);
		//Checar si el paciente tiene una cita
		if(ConexionAccess.revisar_datos(new Cita(p1, d1, fecha_cita)) == 1) {
			int status = ConexionAccess.update(new Cita(p1, d1, fecha_cita));
			if(status > 0) {
				JOptionPane.showMessageDialog(frame3.getRootPane(), "La cita fue actualizada");
			}
			else {
				JOptionPane.showMessageDialog(frame3.getRootPane(), "La cita no se pudo actualizar");
			}
			limpiar_pantalla_paciente();
		}
		else if(ConexionAccess.revisar_datos(new Cita(p1, d1, fecha_cita)) == 2){
			JOptionPane.showMessageDialog(frame3.getRootPane(), "Horario no disponible");
		}
		else {
			JOptionPane.showMessageDialog(frame3.getRootPane(), "No se tiene registro de cita");
		}
		mostrar_tablaPacientes();
	}
	
	//Eventos de tablas
	protected void tblDoctorMouse(MouseEvent evt) {
		Doctor d1 = new Doctor();
		int fila = tblDoctor.rowAtPoint(evt.getPoint());
		d1.setNombre(tblDoctor.getValueAt(fila, 0).toString());
		d1.setApellidos(tblDoctor.getValueAt(fila, 1).toString());
		d1.setEdad(Integer.parseInt(tblDoctor.getValueAt(fila, 2).toString()));
		d1.setEspecialidad(tblDoctor.getValueAt(fila, 3).toString());
		int status = ConexionAccess.delete_data(d1);
		if(status > 0) {
			JOptionPane.showMessageDialog(frame4.getRootPane(), "Doctor " + d1.toString() + " eliminado");
		}		
		mostrar_tablaDr(tblDoctor);
	}
	
	protected Doctor tblDoctorCitas(MouseEvent evt) {
		Doctor d1 = new Doctor();
		int fila = tblCita.rowAtPoint(evt.getPoint());
		d1.setNombre(tblCita.getValueAt(fila, 0).toString());
		d1.setApellidos(tblCita.getValueAt(fila, 1).toString());
		d1.setEdad(Integer.parseInt(tblCita.getValueAt(fila, 2).toString()));
		d1.setEspecialidad(tblCita.getValueAt(fila, 3).toString());
		return d1;
	}
	
	protected void tblPacienteMouse(MouseEvent evt) {
		Paciente p1 = new Paciente();
		int fila = tblPaciente.rowAtPoint(evt.getPoint());
		p1.setNombre(tblPaciente.getValueAt(fila, 0).toString());
		p1.setApellidos(tblPaciente.getValueAt(fila,1).toString());
		p1.setTelefono(tblPaciente.getValueAt(fila, 2).toString());
		String array [] = tblPaciente.getValueAt(fila, 3).toString().split("/");
		Date d1 = new Date(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]));
		int hora = Integer.parseInt(tblPaciente.getValueAt(fila,4).toString().split(":")[0]);
		int minuto = Integer.parseInt(tblPaciente.getValueAt(fila,4).toString().split(":")[1]);
		p1.setEdad(Integer.parseInt(tblPaciente.getValueAt(fila,5).toString()));
		p1.setSangre(tblPaciente.getValueAt(fila, 6).toString());
		p1.setAlergias(tblPaciente.getValueAt(fila, 7).toString());
		p1.setSintomas(tblPaciente.getValueAt(fila, 8).toString());
		Cita c1 = new Cita(p1, null, new DateTime(d1.get_day(), d1.get_month(), d1.get_year(), hora, minuto, 0));
		
		cargar_datos(c1);
		
	}
		


}
