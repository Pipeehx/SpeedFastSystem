package vista;

import modelo.Entrega;
import dao.EntregaDAO;
import dao.PedidoDAO;
import dao.RepartidorDAO;
import modelo.Pedido;
import modelo.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaEntregas extends JFrame {

    private JComboBox<String> cbPedidos;
    private JComboBox<String> cbRepartidores;
    private JButton btnRegistrar;
    private JTable tablaEntregas;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar;

    public VentanaEntregas() {
        setTitle("SpeedFastSystem - Gestión de Entregas");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        cargarComboBoxes();
        cargarTablaEntregas();
        configurarEventos();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // 1. Panel Superior para el Registro de Entregas
        JPanel panelNorte = new JPanel(new GridBagLayout());
        panelNorte.setBorder(BorderFactory.createTitledBorder("Registrar Nueva Entrega"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        panelNorte.add(new JLabel("Seleccionar Pedido:"), gbc);
        cbPedidos = new JComboBox<>();
        gbc.gridx = 1; gbc.gridy = 0;
        panelNorte.add(cbPedidos, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelNorte.add(new JLabel("Seleccionar Repartidor:"), gbc);
        cbRepartidores = new JComboBox<>();
        gbc.gridx = 1; gbc.gridy = 1;
        panelNorte.add(cbRepartidores, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        btnRegistrar = new JButton("Registrar Entrega");
        panelNorte.add(btnRegistrar, gbc);

        add(panelNorte, BorderLayout.NORTH);

        // 2. Tabla central para listar entregas
        String[] columnas = {"ID Entrega", "ID Pedido", "ID Repartidor", "Fecha", "Hora"};
        modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaEntregas = new JTable(modeloTabla);
        add(new JScrollPane(tablaEntregas), BorderLayout.CENTER);

        // 3. Panel Sur con botón actualizar
        JPanel panelSur = new JPanel();
        btnActualizar = new JButton("Actualizar Lista");
        panelSur.add(btnActualizar);
        add(panelSur, BorderLayout.SOUTH);
    }

    // Cargar dinámicamente los ComboBox desde la base de datos
    private void cargarComboBoxes() {
        cbPedidos.removeAllItems();
        cbRepartidores.removeAllItems();

        PedidoDAO pedidoDAO = new PedidoDAO();
        List<Pedido> listaPedidos = pedidoDAO.readAll();
        for (Pedido p : listaPedidos) {
            cbPedidos.addItem(p.getId() + " - " + p.getDireccion() + " (" + p.getEstado() + ")");
        }

        RepartidorDAO repartidorDAO = new RepartidorDAO();
        List<Repartidor> listaRepartidores = repartidorDAO.readAll();
        for (Repartidor r : listaRepartidores) {
            cbRepartidores.addItem(r.getId() + " - " + r.getNombre());
        }
    }

    // Método para consultar y llenar el JTable de entregas
    public void cargarTablaEntregas() {
        modeloTabla.setRowCount(0);
        EntregaDAO entregaDAO = new EntregaDAO();
        List<Entrega> lista = entregaDAO.readAll();

        for (Entrega ent : lista) {
            Object[] fila = {
                    ent.getId(),
                    ent.getIdPedido(),
                    ent.getIdRepartidor(),
                    ent.getFecha(),
                    ent.getHora()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> {
            String pedidoSel = (String) cbPedidos.getSelectedItem();
            String repartidorSel = (String) cbRepartidores.getSelectedItem();

            if (pedidoSel == null || repartidorSel == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido y un repartidor válidos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // Extraemos únicamente el ID numérico antes del " - "
                int idPedido = Integer.parseInt(pedidoSel.split(" - ")[0]);
                int idRepartidor = Integer.parseInt(repartidorSel.split(" - ")[0]);

                Entrega nuevaEntrega = new Entrega();
                nuevaEntrega.setIdPedido(idPedido);
                nuevaEntrega.setIdRepartidor(idRepartidor);

                EntregaDAO entregaDAO = new EntregaDAO();
                boolean exito = entregaDAO.create(nuevaEntrega);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "¡Entrega registrada con éxito!");
                    cargarTablaEntregas();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo registrar la entrega.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al procesar los IDs: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnActualizar.addActionListener(e -> {
            cargarComboBoxes();
            cargarTablaEntregas();
            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}