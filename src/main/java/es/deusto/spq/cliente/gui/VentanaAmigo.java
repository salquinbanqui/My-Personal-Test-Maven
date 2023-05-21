package es.deusto.spq.cliente.gui;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.deusto.spq.cliente.ClientApp;
import es.deusto.spq.cliente.Controller;
import es.deusto.spq.cliente.ServiceLocator;
import es.deusto.spq.pojo.Usuario;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;


	
public class VentanaAmigo extends JFrame {
	
		private static final long serialVersionUID = 1L;

		

		private JPanel contentpane;
		private JLabel labelUsuario = new JLabel();
		private JButton botonEliminarAmigo = new JButton();
		private JButton botonAnyadirAmigo = new JButton();
		ServiceLocator serviceLocator = new ServiceLocator();
		private Controller controller = new Controller(serviceLocator);
		private JTextField textField;
		
		private Client client;
		
		
		public  VentanaAmigo() {
			
			
			client = ClientBuilder.newClient();

	        final WebTarget appTarget = client.target("http://localhost:8080/webapi");
	        final WebTarget userTarget = appTarget.path("usuarios");
	        final WebTarget userAllTarget = userTarget.path("all");
	        final WebTarget userRegTarget = userTarget.path("reg");
		
			contentpane = new JPanel();
			contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentpane);
			contentpane.setLayout(null);
			
		

			JLabel labelTitle = new JLabel("Pelis PSC");
			labelTitle.setFont(new Font("French Script MT", Font.BOLD, 40));
			labelTitle.setBounds(161, 10, 334, 50);
			contentpane.add(labelTitle);

			labelUsuario.setText("Lista de amigos");
			labelUsuario.setOpaque(true);
			labelUsuario.setBounds(24, 50, 169, 20);
			labelUsuario.setFont(new Font("Perpetua", Font.BOLD, 16));
			contentpane.add(labelUsuario, BorderLayout.SOUTH);

			botonEliminarAmigo.setForeground(new Color(0, 0, 0));
			botonEliminarAmigo.setBackground(new Color(0, 51, 255));
			botonEliminarAmigo.setBounds(341, 243, 154, 32);
			botonEliminarAmigo.setText("Eliminar amigo");
			botonEliminarAmigo.setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 16));
			contentpane.add(botonEliminarAmigo);

			botonAnyadirAmigo.setBackground(SystemColor.inactiveCaptionBorder);
			botonAnyadirAmigo.setBounds(341, 190, 154, 32);
			botonAnyadirAmigo.setText("Añadir amigo");
			botonAnyadirAmigo.setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 16));
			contentpane.add(botonAnyadirAmigo);
			
			textField = new JTextField();
			textField.setBounds(289, 139, 96, 19);
			contentpane.add(textField);
			textField.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Usuario:");
			lblNewLabel.setBounds(224, 132, 79, 32);
			contentpane.add(lblNewLabel);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(189, 247, -167, -169);
			contentpane.add(scrollPane);
			
			final DefaultListModel<Usuario> userListModel = new DefaultListModel<>();
	        final JList<Usuario> userList = new JList<>(userListModel);

	        scrollPane.add(userList);
	        
	        
	        final JTextField emailTextField = new JTextField("", 10);
	        emailTextField.setBounds(142, 135, 130, 26);
	        final JTextField passwordTextField = new JTextField("", 10);
	        passwordTextField.setBounds(142, 182, 130, 26);
	        final JTextField cardTextField = new JTextField("", 10);
	        final JRadioButton rdbtnNewRadioButton = new JRadioButton("is admin");
			
			
			JButton getAmigosButton = new JButton("Get Amigos");
			getAmigosButton.setBounds(36, 294, 85, 21);
			contentpane.add(getAmigosButton);
			
			
			
			  getAmigosButton.addActionListener(new ActionListener() {
				  @Override
		            public void actionPerformed(ActionEvent e) {
		                try {
		                    GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>() {};
		                    List<Usuario> usuarios = userAllTarget.request(MediaType.APPLICATION_JSON).get(genericType);
		                    userListModel.clear();
		                    for (Usuario user : usuarios) {
		                        userListModel.addElement(user);
		                    }
		                } catch (ProcessingException ex) {
		                }
		            }
		            
		        });

		       
			botonAnyadirAmigo.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
			            	
							//DBManager.getInstance();
			            	Usuario newUser = new Usuario(textField.getText(), emailTextField.getText(), passwordTextField.getText(), cardTextField.getText(), rdbtnNewRadioButton.isSelected());
			            	userListModel.addElement(newUser);
			            	
			            	WebTarget userRegTarget = userTarget.path("reg");
							List<String> usuarioL = new ArrayList<>(); 
							usuarioL.add(newUser.getNombreUsuario());
							usuarioL.add(newUser.getEmail());
							usuarioL.add(newUser.getPassword());
							usuarioL.add(newUser.getTarjeta());
							usuarioL.add(newUser.getAdmin());
							
							//userRegTarget.request().post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
							
							userRegTarget.request().post(Entity.entity(usuarioL, MediaType.APPLICATION_JSON));
			            }
			            
				
			});
			
			
			botonEliminarAmigo.addActionListener(new ActionListener() {
				 @Override
		            public void actionPerformed(ActionEvent e) {
		                WebTarget deleteTarget = userTarget.path(userList.getSelectedValue().getNombreUsuario());
		                Response response = deleteTarget.request().buildDelete().invoke();
		                
		               // deleteTarget.request().delete();
		                System.out.println(userList.getSelectedValue().getNombreUsuario());
		                
		               // WebTarget.class.
		               
		                
		                if (response.getStatus() == Status.OK.getStatusCode()) {
		                    JOptionPane.showMessageDialog(VentanaAmigo.this, "User '" + userList.getSelectedValue().getNombreUsuario() + "'" + " correctly deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
		                    userListModel.removeElementAt(userList.getSelectedIndex());
		                    textField.setText("");
		                    emailTextField.setText("");
		                    passwordTextField.setText("");
		                    cardTextField.setText("");
		                    rdbtnNewRadioButton.setSelected(false);
		                    
		                    
		                } else {
		                    JOptionPane.showMessageDialog(VentanaAmigo.this, "Could not delete user '" + userList.getSelectedValue().getNombreUsuario() + "'", "Message", JOptionPane.ERROR_MESSAGE);
		                }
		               
		            }
			});
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(542, 355);
			setVisible(true);
			setTitle("Pelis PSC");

		}

		
		

		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VentanaAmigo va = new VentanaAmigo();
						va.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
