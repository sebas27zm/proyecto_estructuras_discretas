package Producto;

import Helpers.Categoria;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.time.LocalDate;

public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private Categoria categoria;
    private LocalDate fechaVencimiento;
    private int cantidad;
    private ArrayList<String> listaImagenes;

    public Producto() {
        listaImagenes = new ArrayList<>();
    }

    // Constructor parametrizado: permite crear un Producto completo en una sola instruccion.
    public Producto(String nombre, double precio, Categoria categoria, LocalDate fechaVencimiento, int cantidad) {
        this();
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidad = cantidad;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }


    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public ArrayList<String> getListaImagenes() { return listaImagenes; }
    public void guardarImagen(String rutaOrigen, String nombreArchivo) throws IOException {
        // Directorio destino: /src/recursos/{codigo}/
        Path directorio = Paths.get("src/recursos/" + this.codigo);
        if (!Files.exists(directorio)) {
            Files.createDirectories(directorio); // crea carpeta si no existe
        }

        // Ruta final del archivo
        Path destino = directorio.resolve(nombreArchivo);

        // Copiar bytes de la imagen original al destino
        byte[] bytes = Files.readAllBytes(Paths.get(rutaOrigen));
        Files.write(destino, bytes);

        // Registrar la ruta relativa en listaImagenes
        this.listaImagenes.add("/src/recursos/" + this.codigo + "/" + nombreArchivo);
    }

    // Representacion legible del producto, usada por ArbolProductos al listar el inventario
    // y por el menu al mostrar resultados de busqueda.
    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                ", cantidad=" + cantidad +
                (fechaVencimiento != null ? ", vence=" + fechaVencimiento : "") +
                '}';
    }
}