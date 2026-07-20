package Arbol;

import Producto.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * arbol binario de busqueda (BST) que representa el inventario de la Tienda.
 * La llave de ordenamiento/busqueda es el nombre del producto (comparado sin
 * distinguir mayusculas/minusculas), por lo que a la izquierda de cada nodo
 * quedan los productos cuyo nombre es alfabeticamente menor y a la derecha
 * los que son mayores.
 *
 * Todos los recorridos se implementan de forma recursiva, que es la manera
 * natural de trabajar sobre árboles binarios.
 */
public class ArbolProductos {

    private NodoProducto raiz;

    public ArbolProductos() {
        this.raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    // Punto de entrada publico: inserta un producto en el arbol, ordenado por nombre.
    public void insertar(Producto p) {
        if (p == null || p.getNombre() == null) {
            return;
        }
        raiz = insertarRecursivo(raiz, p);
    }

    // Insercion recursiva estandar de un BST:
    private NodoProducto insertarRecursivo(NodoProducto actual, Producto p) {
        if (actual == null) {
            return new NodoProducto(p);
        }

        int comparacion = p.getNombre().compareToIgnoreCase(actual.getProducto().getNombre());

        if (comparacion < 0) {
            actual.setIzquierdo(insertarRecursivo(actual.getIzquierdo(), p));
        } else if (comparacion > 0) {
            actual.setDerecho(insertarRecursivo(actual.getDerecho(), p));
        } else {
            System.out.println("Ya existe un producto con el nombre '" + p.getNombre() +
                    "' en el inventario; no se insertó un duplicado.");
        }

        return actual;
    }

    public Producto buscar(String nombre) {
        if (nombre == null) {
            return null;
        }
        NodoProducto encontrado = buscarRecursivo(raiz, nombre);
        return (encontrado != null) ? encontrado.getProducto() : null;
    }

    // Búsqueda recursiva estándar de un BST: en cada paso se descarta la mitad
    // del árbol restante, comparando el nombre buscado contra la llave del nodo actual.
    private NodoProducto buscarRecursivo(NodoProducto actual, String nombre) {
        if (actual == null) {
            return null;
        }

        int comparacion = nombre.compareToIgnoreCase(actual.getProducto().getNombre());

        if (comparacion == 0) {
            return actual;
        } else if (comparacion < 0) {
            return buscarRecursivo(actual.getIzquierdo(), nombre);
        } else {
            return buscarRecursivo(actual.getDerecho(), nombre);
        }
    }

    public boolean contiene(String nombre) {
        return buscar(nombre) != null;
    }

    // Recorrido in-order: visita izquierda -> raíz -> derecha
    // ordenado por nombre produce el listado alfabético de todo el inventario.
    public void listarProductos() {
        if (estaVacio()) {
            System.out.println("El inventario de la tienda está vacío.");
            return;
        }

        System.out.println("\n### INVENTARIO de la tienda (orden alfabético) ###");
        listarRecursivo(raiz);
        System.out.println("---------------------------------------------------\n");
    }

    private void listarRecursivo(NodoProducto actual) {
        if (actual == null) {
            return;
        }
        listarRecursivo(actual.getIzquierdo());
        System.out.println(actual.getProducto());
        listarRecursivo(actual.getDerecho());
    }

    // Variante que devuelve la lista ordenada en vez de imprimirla
    public List<Producto> obtenerListaOrdenada() {
        List<Producto> resultado = new ArrayList<>();
        obtenerListaOrdenadaRecursivo(raiz, resultado);
        return resultado;
    }

    private void obtenerListaOrdenadaRecursivo(NodoProducto actual, List<Producto> acumulador) {
        if (actual == null) {
            return;
        }
        obtenerListaOrdenadaRecursivo(actual.getIzquierdo(), acumulador);
        acumulador.add(actual.getProducto());
        obtenerListaOrdenadaRecursivo(actual.getDerecho(), acumulador);
    }
}
