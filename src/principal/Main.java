package principal;

import modelo.Pedido;
import modelo.ZonaDeCarga;
import modelo.Repartidor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // 1. Instanciar la ZonaDeCarga
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        // 2. Agregar al menos 5 pedidos al sistema
        zonaDeCarga.agregarPedido(new Pedido(1, "Santiago Centro"));
        zonaDeCarga.agregarPedido(new Pedido(2, "Providencia"));
        zonaDeCarga.agregarPedido(new Pedido(3, "Ñuñoa"));
        zonaDeCarga.agregarPedido(new Pedido(4, "Recoleta"));
        zonaDeCarga.agregarPedido(new Pedido(5, "Las Condes"));

        System.out.println("--------------------------------------------------");

        // 3. Crear e iniciar 3 hilos de tipo Repartidor usando ExecutorService
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(new Repartidor("Juan", zonaDeCarga));
        executor.execute(new Repartidor("Camila", zonaDeCarga));
        executor.execute(new Repartidor("Pedro", zonaDeCarga));

        // Apagar el servicio y esperar la finalización de los hilos
        executor.shutdown();
        try {
            if (!executor.awaitTermination(15, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("--------------------------------------------------");
        System.out.println("[Zona de carga vacía]");
        System.out.println("Todas los pedidos han sido entregados correctamente.");
    }
}