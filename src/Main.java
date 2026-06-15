import java.util.Scanner;
import ListaProductos.ListaProductos;
import Producto.Producto;
import Nodo.Nodo;

public class Main {

    public static void main(String[] args) {
        ListaProductos lista = new ListaProductos();
        menu(lista);
    }

    public static void menu(ListaProductos lista){
        Scanner sc = new Scanner(System.in);
        int opcion;

        do{
            System.out.println("\n##### Menu de la aplicacion de ventas #####");
            System.out.println("1. Insertar producto");
            System.out.println("2. Modificar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Reporte de costos");
            System.out.println("5. Salir");
            System.out.print("Eleja una opcion: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion){
                case 1:
                    insertarProducto(sc, lista);
                    break;
                case 2:
                    modificarProducto(sc, lista);
                    break;
                case 3:
                    eliminarProducto(sc, lista);
                    break;
                case 4:
                    reporteCostos(lista);
                    break;
                case 5:
                    System.out.println("Saliendo");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 5);

        sc.close();
    }

    private static void insertarProducto(Scanner sc, ListaProductos lista){
        Producto p = new Producto();

        System.out.print("Nombre: ");
        p.setNombre(sc.nextLine());

        System.out.print("Precio: ");
        p.setPrecio(Double.parseDouble(sc.nextLine()));

        System.out.print("Categoria: ");
        p.setCategoria(sc.nextLine());

        System.out.print("Fecha de vencimiento (vacio si no aplica): ");
        String fecha = sc.nextLine();
        p.setFechaVencimiento(fecha.isEmpty() ? null : fecha);

        System.out.print("Cantidad: ");
        p.setCantidad(Integer.parseInt(sc.nextLine()));

        System.out.print("Insertar al inicio o al final? (i/f): ");
        String pos = sc.nextLine();

        if (pos.equalsIgnoreCase("i")){
            lista.insertarAlInicio(p);
        } else{
            lista.insertarAlFinal(p);
        }

        System.out.println("Producto insertado.");
    }

    private static void modificarProducto(Scanner sc, ListaProductos lista){
        System.out.print("Nombre del producto a modificar: ");
        String nombre = sc.nextLine();

        //Verificar si existe antes de pedir todos los datos
        if (!existeProducto(lista, nombre)){
            System.out.println("Producto no encontrado.");
            return;
        }

        Producto nuevosDatos = new Producto();

        System.out.print("Nuevo nombre: ");
        nuevosDatos.setNombre(sc.nextLine());

        System.out.print("Nuevo precio: ");
        nuevosDatos.setPrecio(Double.parseDouble(sc.nextLine()));

        System.out.print("Nueva categoria: ");
        nuevosDatos.setCategoria(sc.nextLine());

        System.out.print("Nueva fecha de vencimiento (vacio si no aplica): ");
        String fecha = sc.nextLine();
        nuevosDatos.setFechaVencimiento(fecha.isEmpty() ? null : fecha);

        System.out.print("Nueva cantidad: ");
        nuevosDatos.setCantidad(Integer.parseInt(sc.nextLine()));

        lista.modificarProducto(nombre, nuevosDatos);
        System.out.println("Producto modificado.");

        System.out.print("Desea agregar una imagen? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")){
            System.out.print("Ruta de la imagen (ej. /src/main/resources/images/foto.png): ");
            String ruta = sc.nextLine();
            agregarImagenAProducto(lista, nuevosDatos.getNombre(), ruta);
            System.out.println("Imagen agregada.");
        }
    }

    private static void eliminarProducto(Scanner sc, ListaProductos lista){
        System.out.print("Nombre del producto a eliminar: ");
        String nombre = sc.nextLine();

        if (!existeProducto(lista, nombre)){
            System.out.println("Producto no encontrado.");
            return;
        }

        lista.eliminarProducto(nombre);
        System.out.println("Producto eliminado.");
    }

    private static void reporteCostos(ListaProductos lista){
        Nodo actual = lista.getCabeza();
        double costoTotal = 0;

        if (actual == null){
            System.out.println("La lista esta vacia.");
            return;
        }

        System.out.println("\n--- Reporte de Costos ---");

        while (actual != null){
            Producto p = actual.getProducto();
            double costoProducto = p.getPrecio() * p.getCantidad();
            costoTotal += costoProducto;

            System.out.printf("%s -> %.2f x %d = %.2f%n",
                    p.getNombre(), p.getPrecio(), p.getCantidad(), costoProducto);

            actual = actual.getSig();
        }

        System.out.printf("Costo total acumulado: %.2f%n", costoTotal);
    }

    //Métodos auxiliares

    private static boolean existeProducto(ListaProductos lista, String nombre){
        Nodo actual = lista.getCabeza();
        while (actual != null){
            if (actual.getProducto().getNombre().equalsIgnoreCase(nombre)){
                return true;
            }
            actual = actual.getSig();
        }
        return false;
    }

    private static void agregarImagenAProducto(ListaProductos lista, String nombre, String ruta){
        Nodo actual = lista.getCabeza();
        while (actual != null){
            if (actual.getProducto().getNombre().equalsIgnoreCase(nombre)){
                actual.getProducto().agregarImagen(ruta);
                return;
            }
            actual = actual.getSig();
        }
    }
}