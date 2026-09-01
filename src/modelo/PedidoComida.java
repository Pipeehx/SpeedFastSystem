package modelo;

public class PedidoComida extends Pedido {
    private String restaurante;

    public PedidoComida(int id, String direccion, double distancia, String restaurante) {
        super(id, direccion, distancia);
        this.restaurante = restaurante;
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (this.distancia * 4) + 15; // Lógica propia de comida
    }

    @Override
    public void asignarRepartidor() {
        this.repartidor = "Luis Díaz"; // Automático para comida
    }

    @Override
    public void mostrarResumen() {
        System.out.print("[Pedido Comida] ");
        super.mostrarResumen();
    }
}