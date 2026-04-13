package proyectografos;

import java.util.*;

public class BFS {

    public static void main(String[] args) {

        int V = 6;
        List<List<Integer>> grafo = new ArrayList<>();

        for (int i = 0; i < V; i++)
            grafo.add(new ArrayList<>());

        grafo.get(0).add(1);
        grafo.get(0).add(2);
        grafo.get(1).add(3);
        grafo.get(2).add(3);
        grafo.get(3).add(4);
        grafo.get(4).add(5);

        int origen = 0, destino = 5;

        Queue<Integer> cola = new LinkedList<>();
        boolean[] visitado = new boolean[V];
        int[] padre = new int[V];

        cola.add(origen);
        visitado[origen] = true;
        padre[origen] = -1;

        while (!cola.isEmpty()) {
            int actual = cola.poll();

            for (int vecino : grafo.get(actual)) {
                if (!visitado[vecino]) {
                    cola.add(vecino);
                    visitado[vecino] = true;
                    padre[vecino] = actual;
                }
            }
        }

        List<Integer> camino = new ArrayList<>();
        for (int i = destino; i != -1; i = padre[i])
            camino.add(i);

        Collections.reverse(camino);

        System.out.println("Camino más corto:");
        for (int n : camino)
            System.out.print(n + " -> ");
    }
}