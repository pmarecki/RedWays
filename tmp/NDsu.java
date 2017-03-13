package prepared.data_structures;

import static java.lang.String.format;
import static java.lang.System.out;

public class NDsu {
    final int MAXN = 100;
    int[] fa = new int[MAXN << 1];
    int[] rank = new int[MAXN << 1];    //speedup

    //extra for task
    int[] neg = new int[MAXN << 1];

    int find(int x){
        return (x==fa[x]) ? x : (fa[x] = find(fa[x]));
    }

    void connect(int x, int y){
        int rx=find(x), ry=find(y);
        if(rx == ry) return;
        fa[rx] = ry;    //simple (not fastest)

        //speedup
//        if(rank[rx] > rank[ry]) {fa[ry] = rx; rank[rx] += rank[ry];}
//        if(rank[rx] <= rank[ry]) {fa[rx] = ry; rank[ry] += rank[rx];}
    }

    void init(int n) {
        for (int i = 0; i < n; i++) {
            fa[i] = i;
            rank[i] = 1;
            //fa[n+i]=n+i;
        }
    }

    public static void main(String[] args) {
        NDsu dsu = new NDsu();
        dsu.init(20);
        dsu.connect(0, 1);
        dsu.connect(1, 2);
        for (int i = 0; i < 10; i++) {
            out.println(format("[%d]: %d; rank(rt)=%d", i, dsu.find(i), dsu.rank[i]));
        }

    }
}
