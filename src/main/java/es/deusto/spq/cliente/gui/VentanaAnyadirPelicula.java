package es.deusto.spq.cliente.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;

public class VentanaAnyadirPelicula extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTitulo;
	private JTextField textFieldcategoria;
	private JTextField textFieldPrceio;
	private JTextField textFieldFecha;
	private JTextField textFieldDescripcion;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		textFieldcategoria = new JTextField();
		textFieldcategoria.setBounds(113, 63, 130, 26);
		contentPane.add(textFieldcategoria);
		textFieldcategoria.setColumns(10);
		
		textFieldPrceio = new JTextField();
		textFieldPrceio.setBounds(113, 91, 61, 26);
		contentPane.add(textFieldPrceio);
		textFieldPrceio.setColumns(10);
		
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
	}
}
