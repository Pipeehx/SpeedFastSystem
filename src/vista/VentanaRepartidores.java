package vista;

import dao.RepartidorDAO;
import modelo.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaRepartidores extends JFrame {

    private JTextField txtNombre;
    private JButton btnGuardar;
    private JTable tablaRepartidores;
    private DefaultTableModel modeloTabla;
    private JButton btnActualizar;

    public VentanaRepartidores() {
        setTitle("SpeedFastSystem - Gestión de Repartidores");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        cargarDatosTabla();
        configurarEventos();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // 1. Panel Superior para el Registro
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelNorte.setBorder(BorderFactory.createTitledBorder("Registrar Nuevo Repartidor"));

        panelNorte.add(new JLabel("Nombre:"));
        txtNombre = new JTextField(15);
        panelNorte.add(txtNombre);

        btnGuardar = new JButton("Guardar");
        panelNorte.add(btnGuardar);

        add(panelNorte, BorderLayout.NORTH);

        // 2. Configuración de la JTable central
        String[] columnas = {"ID", "Nombre"};
        modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Celdas no editables directamente
            }
        };

        tablaRepartidores = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaRepartidores);
        add(scrollPane, BorderLayout.CENTER);

        // 3. Panel Inferior con botón de actualizar
        JPanel panelSur = new JPanel();
        btnActualizar = new JButton("Actualizar Lista");
        panelSur.add(btnActualizar);
        add(panelSur, BorderLayout.SOUTH);
    }

    // Método para consultar la BD y llenar la tabla
    public void cargarDatosTabla() {
        modeloTabla.setRowCount(0); // Limpiar tabla

        RepartidorDAO repartidorDAO = new RepartidorDAO();
        List<Repartidor> lista = repartidorDAO.readAll();

        for (Repartidor r : lista) {
            Object[] fila = {
                    r.getId(),
                    r.getNombre()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void configurarEventos() {
        // Evento para guardar un nuevo repartidor
        btnGuardar.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre del repartidor es obligatorio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Repartidor nuevoRepartidor = new Repartidor();
            nuevoRepartidor.setNombre(nombre);

            RepartidorDAO dao = new RepartidorDAO();
            boolean exito = dao.create(nuevoRepartidor);

            if (exito) {
                JOptionPane.showMessageDialog(this, "¡Repartidor registrado correctamente!");
                txtNombre.setText("");
                cargarDatosTabla(); // Refrescar la tabla automáticamente
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar el repartidor.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Evento para refrescar la tabla manualmente
        btnActualizar.addActionListener(e -> {
            cargarDatosTabla();
            JOptionPane.showMessageDialog(this, "Lista de repartidores actualizada.", "Información", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}