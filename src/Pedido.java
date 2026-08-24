public abstract class Pedido {
    // Atributos comunes solicitados
    private int idPedido;
    private String direccionEntrega;
    private double distanciaKm;

    // Constructor completo
    public Pedido(int idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
    }

    // Getters y Setters
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public String getDireccionEntrega() { return direccionEntrega; }
    public void setDireccionEntrega(String direccionEntrega) { this.direccionEntrega = direccionEntrega; }

    public double getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(double distanciaKm) { this.distanciaKm = distanciaKm; }

    // Método implementado para mostrar los datos básicos
    public void mostrarResumen() {
        // Formatea el ID con ceros a la izquierda para que luzca como #001
        System.out.println("Pedido #" + String.format("%03d", idPedido));
        System.out.println("Dirección: " + direccionEntrega);
        System.out.println("Distancia: " + (int)distanciaKm + " km");
    }

    // Método abstracto que obligatoriamente implementarán las clases hijas
    public abstract int calcularTiempoEntrega();
}