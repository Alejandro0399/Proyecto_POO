package proyectoFinal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmPrueba{
	
	private JTable tblPaciente, tblDoctor;
	private JScrollPane jspPaciente, jspDoctor;
	private DefaultTableModel dtmPaciente, dtmDoctor;
	private String titulos_doctor [] = {"Nombre", "Apellidos", "Edad", "Especialidad"};
	private String titulos [] = {"Nombre", "Apellidos", "Telefono", "Fecha", "Edad", "Tipo de Sangre", "Alergias", "Sintomas", };
	
	private JTextField txtNombre, txtApellidos, txtTelefono, txtFecha, txtEdad, txtSangre, txtAlergias, txtSintomas;
	private static JLabel lblNombre;
	private static JLabel lblApellidos;
	private static JLabel lblDoctores;
	private static JLabel lblPacientes;
	private JLabel lblTelefono;
	private JLabel lblFecha;
	private JLabel lblSangre;
	private JLabel lblAlergias;
	private JLabel lblSintomas;
	private static JLabel lblEdad;
	private static JTextField drNombre;
	private static JTextField drApellidos;
	private static JTextField drEdad;
	private static JTextField drEspecialidad;
	private static JLabel lblEspecialidad;
	
	private JButton btnGuardar, btnNuevo, btnDelete, btnGuardarDr, btnDeleteDr;
	private JButton btnPaciente, btnRegresar;
	private JButton btnDoctor;
	private Connection con = null;
	private JFrame frame1, frame2, frame3, frame4;
	
	public FrmPrueba() {
		frame1 = new JFrame();
		frame2 = new JFrame();
		frame3 = new JFrame();
		frame4 = new JFrame();
		frame1.setVisible(false);
		frame2.setVisible(false);
		frame3.setVisible(false);
		frame4.setVisible(false);
		
		//Text Fields
		drNombre = new JTextField();
		drApellidos = new JTextField();
		drEdad = new JTextField();;
		drEspecialidad = new JTextField();
		txtNombre = new JTextField();
		txtApellidos = new JTextField();
		txtTelefono = new JTextField();
		txtFecha = new JTextField();
		txtSangre = new JTextField();
		txtAlergias = new JTextField();
		txtSintomas = new JTextField();
		txtEdad = new JTextField();
		
		//Botones
		btnGuardarDr = new JButton("Guardar");
		btnGuardar = new JButton("Guardar");
		btnPaciente = new JButton("Agendar Cita");
		btnDoctor = new JButton("Dar de alta a Doctor");
		btnGuardar = new JButton("Guardar");
		btnNuevo = new JButton("Limpiar");
		btnDelete = new JButton("Eliminar");
		btnDeleteDr = new JButton("Eliminar Doctor");
		btnRegresar = new JButton("Regresar");
		
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
		
		//Tablas
		dtmPaciente = new DefaultTableModel(null, titulos);
		tblPaciente = new JTable(dtmPaciente);
		jspPaciente = new JScrollPane(tblPaciente);
		dtmDoctor = new DefaultTableModel(null, titulos_doctor);
		tblDoctor = new JTable(dtmDoctor);
		jspDoctor = new JScrollPane(tblDoctor);
		
		//Callbacks para acciones
		btnPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				frame1.setVisible(false);
				btnPacienteEvent(evt);
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
				crear_paciente(frame3);
			}
		});
		
		btnDeleteDr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				frame1.setVisible(false);
				btnDeleteDrEvent(evt);
				
			}
		});
		
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				frame2.setVisible(false);
				frame3.setVisible(false);
				frame4.setVisible(false);
				empezar_gui();
			}
		});
		
		tblDoctor.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				tblDoctorMouse(evt);
			}
		});
		
	}
	
	public void empezar_gui() {
		bienvenida_layout();
	}
	
	public void bienvenida_layout() {
		frame1.setLayout(null);
		frame1.setTitle("Bienvenido");
		frame1.setSize(450, 400);
		frame1.setVisible(true);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame1.add(btnPaciente);
		frame1.add(btnDoctor);
		frame1.add(lblDoctores);
		frame1.add(lblPacientes);
		frame1.add(btnDeleteDr);
		
		lblDoctores.setBounds(50, 50, 100, 25);
		lblPacientes.setBounds(250, 50, 100, 25);
		btnPaciente.setBounds(250, 80, 150, 25);
		btnDoctor.setBounds(50, 80, 150, 25);
		btnDeleteDr.setBounds(50, 120, 150, 25);
	}
	
	public void alta_doctor() {
		frame2.setLayout(null);
		frame2.setTitle("Doctores");
		frame2.setSize(400, 300);
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		drNombre.setText("");
		drApellidos.setText("");
		drEdad.setText("");
		drEspecialidad.setText("");

		frame2.add(drNombre);
		frame2.add(lblNombre);
		
		frame2.add(drApellidos);
		frame2.add(lblApellidos);
		
		frame2.add(drEdad);
		frame2.add(lblEdad);
		
		frame2.add(drEspecialidad);
		frame2.add(lblEspecialidad);
		
		frame2.add(btnGuardarDr);
		frame2.add(btnRegresar);
		
		lblNombre.setBounds(20,50,100,20);
		drNombre.setBounds(120,50,200,20);
		
		lblApellidos.setBounds(20, 80, 100, 20);	
		drApellidos.setBounds(120, 80, 200, 20);
		
		lblEdad.setBounds(20,110,100,20);
		drEdad.setBounds(120,110,200,20);
		
		lblEspecialidad.setBounds(20,140,100,20);
		drEspecialidad.setBounds(120,140,200,20);
		
		btnGuardarDr.setBounds(150, 170, 100, 25);
		btnRegresar.setBounds(150, 210, 100, 25);
	}
	
	
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
		mostrar_tablaDr();
		
		
	}
	
	public void crear_doctor(JFrame frame) {
		Doctor d1 = new Doctor(drNombre.getText(), drApellidos.getText(), Integer.parseInt(drEdad.getText().strip()), drEspecialidad.getText());
		int status = ConexionAccess.guardar_datos(d1);
		if(status > 0) {
			JOptionPane.showMessageDialog(frame.getRootPane(), "Datos del doctor guardados");
		}
		else if(status == -2) {
			JOptionPane.showMessageDialog(frame.getRootPane(), "Datos ya se encuentran en la base de datos");
		}
		bienvenida_layout();
	}
		
	public void crear_paciente(JFrame frame) {
		String array [] = txtFecha.getText().split("/");
		if(array.length != 3) {
			return;
		}
		Date fecha = new Date(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]));
		Paciente p1 = new Paciente(txtNombre.getText(),
								   txtApellidos.getText(),
								   Integer.parseInt(txtEdad.getText()),
								   txtSangre.getText(),
								   txtAlergias.getText(),
								   txtSintomas.getText(),
								   txtTelefono.getText(),
								   fecha);
		int status = ConexionAccess.guardar_datos(p1);
		if(status > 0) {
			JOptionPane.showMessageDialog(frame.getRootPane(), "Datos del paciente guardados");
		}
		mostrar_tablaPacientes();
		txtNombre.setText("");
		txtApellidos.setText("");
		txtEdad.setText("");
		txtSangre.setText("");
		txtAlergias.setText("");
		txtSintomas.setText("");
		txtTelefono.setText("");
		txtFecha.setText("");
	}
	
	public void guardar_paciente() {
		frame3.setLayout(null);
		frame3.setTitle("Registrar Paciente");
		frame3.setSize(600, 600);
		frame3.setVisible(true);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		
		frame3.add(lblNombre);
		frame3.add(txtNombre);
		
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
		
		frame3.add(jspPaciente);
		
		
		lblNombre.setBounds(20,50,100,20);
		txtNombre.setBounds(150,50,200,20);
		
		lblApellidos.setBounds(20, 80, 100, 20);	
		txtApellidos.setBounds(150, 80, 200, 20);
		
		lblTelefono.setBounds(20,110,100,20);
		txtTelefono.setBounds(150,110,200,20);
		
		lblEdad.setBounds(20,140,100,20);
		txtEdad.setBounds(150,140,200,20);
		
		lblSangre.setBounds(20,170,100,20);
		txtSangre.setBounds(150,170,200,20);
		
		lblAlergias.setBounds(20,200,100,20);
		txtAlergias.setBounds(150,200,200,20);
		
		lblSintomas.setBounds(20,230,100,20);
		txtSintomas.setBounds(150,230,200,20);
		
		lblFecha.setBounds(20,260,130,20);
		txtFecha.setBounds(150,260,200,20);
		
		btnGuardar.setBounds(390, 100, 100, 25);
		btnNuevo.setBounds(390, 140, 100, 25);
		btnDelete.setBounds(390, 60, 100, 25);
		btnRegresar.setBounds(390, 180, 100, 25);
		
		jspPaciente.setBounds(20,300,550,200);
		
		mostrar_tablaPacientes();
		
	}
	
	//Eventos de botones
	protected void btnPacienteEvent(ActionEvent evt) {
		guardar_paciente();
	}
	
	protected void btnDoctorEvent(ActionEvent evt) {
		alta_doctor();
	}
	
	protected void btnDeleteDrEvent(ActionEvent evt) {
		eliminar_doctor();
	}
	/*
	protected void btnEliminarActionPerformed(ActionEvent evt) {
		try {
			con = ConexionAccess.obtenerConexion();
			if(txtId.getText().equals("")) {
				JOptionPane.showMessageDialog(frame3.getRootPane(), "Selecciona persona para eliminar");
			}
			else {
				String sql = "delete from tblPersona where Id = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1,txtId.getText());
				int n = pst.executeUpdate();
				if (n > 0) {
					JOptionPane.showMessageDialog(frame3.getRootPane(), "Se elimino de forma correcta");
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		mostrar_tablaPacientes();
		txtId.setText("");
		txtNombre.setText("");
		txtSangre.setText("");
		txtTelefono.setText("");
		txtEdad.setText("");
		txtFecha.setText("");
		txtApellidos.setText("");
		txtAlergias.setText("");
		txtSintomas.setText("");
		
	}

	protected void btnNuevoActionPerformed(ActionEvent evt) {
		txtId.setText("");
		txtNombre.setText("");
		txtSangre.setText("");
		txtTelefono.setText("");
		txtEdad.setText("");
		txtFecha.setText("");
		txtApellidos.setText("");
		txtAlergias.setText("");
		txtSintomas.setText("");
	}
	*/
	protected void tblDoctorMouse(MouseEvent evt) {
		Doctor d1 = new Doctor();
		int fila = tblDoctor.rowAtPoint(evt.getPoint());
		d1.setNombre(tblDoctor.getValueAt(fila, 0).toString());
		d1.setApellidos(tblDoctor.getValueAt(fila, 1).toString());
		d1.setEdad(Integer.parseInt(tblDoctor.getValueAt(fila, 2).toString()));
		d1.setEspecialidad(tblDoctor.getValueAt(fila, 3).toString());
		int status = ConexionAccess.delete_data(d1);
		if(status > 0) {
			JOptionPane.showMessageDialog(frame4.getRootPane(), "Doctor " + d1.getNombre() + " " + d1.getApellidos() + " eliminado");
		}		
		mostrar_tablaDr();
	}
	
	protected void mostrar_tablaDr() {
		ConexionAccess.read_drtbl(tblDoctor);
	}
	
	protected void mostrar_tablaPacientes() {
		ConexionAccess.read_pttbl(tblPaciente);
	}
	/*
	protected void tblPruebaMouseClicked(MouseEvent evt) {
		int fila = tblPrueba.rowAtPoint(evt.getPoint());
		txtId.setText(tblPrueba.getValueAt(fila, 0).toString());
		txtNombre.setText(tblPrueba.getValueAt(fila, 1).toString());
		txtDomicilio.setText(tblPrueba.getValueAt(fila, 2).toString());
		txtTelefono.setText(tblPrueba.getValueAt(fila, 3).toString());
		txtEdad.setText(tblPrueba.getValueAt(fila, 4).toString());
		
	}

	protected void btnGuardarActionPerformed(ActionEvent evt) {
		try {
			con = ConexionAccess.obtenerConexion();
			if(txtId.getText().equals("")) {
				String sql = "insert into tblPersona (Nombre, Domicilio, Telefono, Edad) values(?,?,?,?)";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1,txtNombre.getText());
				pst.setString(2,txtDomicilio.getText());
				pst.setString(3,txtTelefono.getText());
				pst.setString(4,txtEdad.getText());
				int n = pst.executeUpdate();
				if (n > 0) {
					JOptionPane.showMessageDialog(rootPane, "Se registro de forma correcta");
				}
			}
			else {
				String sql = "update tblPersona set Nombre = ?, Domicilio = ?, Telefono = ?, Edad = ? where Id = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1,txtNombre.getText());
				pst.setString(2,txtDomicilio.getText());
				pst.setString(3,txtTelefono.getText());
				pst.setString(4,txtEdad.getText());
				pst.setInt(5,Integer.parseInt(txtId.getText()));
				int n = pst.executeUpdate();
				if (n > 0) {
					JOptionPane.showMessageDialog(rootPane, "Se actualizo de forma correcta");
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		mostrar();
		txtId.setText("");
		txtNombre.setText("");
		txtDomicilio.setText("");
		txtTelefono.setText("");
		txtEdad.setText("");
	}
	*/

	
}
