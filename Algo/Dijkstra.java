
import java.util.*;

/**
 * By net12k44, contest: Codeforces Round #Pi (Div. 2), problem: (E) President and Roads
 *
 * http://codeforces.com/contest/567/status/E
 *
 *
 * --> gives shortest distances d[] from given source s.
 * Complexity O(V log V)    (V=number of edges)
 *
 *
 */


class Edge{
    int nextNode, length;
    Edge(int nextNode, int length){
        this.nextNode = nextNode;
        this.length = length;
    }
}

class Dijkstra {
    static void dijkstra(int s, final long[] d, List<List<Edge>> a){
        Arrays.fill(d, 1000000000000000L);
        d[s] = 0;

        TreeSet<Integer> q = new TreeSet<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer i, Integer j){
                if (d[i] < d[j]) return -1;
                else if (d[i] > d[j]) return 1;
                else return i-j;
            }
        });

        q.add(s);
        while (!q.isEmpty()){
            int i = q.pollFirst();
            for(Edge e: a.get(i)){
                int j = e.nextNode;
                if (d[j] > d[i]+e.length){
                    q.remove(j);
                    d[j] = d[i]+e.length;
                    q.add(j);
                }
            }
        }
    }
}

/**
 * Usage:
 */

public class DijkstraUsage {
    public static void main(String[] args) {
        int n = 5;  //nodes
        int m = 6;  //edges
        List< List<Edge> > next = new ArrayList<>();    //has "nodes" and "edge_length" information
        List< List<Edge> > prev = new ArrayList<>();
        for(int i=0; i<n; ++i){
            next.add( new ArrayList<>() );
            prev.add( new ArrayList<>() );
        }
        //dane
        int[] x = new int[m], y = new int[m], len = new int[m];
        x[0] = 0; y[0] = 1; len[0] = 1;
        x[1] = 0; y[1] = 2; len[1] = 5;
        x[2] = 1; y[2] = 2; len[2] = 1;
        x[3] = 1; y[3] = 4; len[3] = 5;
        x[4] = 2; y[4] = 3; len[4] = 1;
        x[5] = 3; y[5] = 4; len[5] = 1;

        //pack into graph info structure
        for(int k=0; k<m; ++k){
            next.get(x[k]).add( new Edge(y[k], len[k]) );
            prev.get(y[k]).add( new Edge(x[k], len[k]) );
        }

        long[] ds = new long[n], dt = new long[n];
        Dijkstra.dijkstra(0, ds, next);     //start from 0
        Dijkstra.dijkstra(3, dt, prev);     //end at 3
        System.out.println(Arrays.toString(ds));
        System.out.println(Arrays.toString(dt));
    }
}
