package Prepared.DataStructures;
import java.util.*;

import static javax.swing.UIManager.put;


class Dsu {
    private int[] parent;
    Dsu(int size) {
        parent = new int[size];
        Arrays.fill(parent,-1);
    }

    //top of tree in which "v" resides
    int root(int v) {
        if (parent[v]<0) return v;
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

    //parent points to the root of the tree of each element;
    //for root elements, it gives the (-) number of elements in current subtree
    private int[] getParent() {
        return parent;
    }

    public int size() {
        return parent.length;
    }
    public int[] getParentArray() {
        return parent;
    }
}

/**
 * Notes:
 * - can join dsu's L, R:
 *   forEach(node:  R.root(node)!=node)
 *      L.merge(node, R.root(node))
 * - int[] parent is the whole information; just clone() if Dsu needs to be cloned
 */


class DsuUtils {
    //n : size to process
    public static Map<Integer,Set<Integer>> gatherComponents(Dsu dsu, int n) {
        Map<Integer, Set<Integer>> m = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int rt = dsu.root(i);
            if (!m.containsKey(rt)) m.put(rt, new HashSet<>());
            m.get(rt).add(i);
        }
        return m;
    }
}


public class DsuTest {
    public static void main(String[] args) {
        Dsu dsu = new Dsu(10);    //elems 0..9
        dsu.merge(0, 1);
        dsu.merge(0, 2);
        dsu.merge(0, 3);
        dsu.merge(5, 6);
        dsu.merge(6, 9);

        System.out.println(dsu.root(0));      //0
        System.out.println(dsu.root(1));      //0
        System.out.println(dsu.root(2));      //0
        System.out.println(dsu.root(3));      //0
        System.out.println(dsu.root(4));      //4
        System.out.println(dsu.root(5));      //5
        System.out.println(dsu.root(6));      //5
        System.out.println(dsu.root(7));      //7
        System.out.println(dsu.root(8));      //8
        System.out.println(dsu.root(9));      //5

        System.out.println(dsu.elems(0));     //4
        System.out.println(dsu.elems(5));     //3
        System.out.println(dsu.elems(7));     //1

        System.out.println("--------");

        for(Set<Integer> s : DsuUtils.gatherComponents(dsu,10).values()) {
            System.out.println(s);
        }
    }
}
