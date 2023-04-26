package es.deusto.spq.cliente.gui;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import com.mysql.cj.xdevapi.Client;

import es.deusto.spq.cliente.ClientApp;
import es.deusto.spq.cliente.Controller;
import es.deusto.spq.cliente.ServiceLocator;
import es.deusto.spq.pojo.Usuario;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;



public class VentanaAmigo extends JFrame {
	

		private static final long serialVersionUID = 1L;

		

		private JPanel contentpane;
		private JLabel labelUsuario = new JLabel();
		private JButton botonEliminarAmigo = new JButton();
		private JButton botonAnyadirAmigo = new JButton();
		ServiceLocator serviceLocator = new ServiceLocator();
		private Controller controller = new Controller(serviceLocator);
		private JTextField textField;
		private JTextField textField_1;
		
		
		
		
		public  VentanaAmigo() {
			
		
		
			contentpane = new JPanel();
			contentpane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentpane);
			contentpane.setLayout(null);
			
		

			JLabel labelTitle = new JLabel("Pelis PSC");
			labelTitle.setFont(new Font("French Script MT", Font.BOLD, 40));
			labelTitle.setBounds(161, 10, 334, 50);
			contentpane.add(labelTitle);

			labelUsuario.setText("Lista de amigos");
			labelUsuario.setOpaque(true);
			labelUsuario.setBounds(24, 50, 169, 20);
			labelUsuario.setFont(new Font("Perpetua", Font.BOLD, 16));
			contentpane.add(labelUsuario, BorderLayout.SOUTH);

			botonEliminarAmigo.setForeground(new Color(0, 0, 0));
			botonEliminarAmigo.setBackground(new Color(0, 51, 255));
			botonEliminarAmigo.setBounds(341, 243, 154, 32);
			botonEliminarAmigo.setText("Eliminar amigo");
			botonEliminarAmigo.setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 16));
			contentpane.add(botonEliminarAmigo);

			botonAnyadirAmigo.setBackground(SystemColor.inactiveCaptionBorder);
			botonAnyadirAmigo.setBounds(341, 190, 154, 32);
			botonAnyadirAmigo.setText("Añadir amigo");
			botonAnyadirAmigo.setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 16));
			contentpane.add(botonAnyadirAmigo);
			
			textField = new JTextField();
			textField.setBounds(275, 122, 96, 19);
			contentpane.add(textField);
			textField.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Usuario:");
			lblNewLabel.setBounds(202, 115, 79, 32);
			contentpane.add(lblNewLabel);
			
			textField_1 = new JTextField();
			textField_1.setBounds(275, 151, 96, 20);
			contentpane.add(textField_1);
			textField_1.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Email:");
			lblNewLabel_1.setBounds(202, 154, 62, 13);
			contentpane.add(lblNewLabel_1);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(189, 247, -167, -169);
			contentpane.add(scrollPane);
			
			JList list = new JList();
			list.setBounds(135, 294, -113, -209);
			contentpane.add(list);
			
			JButton getAmigosButton = new JButton("Get Amigos");
			getAmigosButton.setBounds(36, 294, 85, 21);
			contentpane.add(getAmigosButton);
			
			  getAmigosButton.addActionListener(new ActionListener() {

		            @Override
		            public void actionPerformed(ActionEvent e) {
		                
		            }
		            
		        });

			botonAnyadirAmigo.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
				
					
				}
				
			});
			
			
			botonEliminarAmigo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(542, 355);
			setVisible(true);
			setTitle("Pelis PSC");

		}

		
		

		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VentanaAmigo va = new VentanaAmigo();
						va.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
