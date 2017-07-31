//2d Fenwick tree: https://www.quora.com/How-do-I-implement-a-2D-binary-indexed-tree-for-range-update-and-range-query-operations
class F2 {
    int MX = 110;
    int[][] tree = new int[MX][MX];

    //coords >=1

    void update(int x , int y , int val){
        int y1;
        while (x <= MX){
            y1 = y;
            while (y1 <= MX){
                tree[x][y1] += val;
                y1 += (y1 & -y1);
            }
            x += (x & -x);
        }
    }

    int read(int x,int y){ // return sum from 1,1 to x,y.
        int sum= 0;
        while(x!=0){
            int y1 = y;
            while(y1!=0){
                sum += tree[x][y1];
                y1 -= y1 & -y1;
            }
            x -= x & -x;
        }
        return sum;
    }

}

public class AA {
    public static void main(String[] args) {
        F2 f = new F2();
        f.update(5, 5, 1);
        f.update(1, 1, 1);
        f.update(100, 100, 1);

        System.out.println(f.read(6,6));
    }
}
