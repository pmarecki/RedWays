package Prepared;
import java.util.Arrays;

/**
 * Created by mareckip on 29.04.15.
 */

//DSU by bmerry
//each: root v, par[v]=-(number_of_children)
// ranges as array[size], 0..size-1
// 1e3 runs of 1e4 merge will be OK
class Dsu {
    private int[] par;
    Dsu(int size) {
        par = new int[size];
        Arrays.fill(par,-1);
    }
    //top of tree in which "v" resides
    int root(int v) {
        if (par[v]<0) return v;
        else return par[v] = root(par[v]);
    }
    void merge(int x,int y){
        x = root(x); y = root(y);
        if (x==y) return;
        if(par[y] < par[x]) {
            int t = x; x=y; y=t;
        }
        par[x] += par[y];
        par[y] = x;
    }
    //--------cut here-------------------------------------

    //number of elements in tree in which "v" resides
    int elems(int v) {
        return -par[root(v)];
    }

    //par points to the root of the tree of each element;
    //for root elements, it gives the (-) number of elements in current subtree
    int[] getPar() {
        return par;
    }

}

/**
 * Notes:
 * - can join dsu's L, R:
 *   forEach(node:  R.root(node)!=node)
 *      L.merge(node, R.root(node))
 * - int[] par is the whole information; just clone() if Dsu needs to be cloned
 */





public class DsuSnippet {
    public static void main(String[] args) {
        //test
        Dsu d = new Dsu(2);
        d.merge(0, 1);
        System.out.println(d.root(0));      //0
        System.out.println(d.root(1));      //0
        System.out.println(d.elems(0));     //2
    }
}
