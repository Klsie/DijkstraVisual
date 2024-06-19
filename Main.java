package finalAlgoritmia;

/**
 *
 * @author Rodrigo Arana @Klsie 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.border.Border;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String texto = "Bienvenido Usuario "
                + " | En opciones Inicio,Fin,Obstaculos y Reiniciar";

        JFrame frame = new JFrame("DIjkstra Visual");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        JPanel simbolos = new JPanel(); // Creamos un panel para contener los botones
        simbolos.setBackground(new Color(100, 100, 100)); // Color de fondo para simbolos
        simbolos.setOpaque(true);
        frame.getContentPane().add(simbolos, BorderLayout.NORTH); // Lo ubicamos arriba del frame

        JPanel textPanel = new JPanel();
        textPanel.setBackground(new Color(100, 100, 100)); // Color de fondo para textPanel
        textPanel.setOpaque(true);
        frame.getContentPane().add(textPanel, BorderLayout.SOUTH);

        JButton inicio = new JButton("Inicio");
        JButton camino = new JButton("Camino");
        JButton visualizar = new JButton("Ver");
        JButton fin = new JButton("Fin");
        JButton vistos = new JButton("Revisados");
        Label nombre = new Label("Eric Rodrigo Arana Martinez 22310401 | Estructura de datos y algoritmia");
        Label lblUno = new Label(texto);

        // Atributos Inicio
        inicio.setBackground(new Color(204, 0, 0));
        inicio.setForeground(Color.BLACK);
        inicio.setOpaque(true);
        inicio.setContentAreaFilled(true);
        inicio.setBorderPainted(true);
        inicio.setEnabled(false);
        inicio.setBorder(new RoundBorder(5));

        Grafos grafico = new Grafos(); // Se crea el objeto de la clase GridGraph

        // Atributos Camino
        camino.setBackground(new Color(138, 43, 242));
        camino.setForeground(Color.BLACK);
        camino.setOpaque(true);
        camino.setContentAreaFilled(true);
        camino.setBorderPainted(true);
        camino.setEnabled(false);
        camino.setBorder(new RoundBorder(5));

        // Atributos Fin
        fin.setBackground(new Color(0, 51, 0));
        fin.setForeground(Color.BLACK);
        fin.setOpaque(true);
        fin.setContentAreaFilled(true);
        fin.setBorderPainted(true);
        fin.setEnabled(false);
        fin.setBorder(new RoundBorder(5));

        // Atributos Visualizar
        visualizar.setBackground(new Color(69, 179, 157));
        visualizar.setForeground(Color.BLACK);
        visualizar.setOpaque(true);
        visualizar.setContentAreaFilled(true);
        visualizar.setBorderPainted(true);
        visualizar.setBorder(new RoundBorder(5));
        visualizar.addActionListener((ActionEvent e) -> {
            if (grafico.getStartNode() != null && grafico.getEndNode() != null) {
                grafico.dijkstra();
            } else {
                JOptionPane.showMessageDialog(frame, "Selecciona un Inicio o Final");
            }
        });

        // Atributos Vistos
        vistos.setBackground(new Color(0, 102, 204));
        vistos.setForeground(Color.BLACK);
        vistos.setOpaque(true);
        vistos.setContentAreaFilled(true);
        vistos.setBorderPainted(true);
        vistos.setEnabled(false);
        vistos.setBorder(new RoundBorder(5));

        // Agregamos al los Paneles
        simbolos.add(lblUno);
        textPanel.add(vistos);
        textPanel.add(inicio);
        textPanel.add(fin);
        textPanel.add(camino);
        textPanel.add(nombre);
        textPanel.add(visualizar);
//        textPanel.add(reset);

        Label Dijkstra = new Label("Disjkstra");

        JPanel panel = new JPanel(new GridLayout(23, 32)); // Se crea el objeto tipo Panel

        panel.setBackground(new Color(150, 150, 150)); // Color de fondo para panel
        panel.setOpaque(true);
        frame.getContentPane().add(panel); // Agregamos el panel en el Frame
        frame.getContentPane().setBackground(new Color(70, 80, 70)); // Color de fondo del contentPane

        for (List<Nodo> row : grafico.getGraph()) {
            for (Nodo node : row) {
                panel.add(node);
            }
        }

        menu(frame, grafico);
        frame.setVisible(true);
    }

    public static void menu(JFrame frame, Grafos grafico) {
        JMenuBar menu = new JMenuBar(); // Creación Objeto Menu
        frame.setJMenuBar(menu); // Asegúrate de usar setJMenuBar para que se vea correctamente

        // MENU'S
        // Exit
        JMenu salir = new JMenu("Salir");

        // Opciones
        JMenu opciones = new JMenu("Opciones"); // Creamos el Objeto Opciones

        // ITEM'S
        // Item Salir
        JMenuItem exit = new JMenuItem("SALIR"); // creamos
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        salir.add(exit); // Añadir el JMenuItem al JMenu

        // Item Reiniciar
        JMenuItem reset = new JMenuItem("RE-INICIAR");
        reset.addActionListener((ActionEvent e) -> {
            grafico.reset();
        });

        // Item Inicio
        JMenuItem inicio = new JMenuItem("INICIO");
        inicio.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(frame, "Selecciona un nodo para establecer el Inicio.");
            grafico.setMode("INICIO");
        });

        // Item Fin
        JMenuItem fin = new JMenuItem("FIN");
        fin.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(frame, "Selecciona un nodo para establecer el Fin.");
            grafico.setMode("FIN");
        });

        // Item Obstáculo
        JMenuItem obstaculo = new JMenuItem("OBSTACULO");
        obstaculo.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(frame, "Selecciona un nodo para establecer un Obstáculo.");
            grafico.setMode("OBSTACULO");
        });
        
        //Agreados al JMenu
        menu.add(opciones); // Se agrega al menú
        menu.add(salir);
        
        //Agregados al Menu Opciones
        opciones.add(inicio);
        opciones.add(fin);
        opciones.add(obstaculo);
        opciones.add(reset);

    }
}

class RoundBorder implements Border {

    private int radius;

    public RoundBorder(int radius) {
        this.radius = radius;
    }

    Color miColor = new Color(0x726866);

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(miColor);
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius, this.radius, this.radius, this.radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
