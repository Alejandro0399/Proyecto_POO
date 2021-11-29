package proyectoFinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ConexionAccess {

	protected static Connection con = null;
	protected static String tabla1 = "Doctores";
	protected static String tabla2 = "Pacientes";
	private static String titulos_doctor [] = {"Nombre", "Apellidos", "Edad", "Especialidad"};
	private static String titulos_pacientes [] = {"Nombre", "Apellidos", "Telefono", "Fecha", "Edad", "Tipo de Sangre", "Alergias", "Sintomas", };

	
	//Driver para conectar con Access
	private static String driver = "net.ucanaccess.jdbc.UcanaccessDriver";	
	//URL en el cual se encuentra el path de la base de datos
	private static String url = "jdbc:ucanaccess://C:\\Users\\Alejandro\\Documents\\BaseDatos\\ProyectoFinal.accdb";
	
	public static Connection obtenerConexion() {
		try {
			if(con == null) {
				Class.forName(driver);	//Cargar el driver
				con = DriverManager.getConnection(url);	//Establecer la conexion con la base de datos
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			con = null;
		}
		return con;
	}
	
	public static int revisar_datos(Object o) {
		try {
			Connection con1 = obtenerConexion();
			if(con1 == null) {
				return -1;
			}
			if (o instanceof Doctor) {
				Doctor d1 = (Doctor) o;
				String sql = "select * from " + tabla1 ;
				String dts [] = new String[5];
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					dts[0] = rs.getString(1);
					dts[1] = rs.getString(2);	//Nombre
					dts[2] = rs.getString(3);	//Apellidos
					dts[3] = rs.getString(4);	//Edad
					dts[4] = rs.getString(5);	//Especialidad
					if(d1.getNombre().compareTo(dts[1]) == 0 && d1.getApellidos().compareTo(dts[2]) == 0 && String.valueOf(d1.getEdad()).compareTo(dts[3]) == 0 && d1.getEspecialidad().compareTo(dts[4]) == 0) {
						return 1;
					}
				}
			}
			else if(o instanceof Paciente) {
				return 0;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	
	public static int guardar_datos(Object o) {
		try {
			Connection con1 = obtenerConexion();
			if(con1 == null) {
				return -1;
			}
			if (o instanceof Doctor) {
				Doctor d1 = (Doctor) o;
				int estado = revisar_datos(d1);
				if(estado != 1) {
					String sql = "insert into " + tabla1 + " (Nombre,Apellidos,Edad,Especialidad) values(?,?,?,?)";
					PreparedStatement pst = con1.prepareStatement(sql);
					pst.setString(1, d1.getNombre());
					pst.setString(2, d1.getApellidos());
					pst.setString(3, String.valueOf(d1.getEdad()));
					pst.setString(4, d1.getEspecialidad());
					int n = pst.executeUpdate();
					return n;
				}
				else {
					return -2;
				}
			}
			else if(o instanceof Paciente) {
				Paciente p1 = (Paciente) o;
				int estado = revisar_datos(p1);
				if(estado != 1) {
					String sql = "insert into " + tabla2 + " (Nombre,Apellidos,Telefono,Fecha,Edad,Sangre,Alergias,Sintomas) values(?,?,?,?,?,?,?,?)";
					PreparedStatement pst = con1.prepareStatement(sql);
					pst.setString(1, p1.getNombre());
					pst.setString(2, p1.getApellidos());
					pst.setString(3, p1.getTelefono());
					pst.setString(4, p1.getFecha().toString());
					pst.setString(5, String.valueOf(p1.getEdad()));
					pst.setString(6, p1.getSangre());
					pst.setString(7, p1.getAlergias());
					pst.setString(8, p1.getSintomas());
					int n = pst.executeUpdate();
					return n;
				}
				else {
					return -2;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}

	public static int delete_data(Object o) {
		try {
			Connection con1 = obtenerConexion();
			if (o instanceof Doctor) {
				Doctor d1 = (Doctor) o;
				String sql = "delete from " + tabla1 + " where Nombre = ? and Apellidos = ? and Especialidad = ?";
				PreparedStatement pst = con1.prepareStatement(sql);
				pst.setString(1, d1.getNombre());
				pst.setString(2, d1.getApellidos());
				pst.setString(3, d1.getEspecialidad());
				int n = pst.executeUpdate();
				if(n>0) {
					return n;
				}
			}
			else if(o instanceof Paciente) {

			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	
	public static void read_drtbl(JTable table) {
		try {
			Connection c1 = ConexionAccess.obtenerConexion();
			DefaultTableModel miModelo = new DefaultTableModel(null, titulos_doctor);
			String dts [] = new String[4];
			String sql = "select * from " + tabla1;
			Statement st = c1.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				dts[0] = rs.getString(2);
				dts[1] = rs.getString(3);
				dts[2] = rs.getString(4);
				dts[3] = rs.getString(5);
				miModelo.addRow(dts);
			}
			table.setModel(miModelo);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void read_pttbl(JTable table) {
		try {
			Connection c1 = ConexionAccess.obtenerConexion();
			DefaultTableModel miModelo = new DefaultTableModel(null, titulos_pacientes);
			String dts [] = new String[8];
			String sql = "select * from " + tabla2;
			Statement st = c1.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				dts[0] = rs.getString(2);
				dts[1] = rs.getString(3);
				dts[2] = rs.getString(4);
				dts[3] = rs.getString(5);
				dts[4] = rs.getString(6);
				dts[5] = rs.getString(7);
				dts[6] = rs.getString(8);
				dts[7] = rs.getString(9);
				miModelo.addRow(dts);
			}
			table.setModel(miModelo);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
