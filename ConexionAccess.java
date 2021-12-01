package proyectoFinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ConexionAccess {

	protected static Connection con = null;
	protected static final String TABLA = "Doctores";
	public static final String TITULOS_DOCTOR [] = {"Nombre", "Apellidos", "Edad", "Especialidad"};
	public static final String TITULOS_PACIENTES [] = {"Nombre", "Apellidos", "Telefono", "Fecha", "Hora", "Edad", "Sangre", "Alergias", "Sintomas"};
	
	
	//Driver para conectar con Access
	private static String driver = "net.ucanaccess.jdbc.UcanaccessDriver";	
	//URL en el cual se encuentra el path de la base de datos
	private static String url = "jdbc:ucanaccess://C:\\Users\\Alejandro\\Documents\\BaseDatos\\ProyectoFinal.accdb";
	
	/**
	 * Funcion que se usa para establecer una conexion con la base de datos
	 * @return
	 */
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
	
	/**
	 * Funcion que se usa para revisar si los datos ya estan en la base de datos
	 * @param o, Objeto en el cual se quieren revisar los datos
	 * @return
	 */
	public static int revisar_datos(Object o) {
		try {
			Connection con1 = obtenerConexion();
			if(con1 == null) {
				return -1;
			}
			if (o instanceof Doctor) {
				Doctor d1 = (Doctor) o;
				String sql = "select * from " + TABLA ;
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
			else if(o instanceof Cita) {
				Cita c1 = (Cita) o;
				Paciente p1 = c1.getPaciente();
				Doctor d1 = c1.getDoctor();
				DateTime fecha = c1.getFecha();
				String sql = "select * from " + d1.table_format();
				String dts [] = new String[9];
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					dts[0] = rs.getString(1);	//Nombre
					dts[1] = rs.getString(2);	//Apellidos
					dts[2] = rs.getString(3);	//Telefono
					dts[3] = rs.getString(4);	//Fecha
					dts[4] = rs.getString(5);	//Hora
					dts[5] = rs.getString(6);	//Edad
					dts[6] = rs.getString(7);	//Sangre
					dts[7] = rs.getString(8);	//Alergias
					dts[8] = rs.getString(9);	//Sintomas
					String fecha_st [] = dts[3].split("/");
					Date tmp = new Date(Integer.parseInt(fecha_st[0]), Integer.parseInt(fecha_st[1]), Integer.parseInt(fecha_st[2]));
					int hora = Integer.parseInt(dts[4].split(":")[0]);
					int minuto = Integer.parseInt(dts[4].split(":")[1]);
					if(p1.getNombre().compareTo(dts[0]) == 0 && p1.getApellidos().compareTo(dts[1]) == 0) {
						p1.setAlergias(dts[7]);
						p1.setTelefono(dts[2]);
						p1.setSintomas(dts[8]);
						c1.setFecha(new DateTime(tmp.get_day(), tmp.get_month(), tmp.get_year(), hora, minuto, 0));
						p1.setEdad(Integer.parseInt(dts[5]));
						p1.setSangre(dts[6]);
						return 1;
					}
					else if(fecha.compareTo(tmp) == 0 && fecha.getHour() == hora && fecha.getMinute() == minuto) {
						return 2;
					}
				}
				return 0;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Metodo que se usa para guardar los datos en la bade de datos
	 * @param o, Objeto que se quiere guardar en la base de datos
	 * @return
	 */
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
					String sql = "insert into " + TABLA + " (Nombre,Apellidos,Edad,Especialidad) values(?,?,?,?)";
					PreparedStatement pst = con1.prepareStatement(sql);
					pst.setString(1, d1.getNombre());
					pst.setString(2, d1.getApellidos());
					pst.setString(3, String.valueOf(d1.getEdad()));
					pst.setString(4, d1.getEspecialidad());
					int n = pst.executeUpdate();
					create_table(d1);
					return n;
				}
				else {
					return -2;
				}
			}
			else if(o instanceof Cita) {
				Cita c1 = (Cita) o;
				int estado = revisar_datos(c1);
				String hora_string = c1.getFecha().formato();
				if(estado != 1 && estado != 2) {
					String sql = "insert into " + c1.getDoctor().table_format() + " (Nombre,Apellidos,Telefono,Fecha,Hora,Edad,Sangre,Alergias,Sintomas) values(?,?,?,?,?,?,?,?,?)";
					PreparedStatement pst = con1.prepareStatement(sql);
					pst.setString(1, c1.getPaciente().getNombre());
					pst.setString(2, c1.getPaciente().getApellidos());
					pst.setString(3, c1.getPaciente().getTelefono());
					pst.setString(4, c1.getFecha().toString());
					pst.setString(5, hora_string);
					pst.setString(6, String.valueOf(c1.getPaciente().getEdad()));
					pst.setString(7, c1.getPaciente().getSangre());
					pst.setString(8, c1.getPaciente().getAlergias());
					pst.setString(9, c1.getPaciente().getSintomas());
					int n = pst.executeUpdate();
					return n;
				}
				else if(estado == 1){
					return -2;
				}
				else {
					return -3;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Metodo que se usa para eliminar datos de una base de datos
	 * @param o, objeto que se desea eliminar de la base de datos
	 * @return
	 */
	public static int delete_data(Object o) {
		try {
			Connection con1 = obtenerConexion();
			if (o instanceof Doctor) {
				Doctor d1 = (Doctor) o;
				String sql = "delete from " + TABLA + " where Nombre = ? and Apellidos = ? and Especialidad = ?";
				PreparedStatement pst = con1.prepareStatement(sql);
				pst.setString(1, d1.getNombre());
				pst.setString(2, d1.getApellidos());
				pst.setString(3, d1.getEspecialidad());
				int n = pst.executeUpdate();
				if(n>0) {
					delete_table(d1);
					return n;
				}
			}
			else if(o instanceof Cita) {
				Cita cita = (Cita) o;
				String sql = "delete from " + cita.getDoctor().table_format()+ " where Nombre = ? and Apellidos = ?";
				PreparedStatement pst = con1.prepareStatement(sql);
				pst.setString(1, cita.getPaciente().getNombre());
				pst.setString(2, cita.getPaciente().getApellidos());
				int n = pst.executeUpdate();
				if(n > 0) {
					return n;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Metodo que se usa para leer la tabla de los doctores en la base de datos
	 * @param table, tabla en donde se va a desplegar la informacion
	 */
	public static void read_drtbl(JTable table) {
		try {
			Connection c1 = ConexionAccess.obtenerConexion();
			DefaultTableModel miModelo = new DefaultTableModel(null, TITULOS_DOCTOR);
			String dts [] = new String[4];
			String sql = "select * from " + TABLA;
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
	
	/**
	 * Metodo que se usa para desplegar la tabla de citas
	 * @param table, Tabla donde se va a desplegar la informacion
	 * @param d1, Doctor del que se quiere consultar la tabla
	 */
	public static void read_pttbl(JTable table, Doctor d1) {
		try {
			Connection c1 = ConexionAccess.obtenerConexion();
			DefaultTableModel miModelo = new DefaultTableModel(null, TITULOS_PACIENTES);
			String dts [] = new String[9];
			String sql = "select * from " + d1.table_format();
			Statement st = c1.createStatement();
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
			table.setModel(miModelo);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void create_table(Doctor d1) {
		try {
			Connection c1 = ConexionAccess.obtenerConexion();
			String sql = "create table " + d1.table_format() + " "
					+ "(" + TITULOS_PACIENTES[0] +" TEXT,"
					+ TITULOS_PACIENTES[1] +" TEXT,"
					+ TITULOS_PACIENTES[2] +" TEXT,"
					+ TITULOS_PACIENTES[3] +" TEXT,"
					+ TITULOS_PACIENTES[4] +" TEXT,"
					+ TITULOS_PACIENTES[5] +" TEXT,"
					+ TITULOS_PACIENTES[6] +" TEXT,"
					+ TITULOS_PACIENTES[7] +" TEXT,"
					+ TITULOS_PACIENTES[8] +" TEXT)";
			Statement pst = c1.createStatement();
			pst.execute(sql);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Elimina una tabla de la base de datos
	 * @param d1, Doctor del cual se quiere eliminar la tabla
	 */
	public static void delete_table(Doctor d1) {
		try {
			Connection c1 = ConexionAccess.obtenerConexion();
			String sql = "drop table " + d1.table_format();
			Statement pst = c1.createStatement();
			pst.execute(sql);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Metodo que se utiliza para eliminar las citas de un dia anterior de la base de datos
	 * @param fecha
	 * @return
	 */
	public static int citas_previas(Date fecha) {
		int n = 0;
		try {
			Connection c1 = ConexionAccess.obtenerConexion();
			String dts [] = new String[4];
			String sql = "select * from " + TABLA;
			Statement st = c1.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				dts[0] = rs.getString(2); 
				dts[1] = rs.getString(3);
				dts[2] = rs.getString(4);
				dts[3] = rs.getString(5);
				Doctor tmp = new Doctor();
				tmp.setNombre(dts[0]);
				tmp.setApellidos(dts[1]);
				String sql2 = "select * from " + tmp.table_format();
				Statement st1 = c1.createStatement();
				ResultSet rs1 = st1.executeQuery(sql2);
				while(rs1.next()) {
					String fecha_st = rs1.getString(4);
					String fecha_arr [] = fecha_st.split("/");
					Date fecha_eliminar = new Date(Integer.parseInt(fecha_arr[0]), Integer.parseInt(fecha_arr[1]), Integer.parseInt(fecha_arr[2]));
					if(Integer.parseInt(fecha_arr[2]) < fecha.get_year()) {
						String sql_del = "delete from " + tmp.table_format() + " where fecha = ?";
						PreparedStatement pst = c1.prepareStatement(sql_del);
						pst.setString(1, fecha_eliminar.toString());
						n = pst.executeUpdate();
					}
					else if(Integer.parseInt(fecha_arr[1]) < fecha.get_month()) {
						String sql_del = "delete from " + tmp.table_format() + " where fecha = ?";
						PreparedStatement pst = c1.prepareStatement(sql_del);
						pst.setString(1, fecha_eliminar.toString());
						n = pst.executeUpdate();
					}
					else if(Integer.parseInt(fecha_arr[0]) < fecha.get_day()) {
						String sql_del = "delete from " + tmp.table_format() + " where fecha = ?";
						PreparedStatement pst = c1.prepareStatement(sql_del);
						pst.setString(1, fecha_eliminar.toString());
						n = pst.executeUpdate();
					}
				}
				//Ejecutar eliminar datos
				if(n > 0) {
					return n;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Metodo que se usa para actualizar una cita
	 * @param cita, cita a actualizar
	 * @return
	 */
	public static int update(Cita cita) {
		try {
			Connection c1 = ConexionAccess.obtenerConexion();
			String sql = "update " + cita.getDoctor().table_format() + " set fecha = ?, hora = ? where Nombre=? and Apellidos=?";
			PreparedStatement pst = c1.prepareStatement(sql);
			pst.setString(1, cita.getFecha().toString());
			pst.setString(2, cita.getFecha().formato());
			pst.setString(3, cita.getPaciente().getNombre());
			pst.setString(4, cita.getPaciente().getApellidos());
			int n = pst.executeUpdate();
			if(n > 0) {
				return n;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	
}
