public class Main {
    public static void main(String[] args) {
        // Instanciación de objetos usando polimorfismo
        Pedido pedido1 = new PedidoComida(101, "Av. Vicuña Mackenna 4860");
        Pedido pedido2 = new PedidoEncomienda(102, "Alameda 1234");
        Pedido pedido3 = new PedidoExpress(103, "Providencia 987");

        // Pruebas llamando a métodos sobrescritos y sobrecargados
        // Versión sobrecargada (con nombre de repartidor, acorde al ejemplo de salida)
        pedido1.asignarRepartidor("Juan Pérez");
        pedido2.asignarRepartidor("Camila Soto");
        pedido3.asignarRepartidor("Luis Díaz");

        // Opcional: Probar la versión sin parámetros (método sobrescrito simple)
        // pedido1.asignarRepartidor();
    }
}