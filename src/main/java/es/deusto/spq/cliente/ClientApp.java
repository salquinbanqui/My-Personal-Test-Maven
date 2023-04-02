package es.deusto.spq.cliente;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.ArrayList;
import java.util.List;

import es.deusto.spq.pojo.Usuario;
import es.deusto.spq.server.DBManager;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class ClientApp extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Client client;

    public ClientApp() {
        client = ClientBuilder.newClient();

        final WebTarget appTarget = client.target("http://localhost:8080/webapi");
        final WebTarget userTarget = appTarget.path("usuarios");
        final WebTarget userAllTarget = userTarget.path("all");
        final WebTarget userRegTarget = userTarget.path("reg");

        setSize(904, 561);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton getUsersButton = new JButton("Get Users");
        JPanel buttonsPanel = new JPanel();

        JButton deleteUserButton = new JButton("Delete User");

        buttonsPanel.add(getUsersButton);
        buttonsPanel.add(deleteUserButton);
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        final DefaultListModel<Usuario> userListModel = new DefaultListModel<>();
        JList<Usuario> userList = new JList<>(userListModel);

        JScrollPane listScrollPane = new JScrollPane(userList);
        getContentPane().add(listScrollPane, BorderLayout.WEST);
        
        JPanel rightPanel = new JPanel();
        getContentPane().add(rightPanel);
        rightPanel.setLayout(null);

        JButton addUserButton = new JButton("Add user");
        addUserButton.setBounds(310, 248, 100, 29);
        rightPanel.add(addUserButton);
        final JTextField emailTextField = new JTextField("", 10);
        emailTextField.setBounds(142, 135, 130, 26);
        final JTextField passwordTextField = new JTextField("", 10);
        passwordTextField.setBounds(142, 182, 130, 26);
        final JTextField cardTextField = new JTextField("", 10);
        cardTextField.setBounds(142, 248, 130, 26);
        
              //  final JTextField codeTextField = new JTextField("", 10);
                final JTextField usernameTextField = new JTextField("", 10);
                usernameTextField.setBounds(142, 88, 130, 26);
                


      //  rightPanel.add(codeTextField);
        rightPanel.add(usernameTextField);
        rightPanel.add(emailTextField);
        rightPanel.add(passwordTextField);
        
        JLabel lblNewLabel = new JLabel("Username:");
        lblNewLabel.setBounds(55, 93, 100, 16);
        rightPanel.add(lblNewLabel);
        rightPanel.add(cardTextField);
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(55, 140, 100, 16);
        rightPanel.add(lblEmail);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(55, 187, 100, 16);
        rightPanel.add(lblPassword);
        
        JLabel lblNTarjeta = new JLabel("Nº Tarjeta:");
        lblNTarjeta.setBounds(55, 253, 100, 16);
        rightPanel.add(lblNTarjeta);
        
        JLabel lblNewLabel_1 = new JLabel("Admin:");
        lblNewLabel_1.setBounds(55, 307, 61, 16);
        rightPanel.add(lblNewLabel_1);
        
        JRadioButton rdbtnNewRadioButton = new JRadioButton("is admin");
        rdbtnNewRadioButton.setBounds(131, 303, 141, 23);
        rightPanel.add(rdbtnNewRadioButton);
        
        
        

        getUsersButton.addActionListener(new ActionListener() {

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
                    JOptionPane.showMessageDialog(ClientApp.this, "Error connecting with server", "Error message", ERROR_MESSAGE);
                }
            }
            
        });

       
        
       
        addUserButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	//DBManager.getInstance();
            	Usuario newUser = new Usuario(usernameTextField.getText(), emailTextField.getText(), passwordTextField.getText(), cardTextField.getText(), rdbtnNewRadioButton.isSelected());
            	userListModel.addElement(newUser);
            	
            	WebTarget userRegTarget = userTarget.path("reg");
				List<String> usuarioL = new ArrayList<>(); 
				usuarioL.add(newUser.getNombreUsuario());
				usuarioL.add(newUser.getEmail());
				usuarioL.add(newUser.getPassword());
				usuarioL.add(newUser.getTarjeta());
				usuarioL.add(newUser.getAdmin());
				
				userRegTarget.request().post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
				
            	//userTarget.request().post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
            }
            
           
        });

        deleteUserButton.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
                WebTarget deleteTarget = userTarget.path(userList.getSelectedValue().getNombreUsuario());
                Response response = deleteTarget.request().delete();
                
                System.out.println(userList.getSelectedValue().getNombreUsuario());
                
               // WebTarget.class.
               
                
                if (response.getStatus() == Status.OK.getStatusCode()) {
                    JOptionPane.showMessageDialog(ClientApp.this, "User correctly deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
                    
                } else {
                    JOptionPane.showMessageDialog(ClientApp.this, "Could not delete user", "Message", JOptionPane.ERROR_MESSAGE);
                }
               
                //AUTO-REPAINT DE LA LISTA
               
            }

        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
        
            @Override
            public void run() {
                new ClientApp();
            }
        });
    }
}