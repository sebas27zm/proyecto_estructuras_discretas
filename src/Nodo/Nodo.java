package Nodo;

import Producto.Producto;

public class Nodo {
    private Producto producto;
    private Nodo sig;

    // Constructor
    public Nodo(Producto producto) {
        this.producto = producto;
        this.sig = null;
    }

    // Métodos accesores (getters/setters)
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Nodo getSig() {
        return sig;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }
}