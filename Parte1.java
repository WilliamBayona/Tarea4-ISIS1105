import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Parte1 {

    Grafo grafo;
    List<List<Entry<Integer, Integer>>> adjList;
    final static int INF = 99999;

    Parte1(Grafo grafo) {
        this.grafo = grafo;
        this.adjList = grafo.getAdjList();
    }

    // Algoritmo Floyd-Warshall
    public void FloydWarshall() {
        long startTime = System.nanoTime(); // Inicio de medición de tiempo

        int nodos = grafo.getNodos();
        int dist[][] = new int[nodos][nodos];

        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {

                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    boolean found = false;

                    // Recorremos cada entrada en la sublista
                    for (Entry<Integer, Integer> entry : adjList.get(i)) {
                        if (entry.getKey().equals(j)) {
                            found = true;
                            dist[i][j] = entry.getValue();
                            break;
                        }
                    }
                    if (!found) {
                        dist[i][j] = INF;
                    }
                }
            }
        }

        for (int k = 0; k < nodos; k++) {
            for (int i = 0; i < nodos; i++) {
                for (int j = 0; j < nodos; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        long endTime = System.nanoTime(); // Fin de medición de tiempo
        System.out.println("Floyd-Warshall: " + (endTime - startTime) / 1000 + " microsegundos");

        printSolution(dist);
        System.out.println("");
    }

    // Algoritmo Dijkstra
    public void dijkstraAllPairs() {
        long startTime = System.nanoTime(); // Inicio de medición de tiempo

        int nodos = grafo.getNodos();
        int dist2[][] = new int[nodos][nodos];

        // Ejecutar Dijkstra desde cada nodo
        for (int i = 0; i < nodos; i++) {
            dist2[i] = dijkstra(i);
        }

        long endTime = System.nanoTime(); // Fin de medición de tiempo
        System.out.println("Dijkstra: " + (endTime - startTime) / 1000 + " microsegundos");

        printSolution(dist2);
        System.out.println("");
    }

    // Algoritmo Dijkstra desde un nodo fuente
    public int[] dijkstra(int source) {
        int nodos = grafo.getNodos();
        int[] dist = new int[nodos];
        boolean[] visited = new boolean[nodos];

        // Inicializar distancias a infinito y marcar todos los nodos como no visitados
        for (int i = 0; i < nodos; i++) {
            dist[i] = INF;
            visited[i] = false;
        }
        dist[source] = 0;

        // Cola de prioridad para seleccionar el nodo con menor costo
        PriorityQueue<Entry<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Entry::getValue));
        pq.offer(new SimpleEntry<>(source, 0));

        while (!pq.isEmpty()) {
            Entry<Integer, Integer> current = pq.poll();
            int u = current.getKey();

            if (!visited[u]) {
                visited[u] = true;

                // Recorremos los vecinos del nodo actual
                for (Entry<Integer, Integer> neighbor : adjList.get(u)) {
                    int v = neighbor.getKey();
                    int weight = neighbor.getValue();

                    if (!visited[v] && dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pq.offer(new SimpleEntry<>(v, dist[v]));
                    }
                }
            }
        }

        return dist;
    }

    // Algoritmo Bellman-Ford para obtener matriz de costos mínimos
    public void bellmanFordAllPairs() {
        long startTime = System.nanoTime(); // Inicio de medición de tiempo

        int nodos = grafo.getNodos();
        int dist3[][] = new int[nodos][nodos];

        // Ejecutar Bellman-Ford desde cada nodo
        for (int i = 0; i < nodos; i++) {
            dist3[i] = bellmanFord(i);
        }

        long endTime = System.nanoTime(); // Fin de medición de tiempo
        System.out.println("Bellman-Ford: " + (endTime - startTime) / 1000 + " microsegundos");

        printSolution(dist3);
        System.out.println("");
    }

    // Algoritmo Bellman-Ford desde un nodo fuente
    public int[] bellmanFord(int source) {
        int nodos = grafo.getNodos();
        int[] dist = new int[nodos];

        // Inicializar distancias a infinito
        for (int i = 0; i < nodos; i++) {
            dist[i] = INF;
        }
        dist[source] = 0;

        // Relajación de las aristas nodos - 1 veces
        for (int i = 1; i < nodos; i++) {
            for (int u = 0; u < nodos; u++) {
                for (Entry<Integer, Integer> neighbor : adjList.get(u)) {
                    int v = neighbor.getKey();
                    int weight = neighbor.getValue();

                    if (dist[u] != INF && dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                    }
                }
            }
        }

        // Comprobar ciclos negativos
        for (int u = 0; u < nodos; u++) {
            for (Entry<Integer, Integer> neighbor : adjList.get(u)) {
                int v = neighbor.getKey();
                int weight = neighbor.getValue();

                if (dist[u] != INF && dist[u] + weight < dist[v]) {
                    System.out.println("El grafo contiene un ciclo negativo");
                    return null;
                }
            }
        }

        return dist;
    }




    // Método para imprimir la matriz de distancias
    private void printSolution(int dist[][]) {
        int V = dist.length;
        System.out.println("La siguiente matriz muestra las distancias más cortas entre cada par de vértices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
