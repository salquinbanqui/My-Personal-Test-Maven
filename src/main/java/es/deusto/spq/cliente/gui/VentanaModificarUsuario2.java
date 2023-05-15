package es.deusto.spq.cliente.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;



import es.deusto.spq.pojo.Pelicula;
import es.deusto.spq.pojo.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;

public class VentanaModificarUsuario2 extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JTextField textFieldEmail;
	private JTextField textFieldPassword;
	private JTextField textFieldTarjeta;
	private JTextField textFieldAdmin;
	
	private Client client;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			
			
			public void run() {
				try {
					VentanaModPeliculaForm frame = new VentanaModPeliculaForm(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaModificarUsuario2(Usuario u) {
		
		client = (Client) ClientBuilder.newClient();
		
	     final WebTarget appTarget = client.target("http://localhost:8080/webapi");
		  final WebTarget usuariosTarget = appTarget.path("usuarios");
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(255, 0, 127));
		separator.setForeground(new Color(255, 0, 127));
		separator.setBounds(400, 6, 0, 273);
		contentPane.add(separator);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(20, 40, 104, 16);
		contentPane.add(lblUsername);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(20, 73, 78, 16);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(20, 101, 104, 16);
		contentPane.add(lblPassword);
		
		JLabel lblTarjeta = new JLabel("Tarjeta: ");
		lblTarjeta.setBounds(20, 170, 104, 16);
		contentPane.add(lblTarjeta);
		
		JLabel lblAdmin = new JLabel("Admin: ");
		lblAdmin.setBounds(20, 220, 104, 16);
		contentPane.add(lblAdmin);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(128, 35, 260, 26);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		textFieldUsername.setText(u.getNombreUsuario());
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(128, 68, 190, 26);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		textFieldEmail.setText(u.getEmail());
		
		textFieldPassword = new JTextField();
		textFieldPassword.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldPassword.setBounds(128, 96, 190, 26);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		textFieldPassword.setText(String.valueOf(u.getPassword()));
		
		textFieldTarjeta = new JTextField();
		textFieldTarjeta.setBounds(128, 165, 130, 26);
		contentPane.add(textFieldTarjeta);
		textFieldTarjeta.setColumns(10);
		textFieldTarjeta.setText(u.getTarjeta());
		
		textFieldAdmin = new JTextField();
		textFieldAdmin.setBounds(128, 214, 130, 29);
		contentPane.add(textFieldAdmin);
		textFieldAdmin.setColumns(10);
		textFieldAdmin.setText(u.getAdmin());
		
		JButton btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.setBounds(400, 35, 164, 29);
		contentPane.add(btnGuardarCambios);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(385, 6, 48, 300);
		contentPane.add(separator_1);
		
		JButton btnVolver = new JButton("Cancelar");
		btnVolver.setBounds(400, 83, 164, 29);
		contentPane.add(btnVolver);
	
	
	btnGuardarCambios.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
         	//DBManager.getInstance();
         //	Uusario newUser = new Usuario(textFieldUsername.getText(), textFieldEmail.getText(), textFieldPassword.getText(), textFieldTarjeta.getText(), String.valueOf(textFieldAdmin.getText()));
         	//userListModel.addElement(newUser);
         	
         	
         	WebTarget peliRegTarget = usuariosTarget.path("reg");
				List<String> peliL = new ArrayList<>(); 
     //    		peliL.add(newPeli.getNombrePelicula());
       //  		peliL.add(newPeli.getCategoria());
         //		peliL.add(String.valueOf(newPeli.getPrecio()));
         	//	peliL.add(newPeli.getFecha());
         	//	peliL.add(newPeli.getDescripcion());
				
				//userRegTarget.request().post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
				
         		peliRegTarget.request().post(Entity.entity(peliL, MediaType.APPLICATION_JSON));
         }
         
         
         
        
     });
	

	}
}
