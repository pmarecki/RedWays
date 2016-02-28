
import java.util.ArrayList;
import java.util.List;

class AlgoZ {
    //res[] = how many chars of prefix of 's' can be fitted starting at `at`
    public int[] solve(char[] s) {
        int n = s.length;
        int[] z = new int[n];
        int L = 0, R = 0;
        for (int i = 1; i < n; i++) {
            if (i > R) {
                L = R = i;
                while (R < n && s[R-L] == s[R]) R++;
                z[i] = R-L; R--;
            } else {
                int k = i-L;
                if (z[k] < R-i+1) z[i] = z[k];
                else {
                    L = i;
                    while (R < n && s[R-L] == s[R]) R++;
                    z[i] = R-L; R--;
                }
            }
        }
        return z;
    }


    public List<Integer> findOccurances(String target, String pattern) {
        String ss = pattern + "#" + target;
        int[] s = solve(ss.toCharArray());
        ArrayList<Integer> res = new ArrayList<>();
        for(int at = pattern.length(); at<ss.length();++at)
            if (s[at]==pattern.length()) res.add(at - pattern.length() - 1);
        return res;
    }


}


public class algoZ {
    public static void main(String[] args) {
        String target = "BA";
        String pattern = "A";
        List<Integer> goodPos = new AlgoZ().findOccurances(target, pattern);
        System.out.println(goodPos);
    }
}
