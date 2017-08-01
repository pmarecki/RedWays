package data_structures;

/**
 * Created by mareckip on 28.04.15.
 */
public class Fenwick2d {
    private int data[][];
    int N;
    public Fenwick2d(int n) {
        N = n;
        data = new int[n][n];
    }

    void add(int atx, int aty, int howMuch) {
        for (; atx < N; atx += atx & (-atx)) {
            int Y = aty;
            for (; Y < N; Y += Y & (-Y)) {
                data[atx][Y] += howMuch;
            }
        }
    }

    int sum(int atx, int aty) {        //"at" inclusive
        int res = 0;
        for(;atx>0; atx -= atx&(-atx)) {
            int Y = aty;
            for(;Y>0; Y -= Y&(-Y)) {
                res += data[atx][Y];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int N = 100;
        Fenwick2d f= new Fenwick2d(1000);
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                f.add(i,j,1);
            }
        }
        System.out.println(f.sum(100,100));

    }
}