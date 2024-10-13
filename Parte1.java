import java.util.List;
import java.util.Map.Entry;

public class Parte1 {

    Grafo grafo;
    List<List<Entry<Integer, Integer>>> adjList;
    final static int INF = 99999;

    Parte1 (Grafo grafo){
        
        this.grafo = grafo;
        this.adjList = grafo.getAdjList();
    }

    //Algoritmo FloydWarshall

    public void FloydWarshall(){
        
        int nodos = grafo.getNodos();
        int dist[][] = new int[nodos][nodos];
        final int INF = 99999;

        for (int i=0; i < dist.length; i++)
        {
            for(int j=0; j < dist.length; j++){

                if (i==j){
                    dist[i][j]= 0;
                }
                else {
                    boolean found = false;
                
                    // Recorremos cada entrada en la sublista
                    for (Entry<Integer, Integer> entry : adjList.get(i)) {
                        // Si la clave coincide con keyToFind, la encontramos
                        if (entry.getKey().equals(j)) {
                            found = true;
                            dist[i][j]= entry.getValue();
                            break; 
                            }
                    if (!found){
                        dist[i][j] = INF;
                    }
                        
                }
            }
        }
    }

    for (int k = 0; k < nodos; k++) {
        for (int i = 0; i < nodos; i++) {
            for (int j = 0; j < nodos; j++) {
                // Actualizar dist[i][j] si pasa por el vértice k y reduce la distancia
                if (dist[i][k] + dist[k][j] < dist[i][j])
                    dist[i][j] = dist[i][k] + dist[k][j];
            }
        }
    }

    printSolution(dist);



 }

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
