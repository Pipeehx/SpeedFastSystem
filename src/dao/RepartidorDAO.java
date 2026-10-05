package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {

    public List<String> obtenerNombresRepartidores() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nombre FROM repartidor";

        try (Connection con = Conexion.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }

        } catch (Exception e) {
            System.out.println("Error al consultar repartidores: " + e.getMessage());
        }

        return lista;
    }
}