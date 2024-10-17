import java.util.*;
import java.io.*;

public class Parte4 {

    // Hacemos un BFS para encontrar un camino aumentante
    private static boolean bfs(int[][] capacity, int[][] adjMatrix, int source, int sink, int[] parent) {
        // Inicializamos el arreglo de visitados y la cola
        boolean[] visited = new boolean[capacity.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        // Mientras la cola no esté vacía y no hayamos llegado al sumidero
        while (!queue.isEmpty()) {
            int u = queue.poll();

        // Revisamos los nodos adyacentes
            for (int v = 0; v < capacity.length; v++) {
                if (!visited[v] && adjMatrix[u][v] > 0) {
                    if (v == sink) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return false;
    }

    // Implementación del algoritmo de Edmonds-Karp para encontrar el flujo máximo
    private static int edmondsKarp(int[][] capacity, int source, int sink) {
        int u, v;
        int[][] adjMatrix = new int[capacity.length][capacity.length];

        // Inicializamos la matriz de adyacencia
        for (u = 0; u < capacity.length; u++) {
            for (v = 0; v < capacity.length; v++) {
                adjMatrix[u][v] = capacity[u][v];
            }
        }


        int[] parent = new int[capacity.length];
        int maxFlow = 0;

        // Mientras exista un camino aumentante
        while (bfs(capacity, adjMatrix, source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;

            // Encuentra la capacidad mínima en el camino aumentante
            for (v = sink; v != source; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, adjMatrix[u][v]);
            }

            // Actualiza las capacidades residuales
            for (v = sink; v != source; v = parent[v]) {
                u = parent[v];
                adjMatrix[u][v] -= pathFlow;
                adjMatrix[v][u] += pathFlow;
            }

            // Suma el flujo del camino al flujo total
            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    // Método para leer el archivo de entrada y ejecutar el algoritmo
    public static void algoritmo(String nombreArchivo) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(nombreArchivo));

        // Leer el número de fábricas, bodegas, librerías y camiones
        int factories = sc.nextInt();
        int storages = sc.nextInt();
        int libraries = sc.nextInt();
        int trucks = sc.nextInt();


        int nodes = factories + storages + libraries + 2; // nodos en el grafo (superorigen y supersumidero)
        int source = 0; // superOrigen
        int sink = nodes - 1; // superSumidero

        int[][] capacity = new int[nodes][nodes];

        // Leer las capacidades de las bodegas
        for (int i = 0; i < storages; i++) {
            int storageId = sc.nextInt();
            int storageCapacity = sc.nextInt();
            capacity[storageId][sink] = storageCapacity; // Capacidad de bodega al sink
        }

        // Conectar fábricas al superorigen
        for (int i = 0; i < factories; i++) {
            int factoryId = sc.nextInt();
            capacity[source][factoryId] = Integer.MAX_VALUE; // Conexión ilimitada desde el superorigen a las fábricas
        }

        // Conectar librerías al supersumidero
        for (int i = 0; i < libraries; i++) {
            int libraryId = sc.nextInt();
            capacity[libraryId][sink] = Integer.MAX_VALUE; // Conexión ilimitada desde librerías al supersink
        }

        // Leer las rutas de los camiones
        for (int i = 0; i < trucks; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int truckCapacity = sc.nextInt();
            capacity[start][end] = truckCapacity;
        }

        // Calcular el flujo máximo desde el superorigen hasta el supersumidero
        int maxFlow = edmondsKarp(capacity, source, sink);
        System.out.println("Máximo número de libros que se pueden transportar en un día: " + maxFlow);

        sc.close();
    }
}
