package upb.edu.co;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int casos = Integer.parseInt(scan.nextLine());
        String output = "";

        for (int i = 0; i < casos; i++) {

            String linea = scan.nextLine();
            String[] vertices = linea.split(";");


            if (Funciones.esCiclo(linea)) {
                if (vertices.length % 2 != 0) //si el numero de vertices es impar
                {
                    output += "0\n";//si es impar y es ciclo NO ES BIPARTITO
                } else  //si el numero de vertices es par
                {
                    output += "1\n";//si es PAR y es ciclo  ES BIPARTITO
                }
            } else if (Funciones.esRueda(linea)) {
                output += "0\n";
            } else {
                if (Funciones.esOtro(linea)) {
                    output += "1\n";
                } else {
                    output += "0\n";
                }
            }


            //faltan funciones para grafos completos o cualquier otro grafo :(
        }
        System.out.println(output);
    }
}