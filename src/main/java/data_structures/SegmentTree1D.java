package data_structures;

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
    int[] t;

    public SegmentTree1d_max(int n) {
        t = new int[n * 2];
    }

    //left, right inclusive
    int query(int left, int right) {
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
    void add(int at, int value) {
        int n = t.length / 2;
        at += n;
        t[at] += value;
        for (int tx = at; tx > 0; tx /= 2) if (tx > 1) {
            t[tx / 2] = Math.max(t[tx], t[tx ^ 1]);
        }
    }

    int at(int at) {
        return t[t.length/2+at];
    }
}

class SegmentTree1d_sum {
    int[] t;

    public SegmentTree1d_sum(int n) {
        t = new int[n * 2];
    }

    //left, right inclusive
    int query(int left, int right) {
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
    void add(int at, int value) {
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



class SegmentTree1D_Test {
    public static void main(String[] args) {
        int[] t = new int[16];   //0..7
        SegmentTree1d_sum s = new SegmentTree1d_sum(8);
        s.add(4, 1);
        s.add(5, 2);
        s.add(4, -1);
        assertThat(s.query(0, 5)).isEqualTo(2);

        s = new SegmentTree1d_sum(8);
        Arrays.fill(t,0);
        assertThat(s.query(0, 7)).isEqualTo(0);
        s.add(0, 123);
        assertThat(s.query(0, 0)).isEqualTo(123);

        s = new SegmentTree1d_sum(8);
        Random r = new Random();
        int[] tt = new int[8];
        for (int at = 0; at < 8; at++) {
            int ii = r.nextInt(100);
            tt[at] += ii;
            s.add(at, ii);
        }
        for (int i = 0; i < 30; i++) {
            int le = r.nextInt(8);
            int ri = r.nextInt(8);
            int sum = 0;
            if (le>ri) continue;

            for (int j = le; j <= ri; j++) {
                sum += tt[j];
            }
            assertThat(sum).isEqualTo(s.query(le, ri));
        }
    }
}
