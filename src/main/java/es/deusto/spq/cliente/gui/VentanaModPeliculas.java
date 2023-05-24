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

public class VentanaModPeliculas extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModPeliculas frame = new VentanaModPeliculas();
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
	public VentanaModPeliculas() {
		
		  client = ClientBuilder.newClient();

	        final WebTarget appTarget = client.target("http://localhost:8080/webapi");
	        final WebTarget pelisTarget = appTarget.path("peliculas");
	        final WebTarget peliculasAllTarget = pelisTarget.path("all");
	        final WebTarget addPeliTarget = pelisTarget.path("reg");

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//JList listaPeliculas = new JList();
		//scrollPane.setRowHeaderView(listaPeliculas);
		
	     final DefaultListModel<Pelicula> peliListModel = new DefaultListModel<>();
	     final JList<Pelicula> listaPelis = new JList<>(peliListModel);
	     
	     JScrollPane scrollPane = new JScrollPane(listaPelis);
		scrollPane.setBounds(6, 6, 600, 444);
		contentPane.add(scrollPane);
		
		JButton btnGetPeliculas = new JButton("Get Peliculas");
		btnGetPeliculas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
	                    GenericType<List<Pelicula>> genericType = new GenericType<List<Pelicula>>() {};
	                    List<Pelicula> peliculas = peliculasAllTarget.request(MediaType.APPLICATION_JSON).get(genericType);
	                    peliListModel.clear();
	                    for (Pelicula peli : peliculas) {
	                    	peliListModel.addElement(peli);
	                    }
	                } catch (ProcessingException ex) {
	                    JOptionPane.showMessageDialog(VentanaModPeliculas.this, "Error connecting with server", "Error message", ERROR_MESSAGE);
	                }
			}
		});
		btnGetPeliculas.setBounds(611, 46, 183, 29);
		contentPane.add(btnGetPeliculas);
		
		
		
		final JButton btnModSeleccion = new JButton("Modificar Selección");
		btnModSeleccion.setEnabled(false);
		btnModSeleccion.setBounds(611, 123, 183, 29);
		contentPane.add(btnModSeleccion);
		
		btnModSeleccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaModPeliculaForm vMPF = new VentanaModPeliculaForm(listaPelis.getSelectedValue());
				vMPF.setVisible(true);
			}
		});
		
		
		listaPelis.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting() && listaPelis.getSelectedIndex() != -1) {
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
		
		final JButton btnEliminarPelicula = new JButton("Eliminar Selección");
		btnEliminarPelicula.setBounds(611, 177, 183, 29);
		contentPane.add(btnEliminarPelicula);
		
		
		listaPelis.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting() && listaPelis.getSelectedIndex() != -1) {
		        	btnEliminarPelicula.setEnabled(true);
		        } else {
		        	btnEliminarPelicula.setEnabled(false);
		        }
		    }
		});
		
		
		btnEliminarPelicula.addActionListener(new ActionListener() {
	        	
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                WebTarget deleteTarget = pelisTarget.path(listaPelis.getSelectedValue().getNombrePelicula());
	                Response response = deleteTarget.request().buildDelete().invoke();
	                
	               // deleteTarget.request().delete();
	                System.out.println(listaPelis.getSelectedValue().getNombrePelicula());
	                
	               // WebTarget.class.
	               
	                
	                if (response.getStatus() == Status.OK.getStatusCode()) {
	                    JOptionPane.showMessageDialog(VentanaModPeliculas.this, "Pelicula '" + listaPelis.getSelectedValue().getNombrePelicula() + "'" + " correctly deleted", "Message", JOptionPane.INFORMATION_MESSAGE);
	                    peliListModel.removeElementAt(listaPelis.getSelectedIndex());
	                    
	                    
	                } else {
	                    JOptionPane.showMessageDialog(VentanaModPeliculas.this, "Could not delete pelicula '" + listaPelis.getSelectedValue().getNombrePelicula() + "'", "Message", JOptionPane.ERROR_MESSAGE);
	                }
	               
	            }

	        });
	}
}
