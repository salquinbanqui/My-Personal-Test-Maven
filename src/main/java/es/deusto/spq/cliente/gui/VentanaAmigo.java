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

public class VentanaAmigo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAmigo frame = new VentanaAmigo();
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
	public VentanaAmigo() {
		
		  client = ClientBuilder.newClient();

	        final WebTarget appTarget = client.target("http://localhost:8080/webapi");
	        final WebTarget friendsTarget = appTarget.path("Amigos");
	        final WebTarget friendsAllTarget = friendsTarget.path("all");
	        final WebTarget addfriendTarget = friendsTarget.path("reg");

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//JList listaAmigos = new JList();
		//scrollPane.setRowHeaderView(listaAmigos);
		
	     final DefaultListModel<Usuario> amigoListModel = new DefaultListModel<>();
	     JList<Usuario> listaAmigos = new JList<>(amigoListModel);
	     
	     JScrollPane scrollPane = new JScrollPane(listaAmigos);
		scrollPane.setBounds(6, 6, 600, 444);
		contentPane.add(scrollPane);
		
		JButton btnGetAmigos = new JButton("Get Amigos");
		btnGetAmigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
	                    GenericType<List<Usuario>> genericType = new GenericType<List<Usuario>>() {};
	                    List<Usuario> amigos = friendsAllTarget.request(MediaType.APPLICATION_JSON).get(genericType);
	                    amigoListModel.clear();
	                    for (Usuario amigo : amigos) {
	                    	amigoListModel.addElement(amigo);
	                    }
	                } catch (ProcessingException ex) {
	                    JOptionPane.showMessageDialog(VentanaAmigo.this, "Error connecting with server", "Error message", ERROR_MESSAGE);
	                }
			}
		});
		btnGetAmigos.setBounds(611, 46, 183, 29);
		contentPane.add(btnGetAmigos);
		
		
		
		JButton btnModSeleccion = new JButton("Modificar Selección");
		btnModSeleccion.setEnabled(false);
		btnModSeleccion.setBounds(611, 123, 183, 29);
		contentPane.add(btnModSeleccion);
		
		btnModSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaAmigoForm vaf = new VentanaAmigoForm(listaAmigos.getSelectedValue());
				vaf.setVisible(true);
			}
		});
		
		
		listaAmigos.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting() && listaAmigos.getSelectedIndex() != -1) {
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
		
		JButton btnEliminarAmigo = new JButton("Eliminar Selección");
		btnEliminarAmigo.setBounds(611, 177, 183, 29);
		contentPane.add(btnEliminarAmigo);
		
		
		listaAmigos.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting() && listaAmigos.getSelectedIndex() != -1) {
		        	btnEliminarAmigo.setEnabled(true);
		        } else {
		        	btnEliminarAmigo.setEnabled(false);
		        }
		    }
		});
		
		
		btnEliminarAmigo.addActionListener(new ActionListener() {
	        	
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                WebTarget deleteTarget = friendsTarget.path(listaAmigos.getSelectedValue().getNombreUsuario());
	                Response response = deleteTarget.request().buildDelete().invoke();
	                
	               // deleteTarget.request().delete();
	                System.out.println(listaAmigos.getSelectedValue().getNombreUsuario());
	                
	               // WebTarget.class.
	               
	                
	                if (response.getStatus() == Status.OK.getStatusCode()) {
	                    JOptionPane.showMessageDialog(VentanaAmigo.this, "Amigo '" + listaAmigos.getSelectedValue().getNombreUsuario() + "'" + " correctly deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
	                    amigoListModel.removeElementAt(listaAmigos.getSelectedIndex());
	                    
	                    
	                } else {
	                    JOptionPane.showMessageDialog(VentanaAmigo.this, "Could not delete pelicula '" + listaAmigos.getSelectedValue().getNombreUsuario() + "'", "Message", JOptionPane.ERROR_MESSAGE);
	                }
	               
	            }

	        });
	}
}