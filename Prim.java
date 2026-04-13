package proyectografos;

import java.util.*;

public class Prim {

    static final int V = 6;

    static int minClave(int[] clave, boolean[] visitado) {
        int min = Integer.MAX_VALUE, indice = -1;

        for (int i = 0; i < V; i++) {
            if (!visitado[i] && clave[i] < min) {
                min = clave[i];
                indice = i;
            }
        }
        return indice;
    }

    static void prim(int[][] grafo, int inicio) {
        int[] padre = new int[V];
        int[] clave = new int[V];
        boolean[] visitado = new boolean[V];

        Arrays.fill(clave, Integer.MAX_VALUE);
        clave[inicio] = 0;
        padre[inicio] = -1;

        System.out.println("\nInicio desde nodo: " + inicio);

        for (int i = 0; i < V - 1; i++) {
            int u = minClave(clave, visitado);
            visitado[u] = true;

            for (int v = 0; v < V; v++) {
                if (grafo[u][v] != 0 && !visitado[v] && grafo[u][v] < clave[v]) {
                    padre[v] = u;
                    clave[v] = grafo[u][v];
                }
            }
        }

        int costo = 0;
        System.out.println("Aristas seleccionadas:");
        for (int i = 0; i < V; i++) {
            if (padre[i] != -1) {
                System.out.println(padre[i] + " - " + i + " : " + grafo[i][padre[i]]);
                costo += grafo[i][padre[i]];
            }
        }

        System.out.println("Costo total: " + costo);
    }

    public static void main(String[] args) {
        int[][] grafo = {
                {0, 6, 1, 5, 0, 0},
                {6, 0, 2, 0, 5, 0},
                {1, 2, 0, 2, 6, 0},
                {5, 0, 2, 0, 0, 4},
                {0, 5, 6, 0, 0, 3},
                {0, 0, 0, 4, 3, 0}
        };

        prim(grafo, 0);
        prim(grafo, 2);
    }
}