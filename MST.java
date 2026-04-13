package proyectografos;

import java.util.*;

public class MST {

    static class Arista implements Comparable<Arista> {
        int origen, destino, peso;

        public Arista(int o, int d, int p) {
            origen = o;
            destino = d;
            peso = p;
        }

        @Override
        public int compareTo(Arista otra) {
            return this.peso - otra.peso;
        }
    }

    static int V = 6;

    // ================= PRIM =================
    public static void prim(int[][] grafo) {
        boolean[] visitado = new boolean[V];
        int[] clave = new int[V];
        int[] padre = new int[V];

        Arrays.fill(clave, Integer.MAX_VALUE);
        clave[0] = 0;
        padre[0] = -1;

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
        System.out.println("Prim - Aristas del MST:");
        for (int i = 1; i < V; i++) {
            System.out.println(padre[i] + " - " + i + " : " + grafo[i][padre[i]]);
            costo += grafo[i][padre[i]];
        }
        System.out.println("Costo total (Prim): " + costo);
    }

    static int minClave(int[] clave, boolean[] visitado) {
        int min = Integer.MAX_VALUE, indice = -1;

        for (int v = 0; v < V; v++) {
            if (!visitado[v] && clave[v] < min) {
                min = clave[v];
                indice = v;
            }
        }
        return indice;
    }

    // ================= KRUSKAL =================
    static int find(int[] padre, int i) {
        if (padre[i] != i)
            padre[i] = find(padre, padre[i]);
        return padre[i];
    }

    static void union(int[] padre, int[] rango, int x, int y) {
        int raizX = find(padre, x);
        int raizY = find(padre, y);

        if (rango[raizX] < rango[raizY])
            padre[raizX] = raizY;
        else if (rango[raizX] > rango[raizY])
            padre[raizY] = raizX;
        else {
            padre[raizY] = raizX;
            rango[raizX]++;
        }
    }

    public static void kruskal(List<Arista> aristas) {
        Collections.sort(aristas);

        int[] padre = new int[V];
        int[] rango = new int[V];

        for (int i = 0; i < V; i++) {
            padre[i] = i;
            rango[i] = 0;
        }

        int costo = 0;
        System.out.println("\nKruskal - Aristas del MST:");

        for (Arista a : aristas) {
            int x = find(padre, a.origen);
            int y = find(padre, a.destino);

            if (x != y) {
                System.out.println(a.origen + " - " + a.destino + " : " + a.peso);
                costo += a.peso;
                union(padre, rango, x, y);
            }
        }

        System.out.println("Costo total (Kruskal): " + costo);
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        int[][] grafo = {
                {0, 6, 1, 5, 0, 0},
                {6, 0, 2, 0, 5, 0},
                {1, 2, 0, 2, 6, 0},
                {5, 0, 2, 0, 0, 4},
                {0, 5, 6, 0, 0, 3},
                {0, 0, 0, 4, 3, 0}
        };

        prim(grafo);

        List<Arista> aristas = new ArrayList<>();
        aristas.add(new Arista(0,1,6));
        aristas.add(new Arista(0,2,1));
        aristas.add(new Arista(0,3,5));
        aristas.add(new Arista(1,2,2));
        aristas.add(new Arista(1,4,5));
        aristas.add(new Arista(2,3,2));
        aristas.add(new Arista(2,4,6));
        aristas.add(new Arista(3,5,4));
        aristas.add(new Arista(4,5,3));

        kruskal(aristas);
    }
}
