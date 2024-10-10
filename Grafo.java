import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;

class Grafo {
    // Número de Nodos
    private int nodos;
    
    // Lista de adyacencia donde cada elemento es de la forma: nodoOrigen, lista de tuplas,... (donde cada tupla es de la forma: (nodoDestino, peso))
    private List<List<Map.Entry<Integer, Integer>>> adjList;

    // Indica si el grafo es dirigido o no
    private boolean esDirigido;

    // Constructor que acepta si es dirigido o no
    public Grafo(int nodos, boolean esDirigido) {
        this.nodos = nodos;
        this.esDirigido = esDirigido;
        adjList = new ArrayList<>(nodos);

        // Inicializar la lista de adjacencia con el nodoOrigen
        for (int i = 0; i < nodos; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Método para añadir arcos (nodoDestino, peso) al nodoOrigen
    public void addArco(int source, int destination, int weight) {
        adjList.get(source).add(new SimpleEntry<>(destination, weight));

        // Si el grafo no es dirigido, agregar también la arista en la dirección opuesta
        if (!esDirigido) {
            adjList.get(destination).add(new SimpleEntry<>(source, weight));
        }
    }

    // Método para imprimir el grafo
    public void displayGraph() {
        for (int i = 0; i < nodos; i++) {
            System.out.print("Vertex " + i + ":");
            for (Map.Entry<Integer, Integer> edge : adjList.get(i)) {
                System.out.print(" -> " + edge.getKey() + " (Weight: " + edge.getValue() + ")");
            }
            System.out.println();
        }
    }

    // Método para crear grafo a partir de un archivo de texto
    public void construirDesdeArchivo(String nombreArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Parsear la linea en 3 tokens: nodoOrigen, nodoDestino, peso
                String[] partes = linea.split(" ");
                int fuente = Integer.parseInt(partes[0]);
                int destino = Integer.parseInt(partes[1]);
                int costo = Integer.parseInt(partes[2]);

                // Añadir el arco al grafo
                addArco(fuente, destino, costo);
            }
        }
    }

    public int getNodos() {
        return nodos;
    }

    public List<List<Map.Entry<Integer, Integer>>> getAdjList() {
        return adjList;
    }
}

