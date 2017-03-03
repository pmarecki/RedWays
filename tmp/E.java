package wykop;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class A {
    static int gcd(int a, int b) {
        return (a != 0) ? gcd(b % a, a) : b;
    }

    static int f(int n) {
        int ans = 0;
        for (int i = 1; i <= n / 2; i++) {
            int j = n - i;
            if (gcd(i,j)==1) ++ans;
        }
        return ans;
    }

    static int g(int n) {
        Set<Integer> divs = new HashSet<>();
        for (int i = 1; i <= (int)(Math.sqrt(n)+1); i++) {
            if (n%i==0) divs.add(i);
        }
        if (n!=0) divs.add(n);
        int ans = 0;
        for(int d : divs) ans += f(n / d);
        return ans;
    }


    public static void main(String[] args) {
//        System.out.println(gcd(2,6));
//        System.out.println(f(13));
//        System.out.println(g(10));

        int n = 666666;
        for (int i = 0; i < 40; i++) {
            if (i%2==0) n = g(n);
            else n = f(n);
            System.out.println(String.format("k=%2d  \t%8d", i, n));
        }

    }

}
