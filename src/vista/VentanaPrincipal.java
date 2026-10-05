package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        setTitle("SpeedFast - Gestión de Entregas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnRegistrar = new JButton("Registrar Pedido");
        JButton btnListar = new JButton("Listar Pedidos");
        JButton btnSalir = new JButton("Salir");

        // Navegación hacia el formulario de registro
        btnRegistrar.addActionListener(e -> {
            VentanaRegistroPedido ventanaRegistro = new VentanaRegistroPedido();
            ventanaRegistro.setVisible(true);
        });

        // Navegación hacia la tabla de listado
        btnListar.addActionListener(e -> {
            VentanaListaPedidos ventanaLista = new VentanaListaPedidos();
            ventanaLista.setVisible(true);
        });

        btnSalir.addActionListener(e -> System.exit(0));

        add(new JLabel("Bienvenido a SpeedFast System", SwingConstants.CENTER));
        add(btnRegistrar);
        add(btnListar);
        add(btnSalir);
    }
}