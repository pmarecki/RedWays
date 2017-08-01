package data_structures;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class AlgoZ {
    //res[] = how many chars of prefix of 's' can be fitted starting at `at`
    public static int[] solve(char[] s) {
        int n = s.length;
        int[] z = new int[n];
        int L = 0, R = 0;
        for (int i = 1; i < n; i++) {
            if (i > R) {
                L = R = i;
                while (R < n && s[R-L] == s[R]) R++;
                z[i] = R-L;
                R--;
            } else {
                int k = i-L;
                if (z[k] < R-i+1) z[i] = z[k];
                else {
                    L = i;
                    while (R < n && s[R-L] == s[R]) R++;
                    z[i] = R-L;
                    R--;
                }
            }
        }
        return z;
    }


    public static Integer[] findOccurances(String target, String pattern) {
        String ss = pattern + "#" + target;
        int m = pattern.length();
        int[] s = solve(ss.toCharArray());
        ArrayList<Integer> res = new ArrayList<>();
        for(int at = pattern.length(); at<ss.length();++at)
            if (s[at]==pattern.length()) res.add(at - pattern.length() - 1);
        return res.toArray(new Integer[res.size()]);
    }
    public static void main(String[] args) {
        Integer[] goodPos = AlgoZ.findOccurances("BABA", "A");
        assertThat(goodPos).isEqualTo(new int[]{1, 3});

        goodPos = AlgoZ.findOccurances("a10b1a12b1","a12b1");
        assertThat(goodPos).isEqualTo(new int[]{5});

    }

}
