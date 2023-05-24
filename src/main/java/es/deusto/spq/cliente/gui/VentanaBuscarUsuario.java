package es.deusto.spq.cliente.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaBuscarUsuario extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultsArea;

    

    public VentanaBuscarUsuario() {
        // Configurar la ventana
        setTitle("Búsqueda de usuarios");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializar componentes
        searchField = new JTextField();
        searchButton = new JButton("Buscar");
        resultsArea = new JTextArea();

        // Configurar el diseño
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Buscar usuarios: "));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultsArea), BorderLayout.CENTER);

        

       
    }

   

    public static void main(String[] args) {
        VentanaBuscarUsuario bu = new VentanaBuscarUsuario();
        bu.setVisible(true);
    }
}