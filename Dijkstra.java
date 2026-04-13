package proyectografos;

import java.util.*;

public class Dijkstra {

    static final int V = 5;

    static int minDist(int[] dist, boolean[] visitado) {
        int min = Integer.MAX_VALUE, idx = -1;

        for (int i = 0; i < V; i++) {
            if (!visitado[i] && dist[i] < min) {
                min = dist[i];
                idx = i;
            }
        }
        return idx;
    }

    public static void main(String[] args) {

        int[][] grafo = {
                {0,10,0,0,3},
                {0,0,2,0,4},
                {0,0,0,9,0},
                {0,0,7,0,0},
                {0,1,8,2,0}
        };

        int[] dist = new int[V];
        boolean[] visitado = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        for (int i = 0; i < V - 1; i++) {
            int u = minDist(dist, visitado);
            visitado[u] = true;

            for (int v = 0; v < V; v++) {
                if (!visitado[v] && grafo[u][v] != 0 &&
                        dist[u] + grafo[u][v] < dist[v]) {
                    dist[v] = dist[u] + grafo[u][v];
                }
            }
        }

        System.out.println("Distancias desde 0:");
        for (int i = 0; i < V; i++)
            System.out.println("0 -> " + i + " = " + dist[i]);
    }
}