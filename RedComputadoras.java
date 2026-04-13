package proyectografos;

import java.util.*;

public class RedComputadoras {

    static List<List<Integer>> red = new ArrayList<>();

    static void agregarConexion(int a, int b) {
        red.get(a).add(b);
        red.get(b).add(a);
    }

    static boolean hayConexion(int origen, int destino) {
        boolean[] visitado = new boolean[red.size()];
        Queue<Integer> cola = new LinkedList<>();

        cola.add(origen);
        visitado[origen] = true;

        while (!cola.isEmpty()) {
            int actual = cola.poll();

            if (actual == destino) return true;

            for (int vecino : red.get(actual)) {
                if (!visitado[vecino]) {
                    visitado[vecino] = true;
                    cola.add(vecino);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

        int n = 6;
        for (int i = 0; i < n; i++)
            red.add(new ArrayList<>());

        agregarConexion(0,1);
        agregarConexion(1,2);
        agregarConexion(2,3);
        agregarConexion(3,4);
        agregarConexion(4,5);

        System.out.println("¿0 se conecta con 5? " + hayConexion(0,5));
    }
}