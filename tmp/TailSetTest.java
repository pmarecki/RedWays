package codeforces;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

import static java.lang.System.currentTimeMillis;

//This is not true:
//http://stackoverflow.com/questions/10706589/what-is-the-time-complexity-of-the-tailset-operation-of-java-util-treeset

/**
 * Iterating the `set.tailSet` is as fast as iterating `set`
 * (can go thru a set of 1e6 elems 10 times per second)
 *
 * Taking set.tailSet.size() is slow(er): takes 100ms on first run (subsequent return instantly)
 */


public class Task {

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
    }


    public static void main(String[] args) {
//        ConcurrentSkipListSet<Integer> s = new ConcurrentSkipListSet<>();
        TreeSet<Integer> s = new TreeSet<>();
        Random r = new Random();
        for (int i = 0; i < 1_000_000; i++) {
            s.add(r.nextInt((int) 1e9));
        }
        System.out.println("...");
//        SortedSet<Integer> ss = s.subSet(000_000_000, 1_000_000_010);
        SortedSet<Integer> ss = s.tailSet(000_000_000);
//        System.out.println(ss);
        long st = currentTimeMillis();
        long sz = 0, sum = 0, val = 0;
        int REP = 1_000_000;
        for (int i = 0; i < REP; i++) {
//            val += kth_element(s, 50);
            sz += ss.size();
//            sum += s.stream().mapToLong(Integer::intValue).sum();
        }
        System.out.println("size=" + sz/REP);
        System.out.println("sum=" + sum/REP);
        long en = currentTimeMillis();
        System.out.println((en-st) + "ms");

//        System.out.println(new TreeSet<Integer>(Arrays.asList(1,2,6,7,9,12)).subSet(6,12));

    }
}
