package Prepared;

import static org.assertj.core.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by mareckip on 26.05.15.
 *
 * Base: http://www.everfall.com/paste/id.php?q1ji49b637h1
 * post by "Alias", http://codeforces.com/blog/entry/3327
 *
 */

class SegmentTree1d_max {

    //left, right inclusive
    int query(int[] t, int left, int right) {
        int n = t.length / 2;
        left += n;
        right += n;
        int res = Integer.MIN_VALUE;
        for (int lx = left, rx = right; lx <= rx; lx=(lx+1)/2, rx=(rx-1)/2) {
            if ((lx&1)!=0) {
                res = Math.max(res, t[lx]);
            }
            if ((rx&1)==0) {
                res = Math.max(res, t[rx]);
            }
        }
        return res;
    }

    //add
    void add(int[] t, int at, int value) {
        int n = t.length / 2;
        at += n;
        t[at] += value;
        for (int tx = at; tx > 0; tx /= 2) if (tx > 1) {
            t[tx / 2] = Math.max(t[tx], t[tx ^ 1]);
        }
    }

    int at(int[] t, int x) {
        return t[t.length/2+x];
    }
}

//todo: `t` should be internalized
class SegmentTree1d_sum {
    //left, right inclusive
    int query(int[] t, int left, int right) {
        int n = t.length / 2;
        left += n;
        right += n;
        int res = 0;
        for (int lx = left, rx = right; lx <= rx; lx=(lx+1)/2, rx=(rx-1)/2) {
            if ((lx&1)!=0) {
                res += t[lx];
            }
            if ((rx&1)==0) {
                res += t[rx];
            }
        }
        return res;
    }

    //add
    void add(int[] t, int at, int value) {
        int n = t.length / 2;
        at += n;
        t[at] += value;
        for (int tx = at; tx > 0; tx /= 2) if (tx > 1) {
            t[tx / 2] = t[tx] +  t[tx ^ 1];
        }
    }

    int at(int[] t, int x) {
        return t[t.length/2+x];
    }
}



public class SegmentTree1D_Test {
    public static void main(String[] args) {
        int[] t = new int[16];   //0..7
        SegmentTree1d_sum s = new SegmentTree1d_sum();
        s.add(t, 4, 1);
        s.add(t, 5, 2);
        s.add(t, 4, -1);
        
        assertThat(s.query(t, 0, 5)).isEqualTo(2);
        Arrays.fill(t,0);
        assertThat(s.query(t, 0, 7)).isEqualTo(0);
        s.add(t, 0, 123);
        assertThat(s.query(t, 0, 0)).isEqualTo(123);

        Arrays.fill(t, 0);

        Random r = new Random();
        int[] tt = new int[8];
        for (int i = 0; i < 8; i++) {
            int ii = r.nextInt(100);
            tt[i] += ii;
            s.add(t, i, ii);
        }
        for (int i = 0; i < 30; i++) {
            int le = r.nextInt(8);
            int ri = r.nextInt(8);
            int sum = 0;
            if (le>ri) continue;

            for (int j = le; j <= ri; j++) {
                sum += tt[j];
            }
            assertThat(sum).isEqualTo(s.query(t, le, ri));
        }
    }
}
