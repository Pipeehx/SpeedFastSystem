package vista;

import modelo.Pedido;
import modelo.SpeedFastGestor;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {
    private JTextField txtId;
    private JTextField txtDireccion;
    private JComboBox<String> cmbEstado; // Opcional para asignar estado inicial si lo deseas

    public VentanaRegistroPedido() {
        setTitle("Registrar Nuevo Pedido");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel(" ID Pedido (número):"));
        txtId = new JTextField();
        add(txtId);

        add(new JLabel(" Dirección de Entrega:"));
        txtDireccion = new JTextField();
        add(txtDireccion);

        add(new JLabel(" Estado Inicial:"));
        cmbEstado = new JComboBox<>(new String[]{"PENDIENTE", "EN_CAMINO", "ENTREGADO"});
        add(cmbEstado);

        JButton btnGuardar = new JButton("Guardar");
        add(new JLabel()); // Espaciador
        add(btnGuardar);

        btnGuardar.addActionListener(e -> guardarPedido());
    }

    private void guardarPedido() {
        try {
            // Validar que el ID sea un número entero válido
            int id = Integer.parseInt(txtId.getText().trim());
            String direccion = txtDireccion.getText().trim();
            String estadoSeleccionado = (String) cmbEstado.getSelectedItem();

            if (direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "La dirección no puede estar vacía.", "Error de validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear el pedido usando tu constructor
            Pedido nuevoPedido = new Pedido(id, direccion);

            // Si deseas setear el estado seleccionado del JComboBox:
            if (estadoSeleccionado != null) {
                nuevoPedido.setEstado(estadoSeleccionado);
            }

            // Guardar en el gestor en memoria
            SpeedFastGestor.agregarPedido(nuevoPedido);

            JOptionPane.showMessageDialog(this, "Pedido registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID del pedido debe ser un número entero válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Estado inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}