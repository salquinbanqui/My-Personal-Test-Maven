package es.deusto.spq.cliente.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import es.deusto.spq.cliente.ServiceLocator;
import es.deusto.spq.cliente.gui.VentanaAdmin;
import es.deusto.spq.cliente.Controller;


public class VentanaLogin extends JFrame {
	

		private static final long serialVersionUID = 1L;

		

		private JPanel contentpane;
		private JLabel labelUsuario = new JLabel();
		private JLabel labelContrasenya = new JLabel();
		private JTextField textoUsuario = new JTextField();
		private JPasswordField textoContrasenya = new JPasswordField();
		private JButton botonIniciarSesion = new JButton();
		private JButton botonRegistrarse = new JButton();
		ServiceLocator serviceLocator = new ServiceLocator();
        private Controller controller = new Controller(serviceLocator);
		
		public Controller getController() {
			return controller;
		}




		public void setController(Controller controller) {
			this.controller = controller;
		}


		Client client = ClientBuilder.newClient();
		final WebTarget appTarget = client.target("http://localhost:8080/webapi");
		final WebTarget userTarget = appTarget.path("usuarios");
		final WebTarget userAllTarget = userTarget.path("all");
		
		/**
		 * Create the frame.
		 */
		
		public  VentanaLogin() {
			
			
		//	client = ClientBuilder.newClient();
			
		//	final WebTarget appTarget = client.target("http://localhost:8080/webapi");
		//    final WebTarget userTarget = appTarget.path("usuarios");
		//    final WebTarget userAllTarget = userTarget.path("all");
		//    final WebTarget userRegTarget = userTarget.path("reg");
		
			contentpane = new JPanel();
			contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentpane);
			contentpane.setLayout(null);

			JLabel labelTitle = new JLabel("Pelis PSC");
			labelTitle.setFont(new Font("French Script MT", Font.BOLD, 40));
			labelTitle.setBounds(125, 30, 334, 50);
			contentpane.add(labelTitle);

			labelUsuario.setText("Usuario:");
			labelUsuario.setOpaque(true);
			labelUsuario.setBounds(86, 122, 91, 20);
			labelUsuario.setFont(new Font("Perpetua", Font.BOLD, 16));
			contentpane.add(labelUsuario, BorderLayout.SOUTH);

			textoUsuario.setBounds(179, 125, 169, 20);
			contentpane.add(textoUsuario);

			labelContrasenya.setText("Contraseña:");
			labelContrasenya.setBounds(64, 175, 105, 20);
			labelContrasenya.setOpaque(true);
			labelContrasenya.setFont(new Font("Perpetua", Font.BOLD, 16));
			contentpane.add(labelContrasenya);

			textoContrasenya.setBounds(179, 175, 169, 20);
			contentpane.add(textoContrasenya);

			botonIniciarSesion.setForeground(new Color(0, 0, 0));
			botonIniciarSesion.setBackground(new Color(0, 51, 255));
			botonIniciarSesion.setBounds(229, 243, 154, 32);
			botonIniciarSesion.setText("Iniciar sesión");
			botonIniciarSesion.setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 16));
			contentpane.add(botonIniciarSesion);

			botonRegistrarse.setBackground(SystemColor.inactiveCaptionBorder);
			botonRegistrarse.setBounds(49, 243, 121, 32);
			botonRegistrarse.setText("Registrarse");
			botonRegistrarse.setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 16));
			contentpane.add(botonRegistrarse);

			botonIniciarSesion.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					controller.loginGestionPelis(textoUsuario.getText(), textoContrasenya.getText());
					VentanaAdmin vi = new VentanaAdmin(client);
					vi.setVisible(true);
					setVisible(false);
					
				}
				
			});
			
			
			botonRegistrarse.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new VentanaRegistro();
					dispose();

				}
			});
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(542, 355);
			setVisible(true);
			setTitle("Pelis PSC");

		}

		
		/**
		 * Launch the application.
		 */

		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VentanaLogin window = new VentanaLogin();
						window.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

	}

