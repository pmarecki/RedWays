package data_structures;

import org.junit.Test;

import static org.junit.Assert.*;

public class LCATest {

    @Test
    public void initTest() {
        int[] p = {1, 1, 2, 3};    //2->1, 3->1, 4->2, 5->3
        LCA testee = new LCA(p);
        testee.init();  //d=[0, 0, 1, 1, 2, 2]
        System.out.println("lca(4,5)= " + testee.lca(4,5));  //1


        p = new int[]{1, 1, 2, 2, 3, 3, 7};
        testee = new LCA(p);
        testee.init();
        System.out.println("lca(4,6)=" + testee.lca(4,6)); //1
        System.out.println("lca(6,8)=" + testee.lca(6,8)); //3

        int x = 0;
        for (int i = 2; i <= 8; i++) {
            for (int j = 2; j <= 8; j++) {
                x += testee.lca(i, j);
            }
        }
        System.out.println(x);

        //long chain
        int MX = 1000;
        p = new int[MX-1];
        for (int i = 0; i < MX-1; i++) {
            p[i] = i+1;
        }
        testee= new LCA(p);
        testee.init();
        System.out.println(testee.lca(500,1000));

    }
}