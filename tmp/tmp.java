package wykop.cp.matching;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public class Matcher {
    List<Integer>[] chs;    //graph
    int[] p, s;  //parent, son
    boolean[] vis;

    boolean dfs(int rt) {
        out.println("Entering " + rt);
        for(int c : chs[rt]) {
            if (!vis[c]) {
                vis[c] = true;
                if (p[c]==-1 || dfs(p[c])) {
                    p[c] = rt;
                    s[rt] = c;
                    return true;
                }
            }
        }
        return false;
    }

    private void run() {
        chs = new List[3];
        chs[0] = Arrays.asList(0, 1, 2);
        chs[1] = Arrays.asList(0);
        chs[2] = Arrays.asList(1);
        p = new int[10];
        s = new int[10];
        Arrays.fill(p, -1);
        Arrays.fill(s, -1);

        //Algo
        for (int i = 0; i < 3; i++) {
            vis = new boolean[10];
            if (!dfs(i)) {
                out.println("Wrong");
                return;
            }
        }
        out.println("OK");
        out.println(Arrays.toString(s));
    }

    public static void main(String[] args) {
        Matcher m = new Matcher();
        m.run();
    }

}
