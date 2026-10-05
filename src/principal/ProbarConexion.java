package principal;

import dao.Conexion;
import java.sql.Connection;

public class ProbarConexion {
    public static void main(String[] args) {
        System.out.println("Intentando conectar a MySQL...");
        Connection conexion = Conexion.conectar();

        if (conexion != null) {
            System.out.println("¡Todo listo! La conexión se estableció correctamente.");
        } else {
            System.out.println("No se pudo establecer la conexión. Revisa tus credenciales o el driver.");
        }
    }
}