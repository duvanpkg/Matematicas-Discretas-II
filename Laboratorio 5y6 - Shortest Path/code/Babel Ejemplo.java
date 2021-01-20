/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package upb.edu.co;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author Alejandro E. Garces
 */


public class Babel {

    public static class Edge implements Comparable{

        int v;
        int w;
        String word;
        boolean visited;
        Edge(int vv, int ww, String wordw, boolean visi )
        {
            v = vv;
            w = ww;
            word = wordw;
            visited = visi;
        }

        @Override
        public int compareTo(Object o)
        {
            return this.w-((Edge)o).w;
        }

        @Override
        public String toString()
        {
            return v+" * "+w+" * "+word;
        }

    }


    public static void main(String[] args) throws IOException{

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> mapNames;
        ArrayList<ArrayList<Edge>> nodes;
        Queue<Edge> qBabel;
        int pos = 0;
        int[] dist;
        while(true)
        {
            int n = Integer.parseInt(in.readLine());
            if(n == 0)break;

            mapNames = new HashMap<String, Integer>();
            nodes = new ArrayList<ArrayList<Edge>>();
            qBabel = new PriorityQueue<Edge>();
            String[] test = in.readLine().split("[ ]+");
            pos = 0;
            for(int i=0;i<n;i++)
            {
                String[] read = in.readLine().split("[ ]+");
                int u = 0;
                int v = 0;
                if(mapNames.containsKey(read[0]))
                    u = mapNames.get(read[0]);
                else{
                    u = pos++;
                    mapNames.put(read[0], u);
                }

                if(mapNames.containsKey(read[1]))
                    v = mapNames.get(read[1]);
                else{
                    v = pos++;
                    mapNames.put(read[1], v);
                }

                if(u >= nodes.size())
                    nodes.add(new ArrayList<Edge>());
                if(v >= nodes.size())
                    nodes.add(new ArrayList<Edge>());


                nodes.get(u).add(new Edge(v, 0, read[2], false));
                nodes.get(v).add(new Edge(u, 0, read[2], false));

            }
            //System.out.println(mapNames);
            ///for(int i=0;i<nodes.size();i++)
            // System.out.println(nodes.get(i));
            dist = new int[pos+1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            int uu = -1;
            int vv = -1;
            if(mapNames.containsKey(test[0]))
                uu = mapNames.get(test[0]);

            if(mapNames.containsKey(test[1]))
                vv = mapNames.get(test[1]);

            if(uu == -1 || vv == -1)
            {
                System.out.println("impossivel");
                continue;
            }

            if(uu == vv)
            {
                System.out.println("0");
                continue;

            }
            dist[uu] = 0;

            for(int i=0;i<nodes.get(uu).size();i++)
            {
                Edge aux = nodes.get(uu).get(i);
                dist[aux.v] = aux.word.length();
                nodes.get(uu).get(i).visited = true;
                qBabel.offer(new Edge(aux.v, aux.word.length(), aux.word, true));
            }
            //System.out.println(qBabel+" B*");
            int answer = -1;
            while(!qBabel.isEmpty())
            {
                int v = qBabel.peek().v;
                int w = qBabel.peek().w;
                String word = qBabel.poll().word;
                //System.out.println(v+" | "+w+" | "+word+" A");
                // System.out.println(qBabel+" B");

                if(v == vv)
                {   answer = w;
                    break;
                }

                for(int i=0;i<nodes.get(v).size();i++)
                {
                    Edge aux = nodes.get(v).get(i);
                    //System.out.println("FOR - "+aux+" C");
                    if(word.charAt(0) != aux.word.charAt(0) && !aux.visited)
                    {//System.out.println("FOR - "+aux+" - "+dist[aux.v]+" D");
                        //if(dist[aux.v] > dist[v] + aux.word.length())
                        {
                            //dist[aux.v] = dist[v] + aux.word.length();
                            int disAux  = w + aux.word.length();
                            nodes.get(v).get(i).visited = true;
                            // System.out.println("FOR - "+aux+" - "+dist[aux.v]+" E");
                            //qBabel.offer(new Edge(aux.v, dist[aux.v], aux.word));
                            qBabel.offer(new Edge(aux.v,disAux, aux.word, true));
                        }
                    }
                }

            }

            System.out.println((answer>-1?answer:"impossivel"));
        }
        in.close();
        System.exit(0);
    }
}