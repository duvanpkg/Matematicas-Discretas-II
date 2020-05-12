package upb.edu.co;
import java.lang.reflect.Array;
import java.util.*;
public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int casos = Integer.parseInt(scan.nextLine());
        String output = "";

        for (int i = 0; i < casos; i++) {

            String linea = scan.nextLine();
            String[] vertices = linea.split(";");

            HashMap<String, Integer> cont = new HashMap<String, Integer>();

            for (int j = 0; j < vertices.length; j++) {

                String[] edges = vertices[j].split(":");
                if (edges.length == 2) {
                    cont.put(edges[0], edges[1].length());
                } else {
                    cont.put(edges[0], 0);
                }

                //Aqui agregamos las llaves :D

            }
            for (int k = 0; k < vertices.length; k++) {

                String[] edges = vertices[k].split(":");

                Set<String> keys = cont.keySet();
                String[] keys2 = new String[keys.size()];
                keys.toArray(keys2);

                if (edges.length >= 2)
                {


                    for (int j = 0; j < edges[1].length(); j++) {

                        String x = edges[1];
                        cont.put(String.valueOf(x.charAt(j)), cont.get(String.valueOf(x.charAt(j))) + 1);

                    }

                }
            }
            for (Map.Entry<String, Integer> entry : cont.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                output += key + "=" + String.valueOf(value) + ",";


                }
            output = output.substring(0,output.length()-1);
            output += "\n";
        }
        System.out.println(output);
    }
}
