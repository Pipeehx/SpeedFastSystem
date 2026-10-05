package vista;

import modelo.Pedido;
import modelo.SpeedFastGestor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaPedidos extends JFrame {
    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;

    public VentanaListaPedidos() {
        setTitle("Listado de Pedidos Activos");
        setSize(550, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Columnas adaptadas a tus atributos reales
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Dirección de Entrega", "Estado"}, 0);
        tablaPedidos = new JTable(modeloTabla);

        cargarDatos();

        add(new JScrollPane(tablaPedidos), BorderLayout.CENTER);

        JButton btnRefrescar = new JButton("Actualizar Tabla");
        btnRefrescar.addActionListener(e -> cargarDatos());
        add(btnRefrescar, BorderLayout.SOUTH);
    }

    private void cargarDatos() {
        modeloTabla.setRowCount(0); // Limpia la tabla
        for (Pedido p : SpeedFastGestor.getListaPedidos()) {
            // Usando tus métodos getDireccionEntrega() y getEstado()
            modeloTabla.addRow(new Object[]{
                    p.getId(),
                    p.getDireccionEntrega(),
                    p.getEstado()
            });
        }
    }
}