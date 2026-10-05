package modelo;

import java.util.ArrayList;
import java.util.List;

public class ZonaDeCarga {
    private List<Pedido> pedidosPendientes;

    public ZonaDeCarga() {
        this.pedidosPendientes = new ArrayList<>();
        System.out.println("[Zona de carga inicializada]");
    }

    public synchronized void agregarPedido(Pedido p) {
        pedidosPendientes.add(p);
        // Corregido: se usa getDireccion() en lugar de getDireccionEntrega()
        System.out.println("Pedido #" + p.getId() + " agregado. Destino: " + p.getDireccion());
    }

    public synchronized Pedido retirarPedido() {
        if (pedidosPendientes.isEmpty()) {
            return null;
        }
        // Retira y remueve de forma segura el primer pedido disponible
        return pedidosPendientes.remove(0);
    }
}