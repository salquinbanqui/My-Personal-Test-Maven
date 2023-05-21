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
import es.deusto.spq.pojo.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;

public class VentanaModPeliculaForm extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTitulo;
	private JTextField textFieldCategoria;
	private JTextField textFieldPrecio;
	private JTextField textFieldFecha;
	private JTextField textFieldDescripcion;
	
	private Client client;
	JButton btnGuardarCambios;
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			
			
			public void run() {
				try {
					VentanaModPeliculaForm frame = new VentanaModPeliculaForm(null);
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
	public VentanaModPeliculaForm(Pelicula p) {
		
		client = (Client) ClientBuilder.newClient();
		
	     final WebTarget appTarget = client.target("http://localhost:8080/webapi");
		  final WebTarget pelisTarget = appTarget.path("peliculas");
		
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
		
		setTextFieldTitulo(new JTextField());
		getTextFieldTitulo().setBounds(113, 35, 260, 26);
		contentPane.add(getTextFieldTitulo());
		getTextFieldTitulo().setColumns(10);
		getTextFieldTitulo().setText(p.getNombrePelicula());
		
		setTextFieldCategoria(new JTextField());
		getTextFieldCategoria().setBounds(113, 63, 153, 26);
		contentPane.add(getTextFieldCategoria());
		getTextFieldCategoria().setColumns(10);
		getTextFieldCategoria().setText(p.getCategoria());
		
		setTextFieldPrecio(new JTextField());
		getTextFieldPrecio().setHorizontalAlignment(SwingConstants.RIGHT);
		getTextFieldPrecio().setBounds(113, 91, 80, 26);
		contentPane.add(getTextFieldPrecio());
		getTextFieldPrecio().setColumns(10);
		getTextFieldPrecio().setText(String.valueOf(p.getPrecio()));
		
		setTextFieldFecha(new JTextField());
		getTextFieldFecha().setBounds(113, 119, 130, 26);
		contentPane.add(getTextFieldFecha());
		getTextFieldFecha().setColumns(10);
		getTextFieldFecha().setText(p.getFecha());
		
		setTextFieldDescripcion(new JTextField());
		getTextFieldDescripcion().setBounds(113, 152, 260, 90);
		contentPane.add(getTextFieldDescripcion());
		getTextFieldDescripcion().setColumns(10);
		getTextFieldDescripcion().setText(p.getDescripcion());
		
		
		JLabel lblNewLabel = new JLabel("€");
		lblNewLabel.setBounds(205, 96, 61, 16);
		contentPane.add(lblNewLabel);
		
		setBtnGuardarCambios(new JButton("Guardar cambios"));
		getBtnGuardarCambios().setBounds(400, 35, 164, 29);
		contentPane.add(getBtnGuardarCambios());
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(385, 6, 48, 300);
		contentPane.add(separator_1);
		
		JButton btnVolver = new JButton("Cancelar");
		btnVolver.setBounds(400, 83, 164, 29);
		contentPane.add(btnVolver);
	
	
	getBtnGuardarCambios().addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
         	//DBManager.getInstance();
         	Pelicula newPeli = new Pelicula(getTextFieldTitulo().getText(), getTextFieldCategoria().getText(), Double.parseDouble(getTextFieldPrecio().getText()), getTextFieldFecha().getText(), getTextFieldDescripcion().getText());
         	//userListModel.addElement(newUser);
         	
         	
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

	public JTextField getTextFieldTitulo() {
		return textFieldTitulo;
	}

	public void setTextFieldTitulo(JTextField textFieldTitulo) {
		this.textFieldTitulo = textFieldTitulo;
	}

	public JTextField getTextFieldCategoria() {
		return textFieldCategoria;
	}

	public void setTextFieldCategoria(JTextField textFieldCategoria) {
		this.textFieldCategoria = textFieldCategoria;
	}

	public JTextField getTextFieldPrecio() {
		return textFieldPrecio;
	}

	public void setTextFieldPrecio(JTextField textFieldPrecio) {
		this.textFieldPrecio = textFieldPrecio;
	}

	public JTextField getTextFieldFecha() {
		return textFieldFecha;
	}

	public void setTextFieldFecha(JTextField textFieldFecha) {
		this.textFieldFecha = textFieldFecha;
	}

	public JTextField getTextFieldDescripcion() {
		return textFieldDescripcion;
	}

	public void setTextFieldDescripcion(JTextField textFieldDescripcion) {
		this.textFieldDescripcion = textFieldDescripcion;
	}

	public JButton getBtnGuardarCambios() {
		return btnGuardarCambios;
	}

	public void setBtnGuardarCambios(JButton btnGuardarCambios) {
		this.btnGuardarCambios = btnGuardarCambios;
	}

	public void setPelicula(Pelicula peliculaMock) {
	    getTextFieldTitulo().setText(peliculaMock.getNombrePelicula());
	    getTextFieldCategoria().setText(peliculaMock.getCategoria());
	    getTextFieldPrecio().setText(String.valueOf(peliculaMock.getPrecio()));
	    getTextFieldFecha().setText(peliculaMock.getFecha());
	    getTextFieldDescripcion().setText(peliculaMock.getDescripcion());
	}
	

}





