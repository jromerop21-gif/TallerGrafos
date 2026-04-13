package proyectografos;

import java.util.*;

public class Kruskal {

    static class Arista implements Comparable<Arista> {
        int o, d, p;

        Arista(int o, int d, int p) {
            this.o = o;
            this.d = d;
            this.p = p;
        }

        @Override
        public int compareTo(Arista a) {
            return this.p - a.p;
        }
    }

    static int find(int[] padre, int i) {
        if (padre[i] != i)
            padre[i] = find(padre, padre[i]);
        return padre[i];
    }

    static void union(int[] padre, int[] rango, int x, int y) {
        int rx = find(padre, x);
        int ry = find(padre, y);

        if (rango[rx] < rango[ry])
            padre[rx] = ry;
        else if (rango[rx] > rango[ry])
            padre[ry] = rx;
        else {
            padre[ry] = rx;
            rango[rx]++;
        }
    }

    public static void main(String[] args) {

        int V = 6;
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

        Collections.sort(aristas);

        int[] padre = new int[V];
        int[] rango = new int[V];

        for (int i = 0; i < V; i++) {
            padre[i] = i;
            rango[i] = 0;
        }

        int costo = 0;
        int descartadas = 0;

        System.out.println("Aristas del MST:");

        for (Arista a : aristas) {
            int x = find(padre, a.o);
            int y = find(padre, a.d);

            if (x != y) {
                System.out.println(a.o + " - " + a.d + " : " + a.p);
                costo += a.p;
                union(padre, rango, x, y);
            } else {
                descartadas++;
            }
        }

        System.out.println("Costo total: " + costo);
        System.out.println("Aristas descartadas: " + descartadas);
    }
}