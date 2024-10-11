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

    public List<UndirectedWeightedEdge> findSpanningTree(List<UndirectedWeightedEdge> graph) {
        int n = 0;
        List<UndirectedWeightedEdge> pesos = new ArrayList<UndirectedWeightedEdge>();

	    for (List<Entry<Integer, Integer>> elemento  : adjList) {
            n += 1;
            for ( Entry<Integer, Integer>  vertice : elemento ) {
                UndirectedWeightedEdge arcos = new UndirectedWeightedEdge(n,vertice.getKey() , vertice.getValue());
                pesos.add(arcos);
            }    
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
		return answer;
	}

}
