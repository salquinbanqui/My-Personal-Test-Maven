package es.deusto.spq.cliente.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class VentanaInicio extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentpane;

	public VentanaInicio() {
		
		contentpane = new JPanel();
		contentpane.setBorder(new EmptyBorder(50, 5, 5, 5));
		setContentPane(contentpane);
		contentpane.setLayout(null);

		JLabel labelTitulo = new JLabel("Pelis PSC");
		labelTitulo.setFont(new Font("French Script MT", Font.BOLD, 40));
		labelTitulo.setBounds(108, 11, 194, 52);
		contentpane.add(labelTitulo);
		
		JButton btnVerPelis = new JButton("Películas");
		btnVerPelis.setBounds(149, 74, 121, 31);
		btnVerPelis.setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 16));
		contentpane.add(btnVerPelis);
		
		JButton btnPelisAñadidas = new JButton("Biblioteca");
		btnPelisAñadidas.setBounds(149, 130, 121, 31);
		btnPelisAñadidas.setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 16));
		contentpane.add(btnPelisAñadidas);
		
		JButton btnAmigos = new JButton("Lista de amigos");
		btnAmigos.setBounds(137, 185, 153, 31);
		btnAmigos.setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 16));
		contentpane.add(btnAmigos);
		
		btnVerPelis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new VentanaPeliculas();
				dispose();

			}
		});
		
		btnPelisAñadidas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new VentanaTCompra();
				dispose();

			}
		});
		
		btnAmigos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new VentanaAmigo();
				dispose();

			}
		});

	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio window = new VentanaInicio();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
