package principal;

import interfaces.Rastreable;
import modelo.Pedido;
import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;

import java.util.ArrayList;

public class Main implements Rastreable {

    private ArrayList<String> historialEntregas = new ArrayList<>();

    public static void main(String[] args) {
        Main sistema = new Main();
        sistema.ejecutarSimulacion();
    }

    public void ejecutarSimulacion() {
        Pedido p1 = new PedidoComida(101, "Av. Vicuña Mackenna 123", 4.0, "Burger King");
        Pedido p2 = new PedidoEncomienda(102, "Av. Santa Rosa 567", 7.0, 2.5);
        Pedido p3 = new PedidoExpress(103, "Alameda 999", 2.0, true);

        p1.asignarRepartidor();
        p2.asignarRepartidor("Daniela Tapia");
        p3.asignarRepartidor();

        p1.mostrarResumen();
        p1.despachar();
        historialEntregas.add("PedidoComida #" + p1.getId() + " – entregado por " + p1.getRepartidor());

        System.out.println();

        p2.mostrarResumen();
        p2.despachar();
        historialEntregas.add("PedidoEncomienda #" + p2.getId() + " – entregado por " + p2.getRepartidor());

        System.out.println();

        System.out.println("Cancelando Pedido Express #" + p3.getId() + "...");
        p3.cancelar();

        System.out.println();

        verHistorial();
    }

    @Override
    public void verHistorial() {
        System.out.println("Historial:");
        for (String registro : historialEntregas) {
            System.out.println("- " + registro);
        }
    }
}