package finalAlgoritmia;

/**
 *
 * @author Rodrigo Arana @Klsie
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import javax.swing.JOptionPane;

public class Grafos {

    private List<List<Nodo>> graph;
    private Nodo startNode;
    private Nodo endNode;
    private String mode;

    public Grafos() {
        graph = new ArrayList<>();
        initializeGraph();
    }

    private void initializeGraph() {
        for (int i = 0; i < 23; i++) {
            List<Nodo> row = new ArrayList<>();
            for (int j = 0; j < 32; j++) {
                Nodo node = new Nodo(i, j, this);
                row.add(node);
            }
            graph.add(row);
        }

        for (int i = 0; i < 23; i++) {
            for (int j = 0; j < 32; j++) {
                Nodo currentNode = graph.get(i).get(j);
                if (i > 0) {
                    currentNode.addNeighbor(graph.get(i - 1).get(j));
                }
                if (j > 0) {
                    currentNode.addNeighbor(graph.get(i).get(j - 1));
                }
                if (i < 22) {
                    currentNode.addNeighbor(graph.get(i + 1).get(j));
                }
                if (j < 31) {
                    currentNode.addNeighbor(graph.get(i).get(j + 1));
                }
            }
        }
    }

    public List<List<Nodo>> getGraph() {
        return graph;
    }

    public Nodo getStartNode() {
        return startNode;
    }

    public void setStartNode(Nodo startNode) {
        this.startNode = startNode;
        //checkAndExecuteAlgorithm();
    }

    public Nodo getEndNode() {
        return endNode;
    }

    public void setEndNode(Nodo endNode) {
        this.endNode = endNode;
        //checkAndExecuteAlgorithm();
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void dijkstra() {
        Nodo startNode = getStartNode();
        Nodo endNode = getEndNode();

        if (startNode == null || endNode == null) {
            System.out.println("No se ha seleccionado nodo de inicio o fin");
            return;
        }

        // Crear una cola de prioridad para los nodos a visitar
        PriorityQueue<Nodo> queue = new PriorityQueue<>((n1, n2) -> {
            return (int) (n1.getDistance() - n2.getDistance());
        });

        // Agregar el nodo de inicio a la cola
        queue.add(startNode);

        // Crear un conjunto de nodos visitados
        Set<Nodo> visited = new HashSet<>();

        // Crear un mapa de nodos padre
        Map<Nodo, Nodo> parentMap = new HashMap<>();

        // Inicializar la distancia de todos los nodos a infinito      
        for (int i = 0; i < 23; i++) {
            for (int j = 0; j < 32; j++) {
                Nodo currentNode = graph.get(i).get(j);
                currentNode.setWeight(Integer.MAX_VALUE);
            }
        }

        // Establecer la distancia del nodo de inicio a 0
        startNode.setWeight(0);

        while (!queue.isEmpty()) {
            Nodo currentNode = queue.poll();

            if (currentNode.equals(endNode)) {
                // Reconstruir el camino desde el nodo de fin hasta el nodo de inicio
                List<Nodo> path = new ArrayList<>();
                while (currentNode != null) {
                    path.add(currentNode);
                    currentNode = parentMap.get(currentNode);
                }
                // Mostrar el camino
                for (Nodo node : path) {
                    node.setBackground(EstadoNodo.PATH.getColor());
                }
                return;
            }

            visited.add(currentNode);

            // Marcar el nodo actual como CHECK para visualizar que se está visitando
            currentNode.setState(EstadoNodo.CHECK);
            currentNode.setCustomColor(EstadoNodo.CHECK.getColor());

            for (Nodo neighbor : currentNode.getNeighbors()) {
                if (!visited.contains(neighbor) && neighbor.getState() != EstadoNodo.GRAY) {
                    // Calcular la distancia desde el nodo de inicio hasta el nodo vecino
                    int distance = currentNode.getWeight() + 1;

                    // Si la distancia calculada es menor que la distancia actual del nodo vecino
                    if (distance < neighbor.getWeight()) {
                        // Actualizar la distancia del nodo vecino
                        neighbor.setWeight(distance);
                        // Agregar el nodo vecino a la cola con su prioridad
                        queue.add(neighbor);
                        parentMap.put(neighbor, currentNode);
                    }
                }
            }
        }

        JOptionPane.showMessageDialog(null, "No se encontró camino");
        reset();
    }

    void reset() {
        for (int i = 0; i < 23; i++) {
            for (int j = 0; j < 32; j++) {
                Nodo currentNode = graph.get(i).get(j);
                currentNode.setState(EstadoNodo.WHITE);
                currentNode.setCustomColor(EstadoNodo.WHITE.getColor());
                currentNode.setNumState(0);
            }
        }
        startNode = null;
        endNode = null;
    }
}
