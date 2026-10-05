package vista;

import dao.PedidoDAO;
import modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaListaPedidos extends JFrame {

    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar;

    public VentanaListaPedidos() {
        setTitle("SpeedFastSystem - Lista de Pedidos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        cargarDatosTabla();
        configurarEventos();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // 1. Configuración de la JTable y columnas
        String[] columnas = {"ID", "Dirección", "Tipo", "Estado"};
        modeloTabla = new DefaultTableModel(null, columnas) {
            // Hacemos que las celdas no sean editables directamente haciendo doble clic
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaPedidos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);
        add(scrollPane, BorderLayout.CENTER);

        // 2. Panel inferior con botón de actualizar
        JPanel panelInferior = new JPanel();
        btnActualizar = new JButton("Actualizar Lista");
        panelInferior.add(btnActualizar);
        add(panelInferior, BorderLayout.SOUTH);
    }

    // Método para consultar la BD y llenar el JTable
    public void cargarDatosTabla() {
        // Limpiamos la tabla antes de cargar nuevos datos
        modeloTabla.setRowCount(0);

        PedidoDAO pedidoDAO = new PedidoDAO();
        List<Pedido> listaPedidos = pedidoDAO.readAll();

        for (Pedido p : listaPedidos) {
            Object[] fila = {
                    p.getId(),
                    p.getDireccion(),
                    p.getTipo(),
                    p.getEstado()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void configurarEventos() {
        // Al hacer clic en actualizar, volvemos a consultar la base de datos
        btnActualizar.addActionListener(e -> {
            cargarDatosTabla();
            JOptionPane.showMessageDialog(this, "Lista actualizada correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}