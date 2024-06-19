package finalAlgoritmia;

/**
 *
 * @author Rodrigo Arana @Klsie
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Nodo extends JButton {

    private final int row;
    private final int column;
    private List<Nodo> neighbors;
    private EstadoNodo state;
    private int numState;
    private Grafos graph;
    private int weight;

    public Nodo(int row, int column, Grafos graph) {
        super();
        this.row = row;
        this.column = column;
        this.neighbors = new ArrayList<>();
        setPreferredSize(new Dimension(100, 100));
        setBackground(Color.WHITE);
        setBorder(new RoundBorder(5));
        this.state = EstadoNodo.WHITE;
        this.numState = 0;
        this.graph = graph;
        this.weight = 0;

        addActionListener(new ActionListener() {
            Color initialColor = getBackground();

            @Override
            public void actionPerformed(ActionEvent e) {
                setColor();
            }
        });

    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public EstadoNodo getState() {
        return state;
    }

    public void setState(EstadoNodo state) {
        this.state = state;
    }

    public int getNumState() {
        return numState;
    }

    public void setNumState(int numState) {
        this.numState = numState;
    }

    public Grafos getGraph() {
        return graph;
    }

    public void setGraph(Grafos graph) {
        this.graph = graph;
    }

    public void setCustomColor(Color color) {
        setBackground(color);
    }

    public void addNeighbor(Nodo node) {
        neighbors.add(node);
    }

    public List<Nodo> getNeighbors() {
        return neighbors;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setColor() {
        String mode = graph.getMode();
        if (mode == null) {
            return;
        }

        switch (mode) {
            case "INICIO":
                if (graph.getStartNode() != null) {
                    graph.getStartNode().setState(EstadoNodo.WHITE);
                    graph.getStartNode().setCustomColor(EstadoNodo.WHITE.getColor());
                }
                graph.setStartNode(this);
                setState(EstadoNodo.START);
                setCustomColor(EstadoNodo.START.getColor());
                break;
            case "FIN":
                if (graph.getEndNode() != null) {
                    graph.getEndNode().setState(EstadoNodo.WHITE);
                    graph.getEndNode().setCustomColor(EstadoNodo.WHITE.getColor());
                }
                graph.setEndNode(this);
                setState(EstadoNodo.END);
                setCustomColor(EstadoNodo.END.getColor());
                break;
            case "OBSTACULO":
                setState(EstadoNodo.GRAY);
                setCustomColor(EstadoNodo.GRAY.getColor());
                break;
        }
    }

    public int getDistance(Nodo node) {
        return Math.abs(this.getRow() - node.getRow()) + Math.abs(this.getColumn() - node.getColumn());
    }

    public int getDistance() {
        return getDistance(graph.getStartNode());
    }
}
