import java.util.List;
import java.util.Map.Entry;
import java.util.Collections;
import java.util.ArrayList;

public class Parte3 {

    Grafo grafo;
    List<List<Entry<Integer, Integer>>> adjList;


    Parte3 (Grafo grafo){
        this.grafo = grafo;
        this.adjList = grafo.getAdjList();
    }

    public List<UndirectedWeightedEdge> findSpanningTree() {
        int n = 0;
        List<UndirectedWeightedEdge> pesos = new ArrayList<UndirectedWeightedEdge>();

	    for (List<Entry<Integer, Integer>> elemento  : adjList) {
            
            for ( Entry<Integer, Integer>  vertice : elemento ) {
                UndirectedWeightedEdge arcos = new UndirectedWeightedEdge(n,vertice.getKey() , vertice.getValue());
                pesos.add(arcos);
               
            }  
            n += 1;  
        }
        List<UndirectedWeightedEdge> copy = pesos;
        Collections.sort(copy, (e1, e2) -> e1.getWeight() - e2.getWeight());
		List<UndirectedWeightedEdge> answer = new ArrayList<UndirectedWeightedEdge>();
		Partition p = new Partition(n);
		for(int i=0;i<copy.size() && answer.size()<n;i++) {
			UndirectedWeightedEdge next = copy.get(i);
			if(!p.sameSubsets(next.getV1(), next.getV2())) {
                
				answer.add(next);
				p.union(next.getV1(), next.getV2());
			}
		}
        System.out.println("Aristas en el Árbol de Expansión Mínima:");
        for (UndirectedWeightedEdge edge : answer) {
            System.out.println("Vértice 1: " + edge.getV1() + ", Vértice 2: " + edge.getV2() + ", Peso: " + edge.getWeight());
        }
		return answer;
	}

}
