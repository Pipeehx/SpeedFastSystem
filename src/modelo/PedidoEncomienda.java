package modelo;

public class PedidoEncomienda extends Pedido {
    private double pesoKg;

    public PedidoEncomienda(int id, String direccion, double distancia, double pesoKg) {
        super(id, direccion, distancia);
        this.pesoKg = pesoKg;
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (this.distancia * 6) + 10; // Lógica propia de encomienda
    }

    @Override
    public void asignarRepartidor() {
        this.repartidor = "Daniela Tapia"; // Automático para encomienda
    }

    @Override
    public void mostrarResumen() {
        System.out.print("[Pedido Encomienda] ");
        super.mostrarResumen();
    }
}