package modelo;

import java.util.ArrayList;
import java.util.List;

public class SpeedFastGestor {
    private static List<Pedido> listaPedidos = new ArrayList<>();

    public static void agregarPedido(Pedido pedido) {
        listaPedidos.add(pedido);
    }

    public static List<Pedido> getListaPedidos() {
        return listaPedidos;
    }
}