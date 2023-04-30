package es.deusto.spq.cliente.gui;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

	
	public class VentanaPerfil extends JFrame {

		private JPanel contentPane;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VentanaPerfil frame = new VentanaPerfil();
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
	   private Client client;
		public VentanaPerfil() {
			
			  client = ClientBuilder.newClient();

		        final WebTarget appTarget = client.target("http://localhost:8080/webapi");
		        

			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 800, 500);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel labelTitle = new JLabel("Modificar Perfil");
			labelTitle.setFont(new Font("French Script MT", Font.BOLD, 40));
			labelTitle.setBounds(239, 22, 315, 50);
			contentPane.add(labelTitle);
			
			JLabel lblUsuario = new JLabel("Username:");
		    lblUsuario.setBounds(224, 132, 100, 16);
		    contentPane.add(lblUsuario);
		        
		    JLabel lblEmail = new JLabel("Email:");
		    lblEmail.setBounds(224, 180, 100, 16);
		    contentPane.add(lblEmail);
		        
		    JLabel lblPassword = new JLabel("Password:");
		    lblPassword.setBounds(224, 232, 100, 16);
		    contentPane.add(lblPassword);
		        
		    JLabel lblNTarjeta = new JLabel("Nº Tarjeta:");
		    lblNTarjeta.setBounds(224, 285, 100, 16);
		    contentPane.add(lblNTarjeta);
		    
		    JTextField emailTextField = new JTextField("", 10);
	        emailTextField.setBounds(320, 175, 130, 26);
	        contentPane.add(emailTextField);
	        
	        JTextField passwordTextField = new JTextField("", 10);
	        passwordTextField.setBounds(320, 227, 130, 26);
	        contentPane.add(passwordTextField);
	        
	        JTextField cardTextField = new JTextField("", 10);
	        cardTextField.setBounds(320, 280, 130, 26);
	        contentPane.add(cardTextField);
	        
	        JTextField usernameTextField = new JTextField("", 10);
	        usernameTextField.setBounds(320, 127, 130, 26);
	        contentPane.add(usernameTextField);
			
		     
			JButton btnModPerfil = new JButton("Modificar Perfil");
			btnModPerfil.setBounds(295, 355, 183, 29);
			contentPane.add(btnModPerfil);
			
			btnModPerfil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
				}
			});
			
			
		
			
			JButton btnMenu = new JButton("Volver al Menu");
			btnMenu.setBounds(591, 421, 183, 29);
			contentPane.add(btnMenu);
			
			btnMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					VentanaInicio vi = new VentanaInicio();
					vi.setVisible(true);
				}
			});
		}
	}



