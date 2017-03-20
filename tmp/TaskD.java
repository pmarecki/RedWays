
public class TaskD {
    List<Integer>[] chs;
    int k;
    long total = 0;

    long[] dfs(int rt, int pa) {
        System.out.println("enter:" + rt);
        long[] res = new long[k+1]; //[k] are full ones
        for(int ch : chs[rt]) if (ch!=pa) {
            long[] res_ch = dfs(ch, rt);
            long[] promoted = new long[k+1];  //must keep the fulls @[k]
            //promote
            long full_at_rt = res_ch[k-1];
            promoted[k] = res_ch[k];
            for (int i = 0; i < k; i++) promoted[i + 1] += res_ch[i]; //include adding to full @[k]
            promoted[0] = full_at_rt;
            System.out.println("prom from " + ch + " " + Arrays.toString(promoted));
            long delta = 0;



            //close paths around (rt): update total
            //join with current
            //all fractionals + all fulls count as 1; zero does not count as it duplicates `k`
            for (int i = 1; i <= k; i++) delta += promoted[i];


            //todo: rethink the join with full ones
            //join with what is already joined
            for (int i = 1; i <= k; i++) {
                for (int j = 1; j <= k; j++) {
                    delta += (res[i] * promoted[j]) * (i + j + k-1)/k;
                }
            }

            System.out.println("delta from " + ch + " at " + rt + " = " + delta);
            total += delta;

            //blend into "reached root":
            for (int i = 0; i <= k; i++) res[i] += promoted[i];
        }
        res[0] += 1; //include the rt
        System.out.println("leave:" + rt + " ans:" + Arrays.toString(res));
        return res;
    }

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        k = in.nextInt();
        chs = new List[n];
        for (int i = 0; i < n; i++) chs[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt()-1;
            int v = in.nextInt()-1;
            chs[u].add(v); chs[v].add(u);
        }
        dfs(0, -1);
        out.println(total);
    }
}
