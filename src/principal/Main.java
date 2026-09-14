package principal;

import modelo.SpeedFastGestor;

public class Main {
    public static void main(String[] args) {
        // Delegamos la responsabilidad de gestión al controlador independiente
        SpeedFastGestor gestor = new SpeedFastGestor();
        gestor.inicializarSistema();
    }
}