public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        // Fórmula: 20 min + 1.5 min por kilómetro (redondeado a entero)
        double calculo = 20 + (1.5 * getDistanciaKm());
        return (int) Math.round(calculo);
    }
}