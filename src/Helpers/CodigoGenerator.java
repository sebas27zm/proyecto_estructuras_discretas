package Helpers;

public class CodigoGenerator {
    private static int contador = 1;

    // Genera un código único incremental de 4 dígitos
    public static String generarCodigoUnico() {
        return String.format("%04d", contador++); // 0001, 0002, 0003...
    }

    // Genera un código visible con prefijo de categoría
    public static String generarCodigoConCategoria(Categoria categoria) {
        // Tomamos el nombre del enum y usamos los primeros 3 caracteres
        String prefijo = categoria.name().substring(0, 3).toUpperCase();
        return prefijo + "-" + generarCodigoUnico();
    }
}
