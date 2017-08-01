package data_structures;

import java.util.Arrays;

class DSU {
    private int[] parent;

    DSU(int size) {
        parent = new int[size];
        Arrays.fill(parent, -1);
    }

    //top of tree in which "v" resides
    int root(int v) {
        if (parent[v] < 0) return v;
        else return parent[v] = root(parent[v]);
    }

    void merge(int x, int y) {
        x = root(x);
        y = root(y);
        if (x == y) return;
        if (parent[y] < parent[x]) {
            int t = x;
            x = y;
            y = t;
        }
        parent[x] += parent[y];
        parent[y] = x;
    }

    //number of elements in tree in which "v" resides
    int elems(int v) {
        return -parent[root(v)];
    }
    public static void main(String[] args) {
        DSU d = new DSU(5);
        d.merge(0, 1);
        System.out.println(d.root(0));
        System.out.println(d.root(1));
        System.out.println(d.root(4));
        System.out.println(d.elems(4));
    }

}
