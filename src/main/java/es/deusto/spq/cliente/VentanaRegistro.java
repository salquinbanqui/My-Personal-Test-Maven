package es.deusto.spq.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import es.deusto.spq.pojo.Usuario;
import javax.swing.JLabel;

public class VentanaRegistro extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Client client;

    public VentanaRegistro() {
        client = ClientBuilder.newClient();

        final WebTarget appTarget = client.target("http://localhost:8080/webapi");
        final WebTarget usersTarget = appTarget.path("usuarios");

        setSize(904, 561);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

  //    JButton getUsersButton = new JButton("Get Users");
  //	JPanel buttonsPanel = new JPanel();

  //   	JButton deleteUserButton = new JButton("Delete User");

 //       buttonsPanel.add(getUsersButton);
   //     buttonsPanel.add(deleteUserButton);
  //      getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

 //       final DefaultListModel<Usuario> userListModel = new DefaultListModel<>();
  //      JList<Usuario> userList = new JList<>(userListModel);

    //    JScrollPane listScrollPane = new JScrollPane(userList);
     //   getContentPane().add(listScrollPane, BorderLayout.WEST);

    /*    getUsersButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>() {};
                    List<Usuario> usuarios = usersTarget.request(MediaType.APPLICATION_JSON).get(genericType);
                    userListModel.clear();
                    for (Usuario user : usuarios) {
                        userListModel.addElement(user);
                    }
                } catch (ProcessingException ex) {
                    JOptionPane.showMessageDialog(ClientApp.this, "Error connecting with server", "Error message", ERROR_MESSAGE);
                }
            }
            
        });
        
        */

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
        cardTextField.setBounds(142, 282, 130, 26);
        
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
        lblNTarjeta.setBounds(55, 287, 100, 16);
        rightPanel.add(lblNTarjeta);
        
        JLabel lblNewLabel_1 = new JLabel("Confirm password:");
        lblNewLabel_1.setBounds(55, 233, 149, 16);
        rightPanel.add(lblNewLabel_1);
        
        

        addUserButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	Usuario newUser = new Usuario(usernameTextField.getText(), emailTextField.getText(), passwordTextField.getText(), cardTextField.getText(), false);
                usersTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
            }
            
           
        });

    /*    deleteUserButton.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
                WebTarget deleteTarget = usersTarget.path(usernameTextField.getText());
                Response response = deleteTarget.request().delete();
                if (response.getStatus() == Status.OK.getStatusCode()) {
                    JOptionPane.showMessageDialog(ClientApp.this, "User correctly deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ClientApp.this, "Could not delete user", "Message", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        
        */

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