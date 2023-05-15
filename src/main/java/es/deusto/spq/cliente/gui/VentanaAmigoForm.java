package es.deusto.spq.cliente.gui;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.pojo.Pelicula;
import es.deusto.spq.pojo.Usuario;

public class VentanaAmigoForm extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JTextField textFieldEmail;
	
	
	private Client client;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			
			
			public void run() {
				try {
					VentanaAmigoForm frame = new VentanaAmigoForm(null);
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
	public VentanaAmigoForm(Usuario u) {
		
		client = (Client) ClientBuilder.newClient();
		
	     final WebTarget appTarget = client.target("http://localhost:8080/webapi");
		  final WebTarget amigosTarget = appTarget.path("amigos");
		
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
		
		JLabel lblTitulo = new JLabel("Nombre: ");
		lblTitulo.setBounds(20, 40, 61, 16);
		contentPane.add(lblTitulo);
		
		JLabel lblCategoria = new JLabel("Email: ");
		lblCategoria.setBounds(20, 68, 78, 16);
		contentPane.add(lblCategoria);
		
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(113, 35, 260, 26);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		textFieldNombre.setText(u.getNombreUsuario());
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(113, 63, 153, 26);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		textFieldEmail.setText(u.getEmail());
		
		
		
		JLabel lblNewLabel = new JLabel("€");
		lblNewLabel.setBounds(205, 96, 61, 16);
		contentPane.add(lblNewLabel);
		
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
         	Usuario newUsu = new Usuario(textFieldNombre.getText(), textFieldEmail.getText(), null, null, (Boolean) null);
         	//userListModel.addElement(newUser);
         	
         	
         	WebTarget peliRegTarget = amigosTarget.path("reg");
				List<String> amigos = new ArrayList<>(); 
         		amigos.add(newUsu.getNombreUsuario());
         		amigos.add(newUsu.getEmail());
         		
				
				//userRegTarget.request().post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
				
         		peliRegTarget.request().post(Entity.entity(amigos, MediaType.APPLICATION_JSON));
         }
         
         
         
        
     });
	

	}
}

