package proyectoFinal;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmPrueba{
	
	private JTable tblPaciente;
	private JScrollPane jspPaciente;
	private DefaultTableModel dtmPaciente;
	private String titulos [] = {"ID", "Nombre", "Apellidos", "Telefono", "Fecha", "Edad", "Tipo de Sangre", "Alergias", "Sintomas", };
	private String nombre_tabla = "Pacientes";
	
	private JTextField txtNombre, txtApellidos, txtTelefono, txtFecha, txtId, txtEdad, txtSangre, txtAlergias, txtSintomas;
	private JLabel lblNombre, lblApellidos, lblTelefono, lblFecha, lblId, lblSangre, lblAlergias, lblSintomas, lblEdad;
	private JTextField drNombre, drApellidos, drEdad, drDireccion, drEspecialidad;
	private JLabel lblEspecialidad, lblDireccion;
	
	private JButton btnGuardar, btnNuevo, btnDelete;
	private JLabel mensaje_bienvenida;
	private JButton btnPaciente;
	private JButton btnDoctor;
	private JPanel panel, panel1, panel2;
	private Connection con = null;
	private JFrame frame1, frame2, frame3;
	
	public FrmPrueba() {
		frame1 = new JFrame();
		frame2 = new JFrame();
		frame3 = new JFrame();
		frame1.setVisible(false);
		frame2.setVisible(false);
		frame3.setVisible(false);
		bienvenida_layout();
	}
	
	public void bienvenida_layout() {
		frame1.setLayout(null);
		frame1.setTitle("Bienvenido");
		frame1.setSize(300, 250);
		frame1.setVisible(true);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mensaje_bienvenida = new JLabel("BIENVENIDOS");
		btnPaciente = new JButton("Agendar Cita");
		btnDoctor = new JButton("Dar de alta a Doctor");
		
		frame1.add(mensaje_bienvenida);
		frame1.add(btnPaciente);
		frame1.add(btnDoctor);
		
		mensaje_bienvenida.setBounds(100,50,100,25);
		btnPaciente.setBounds(75, 80, 150, 25);
		btnDoctor.setBounds(75, 120, 150, 25);
		
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
	}
	
	public void alta_doctor() {
		frame2.setLayout(null);
		frame2.setTitle("Doctores");
		frame2.setSize(400, 300);
		frame2.setVisible(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		drNombre = new JTextField();
		drApellidos = new JTextField();
		drEdad = new JTextField();;
		drDireccion = new JTextField();
		drEspecialidad = new JTextField();
		
		lblNombre = new JLabel("Nombre:");
		lblApellidos = new JLabel("Apellidos:");
		lblEdad = new JLabel("Edad:");
		lblDireccion = new JLabel("Direccion:");
		lblEspecialidad = new JLabel("Especialidad:");
		
		btnGuardar = new JButton("Guardar");
		
		frame2.add(drNombre);
		frame2.add(lblNombre);
		
		frame2.add(drApellidos);
		frame2.add(lblApellidos);
		
		frame2.add(drEdad);
		frame2.add(lblEdad);
		
		frame2.add(drDireccion);
		frame2.add(lblDireccion);
		
		frame2.add(drEspecialidad);
		frame2.add(lblEspecialidad);
		
		frame2.add(btnGuardar);
		
		lblNombre.setBounds(20,50,100,20);
		drNombre.setBounds(120,50,200,20);
		
		lblApellidos.setBounds(20, 80, 100, 20);	
		drApellidos.setBounds(120, 80, 200, 20);
		
		lblDireccion.setBounds(20,110,100,20);
		drDireccion.setBounds(120,110,200,20);
		
		lblEdad.setBounds(20,140,100,20);
		drEdad.setBounds(120,140,200,20);
		
		lblEspecialidad.setBounds(20,170,100,20);
		drEspecialidad.setBounds(120,170,200,20);
		
		btnGuardar.setBounds(150, 220, 100, 25);
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				frame2.setVisible(false);
				guardar_doctor(evt);
			}
		});
		
	}
	
	public void guardar_doctor(ActionEvent evt) {
		// Guardar datos
		bienvenida_layout();
	}
	
	public void guardar_paciente() {
		frame3.setLayout(null);
		frame3.setTitle("Registrar Paciente");
		frame3.setSize(600, 600);
		frame3.setVisible(true);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnGuardar = new JButton("Guardar");
		btnNuevo = new JButton("Limpiar");
		btnDelete = new JButton("Eliminar");
		
		txtNombre = new JTextField();
		txtApellidos = new JTextField();
		txtTelefono = new JTextField();
		txtFecha = new JTextField();
		txtId = new JTextField();
		txtSangre = new JTextField();
		txtAlergias = new JTextField();
		txtSintomas = new JTextField();
		txtEdad = new JTextField();
		
		lblNombre = new JLabel("Nombre:");
		lblApellidos = new JLabel("Apellidos:");
		lblTelefono = new JLabel("Telefono:");
		lblEdad = new JLabel("Edad:");
		lblId = new JLabel("ID:");
		lblSangre = new JLabel("Tipo de Sangre:");
		lblAlergias = new JLabel("Alergias:");
		lblSintomas = new JLabel("Sintomas:");
		lblFecha = new JLabel("Fecha:");
		
		dtmPaciente = new DefaultTableModel(null, titulos);
		tblPaciente = new JTable(dtmPaciente);
		jspPaciente = new JScrollPane(tblPaciente);
		
		
		frame3.add(lblId);
		frame3.add(txtId);
		
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
		
		frame3.add(jspPaciente);
		
		lblId.setBounds(20, 20, 100, 20);
		txtId.setBounds(120,20,200,20);
		
		lblNombre.setBounds(20,50,100,20);
		txtNombre.setBounds(120,50,200,20);
		
		lblApellidos.setBounds(20, 80, 100, 20);	
		txtApellidos.setBounds(120, 80, 200, 20);
		
		lblTelefono.setBounds(20,110,100,20);
		txtTelefono.setBounds(120,110,200,20);
		
		lblEdad.setBounds(20,140,100,20);
		txtEdad.setBounds(120,140,200,20);
		
		lblSangre.setBounds(20,170,100,20);
		txtSangre.setBounds(120,170,200,20);
		
		lblAlergias.setBounds(20,200,100,20);
		txtAlergias.setBounds(120,200,200,20);
		
		lblSintomas.setBounds(20,230,100,20);
		txtSintomas.setBounds(120,230,200,20);
		
		lblFecha.setBounds(20,260,100,20);
		txtFecha.setBounds(120,260,200,20);

		
		btnGuardar.setBounds(390, 100, 100, 25);
		btnNuevo.setBounds(390, 140, 100, 25);
		btnDelete.setBounds(390, 60, 100, 25);
		
		jspPaciente.setBounds(20,300,550,200);
		
		txtFecha.setText("dd/mm/yyyy");
		
		/*
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnGuardarActionPerformed(evt);
			}
		});
		*/
		/*
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnNuevoActionPerformed(evt);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnEliminarActionPerformed(evt);
			}
		});
		/*
		tblPaciente.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				tblPruebaMouseClicked(evt);
			}
		});
		mostrar();
		*/
		
	}
	
	protected void btnPacienteEvent(ActionEvent evt) {
		guardar_paciente();
	}
	
	protected void btnDoctorEvent(ActionEvent evt) {
		alta_doctor();
	}
	
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
		mostrar();
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
	public void mostrar() {
		try {
			con = ConexionAccess.obtenerConexion();
			DefaultTableModel miModelo = new DefaultTableModel(null, titulos);
			String dts [] = new String[9];
			String sql = "select * from " + nombre_tabla;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				dts[0] = rs.getString(1);
				dts[1] = rs.getString(2);
				dts[2] = rs.getString(3);
				dts[3] = rs.getString(4);
				dts[4] = rs.getString(5);
				dts[5] = rs.getString(6);
				dts[6] = rs.getString(7);
				dts[7] = rs.getString(8);
				dts[8] = rs.getString(9);
				miModelo.addRow(dts);
			}
			tblPaciente.setModel(miModelo);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
}
