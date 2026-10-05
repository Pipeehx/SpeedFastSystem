package vista;

import dao.PedidoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaListaPedidos extends JFrame {

    private JTable tablaPedidos;
    private JButton btnActualizar;

    public VentanaListaPedidos() {
        setTitle("SpeedFastSystem - Lista de Pedidos");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        cargarDatosTabla();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Configuración de la tabla
        tablaPedidos = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior con botón de actualizar
        JPanel panelInferior = new JPanel();
        btnActualizar = new JButton("Actualizar Tabla");

        btnActualizar.addActionListener(e -> {
            cargarDatosTabla();
            JOptionPane.showMessageDialog(this, "Tabla actualizada desde la base de datos.");
        });

        panelInferior.add(btnActualizar);
        add(panelInferior, BorderLayout.SOUTH);
    }

    public void cargarDatosTabla() {
        PedidoDAO pedidoDAO = new PedidoDAO();
        List<String[]> listaPedidos = pedidoDAO.obtenerPedidosParaTabla();

        // Columnas de la tabla según la base de datos
        String[] columnas = {"ID", "Dirección", "Tipo", "Estado"};

        // Modelo de tabla no editable
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Rellenar filas con los datos de MySQL
        for (String[] fila : listaPedidos) {
            modelo.addRow(fila);
        }

        tablaPedidos.setModel(modelo);
    }
}