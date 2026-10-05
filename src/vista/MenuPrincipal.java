package vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    private JComboBox<String> cmbRepartidores;
    private JButton btnActualizarLista;
    private JButton btnVerPedidos;

    public MenuPrincipal() {
        setTitle("SpeedFastSystem - Menú Principal");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        add(new JLabel("Seleccione un Repartidor de la Base de Datos:"));

        cmbRepartidores = new JComboBox<>();
        cmbRepartidores.addItem("Juan Pérez");
        add(cmbRepartidores);

        btnActualizarLista = new JButton("Actualizar Lista");
        add(btnActualizarLista);

        btnVerPedidos = new JButton("Ver Pedidos en Base de Datos");
        btnVerPedidos.addActionListener(e -> {
            VentanaListaPedidos ventanaTabla = new VentanaListaPedidos();
            ventanaTabla.setVisible(true);
        });
        add(btnVerPedidos);
    }
}