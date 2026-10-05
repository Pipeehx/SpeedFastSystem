package vista;

import dao.RepartidorDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class MenuPrincipal extends JFrame {

    private JComboBox<String> cmbRepartidores;
    private JButton btnActualizarLista;
    private JButton btnRegistrarPedido;
    private JButton btnVerPedidos;
    private JButton btnGestionRepartidores;
    private JButton btnGestionEntregas;
    private JButton btnSalir;

    public MenuPrincipal() {
        setTitle("SpeedFastSystem - Menú Principal");
        setSize(420, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        cargarRepartidores();
    }

    private void initComponents() {
        // Usamos un panel principal con márgenes (EmptyBorder) y BoxLayout vertical
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

        // 1. Panel superior para el ComboBox de Repartidores
        JPanel panelRepartidor = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panelRepartidor.setBorder(BorderFactory.createTitledBorder("Seleccionar Repartidor Activo"));

        cmbRepartidores = new JComboBox<>();
        cmbRepartidores.setPreferredSize(new Dimension(180, 25));
        btnActualizarLista = new JButton("Actualizar");

        panelRepartidor.add(cmbRepartidores);
        panelRepartidor.add(btnActualizarLista);

        panelRepartidor.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        panelPrincipal.add(panelRepartidor);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10))); // Espaciador

        // 2. Botones de navegación
        btnRegistrarPedido = crearBoton("Registrar Nuevo Pedido");
        btnRegistrarPedido.addActionListener(e -> new VentanaRegistroPedido().setVisible(true));
        panelPrincipal.add(btnRegistrarPedido);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 8)));

        btnVerPedidos = crearBoton("Ver Lista de Pedidos");
        btnVerPedidos.addActionListener(e -> new VentanaListaPedidos().setVisible(true));
        panelPrincipal.add(btnVerPedidos);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 8)));

        btnGestionRepartidores = crearBoton("Gestionar Repartidores");
        btnGestionRepartidores.addActionListener(e -> new VentanaRepartidores().setVisible(true));
        panelPrincipal.add(btnGestionRepartidores);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 8)));

        btnGestionEntregas = crearBoton("Gestionar Entregas");
        btnGestionEntregas.addActionListener(e -> new VentanaEntregas().setVisible(true));
        panelPrincipal.add(btnGestionEntregas);
        panelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));

        // 3. Botón de salida
        btnSalir = crearBoton("Salir del Sistema");
        btnSalir.setBackground(new Color(245, 230, 230)); // Toque visual distintivo
        btnSalir.addActionListener(e -> System.exit(0));
        panelPrincipal.add(btnSalir);

        // Agregamos el panel principal a la ventana
        add(panelPrincipal);

        // Evento para actualizar el JComboBox de repartidores
        btnActualizarLista.addActionListener(e -> {
            cargarRepartidores();
            JOptionPane.showMessageDialog(this, "Lista de repartidores actualizada.", "Información", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    // Método auxiliar para estandarizar el tamaño y estilo de los botones
    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        return boton;
    }

    // Método para poblar el ComboBox consultando la BD
    private void cargarRepartidores() {
        cmbRepartidores.removeAllItems();
        RepartidorDAO repartidorDAO = new RepartidorDAO();
        List<String> nombres = repartidorDAO.obtenerNombresRepartidores();

        if (nombres.isEmpty()) {
            cmbRepartidores.addItem("No hay repartidores registrados");
        } else {
            for (String nombre : nombres) {
                cmbRepartidores.addItem(nombre);
            }
        }
    }
}