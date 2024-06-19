package finalAlgoritmia;

/**
 *
 * @author Rodrigo Arana @Klsie 
 */

import java.awt.Color;

public class EstadoNodo {

    public static final EstadoNodo START = new EstadoNodo("INICIO", new Color(204, 0, 0));
    public static final EstadoNodo END = new EstadoNodo("FIN", new Color(0, 51, 0));
    public static final EstadoNodo GRAY = new EstadoNodo("GRIS", new Color(0, 0, 0));
    public static final EstadoNodo WHITE = new EstadoNodo("BLANCO", new Color(255, 255, 255));
    public static final EstadoNodo CHECK = new EstadoNodo("REVISADO", new Color(4, 211, 255));
    public static final EstadoNodo PATH = new EstadoNodo("CAMINO", new Color(138, 43, 242));

    private String name;
    private Color color;

    private EstadoNodo(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
