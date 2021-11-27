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

public class FrmPrueba extends JFrame {
	
	private JTable tblPrueba;
	private JScrollPane jspPrueba;
	private DefaultTableModel dtmPrueba;
	private String titulos [] = {"ID", "Nombre", "Domicilio", "Telefono", "Edad"};
	
	private JTextField txtNombre, txtDomicilio, txtTelefono, txtEdad, txtId;
	private JLabel lblNombre, lblDomicilio, lblTelefono, lblEdad, lblId;
	
	private JButton btnGuardar, btnNuevo, btnDelete;
	private Connection con = null;
	
	public FrmPrueba() {
		setLayout(null);
		setTitle("Mostrar, Insertar y Editar - ACCESS");
		setSize(600, 500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		btnGuardar = new JButton("Guardar");
		btnNuevo = new JButton("Nuevo");
		btnDelete = new JButton("Eliminar");
		
		txtNombre = new JTextField();
		txtDomicilio = new JTextField();
		txtTelefono = new JTextField();
		txtEdad = new JTextField();
		txtId = new JTextField();
		
		lblNombre = new JLabel("Nombre:");
		lblDomicilio = new JLabel("Domicilio:");
		lblTelefono = new JLabel("Telefono:");
		lblEdad = new JLabel("Edad:");
		lblId = new JLabel("ID:");
		
		dtmPrueba = new DefaultTableModel(null, titulos);
		tblPrueba = new JTable(dtmPrueba);
		jspPrueba = new JScrollPane(tblPrueba);
		
		add(jspPrueba);
		add(txtNombre);
		add(txtDomicilio);
		add(txtTelefono);
		add(txtEdad);
		add(txtId);
		add(lblNombre);
		add(lblDomicilio);
		add(lblTelefono);
		add(lblEdad);
		add(lblId);
		
		add(btnGuardar);
		add(btnNuevo);
		add(btnDelete);
		
		jspPrueba.setBounds(20,200,550,200);
		
		lblId.setBounds(20,20,200,20);
		txtId.setBounds(120,20,200,20);
		
		
		lblNombre.setBounds(20,50,100,20);
		txtNombre.setBounds(120,50,200,20);
		
		lblDomicilio.setBounds(20,80,100,20);
		txtDomicilio.setBounds(120,80,200,20);
		
		lblTelefono.setBounds(20,110,100,20);
		txtTelefono.setBounds(120,110,200,20);

		lblEdad.setBounds(20,140,100,20);
		txtEdad.setBounds(120,140,200,20);
		
		btnGuardar.setBounds(390, 100, 100, 25);
		btnNuevo.setBounds(390, 140, 100, 25);
		btnDelete.setBounds(390, 60, 100, 25);
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnGuardarActionPerformed(evt);
			}
		});
		
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
		
		tblPrueba.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				tblPruebaMouseClicked(evt);
			}
		});
		mostrar();
	}
	
	protected void btnEliminarActionPerformed(ActionEvent evt) {
		try {
			con = ConexionAccess.obtenerConexion();
			if(txtId.getText().equals("")) {
				JOptionPane.showMessageDialog(rootPane, "Selecciona persona para eliminar");
			}
			else {
				String sql = "delete from tblPersona where Id = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1,txtId.getText());
				int n = pst.executeUpdate();
				if (n > 0) {
					JOptionPane.showMessageDialog(rootPane, "Se elimino de forma correcta");
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

	protected void btnNuevoActionPerformed(ActionEvent evt) {
		txtId.setText("");
		txtNombre.setText("");
		txtDomicilio.setText("");
		txtTelefono.setText("");
		txtEdad.setText("");
	}

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
	
	public void mostrar() {
		try {
			con = ConexionAccess.obtenerConexion();
			DefaultTableModel miModelo = new DefaultTableModel(null, titulos);
			String dts [] = new String[5];
			String sql = "select * from tblPersona";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				dts[0] = rs.getString(1);
				dts[1] = rs.getString(2);
				dts[2] = rs.getString(3);
				dts[3] = rs.getString(4);
				dts[4] = rs.getString(5);
				miModelo.addRow(dts);
			}
			tblPrueba.setModel(miModelo);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	
}
