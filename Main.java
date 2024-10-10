import java.io.IOException;

public class Main {
    
    public static void main(String[] args) {
        // Number of nodes (you can set it dynamically if needed)
        int numVertices = 5;

        // Create the graph
        Grafo graph = new Grafo(numVertices);

        // File path (replace with your actual file path)
        String nombreArchivo = "grafoPunto1.txt"; 

        // Build the graph from the file
        try {
            graph.construirDesdeArchivo(nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        // Display the graph
        graph.displayGraph();
    }
}
