package modelo;

public class Repartidor implements Runnable {
    private String nombre;
    private ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {
        while (true) {
            // Retirar pedido de forma sincronizada
            Pedido pedido = zonaDeCarga.retirarPedido();

            // Si no hay más pedidos, el repartidor finaliza su trabajo
            if (pedido == null) {
                break;
            }

            try {
                System.out.println("[Repartidor - " + nombre + "] Retirando pedido #" + pedido.getId() + "...");

                // Cambiar estado a EN_REPARTO
                pedido.setEstado(EstadoPedido.EN_REPARTO);
                System.out.println("[Repartidor - " + nombre + "] Estado: " + pedido.getEstado());

                System.out.println("[Repartidor - " + nombre + "] Entregando pedido #" + pedido.getId() + "...");

                // Simular el tiempo de entrega con Thread.sleep
                Thread.sleep(1500);

                // Cambiar estado a ENTREGADO
                pedido.setEstado(EstadoPedido.ENTREGADO);
                System.out.println("[Repartidor - " + nombre + "] Estado: " + pedido.getEstado());

            } catch (InterruptedException e) {
                System.err.println("[Repartidor - " + nombre + "] El hilo fue interrumpido: " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}