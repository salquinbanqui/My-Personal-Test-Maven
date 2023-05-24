package es.deusto.spq.cliente.gui;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.glassfish.grizzly.streams.AbstractStreamWriter.DisposeBufferCompletionHandler;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaNotificaciones
{
    public static void main(String[] args) {
        // Crear la ventana
        JFrame ventana = new JFrame("Mi ventana");
        ventana.setSize(548, 344);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Crear un área de texto para mostrar las notificaciones
        JTextArea areaNotificaciones = new JTextArea();
        areaNotificaciones.setEditable(false);
        
        // Agregar el área de texto a la ventana
        ventana.getContentPane().add(areaNotificaciones);
        
        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 new VentanaInicio();
        	}
        });
        ventana.getContentPane().add(btnAtras, BorderLayout.SOUTH);
        
        // Simular múltiples notificaciones de películas nuevas
        String pelicula1 = "Avatar";
        String pelicula2 = "Dragon Ball";
        String pelicula3 = "Posesion Infernal";
        
        areaNotificaciones.append("¡Nueva película disponible: " + pelicula1 + "!\n");
        areaNotificaciones.append("¡Nueva película disponible: " + pelicula2 + "!\n");
        areaNotificaciones.append("¡Nueva película disponible: " + pelicula3 + "!\n");
        
        // Mostrar la ventana
        ventana.setVisible(true);
    }
}






