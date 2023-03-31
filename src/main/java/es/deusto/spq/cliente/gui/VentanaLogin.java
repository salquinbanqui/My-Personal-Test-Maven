package es.deusto.spq.cliente.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaLogin extends JFrame {
	

		private static final long serialVersionUID = 1L;

		

		private JPanel contentpane;
		private JLabel labelUsuario = new JLabel();
		private JLabel labelContrasenya = new JLabel();
		private JTextField textoUsuario = new JTextField();
		private JPasswordField textoContrasenya = new JPasswordField();
		private JButton botonIniciarSesion = new JButton();
		private JButton botonRegistrarse = new JButton();
		

		public  VentanaLogin() {
			
		
			contentpane = new JPanel();
			contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentpane);
			contentpane.setLayout(null);

			JLabel labelTitle = new JLabel("Pelis PSC");
			labelTitle.setFont(new Font("French Script MT", Font.BOLD, 40));
			labelTitle.setBounds(125, 30, 334, 50);
			contentpane.add(labelTitle);

			labelUsuario.setText("Usuario:");
			labelUsuario.setOpaque(true);
			labelUsuario.setBounds(78, 124, 91, 20);
			labelUsuario.setFont(new Font("Perpetua", Font.BOLD, 16));
			contentpane.add(labelUsuario, BorderLayout.SOUTH);

			textoUsuario.setBounds(179, 125, 169, 20);
			contentpane.add(textoUsuario);

			labelContrasenya.setText("Contraseña:");
			labelContrasenya.setBounds(65, 175, 89, 20);
			labelContrasenya.setOpaque(true);
			labelContrasenya.setFont(new Font("Perpetua", Font.BOLD, 16));
			contentpane.add(labelContrasenya);

			textoContrasenya.setBounds(179, 175, 169, 20);
			contentpane.add(textoContrasenya);

			botonIniciarSesion.setForeground(new Color(0, 0, 0));
			botonIniciarSesion.setBackground(new Color(0, 51, 255));
			botonIniciarSesion.setBounds(229, 243, 154, 32);
			botonIniciarSesion.setText("Iniciar sesión");
			botonIniciarSesion.setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 16));
			contentpane.add(botonIniciarSesion);

			botonRegistrarse.setBackground(SystemColor.inactiveCaptionBorder);
			botonRegistrarse.setBounds(49, 243, 121, 32);
			botonRegistrarse.setText("Registrarse");
			botonRegistrarse.setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 16));
			contentpane.add(botonRegistrarse);

			botonIniciarSesion.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
			
			botonRegistrarse.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new VentanaRegistro();
					dispose();

				}
			});
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(440, 355);
			setVisible(true);
			setTitle("Pelis PSC");

		}

		
		

		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VentanaLogin window = new VentanaLogin();
						window.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

	}

