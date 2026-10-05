package modelo;

public class Repartidor implements Runnable {
    private int id; // Agregado para la persistencia en base de datos
    private String nombre;
    private ZonaDeCarga zonaDeCarga; // Opcional, mantenido por tu lógica de hilos

    // Constructor vacío (necesario para el DAO al instanciar registros desde la BD)
    public Repartidor() {
    }

    // Constructor con ID y nombre (ideal para cuando cargas de la BD o listas)
    public Repartidor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Tu constructor original para la lógica de hilos
    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ZonaDeCarga getZonaDeCarga() {
        return zonaDeCarga;
    }

    public void setZonaDeCarga(ZonaDeCarga zonaDeCarga) {
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {
        // Validación por si zonaDeCarga es nulo (cuando se usa puramente para base de datos)
        if (zonaDeCarga == null) {
            return;
        }

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
                pedido.setEstado("EN_REPARTO");
                System.out.println("[Repartidor - " + nombre + "] Estado: " + pedido.getEstado());

                System.out.println("[Repartidor - " + nombre + "] Entregando pedido #" + pedido.getId() + "...");

                // Simular el tiempo de entrega con Thread.sleep
                Thread.sleep(1500);

                // Cambiar estado a ENTREGADO
                pedido.setEstado("ENTREGADO");
                System.out.println("[Repartidor - " + nombre + "] Estado: " + pedido.getEstado());

            } catch (InterruptedException e) {
                System.err.println("[Repartidor - " + nombre + "] El hilo fue interrumpido: " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}