package es.deusto.spq.cliente.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import es.deusto.spq.pojo.Pelicula;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;

public class VentanaAnyadirPelicula extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTitulo;
	private JTextField textFieldCategoria;
	private JTextField textFieldPrecio;
	private JTextField textFieldFecha;
	private JTextField textFieldDescripcion;
	

	private JLabel lblNewLabel;
	private JButton btnAnyadir;
	
	
	
	private JButton btnVolver;

	
	private Client client;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAnyadirPelicula frame = new VentanaAnyadirPelicula();
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
	public VentanaAnyadirPelicula() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(255, 0, 127));
		separator.setForeground(new Color(255, 0, 127));
		separator.setBounds(400, 6, 0, 273);
		contentPane.add(separator);
		
		JLabel lblTitulo = new JLabel("Titulo: ");
		lblTitulo.setBounds(20, 40, 61, 16);
		contentPane.add(lblTitulo);
		
		JLabel lblCategoria = new JLabel("Categoria: ");
		lblCategoria.setBounds(20, 68, 78, 16);
		contentPane.add(lblCategoria);
		
		JLabel lblPrecio = new JLabel("Precio: ");
		lblPrecio.setBounds(20, 96, 61, 16);
		contentPane.add(lblPrecio);
		
		JLabel lblFecha = new JLabel("Fecha: ");
		lblFecha.setBounds(20, 124, 104, 16);
		contentPane.add(lblFecha);
		
		JLabel lblDescripcion = new JLabel("Descripción: ");
		lblDescripcion.setBounds(20, 157, 104, 16);
		contentPane.add(lblDescripcion);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(113, 35, 130, 26);
		contentPane.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		textFieldCategoria = new JTextField();
		textFieldCategoria.setBounds(113, 63, 130, 26);
		contentPane.add(textFieldCategoria);
		textFieldCategoria.setColumns(10);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBounds(113, 91, 61, 26);
		contentPane.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(113, 119, 61, 26);
		contentPane.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setBounds(113, 152, 260, 90);
		contentPane.add(textFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("€");
		lblNewLabel.setBounds(175, 96, 61, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnAnyadir = new JButton("Añadir");
		btnAnyadir.setBounds(400, 35, 164, 29);
		contentPane.add(btnAnyadir);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(385, 6, 48, 300);
		contentPane.add(separator_1);
		
		JButton btnVolver = new JButton("Cancelar");
		btnVolver.setBounds(400, 83, 164, 29);
		contentPane.add(btnVolver);
	
	
		btnAnyadir.addActionListener(new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
        	//DBManager.getInstance();
        	Pelicula newPeli = new Pelicula(textFieldTitulo.getText(), textFieldCategoria.getText(), Double.parseDouble(textFieldPrecio.getText()), textFieldFecha.getText(), textFieldDescripcion.getText());
        	//userListModel.addElement(newUser);
        	

        	client = ClientBuilder.newClient();

  	        final WebTarget appTarget = client.target("http://localhost:8080/webapi");
  	        final WebTarget pelisTarget = appTarget.path("peliculas");
  	        final WebTarget peliculasAllTarget = pelisTarget.path("all");
  	        final WebTarget addPeliTarget = pelisTarget.path("reg");
        	
        	WebTarget peliRegTarget = pelisTarget.path("reg");
				List<String> peliL = new ArrayList<>(); 
        		peliL.add(newPeli.getNombrePelicula());
        		peliL.add(newPeli.getCategoria());
        		peliL.add(String.valueOf(newPeli.getPrecio()));
        		peliL.add(newPeli.getFecha());
        		peliL.add(newPeli.getDescripcion());
				
				//userRegTarget.request().post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
				
        		peliRegTarget.request().post(Entity.entity(peliL, MediaType.APPLICATION_JSON));
        }
        
        
        
       
    });
	
	
	
	}

	public JButton getBtnAnyadir() {
		// TODO Auto-generated method stub
		return this.btnAnyadir;
	}

	public JTextField getTextFieldTitulo() {
		// TODO Auto-generated method stub
		return this.textFieldTitulo;
	}

	public JTextField getTextFieldCategoria() {
		// TODO Auto-generated method stub
		return this.textFieldCategoria;
	}
	
	public JTextField getTextFieldPrecio() {
		// TODO Auto-generated method stub
		return this.textFieldPrecio;
	}
	public JTextField getTextFieldFecha() {
		// TODO Auto-generated method stub
		return this.textFieldFecha;
	}
	public JTextField getTextFieldDescripcion() {
		// TODO Auto-generated method stub
		return this.textFieldCategoria;
	}

	public void btnAnyadirActionPerformed(Object object) {
		// TODO Auto-generated method stub
		Pelicula newPeli = new Pelicula(textFieldTitulo.getText(), textFieldCategoria.getText(), Double.parseDouble(textFieldPrecio.getText()), textFieldFecha.getText(), textFieldDescripcion.getText());
    	//userListModel.addElement(newUser);
    	

    	client = ClientBuilder.newClient();

	        final WebTarget appTarget = client.target("http://localhost:8080/webapi");
	        final WebTarget pelisTarget = appTarget.path("peliculas");
	        final WebTarget peliculasAllTarget = pelisTarget.path("all");
	        final WebTarget addPeliTarget = pelisTarget.path("reg");
    	
    	WebTarget peliRegTarget = pelisTarget.path("reg");
			List<String> peliL = new ArrayList<>(); 
    		peliL.add(newPeli.getNombrePelicula());
    		peliL.add(newPeli.getCategoria());
    		peliL.add(String.valueOf(newPeli.getPrecio()));
    		peliL.add(newPeli.getFecha());
    		peliL.add(newPeli.getDescripcion());
			
			//userRegTarget.request().post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
			
    		peliRegTarget.request().post(Entity.entity(peliL, MediaType.APPLICATION_JSON));
    }
	
}
