package Arbol;

import Producto.Producto;

/**
 * Nodo del arbol binario de búsqueda (BST) utilizado por ArbolProductos.
 * A diferencia de Nodo.Nodo (que se usa para la lista enlazada del carrito),
 * este nodo tiene dos punteros -izquierdo y derecho- ya que forma parte
 * de una estructura jerarquica y no de una estructura lineal.
 */
public class NodoProducto {
    private Producto producto;
    private NodoProducto izquierdo;
    private NodoProducto derecho;

    // Constructor: un nodo nuevo siempre nace como hoja (sin hijos)
    public NodoProducto(Producto producto) {
        this.producto = producto;
        this.izquierdo = null;
        this.derecho = null;
    }

    // Métodos accesores (getters/setters)
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public NodoProducto getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoProducto izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoProducto getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoProducto derecho) {
        this.derecho = derecho;
    }
}
