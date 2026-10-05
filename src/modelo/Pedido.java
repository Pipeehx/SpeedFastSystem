package modelo;

public class Pedido {
    private int id;
    private String direccion;
    private String tipo; // COMIDA, ENCOMIENDA, EXPRESS
    private EstadoPedido estado; // PENDIENTE, EN_REPARTO, ENTREGADO

    // Constructor vacío (necesario para el DAO al instanciar registros desde la BD)
    public Pedido() {
        this.estado = EstadoPedido.PENDIENTE; // Por defecto inicia como PENDIENTE
    }

    // Constructor para registrar nuevos pedidos (sin ID porque es autoincremental)
    public Pedido(String direccion, String tipo, String estado) {
        this.direccion = direccion;
        this.tipo = tipo;
        setEstado(estado); // Asigna y valida el estado
    }

    // Constructor completo (con ID, útil para listar desde la BD)
    public Pedido(int id, String direccion, String tipo, String estado) {
        this.id = id;
        this.direccion = direccion;
        this.tipo = tipo;
        setEstado(estado);
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public EstadoPedido getEstadoEnum() {
        return estado;
    }

    // Devuelve el estado como String para facilitar el manejo con la BD y JComboBox
    public String getEstado() {
        return estado != null ? estado.name() : "PENDIENTE";
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    // Método para actualizar el estado mediante un String (útil para la BD y formularios)
    public void setEstado(String nuevoEstado) {
        if (nuevoEstado != null && !nuevoEstado.isEmpty()) {
            this.estado = EstadoPedido.valueOf(nuevoEstado.toUpperCase());
        }
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " | Destino: " + direccion + " | Tipo: " + tipo + " | Estado: " + estado;
    }
}