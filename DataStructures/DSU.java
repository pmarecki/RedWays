import java.util.Arrays;

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
    int[] getParent() {
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



public class DsuTest {
    public static void main(String[] args) {
        Dsu d = new Dsu(10);    //elems 0..9
        d.merge(0, 1);
        d.merge(0, 2);
        d.merge(0, 3);
        d.merge(5, 6);
        d.merge(6, 9);
        System.out.println(d.root(0));      //0
        System.out.println(d.root(5));      //5
        System.out.println(d.elems(0));     //4
        System.out.println(d.root(8));      //8
    }
}
