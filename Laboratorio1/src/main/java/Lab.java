import java.util.*;

public class Lab {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int test = input.nextInt();
        String output = "";

        for (int i = 0; i < test; i++) {

            String text = input.next();
            int lenText = text.length();

            List<Integer> divisores = new ArrayList<Integer>();

            for (int j = 1; j < lenText; j++) { //ENCONTRAR DIVISORES

                if (lenText % j == 0){

                    divisores.add(j);

                }

            }

            int kFin = lenText;

            int lenList =divisores.size();
            for (int d = 0; d < lenList; d++) {
                int k = divisores.get(lenList-(d+1));

                List<String> subCadenas = new ArrayList<String>();
                int cont =0;

                for (int a = 0; a < lenText; a += k) {
                    String sub = text.substring(cont*k,(cont*k)+k);
                    subCadenas.add(sub);
                    cont++;
                }
                int cont2 = 0;
                for (int j = 0; j <subCadenas.size()-1 ; j++) {

                    if((subCadenas.get(0)).equals(subCadenas.get(j+1))){
                        cont2++;

                    }

                }
                if (cont2 == subCadenas.size()-1){
                    kFin=k;
                }
            }

            output += "\n"+Integer.toString(kFin)+"\n";

        }
        System.out.println(output);

    }

}