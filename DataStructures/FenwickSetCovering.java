package Wykopki;

import java.util.*;

class Pii implements Comparable<Pii> {
    int st;
    int nd;

    public Pii() {
    }

    public Pii(int st, int nd) {
        this.st = st;
        this.nd = nd;
    }

    @Override
    public String toString() {
        return "P{" +
                "st=" + st +
                ", nd=" + nd +
                '}';
    }

    @Override
    public int compareTo(Pii p) {
        int z = st - p.st;
        if (z==0) z = nd - p.nd;
        return z;
    }
}

class Fenwick {
    private TreeSet[] da;
    int N;

    public Fenwick(int n) {
        N = n;
        da = new TreeSet[n];
        for (int i = 0; i < n; i++) {
            da[i] = new TreeSet<Pii>();
        }
    }

    //insertion of element `r` at position `at`; keep track of original insertion and store in pairs
    void add(int l, int r) {
        for(int at = l; at < N; at += at & (-at))
            da[at].add(new Pii(r, l));
    }

    //query: sum up to "at" inclusive
    int sum(int lAt, int maxR) {        //
        int res = 0;
        for(;lAt>0; lAt -= lAt&(-lAt)) res += da[lAt].headSet(new Pii(maxR, (int)1e7)).size();
        return res;
    }
}


public class A {
    public static void main(String[] args) {
        Fenwick f = new Fenwick(200);
        f.add(2, 4);
        f.add(3, 10);
        f.add(3, 4);
        f.add(5, 11);
        f.add(6, 10);

        //elems of li <= L and ri <=R
        int L = 2, R = 4;
        System.out.println(f.sum(R,R) - f.sum(L-1,R));  //number of sets covered by [L,R]

    }
}
