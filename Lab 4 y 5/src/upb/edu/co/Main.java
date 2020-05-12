package upb.edu.co;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String temp;
        while (!(temp = scan.nextLine()).equals("0")) {

            int numPalabras = Integer.parseInt(temp);
            String idiomasInciales = scan.nextLine();
            String idiomaIni = idiomasInciales.split(" ")[0];
            String idiomaFin = idiomasInciales.split(" ")[1];

            List<String> idiomasPala = new ArrayList<>();
            List<String> palabras = new ArrayList<>();

            Set<String> idiomas = new HashSet<>(); //nodos


            for (int i = 0; i < numPalabras; i++) {

                String line = scan.nextLine();

                idiomas.add(line.split(" ")[0]);
                idiomas.add(line.split(" ")[1]);


                String ceroMasUno = ""; //String que recibe los idiomas de cada palabra
                ceroMasUno += line.split(" ")[0];
                ceroMasUno += " ";
                ceroMasUno += line.split(" ")[1];
                idiomasPala.add(ceroMasUno);

                palabras.add(line.split(" ")[2]);

            }


            //Si alguno de los idiomas iniciales o finales no se encuentran es imposible
            int contIni = 0;
            int contFin = 0;


            for (String id : idiomasPala) {
                if (!(id.contains(idiomaIni))) {
                    contIni++;
                } else {

                }
                if (!id.contains(idiomaFin)) {
                    contFin++;
                }
            }

            if (contIni == numPalabras || contFin == numPalabras) {
                System.out.println("Impossivel");
                continue;
            }

            //Llenar el hashmap de vertices y ceros. (Despues se cambiaran por sus longitudes)
            HashMap<String, Integer> vertLong = new HashMap<>();
            for (String a : idiomas) {
                vertLong.put(a, 0);
            }

            //Cambiar valores por sus longitudes, es decir, aplicar dijkstra
            String idiomaVariador = idiomaIni;
            while (vertLong.get(idiomaFin) == 0) {

                for (int i = 0; i < idiomasPala.size(); i++) {

                    if (idiomasPala.get(i).contains(idiomaVariador)) {
                        int lenArista = palabras.get(i).length();

                        String i1 = idiomasPala.get(i).split(" ")[0];
                        String i2 = idiomasPala.get(i).split(" ")[1];




                        if (!(i1.equals(idiomaVariador))) {
                            if (vertLong.get(i1) == 0) {
                                vertLong.put(i1, vertLong.get(i2) + lenArista);

                            } else {
                                if ((vertLong.get(i2) + lenArista) < vertLong.get(i1)) {
                                    vertLong.put(i1, vertLong.get(i2) + lenArista);
                                }
                            }

                        } else {

                            if (vertLong.get(i2) == 0) {
                                vertLong.put(i2, vertLong.get(i1)+lenArista);

                            } else {
                                if ((lenArista+vertLong.get(i1)) < vertLong.get(i2)) {
                                    vertLong.put(i2, vertLong.get(i1)+lenArista);
                                }
                            }
                        }
                    }
                }

                int minTemp = 0;
                String idioTemp = "";
                for (int i = 0; i < idiomasPala.size(); i++) {
                    if (idiomasPala.get(i).contains(idiomaVariador)) {

                        if (minTemp == 0) {

                            minTemp = palabras.get(i).length();
                            String i1 = idiomasPala.get(i).split(" ")[0];
                            String i2 = idiomasPala.get(i).split(" ")[1];

                            if(!idiomaVariador.equals(i1)){
                                idioTemp = i1;
                            }else{
                                idioTemp = i2;
                            }


                        }else{
                            if (minTemp > (palabras.get(i).length())) {
                                minTemp = palabras.get(i).length();

                                String i1 = idiomasPala.get(i).split(" ")[0];
                                String i2 = idiomasPala.get(i).split(" ")[1];

                                if(!idiomaVariador.equals(i1)){
                                    idioTemp = i1;
                                }else{
                                    idioTemp = i2;
                                }

                            }
                        }
                    }
                }
                for (int i = 0; i < idiomasPala.size(); i++) {
                    if (idiomasPala.get(i).contains(idiomaVariador)) {
                        idiomasPala.remove(idiomasPala.get(i));
                        palabras.remove(palabras.get(i));
                    }
                }

                idiomaVariador = idioTemp;
                //tras definir idioma variador debemos eliminarlo de la lista de idioma pala para no volver a pasar por este camino por error
                //sin embargo hacer el debug de nuevo con mas calma.


            }
            System.out.println(vertLong.get(idiomaFin));

        }
    }
}
