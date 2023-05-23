package es.deusto.spq.cliente.gui;

import javax.swing.*;

import es.deusto.spq.pojo.Usuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAnyadirDinero extends JFrame {
    private Usuario usuario;
    private JTextField amountField;

    public VentanaAnyadirDinero(Usuario usuario) {
        this.usuario = usuario;

        // Configurar la ventana
        setTitle("Añadir Fondos");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializar componentes
        JLabel amountLabel = new JLabel("Cantidad a añadir:");
        amountField = new JTextField();
        JButton addButton = new JButton("Añadir Fondos");

        // Configurar el diseño
        setLayout(new GridLayout(2, 2));
        add(amountLabel);
        add(amountField);
        add(new JLabel());
        add(addButton);

        // Configurar el controlador de eventos para el botón
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFundsToAccount();
            }
        });
    }

    private void addFundsToAccount() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            usuario.addFunds(amount);
            JOptionPane.showMessageDialog(this, "Se añadieron " + amount + " a tu cuenta.");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad inválida. Por favor, ingresa un número válido.");
        }
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        Usuario usuario = new Usuario("nombre_usuario", "email@example.com", "contraseña", "1234-5678-9012-3456", false);
        VentanaAnyadirDinero vad = new VentanaAnyadirDinero(usuario);
        vad.setVisible(true);
    }
}