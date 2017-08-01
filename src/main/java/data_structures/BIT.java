package data_structures;

/**
 * query(5) : sum of elems <=4
 *
 * Note: available on [0,n]
 * [==Fenwick]
 */
public class BIT {
    int n;
    int[] c;

    BIT(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    void modify(int x, int s) {
        assert x>0;
        for(;x<=n;x+=x&-x) {
            c[x] += s;
        }
    }

    int query(int x) {
        assert x <= n;
        int s = 0;
        for(;x>0;x-=x&-x) {
            s += c[x];
        }
        return s;
    }

    public static void main(String[] args) {
        BIT b = new BIT(10);
        b.modify(3, 1);
        b.modify(5,-1);
        b.modify(8, 1);
        b.modify(10, 10);
        for (int i = 0; i <= 10; i++) {
            System.out.println(i + "->" + b.query(i));
        }
        //0->0
        //1->0
        //2->0
        //3->1
        //4->1
        //5->0
        //6->0
        //7->0
        //8->1
        //9->1
        //10->11
    }
}
