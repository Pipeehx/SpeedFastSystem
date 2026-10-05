package dao;

import modelo.Repartidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RepartidorDAO {

    // CREATE: Registrar un nuevo repartidor
    public boolean create(Repartidor repartidor) {
        String sql = "INSERT INTO repartidores (nombre) VALUES (?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, repartidor.getNombre());
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar repartidor: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // READ: Obtener todos los repartidores como objetos (necesario para el JTable)
    public List<Repartidor> readAll() {
        List<Repartidor> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM repartidores";

        try (Connection con = Conexion.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Repartidor rep = new Repartidor();
                rep.setId(rs.getInt("id"));
                rep.setNombre(rs.getString("nombre"));
                lista.add(rep);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar repartidores: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }

        return lista;
    }

    // Método original adaptado por si lo usas en algún JComboBox de texto plano
    public List<String> obtenerNombresRepartidores() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nombre FROM repartidores";

        try (Connection con = Conexion.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }

        } catch (Exception e) {
            System.out.println("Error al consultar nombres de repartidores: " + e.getMessage());
        }

        return lista;
    }
}