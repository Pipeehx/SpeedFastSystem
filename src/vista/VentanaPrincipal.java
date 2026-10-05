package vista;

import dao.RepartidorDAO; // Importamos el DAO que se conecta a la BD

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    // Componente visual para la lista de repartidores
    private JComboBox<String> jComboBoxRepartidores;

    public VentanaPrincipal() {
        // Configuración inicial de tu ventana
        setTitle("SpeedFastSystem - Menú Principal");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();

        // LLAMADA CLAVE: Carga los datos de MySQL al iniciar la ventana
        cargarRepartidoresEnCombo();
    }

    private void initComponents() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JLabel lblTitulo = new JLabel("Seleccione un Repartidor de la Base de Datos:");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 13));
        add(lblTitulo);

        // Inicializamos el ComboBox
        jComboBoxRepartidores = new JComboBox<>();
        jComboBoxRepartidores.setPreferredSize(new Dimension(280, 30));
        add(jComboBoxRepartidores);

        JButton btnRecargar = new JButton("Actualizar Lista");
        btnRecargar.addActionListener(e -> cargarRepartidoresEnCombo());
        add(btnRecargar);
    }

    /**
     * Método para consultar la base de datos y llenar el JComboBox
     */
    public void cargarRepartidoresEnCombo() {
        RepartidorDAO dao = new RepartidorDAO();
        List<String> nombres = dao.obtenerNombresRepartidores();

        // Limpiamos los elementos anteriores
        jComboBoxRepartidores.removeAllItems();

        if (nombres.isEmpty()) {
            jComboBoxRepartidores.addItem("No hay repartidores en la BD");
        } else {
            // Añadimos cada repartidor obtenido de MySQL
            for (String nombre : nombres) {
                jComboBoxRepartidores.addItem(nombre);
            }
        }
    }
}