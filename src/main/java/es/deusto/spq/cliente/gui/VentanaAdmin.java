package es.deusto.spq.cliente.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAdmin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdmin frame = new VentanaAdmin();
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
	public VentanaAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(73, 90, 255));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(245, 9, 0));
		separator.setBounds(307, 95, -180, 134);
		contentPane.add(separator);
		
		JButton btnModificarCatalogo = new JButton("Modificar Catálogo");
		btnModificarCatalogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaModPeliculas vMP = new VentanaModPeliculas();
				vMP.setVisible(true);
			}
		});
		btnModificarCatalogo.setBounds(239, 123, 187, 29);
		contentPane.add(btnModificarCatalogo);
		
		JButton btnModificarUsuario = new JButton("Modificar Usuario");
		btnModificarUsuario.setBounds(239, 164, 187, 29);
		contentPane.add(btnModificarUsuario);
		
		JButton btnAnyadirNoticia = new JButton("Añadir Noticia");
		btnAnyadirNoticia.setBounds(6, 164, 170, 29);
		contentPane.add(btnAnyadirNoticia);
		
		JButton btnAnyadirPelicula = new JButton("Añadir Pelicula");
		btnAnyadirPelicula.setBounds(6, 123, 170, 29);
		contentPane.add(btnAnyadirPelicula);
		
		JLabel lblBienvenido = new JLabel("Bienvenido [ADMIN] - ");
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setBounds(6, 17, 438, 16);
		contentPane.add(lblBienvenido);
		
		JLabel lblQuickActions = new JLabel("Quick actions");
		lblQuickActions.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuickActions.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		lblQuickActions.setBounds(6, 77, 170, 16);
		contentPane.add(lblQuickActions);
		
		JLabel lblModificicar = new JLabel("Modificar");
		lblModificicar.setHorizontalAlignment(SwingConstants.CENTER);
		lblModificicar.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		lblModificicar.setBounds(256, 78, 170, 16);
		contentPane.add(lblModificicar);
	}
}
