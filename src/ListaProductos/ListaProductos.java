package ListaProductos;

import Nodo.Nodo;
import Producto.Producto;

public class ListaProductos {
    private Nodo cabeza;

    // Constructor
    public ListaProductos() {
        this.cabeza = null;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    // US1: Algoritmo tradicional de inserción al inicio
    public void insertarAlInicio(Producto p) {
        Nodo nuevoNodo = new Nodo(p);
        nuevoNodo.setSig(this.cabeza);
        this.cabeza = nuevoNodo;
    }

    // US1: Algoritmo tradicional de inserción al final
    public void insertarAlFinal(Producto p) {
        Nodo nuevoNodo = new Nodo(p);
        if (this.cabeza == null) {
            this.cabeza = nuevoNodo;
            return;
        }
        Nodo actual = this.cabeza;
        while (actual.getSig() != null) {
            actual = actual.getSig();
        }
        actual.setSig(nuevoNodo);
    }

    // US2: Búsqueda y desvinculación de nodos
    public void eliminarProducto(String nombre) {
        if (this.cabeza == null) {
            return;
        }

        // Si el elemento a eliminar es la cabeza
        if (this.cabeza.getProducto().getNombre().equalsIgnoreCase(nombre)) {
            this.cabeza = this.cabeza.getSig();
            return;
        }

        // Recorrido secuencial para validar coincidencias
        Nodo actual = this.cabeza;
        while (actual.getSig() != null && !actual.getSig().getProducto().getNombre().equalsIgnoreCase(nombre)) {
            actual = actual.getSig();
        }

        // Reasignación de punteros para aislar el nodo objetivo
        if (actual.getSig() != null) {
            actual.setSig(actual.getSig().getSig());
        }
    }

    // US2: Localización por llave primaria y mutación en espacio heap
    public void modificarProducto(String nombre, Producto nuevosDatos) {
        Nodo actual = this.cabeza;
        while (actual != null) {
            if (actual.getProducto().getNombre().equalsIgnoreCase(nombre)) {
                // Se obtiene la referencia del objeto y se mutan sus atributos
                Producto p = actual.getProducto();
                p.setNombre(nuevosDatos.getNombre());
                p.setPrecio(nuevosDatos.getPrecio());
                p.setCategoria(nuevosDatos.getCategoria());
                p.setFechaVencimiento(nuevosDatos.getFechaVencimiento());
                p.setCantidad(nuevosDatos.getCantidad());
                break; // Se detiene la búsqueda tras mutar el objeto
            }
            actual = actual.getSig();
        }
    }
}