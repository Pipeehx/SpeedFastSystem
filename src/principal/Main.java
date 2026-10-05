package principal;

import dao.Conexion;
import vista.MenuPrincipal; // Importa tu menú principal

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Probar la conexión a la base de datos al arrancar
        try (Connection conn = Conexion.conectar()) {
            if (conn != null) {
                System.out.println("¡Conexión exitosa a la base de datos speedfast_db!");
            }
        } catch (Exception e) {
            System.err.println("Error al conectar: " + e.getMessage());
        }

        // Mostrar la ventana del Menú Principal al ejecutar
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
        });
    }
}