public class PedidoExpress extends Pedido {

    public PedidoExpress(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        // Fórmula: 10 min base, si es > 5 km se suman 5 min extra
        int tiempoBase = 10;
        if (getDistanciaKm() > 5) {
            tiempoBase += 5;
        }
        return tiempoBase;
    }
}