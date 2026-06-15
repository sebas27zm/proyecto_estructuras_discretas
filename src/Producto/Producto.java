package Producto;

import java.util.ArrayList;

public class Producto {
    private String nombre;
    private double precio;
    private String categoria;
    private String fechaVencimiento;
    private int cantidad;

    //#####################################################################################################//
    //Pieza de codigo temporal para hacer el codigo capaz de correr en lo que se agrega lo faltante
    //favor quitarlo una vez ya este integrado el codigo funcional
    private ArrayList<String> listaImagenes = new ArrayList<>();
    public ArrayList<String> getListaImagenes() { return listaImagenes; }
    public void agregarImagen(String ruta) { this.listaImagenes.add(ruta); }
    //#####################################################################################################//

    public Producto() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}