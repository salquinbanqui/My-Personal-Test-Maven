package es.deusto.spq.cliente.gui;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.pojo.Usuario;

public class VentanaRegistro extends JFrame{

	
	 private static final long serialVersionUID = 1L;

	    private Client client;

	    public VentanaRegistro() {
	        client = ClientBuilder.newClient();

	        final WebTarget appTarget = client.target("http://localhost:8080/webapi");
	        final WebTarget userTarget = appTarget.path("usuarios");
	        final WebTarget userAllTarget = userTarget.path("all");
	        final WebTarget userRegTarget = userTarget.path("reg");

	        setSize(550, 400);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        
	        JPanel Panel = new JPanel();
	        getContentPane().add(Panel);
	        Panel.setLayout(null);

	        JButton addUserButton = new JButton("Add user");
	        addUserButton.setBounds(215, 303, 100, 29);
	        Panel.add(addUserButton);
	        final JTextField emailTextField = new JTextField("", 10);
	        emailTextField.setBounds(204, 135, 130, 26);
	        final JTextField passwordTextField = new JTextField("", 10);
	        passwordTextField.setBounds(204, 182, 130, 26);
	        final JTextField cardTextField = new JTextField("", 10);
	        cardTextField.setBounds(204, 234, 130, 26);
	        
	              //  final JTextField codeTextField = new JTextField("", 10);
	                final JTextField usernameTextField = new JTextField("", 10);
	                usernameTextField.setBounds(204, 88, 130, 26);
	                


	      //  rightPanel.add(codeTextField);
	        Panel.add(usernameTextField);
	        Panel.add(emailTextField);
	        Panel.add(passwordTextField);
	  
	        
	        JLabel lblNewLabel = new JLabel("Username:");
	        lblNewLabel.setBounds(127, 93, 100, 16);
	        Panel.add(lblNewLabel);
	        Panel.add(cardTextField);
	        
	        JLabel lblEmail = new JLabel("Email:");
	        lblEmail.setBounds(145, 140, 100, 16);
	        Panel.add(lblEmail);
	        
	        JLabel lblPassword = new JLabel("Password:");
	        lblPassword.setBounds(127, 187, 100, 16);
	        Panel.add(lblPassword);
	        
	        JLabel lblNTarjeta = new JLabel("Nº Tarjeta:");
	        lblNTarjeta.setBounds(127, 239, 100, 16);
	        Panel.add(lblNTarjeta);
	        
	    	JLabel labelTitle = new JLabel("Registro");
			labelTitle.setFont(new Font("French Script MT", Font.BOLD, 40));
			labelTitle.setBounds(164, 11, 334, 50);
			Panel.add(labelTitle);
			
			 addUserButton.addActionListener(new ActionListener() {
		            
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	//DBManager.getInstance();
		            	Usuario newUser = new Usuario(usernameTextField.getText(), emailTextField.getText(), passwordTextField.getText(), cardTextField.getText(), false);
		     
		            	
		            	WebTarget userRegTarget = userTarget.path("reg");
						List<String> usuarioL = new ArrayList<>(); 
						usuarioL.add(newUser.getNombreUsuario());
						usuarioL.add(newUser.getEmail());
						usuarioL.add(newUser.getPassword());
						usuarioL.add(newUser.getTarjeta());
						usuarioL.add(newUser.getAdmin());
						
						//userRegTarget.request().post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
						
						userRegTarget.request().post(Entity.entity(usuarioL, MediaType.APPLICATION_JSON));
						new VentanaLogin();
						dispose();

						
		            }
		            
		           
		        });
	        
	        setVisible(true);
	        
	    }
	        

	       
        
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new Runnable(){
	        
	            @Override
	            public void run() {
	                new VentanaRegistro();
	            }
	           
	        });
	    }
}
