package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public boolean guardar(String direccion, String tipo, String estado) {
        String sql = "INSERT INTO pedido (direccion, tipo, estado) VALUES (?, ?, ?)";

        try (Connection conn = Conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, direccion);
            pstmt.setString(2, tipo);
            pstmt.setString(3, estado);

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al guardar el pedido: " + e.getMessage());
            return false;
        }
    }

    public List<String[]> obtenerPedidosParaTabla() {
        List<String[]> lista = new ArrayList<>();
        String sql = "SELECT id, direccion, tipo, estado FROM pedido";

        try (Connection conn = Conexion.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String[] fila = {
                        String.valueOf(rs.getInt("id")),
                        rs.getString("direccion"),
                        rs.getString("tipo"),
                        rs.getString("estado")
                };
                lista.add(fila);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar los pedidos: " + e.getMessage());
        }

        return lista;
    }
}