public class Partition {
    int n;
    private int[] parents;
  

    /**
     * Construye una nueva partición para n elementos.
     * Asigna cada elemento a un conjunto diferente
     * @param n Número de elementos a considerar
     */
    public Partition(int n) {
        this.n = n;
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i; 
         
        }
    }

    /**
     * Modifica la partición fusionando en un solo conjunto los subconjuntos a los que pertenecen los elementos dados
     * Si los dos elementos pertenecen al mismo conjunto, deja la estructura sin cambios
     * @param e1 Elemento que pertenece al primer conjunto. 0<=e1<n
     * @param e2 Elemento que pertenece al segundo conjunto. 0<=e2<n
     */
    public void union(int e1, int e2) {
        int s1 = find(e1);
        int s2 = find(e2);
        if (s1 == s2) return; 
            parents[s1] = s2;

      
        
    }

    /**
     * Retorna el identificador del conjunto al que pertenece el elemento dado
     * @param e Elemento a buscar. 0<=e<n
     * @return int Conjunto al que pertenece el elemento
     */
    public int find(int e) {
        if (parents[e] == e) return e; 
        parents[e] = find(parents[e]);
        return parents[e];
    }

    /**
     * Determina si los dos elementos pertenecen al mismo conjunto.
     * @param e1 Primer elemento
     * @param e2 Segundo elemento
     * @return boolean true si e1 y e2 pertenecen al mismo conjunto. False en caso contrario
     */
    public boolean sameSubsets(int e1, int e2) {
        return find(e1) == find(e2);
    }
}
