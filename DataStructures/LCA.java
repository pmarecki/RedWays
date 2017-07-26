package prepared.graphs;

import java.util.Arrays;

/**
 * Created based on http://codeforces.com/contest/832/problem/D
 *
 */
public class LCA {
    //structure is 1-based; "0" is spurious
    int n;
    int B = 20; //2**B = max_depth
    int[][] p;  //2**i -th parent
    int[] d;    //depth
    int[] g;    //graph parent

    public LCA(int[] g) {
        this.g = g;
        n = g.length + 1;
        p = new int[n+1][B];
        d = new int[n+1];
    }

    public void init() {
        //direct parents
        p[1][0] = 1;    //root
        for (int i = 2; i <=n; i++) p[i][0] = g[i-2];  //g[i]: parent of node "i+1"
        //exponential parents
        for (int i = 1; i < B; i++) {
            for (int j = 1; j <= n; j++) {
                p[j][i] = p[p[j][i - 1]][i - 1];    //4th parent = 2nd parent of 2nd parent
            }
        }

        for (int i = 2; i <= n; i++) {
            int u = i;
            for (int j = B-1; j >=0 ; j--) {
                if (p[u][j]!=1) {
                    d[i] += (1<<j);
                    u = p[u][j];
                }
            }
            d[i]++;
        }
        System.out.println(Arrays.toString(d));
        System.out.println("------------");
        for (int i = 0; i <= n; i++) {
            System.out.println("p[" + i + "][0]=" + p[i][0]);
        }
        System.out.println("------------");
        for (int i = 0; i <= n; i++) {
            System.out.println("p[" + i + "][1]=" + p[i][1]);
        }
    }

    public int lca(int u, int v) {
        if (d[v]<d[u]) {
            int t = u; u = v; v = t;
        }
        int del = d[v] - d[u];
        for (int i = 0; i < B; i++) {
            if ((del>>i&1)==1) v = p[v][i];
        }
        if (u==v) return u;
        for(int i = B-1; i>=0; --i) {
            if (p[u][i] != p[v][i]) {
                u = p[u][i];
                v = p[v][i];
            }
        }
        return p[u][0];
    }

    public static void main(String[] args) {
        int[] p = {1, 1, 2, 3};    //2->1, 3->1, 4->2, 5->3
        LCA testee = new LCA(p);
        testee.init();  //d=[0, 0, 1, 1, 2, 2]
        System.out.println("lca(4,5)= " + testee.lca(4,5));  //1


        p = new int[]{1, 1, 2, 2, 3, 3, 7};
        testee = new LCA(p);
        testee.init();
        System.out.println("lca(4,6)=" + testee.lca(4,6)); //1
        System.out.println("lca(6,8)=" + testee.lca(6,8)); //3

        int x = 0;
        for (int i = 2; i <= 8; i++) {
            for (int j = 2; j <= 8; j++) {
                x += testee.lca(i, j);
            }
        }
        System.out.println(x);

        //long chain
        int MX = 1000;
        p = new int[MX-1];
        for (int i = 0; i < MX-1; i++) {
            p[i] = i+1;
        }
        testee= new LCA(p);
        testee.init();
        System.out.println(testee.lca(500,1000));

        //random tree of size 10^6
    }

}
