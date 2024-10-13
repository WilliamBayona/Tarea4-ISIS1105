import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Grafo graph;
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            // Mostrar el menú
            System.out.println("\n--- Menú de Opciones ---");
            System.out.println("1. Parte 1");
            System.out.println("2. Parte 2");
            System.out.println("3. Parte 3");
            System.out.println("4. Parte 4");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Usar archivo 1
                    graph = manejarOpcion("data/grafoPunto1.txt", scanner);
                    graph.displayGraph(); 
                    break;
                case 2:
                    // Usar archivo 2
                    graph = manejarOpcion("data/grafoPunto2.txt", scanner);
                    Parte2 parte2 = new Parte2(graph);
                    parte2.componentesConectados(); // Ejecutar los componentes conectados
                    break;
                case 3:
                    graph = manejarOpcion("data/grafoPunto3.txt", scanner);
                    System.out.println("Opción 3 seleccionada.");
                    
                    break;
                case 4:
                    System.out.println("Opción 4 seleccionada.");
                    // Implementa la funcionalidad de la opción 4
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    // Método para manejar las opciones y evitar la repetición de código
    public static Grafo manejarOpcion(String nombreArchivo, Scanner scanner) {
        Grafo graph = null; // Inicializar grafo
        try {
            int numVertices = contarVerticesDesdeArchivo(nombreArchivo);

            // Preguntar si el grafo es dirigido o no
            System.out.print("¿Es el grafo dirigido? (s/n): ");
            char esDirigidoChar = scanner.next().toLowerCase().charAt(0);
            boolean esDirigido = esDirigidoChar == 's';

            graph = new Grafo(numVertices, esDirigido);
            

            // Construir el grafo desde el archivo
            graph.construirDesdeArchivo(nombreArchivo);
            System.out.println("Grafo construido desde el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        System.out.println(graph.getAdjList());
        return graph; // Devolver el grafo construido
    }

    // Método para contar la cantidad de vértices dado un archivo de texto
    public static int contarVerticesDesdeArchivo(String nombreArchivo) throws IOException {
        Set<Integer> vertices = new HashSet<>();

        BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
        String linea;

        while ((linea = reader.readLine()) != null) {
            String[] partes = linea.split(" ");
            int nodoInicio = Integer.parseInt(partes[0]);
            int nodoFinal = Integer.parseInt(partes[1]);

            vertices.add(nodoInicio);
            vertices.add(nodoFinal);
        }
        reader.close();
        return vertices.size();
    }
}





