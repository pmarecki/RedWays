
/**
 * Searches in arrays of elements of arbitrary type (@Override .equals())
 *
 */

class Blok {
    Long len;
    char type;

    public Blok(Long len, char type) {
        this.len = len;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Blok{" +
                "len=" + len +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blok blok = (Blok) o;

        if (type != blok.type) return false;
        return len != null ? len.equals(blok.len) : blok.len == null;

    }

}


class AlgoZ {
    //res[] = how many chars of prefix of 's' can be fitted starting at `at`
    public static int[] solve(Blok[] s) {
        int n = s.length;
        int[] z = new int[n];
        int L = 0, R = 0;
        for (int i = 1; i < n; i++) {
            if (i > R) {
                L = R = i;
                while (R < n && s[R-L].equals(s[R])) R++;        //!!
                z[i] = R-L;
                R--;
            } else {
                int k = i-L;
                if (z[k] < R-i+1) z[i] = z[k];
                else {
                    L = i;
                    while (R < n && s[R-L].equals(s[R])) R++;    //!!
                    z[i] = R-L;
                    R--;
                }
            }
        }
        return z;
    }


    public static Integer[] findOccurances(Blok[] target, Blok[] pattern) {
        Blok[] full = new Blok[target.length + 1 + pattern.length];
        for (int i = 0; i < pattern.length; i++) {
            full[i] = pattern[i];
        }
        full[pattern.length] = new Blok(0L, '@');
        for (int i = 0; i < target.length; i++) {
            full[pattern.length+1+i] = target[i];
        }
        int[] s = solve(full);
        ArrayList<Integer> res = new ArrayList<>();
        for(int at = pattern.length + 1; at<full.length;++at)
            if (s[at]==pattern.length) res.add(at - pattern.length - 1);
        return res.toArray(new Integer[res.size()]);
    }
}
