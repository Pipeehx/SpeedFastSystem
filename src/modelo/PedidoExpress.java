package modelo;

public class PedidoExpress extends Pedido {
    private boolean esUrgente;

    public PedidoExpress(int id, String direccion, double distancia, boolean esUrgente) {
        super(id, direccion, distancia);
        this.esUrgente = esUrgente;
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (this.distancia * 2) + 5; // Más rápido
    }

    @Override
    public void asignarRepartidor() {
        this.repartidor = "Carlos Soto";
    }

    @Override
    public void mostrarResumen() {
        System.out.print("[Pedido Express] ");
        super.mostrarResumen();
    }
}