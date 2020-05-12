package upb.edu.co;

import java.util.HashMap;

public class Funciones {

    public static Boolean esCiclo(String grafo) {
        String[] vertices = grafo.split(";");

        HashMap<String, Integer> cont = new HashMap<>();

        for (int j = 0; j < vertices.length; j++) { //poner de llaves los vertices, y los valores temporalmente en cero
            String[] edges = vertices[j].split(":");
            cont.put(edges[0], 0);

        }
        for (int j = 0; j < vertices.length; j++) {
            String[] edges = vertices[j].split(":");
            if (edges[1].length() > 2) {
                return false;
            }
            for (String f : cont.keySet()
            ) {

                if (edges[1].contains(f)) {
                    cont.put(f, cont.get(f) + 1);
                }
            }

        }
        for (int x : cont.values()) {//retornar si es o no ciclo
            if (x != 2) {
                return false;
            }
        }

        return true;

    }

    public static Boolean esRueda(String grafo) {
        String[] vertices = grafo.split(";");

        int cont3 = 0;//contador para los vertices que deben tener 3


        for (int j = 0; j < vertices.length; j++) {
            String[] edges = vertices[j].split(":");
            if (edges[1].length() == 3) {
                cont3++;
            }
            if (cont3 == vertices.length - 1) {
                if (((edges[1].length()) == (vertices.length - 1))) {
                    cont3++;
                }
            }
        }

        if (cont3 == vertices.length) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean esOtro(String grafo) {
        String[] vertices = grafo.split(";");

        HashMap<String, Boolean> colores = new HashMap<>();
        HashMap<String, String> vertArist = new HashMap<>();

        for (int j = 0; j < vertices.length; j++) { //poner de llaves los vertices, y los valores temporalmente en cero en colores
            String[] edges = vertices[j].split(":");
            colores.put(edges[0], null);
            vertArist.put(edges[0], edges[1]);
        }

        //imposicion de la condicion inicial
        String[] edgesiN = vertices[0].split(":");
        String vertInicial = edgesiN[0];
        String aristInicial = vertArist.get(vertInicial);
        for (int i = 0; i < aristInicial.length(); i++) {
            colores.put(vertInicial, true);
            colores.put(String.valueOf(aristInicial.charAt(i)), false);

        }


        for (int i = 1; i < vertices.length; i++) {

            String[] edges = vertices[i].split(":");
            String vert = edges[0];
            String aristBusqueda = vertArist.get(vert); //igual que edges1

            if (colores.get(vert)==null)
            {
                for (int j = 0; j < aristBusqueda.length(); j++) {

                    if ((colores.get(String.valueOf(aristBusqueda.charAt(j)))) != null) {
                        boolean x = colores.get(String.valueOf(aristBusqueda.charAt(j)));
                        colores.put(vert, !x);
                    }
                }
            }
            for (int j = 0; j < aristBusqueda.length(); j++) {

                if ((colores.get(String.valueOf(aristBusqueda.charAt(j)))) == null) {
                    String vertNulo = vertArist.get(String.valueOf(aristBusqueda.charAt(j)));
                    boolean x = colores.get(String.valueOf(vertNulo.charAt(0)));
                    colores.put(String.valueOf(aristBusqueda.charAt(j)), !x);
                }
                //ya que no hay nulos, siga
                if ((colores.get(String.valueOf(aristBusqueda.charAt(j)))) == (colores.get(vert)))//si son del mismo color y estan seguidas, no es bipartito
                {

                    return false;
                }



            }

        }


        return true;
    }


}
