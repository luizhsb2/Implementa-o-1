
import java.util.*;

class Main {
    static void addAresta(Vector < Integer > adj[],
        int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }
    static void delAresta(Vector < Integer > adj[],
        int u, int v) {
        for (int i = 0; i < adj[u].size(); i++) {
            if (adj[u].get(i) == v) {
                adj[u].remove(i);
                break;
            }
        }
        for (int i = 0; i < adj[v].size(); i++) {
            if (adj[v].get(i) == u) {
                adj[v].remove(i);
                break;
            }
        }
    }
    static void mostrarListaAdj(Vector < Integer > adj[], int V) {
        for (int v = 0; v < V; ++v) {
            System.out.print("vertice " + v + " ");
            for (Integer x: adj[v])
                System.out.print("-> " + x);
            System.out.printf("\n");
        }
        System.out.printf("\n");
    }


    static void grauGrafo(Vector < Integer > adj[], int V) {
        int j = 0;
        for (Integer x: adj[V])
            j++;
        System.out.print("Grau do vertice " + V + " é " + j);
    }

    static void vizinhoGraph(Vector < Integer > adj[], int V, int vertc) {
        int j = 0;
        for (Integer x: adj[V]) {
            if (vertc == x) {
                System.out.print(vertc + " é vizinho de " + V);
            }
            j++;
        }
    }
    void euler(Vector < Integer > adj[], int V)
    {
        int soun = eEuleriano(adj,V);
        if (soun == 0)
            System.out.println("O grafo não é euleriano");
        else if (soun == 1){
            System.out.println("O grafo tem um caminho de Euler:");
            Mostrarcicloeuler(adj,V);
        }
        else{
           System.out.println("O grafo tem um ciclo de Euler: ");
           Mostrarcicloeuler(adj,V);
        }
    }
    boolean eConexo(Vector < Integer > adj[], int V)
    {
        boolean visitado[] = new boolean[V];
        int i;
        for (i = 0; i < V; i++)
            visitado[i] = false;
        for (i = 0; i < V; i++)
            if (adj[i].size() != 0)
                break;
        if (i == V)
            return true;
        Profundidade(adj,V,i, visitado);
        for (i = 0; i < V; i++)
           if (visitado[i] == false && adj[i].size() > 0)
                return false;
 
        return true;
    }
    int eEuleriano(Vector < Integer > adj[], int V)
    {
        if (eConexo(adj,V) == false)
            return 0;
        int impar = 0;
        for (int i = 0; i < V; i++)
            if (adj[i].size()%2!=0)
                impar++;
        if (impar > 2)
            return 0;
        return (impar==2)? 1 : 2;
    }
    void Profundidade(Vector < Integer > adj[], int V,int v,boolean visitado[])
    {
        visitado[v] = true;
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if (!visitado[n])
                Profundidade(adj,V,n, visitado);
        }
    }
    
    private void Mostrarcicloeuler(Vector < Integer > adj[],int V)
    {
        Integer u = 0;
        for (int i = 0; i < V; i++) {
            if (adj[i].size() % 2 == 1) {
                u = i;
                break;
            }
        }
        MostrarcicloeulerUtil(adj,u,V);
        System.out.println();
    }
    private boolean eValido(Vector < Integer > adj[],Integer u, Integer v,int V)
    {
        if (adj[u].size() == 1) {
            return true;
        }
        boolean[] visitado = new boolean[V];
        int count1 = ProfCount(adj,u, visitado);
        delAresta(adj,u, v);
        visitado = new boolean[V];
        int count2 = ProfCount(adj,u, visitado);
        addAresta(adj,u, v);
        return (count1 > count2) ? false : true;
    }
    private int ProfCount(Vector < Integer > adj[],Integer v, boolean[] visitado)
    {
        visitado[v] = true;
        int count = 1;
        for (int adjj : adj[v]) {
            if (!visitado[adjj]) {
                count = count + ProfCount(adj,adjj, visitado);
            }
        }
        return count;
    }
    private void MostrarcicloeulerUtil(Vector < Integer > adj[],Integer u,int V)
    {
        for (int i = 0; i < adj[u].size(); i++) {
            Integer v = adj[u].get(i);
            if (eValido(adj,u, v,V)) {
                System.out.print(u + "->" + v + " ");
                delAresta(adj,u, v);
                MostrarcicloeulerUtil(adj,v,V);
            }
        }
    }
    
    public static void main(String[] args) {
        Main m = new Main();
        int V = 8;
        Vector < Integer > [] adj = new Vector[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Vector < Integer > ();
        System.out.printf("------------------------------------Grafo 1------------------------------------");
        System.out.printf("\n");   
        //adicionar arestas
        addAresta(adj,0, 3);
        addAresta(adj,1, 5);
        addAresta(adj,2, 7);
        addAresta(adj,3, 0);
        addAresta(adj,4, 3);
        addAresta(adj,5, 2);
        addAresta(adj,6, 0);
        addAresta(adj,7, 1);
        
        //mostrar lista de adjacencia
        mostrarListaAdj(adj, V);
        System.out.printf("\n");
        //grau do vertice
        grauGrafo(adj, 0);
        System.out.printf("\n");
        //verifica de os dois vertices sao vizinhos
        vizinhoGraph(adj, 2, 5);
        System.out.printf("\n");
        //System.out.printf("\nDeletando a aresta 0 e 3");
        //deleta a aresta
        //delAresta(adj, 0, 3);
        //mostra se o grafo tem caminho de euler, se nao é euleriano ou se tem ciclo de euler
        m.euler(adj,V);
        
        
        V = 8;
        for (int i = 0; i < V; i++)
            adj[i] = new Vector < Integer > ();
        System.out.printf("\n------------------------------------Grafo 2------------------------------------");
        System.out.printf("\n");    
        addAresta(adj,0, 7);
        addAresta(adj,1, 4);
        addAresta(adj,2, 7);
        addAresta(adj,3, 6);
        addAresta(adj,4, 2);
        addAresta(adj,5, 7);
        addAresta(adj,6, 1);
        addAresta(adj,7, 3);
        
        mostrarListaAdj(adj, V);
        System.out.printf("\n");
        grauGrafo(adj, 1);
        System.out.printf("\n");
        vizinhoGraph(adj, 5, 7);
        System.out.printf("\n");
        //System.out.printf("\nDeletando a aresta 6 e 1");
        //delAresta(adj, 6, 1);
        m.euler(adj,V);
        
        
        V = 8;
        for (int i = 0; i < V; i++)
            adj[i] = new Vector < Integer > ();
        System.out.printf("\n------------------------------------Grafo 3------------------------------------");
        System.out.printf("\n");    
        addAresta(adj,0, 1);
        addAresta(adj,1, 2);
        addAresta(adj,2, 3);
        addAresta(adj,3, 4);
        addAresta(adj,4, 5);
        addAresta(adj,5, 6);
        addAresta(adj,6, 7);
        addAresta(adj,7, 0);
        
        mostrarListaAdj(adj, V);
        System.out.printf("\n");
        grauGrafo(adj, 1);
        System.out.printf("\n");
        vizinhoGraph(adj, 2, 0);
        System.out.printf("\n");
        //System.out.printf("\nDeletando a aresta 0 e 1");
        //delAresta(adj, 0, 1);
        System.out.printf("\n");
        m.euler(adj,V);
    }
}