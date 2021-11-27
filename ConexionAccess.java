package proyectoFinal;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConexionAccess {

	protected static Connection con = null;
	
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
	

	
}
