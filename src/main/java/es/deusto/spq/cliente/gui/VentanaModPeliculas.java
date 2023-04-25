package es.deusto.spq.cliente.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JButton;

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
	public VentanaModPeliculas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 293, 444);
		contentPane.add(scrollPane);
		
		JList listaPeliculas = new JList();
		scrollPane.setRowHeaderView(listaPeliculas);
		
		JButton btnGetPeliculas = new JButton("Get Peliculas");
		btnGetPeliculas.setBounds(311, 46, 183, 29);
		contentPane.add(btnGetPeliculas);
		
		JButton btnModSeleccion = new JButton("Modificar Selección");
		btnModSeleccion.setEnabled(false);
		btnModSeleccion.setBounds(311, 123, 183, 29);
		contentPane.add(btnModSeleccion);
		
		JButton btnMenu = new JButton("Volver al Menu");
		btnMenu.setBounds(311, 421, 183, 29);
		contentPane.add(btnMenu);
	}
}
