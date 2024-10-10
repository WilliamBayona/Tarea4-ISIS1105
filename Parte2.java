import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;

public class Parte2 {

    ArrayList<Integer> visitados; 
    Grafo grafo;

    // Constructor
    Parte2(Grafo grafo) {
        this.grafo = grafo;
        this.visitados = new ArrayList<>();
        
        // Inicializar la lista de visitados con 0 (no visitado)
        for (int i = 0; i < grafo.getNodos(); i++) {
            visitados.add(0);  // Añadir 0 para cada nodo, indicando que no está visitado
        }
    }

    // Método para encontrar todos los componentes conectados
    public void componentesConectados() {
        List<List<Entry<Integer, Integer>>> adjList = grafo.getAdjList();  // Obtener la lista de adyacencia
        
        for (int i = 0; i < visitados.size(); i++) {
            if (visitados.get(i) == 0) {  // Si el nodo no ha sido visitado
                ArrayList<Integer> subgrafo = new ArrayList<>();  // Lista para guardar el subgrafo
                bfs(i, adjList, subgrafo);  // Llamar al método BFS
                System.out.println("Componente conectado: " + subgrafo);  // Imprimir el subgrafo
            }
        }
    }

    // Método para explorar los vértices utilizando BFS
    public void bfs(Integer vActual, List<List<Entry<Integer, Integer>>> adjList, ArrayList<Integer> subgrafo) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(vActual);
        visitados.set(vActual, 1);  // Marcar el vértice actual como visitado

        while (!queue.isEmpty()) {
            Integer actual = queue.poll();
            subgrafo.add(actual);  // Añadir el vértice actual al subgrafo

            // Recorrer los vecinos del vértice actual
            for (Entry<Integer, Integer> destino : adjList.get(actual)) {
                int vecino = destino.getKey();  // Obtener el vecino del vértice actual

                // Si el vecino no ha sido visitado, añadirlo a la cola
                if (visitados.get(vecino) == 0) {
                    queue.add(vecino);
                    visitados.set(vecino, 1);  // Marcarlo como visitado
                }
            }
        }
    }
}

