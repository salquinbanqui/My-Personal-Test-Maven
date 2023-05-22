package es.deusto.spq.cliente.gui;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.deusto.spq.cliente.ClientApp;
import es.deusto.spq.pojo.Pelicula;
import es.deusto.spq.pojo.Usuario;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaModificarUsuario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificarUsuario frame = new VentanaModificarUsuario();
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
	public VentanaModificarUsuario() {
		
		  client = ClientBuilder.newClient();

	        final WebTarget appTarget = client.target("http://localhost:8080/webapi");
	        final WebTarget usuariosTarget = appTarget.path("usuarios");
	        final WebTarget usuariosAllTarget = usuariosTarget.path("all");
	        final WebTarget addUsuarioTarget = usuariosTarget.path("reg");

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//JList listaPeliculas = new JList();
		//scrollPane.setRowHeaderView(listaPeliculas);
		
	     final DefaultListModel<Usuario> userListModel = new DefaultListModel<>();
	     JList<Usuario> listaUsuarios = new JList<>(userListModel);
	     
	     JScrollPane scrollPane = new JScrollPane(listaUsuarios);
		scrollPane.setBounds(6, 6, 600, 444);
		contentPane.add(scrollPane);
		
		JButton btnGetUsuarios = new JButton("Get Usuarios");
		btnGetUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
	                    GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>() {};
	                    List<Usuario> usuarios = usuariosAllTarget.request(MediaType.APPLICATION_JSON).get(genericType);
	                    userListModel.clear();
	                    for (Usuario usu : usuarios) {
	                    	userListModel.addElement(usu);
	                    }
	                } catch (ProcessingException ex) {
	                    JOptionPane.showMessageDialog(VentanaModificarUsuario.this, "Error connecting with server", "Error message", ERROR_MESSAGE);
	                }
			}
		});
		btnGetUsuarios.setBounds(611, 46, 183, 29);
		contentPane.add(btnGetUsuarios);
		
		
		
		JButton btnModSeleccion = new JButton("Modificar Selección");
		btnModSeleccion.setEnabled(false);
		btnModSeleccion.setBounds(611, 123, 183, 29);
		contentPane.add(btnModSeleccion);
		
		btnModSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaModificarUsuario2 vMUF = new VentanaModificarUsuario2(listaUsuarios.getSelectedValue());
				vMUF.setVisible(true);
			}
		});
		
		
		listaUsuarios.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting() && listaUsuarios.getSelectedIndex() != -1) {
		        	btnModSeleccion.setEnabled(true);
		        } else {
		        	btnModSeleccion.setEnabled(false);
		        }
		    }
		});
		
		JButton btnMenu = new JButton("Volver al Menu");
		btnMenu.setBounds(611, 421, 183, 29);
		contentPane.add(btnMenu);
		
		btnMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
				VentanaAdmin aw = new VentanaAdmin(client);
				aw.setVisible(true);
				
			}
			
		});
		
		JButton btnEliminarUsuario = new JButton("Eliminar Selección");
		btnEliminarUsuario.setBounds(611, 177, 183, 29);
		contentPane.add(btnEliminarUsuario);
		
		
		listaUsuarios.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting() && listaUsuarios.getSelectedIndex() != -1) {
		        	btnEliminarUsuario.setEnabled(true);
		        } else {
		        	btnEliminarUsuario.setEnabled(false);
		        }
		    }
		});
		
		
		btnEliminarUsuario.addActionListener(new ActionListener() {
	        	
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                WebTarget deleteTarget = usuariosTarget.path(listaUsuarios.getSelectedValue().getNombreUsuario());
	                Response response = deleteTarget.request().buildDelete().invoke();
	                
	               // deleteTarget.request().delete();
	                System.out.println(listaUsuarios.getSelectedValue().getNombreUsuario());
	                
	               // WebTarget.class.
	               
	                
	                if (response.getStatus() == Status.OK.getStatusCode()) {
	                    JOptionPane.showMessageDialog(VentanaModificarUsuario.this, "Usuario '" + listaUsuarios.getSelectedValue().getNombreUsuario() + "'" + " correctly deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
	                    userListModel.removeElementAt(listaUsuarios.getSelectedIndex());
	                    
	                    
	                } else {
	                    JOptionPane.showMessageDialog(VentanaModificarUsuario.this, "Could not delete user '" + listaUsuarios.getSelectedValue().getNombreUsuario() + "'", "Message", JOptionPane.ERROR_MESSAGE);
	                }
	               
	            }

	        });
	}
}
