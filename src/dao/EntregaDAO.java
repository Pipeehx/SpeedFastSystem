package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.sql.Time;

public class EntregaDAO {

    public boolean guardar(int idPedido, int idRepartidor) {
        String sql = "INSERT INTO entrega (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idPedido);
            pstmt.setInt(2, idRepartidor);
            pstmt.setDate(3, Date.valueOf(LocalDate.now()));
            pstmt.setTime(4, Time.valueOf(LocalTime.now()));

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar la entrega: " + e.getMessage());
            return false;
        }
    }
}