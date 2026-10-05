package dao;

import modelo.Entrega;
import modelo.Entrega;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class EntregaDAO {

    // CREATE: Registrar una nueva entrega
    public boolean create(Entrega entrega) {
        String sql = "INSERT INTO entregas (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, entrega.getIdPedido());
            pstmt.setInt(2, entrega.getIdRepartidor());
            pstmt.setDate(3, Date.valueOf(LocalDate.now()));
            pstmt.setTime(4, Time.valueOf(LocalTime.now()));

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar la entrega: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // READ: Listar todas las entregas
    public List<Entrega> readAll() {
        List<Entrega> listaEntregas = new ArrayList<>();
        String sql = "SELECT id, id_pedido, id_repartidor, fecha, hora FROM entregas";

        try (Connection conn = Conexion.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Entrega entrega = new Entrega();
                entrega.setId(rs.getInt("id"));
                entrega.setIdPedido(rs.getInt("id_pedido"));
                entrega.setIdRepartidor(rs.getInt("id_repartidor"));
                entrega.setFecha(rs.getString("fecha"));
                entrega.setHora(rs.getString("hora"));
                listaEntregas.add(entrega);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar entregas: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
        }

        return listaEntregas;
    }

    // UPDATE: Actualizar una entrega existente
    public boolean update(Entrega entrega) {
        String sql = "UPDATE entregas SET id_pedido = ?, id_repartidor = ?, fecha = ?, hora = ? WHERE id = ?";

        try (Connection conn = Conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, entrega.getIdPedido());
            pstmt.setInt(2, entrega.getIdRepartidor());
            pstmt.setString(3, entrega.getFecha());
            pstmt.setString(4, entrega.getHora());
            pstmt.setInt(5, entrega.getId());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar entrega: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // DELETE: Eliminar una entrega por su ID
    public boolean delete(int id) {
        String sql = "DELETE FROM entregas WHERE id = ?";

        try (Connection conn = Conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar entrega: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}