package Tienda;

import Cliente.Cliente;
import Cliente.ColaClientes;
import Arbol.ArbolProductos;
import Producto.Producto;
import Nodo.Nodo; // Asegúrate de importar el Nodo que maneja la ListaProductos de tu compañero

/**
 * Clase que gestiona la integración entre el inventario (BST) y la cola de prioridad.
 * Cumple con los requerimientos de la consigna para el control del sistema.
 */
public class Tienda {
    private ArbolProductos inventario;
    private ColaClientes colaAtencion;

    public Tienda() {
        this.inventario = new ArbolProductos();
        this.colaAtencion = new ColaClientes();
    }

    public ArbolProductos getInventario() {
        return inventario;
    }

    public ColaClientes getColaAtencion() {
        return colaAtencion;
    }

    /**
     * Atiende al cliente con mayor prioridad en la cola, calcula el costo total
     * de su ListaProductos (carrito) e imprime la factura.
     */
    public void atenderSiguienteCliente() {
        Cliente clienteActual = colaAtencion.desencolar();

        if (clienteActual == null) {
            return; // El método desencolar() de tu compañero ya avisa si está vacía
        }

        System.out.println("\n=======================================");
        System.out.println("          FACTURA DE COMPRA            ");
        System.out.println("=======================================");
        System.out.println("Cliente: " + clienteActual.getNombre() + " " + clienteActual.getPrimerApellido());
        System.out.println("Prioridad: " + clienteActual.getPrioridad() + " (" + clienteActual.getTipoPrioridadString() + ")");
        System.out.println("---------------------------------------");
        System.out.println("Detalle de productos:");

        double totalAcumulado = 0.0;

        // Recorrido de la ListaProductos (carrito) del cliente para calcular el costo
        Nodo actual = clienteActual.getCarrito().getCabeza();

        if (actual == null) {
            System.out.println("  (El carrito está vacío)");
        } else {
            while (actual != null) {
                Producto p = actual.getProducto();
                // Se asume cantidad 1 por producto agregado para el cálculo básico
                System.out.printf(" - %-20s : $%.2f\n", p.getNombre(), p.getPrecio());
                totalAcumulado += p.getPrecio();
                actual = actual.getSig();
            }
        }

        System.out.println("---------------------------------------");
        System.out.printf("COSTO TOTAL ACUMULADO : $%.2f\n", totalAcumulado);
        System.out.println("=======================================\n");
    }
}