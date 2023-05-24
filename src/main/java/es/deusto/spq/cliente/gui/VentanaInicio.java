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
	
	
	private static final long serialVersionUID = 1L;
	private JPanel contentpane;
	/**
	 * Create the frame.
	 */
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
		
		JButton btnNotis = new JButton("Notificaciones");
		btnNotis.setBounds(137, 240, 153, 31);
		btnNotis.setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 16));
		contentpane.add(btnNotis);
		
		JButton btnPerfil = new JButton("Perfil");
		btnPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	VentanaPerfil vp = new VentanaPerfil();
				//vp.setVisible(true);
			}
		});
		btnPerfil.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		btnPerfil.setBounds(137, 236, 153, 31);
		contentpane.add(btnPerfil);
		
		btnVerPelis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//new VentanaModPeliculas();
				dispose();

			}
		});
		
		btnPelisAñadidas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//new VentanaTCompra();
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
		
		/*btnPerfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 new VentanaPerfil();
				dispose();
			}
		});*/
		
		btnNotis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new VentanaNotificaciones();
				dispose();

			}
		});
		
	}
	/**
	 * Launch the application.
	 */
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
