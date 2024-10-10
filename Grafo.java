import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;

class Grafo {
    // Numero de Nodos
    private int nodos;
    
    // Lista de adyacencia donde cada elemento de de la forma: nodoOrigen, lista de tuplas,... (donde cada tupla es de la forma: (nodoDestino, peso))
    private List<List<Map.Entry<Integer, Integer>>> adjList;

    // Metodo Constructor
    public Grafo(int nodos) {
        this.nodos = nodos;
        adjList = new ArrayList<>(nodos);

        // Inicializar la lista de adjacencia con el nodoOrigen
        for (int i = 0; i < nodos; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Metodo para añadir arcos (nodoDestino, peso) al nodoOrigen
    public void addArco(int source, int destination, int weight) {
        adjList.get(source).add(new SimpleEntry<>(destination, weight));
    }

    // Metodo para imprimir el grafo
    public void displayGraph() {
        for (int i = 0; i < nodos; i++) {
            System.out.print("Vertex " + i + ":");
            for (Map.Entry<Integer, Integer> edge : adjList.get(i)) {
                System.out.print(" -> " + edge.getKey() + " (Weight: " + edge.getValue() + ")");
            }
            System.out.println();
        }
    }

    // Metodo para crear grafo a partir de un archivo de texto
    public void construirDesdeArchivo(String nombreArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Parsear la linea en 3 tokens: nodoOrigen, nodoDestino, peso
                String[] partes = linea.split(" ");
                int fuente = Integer.parseInt(partes[0]);
                int destino = Integer.parseInt(partes[1]);
                int costo = Integer.parseInt(partes[2]);

                // añadir el arco al grafo
                addArco(fuente, destino, costo);
            }
        }
    }

}
