package modelo;

import interfaces.Cancelable;
import interfaces.Despachable;

public abstract class Pedido implements Despachable, Cancelable {
    protected int id;
    protected String direccion;
    protected double distancia;
    protected String repartidor;

    public Pedido(int id, String direccion, double distancia) {
        this.id = id;
        this.direccion = direccion;
        this.distancia = distancia;
        this.repartidor = "No asignado";
    }

    public abstract int calcularTiempoEntrega();

    public void asignarRepartidor() {
        this.repartidor = "Repartidor General por Defecto";
    }

    public void asignarRepartidor(String nombre) {
        this.repartidor = nombre;
    }

    public void mostrarResumen() {
        System.out.println("Pedido #" + id + " | Dirección: " + direccion + " | Distancia: " + distancia + " km | Repartidor: " + repartidor + " | Tiempo Estimado: " + calcularTiempoEntrega() + " mins");
    }

    @Override
    public void despachar() {
        System.out.println("Pedido #" + id + " despachado correctamente.");
    }

    @Override
    public void cancelar() {
        System.out.println("→ Pedido cancelado exitosamente.");
    }

    public String getRepartidor() {
        return repartidor;
    }

    public int getId() {
        return id;
    }
}