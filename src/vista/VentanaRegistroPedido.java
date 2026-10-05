package vista;

import dao.PedidoDAO;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {

    private JTextField txtDireccion;
    private JTextField txtTipo;
    private JButton btnGuardar;

    public VentanaRegistroPedido() {
        setTitle("SpeedFastSystem - Registrar Pedido");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        configurarEventos();
    }

    private void initComponents() {
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        add(txtDireccion);

        add(new JLabel("Tipo (COMIDA/ENCOMIENDA/EXPRESS):"));
        txtTipo = new JTextField();
        add(txtTipo);

        add(new JLabel(""));
        btnGuardar = new JButton("Guardar Pedido");
        add(btnGuardar);
    }

    private void configurarEventos() {
        btnGuardar.addActionListener(e -> {
            String direccion = txtDireccion.getText().trim();
            String tipo = txtTipo.getText().trim();
            String estado = "PENDIENTE";

            if (direccion.isEmpty() || tipo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            PedidoDAO pedidoDAO = new PedidoDAO();
            boolean guardadoExitoso = pedidoDAO.guardar(direccion, tipo, estado);

            if (guardadoExitoso) {
                JOptionPane.showMessageDialog(this, "¡Pedido registrado correctamente en la base de datos!");
                txtDireccion.setText("");
                txtTipo.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo guardar el pedido en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}