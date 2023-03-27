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

import java.util.List;

import es.deusto.spq.pojo.Usuario;

public class ClientApp extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Client client;

    public ClientApp() {
        client = ClientBuilder.newClient();

        final WebTarget appTarget = client.target("http://localhost:8080/webapi");
        final WebTarget usersTarget = appTarget.path("usuarios");

        setSize(620, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton getUsersButton = new JButton("Get Users");
        JPanel buttonsPanel = new JPanel();

        JButton deleteUserButton = new JButton("Delete User");

        buttonsPanel.add(getUsersButton);
        buttonsPanel.add(deleteUserButton);
        add(buttonsPanel, BorderLayout.SOUTH);

        final DefaultListModel<Usuario> userListModel = new DefaultListModel<>();
        JList<Usuario> userList = new JList<>(userListModel);

        JScrollPane listScrollPane = new JScrollPane(userList);
        add(listScrollPane, BorderLayout.WEST);

        getUsersButton.addActionListener(new ActionListener() {

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

        JPanel rightPanel = new JPanel();
        add(rightPanel, BorderLayout.EAST);

        JButton addUserButton = new JButton("Add user");
        rightPanel.add(addUserButton);

        final JTextField codeTextField = new JTextField("", 2);
        final JTextField usernameTextField = new JTextField("", 10);
        final JTextField passwordTextField = new JTextField("", 10);
        final JTextField emailTextField = new JTextField("", 10);
        final JTextField cardTextField = new JTextField("", 10);
       


        rightPanel.add(codeTextField);
        rightPanel.add(usernameTextField);
        rightPanel.add(passwordTextField);
        rightPanel.add(emailTextField);
        rightPanel.add(cardTextField);
        
        

        addUserButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
            	Usuario newUser = new Usuario(usernameTextField.getText(), passwordTextField.getText(), emailTextField.getText(), cardTextField.getText());
                usersTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
            }
        });

        deleteUserButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                WebTarget deleteTarget = usersTarget.path(codeTextField.getText());
                Response response = deleteTarget.request().delete();
                if (response.getStatus() == Status.OK.getStatusCode()) {
                    JOptionPane.showMessageDialog(ClientApp.this, "User correctly deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(ClientApp.this, "Could not delete user", "Message", JOptionPane.ERROR_MESSAGE);
                }
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