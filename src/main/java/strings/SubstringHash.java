package strings;

import java.util.Arrays;

/**
 * 1-based hash
 * preh[s] = b^6 s1 + b^5 s2 + b^4 s3 + b^3 s4 + b^2 s5 + b^1 s6 +s7
 *
 * hash(0,1) = s1
 * hash(0,2) = b^1 s1 + s2
 * hash(0,3) = b^2 s1 + b^1 s2 + s3
 *
 * hash(4,7) = b^2 s5 + b^1 s6 + s7
 */
public class SubstringHash {
    static int NMODS = 1;
    long MOD[] =  {1000_000_007L, 2147_483_647L, 2147_483_629L, 2147_483_587L };
    long BASE[] = {298_564_927L, 2147_484_007L};  //todo: check values beyond [0]
    Hash[] powh, preh;

    class Hash {
        long[] h = new long[NMODS];

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Hash hash = (Hash) o;

            return Arrays.equals(h, hash.h);

        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(h);
        }

        @Override
        public String toString() {
            return "Hash{" +
                    "h=" + Arrays.toString(h) +
                    '}';
        }
    }

    void init(char[] s, int n) {
        powh = new Hash[n + 1];
        preh = new Hash[n + 1];
        for (int i = 0; i < n + 1; i++) {
            powh[i] = new Hash();
            preh[i] = new Hash();
        }
        //cache powers of seeds, [0,n]
        for(int k = 0; k < NMODS; ++ k) powh[0].h[k] = 1;
        for(int i = 0; i < n; i ++) for(int k = 0; k < NMODS; ++ k) {
            powh[i+1].h[k] = powh[i].h[k] * BASE[k] % MOD[k];
        }

        //cache polynomial hashes
        preh[0] = new Hash();
        for(int i = 0; i < n; i ++) for(int k = 0; k < NMODS; ++ k) {
            preh[i+1].h[k] = (preh[i].h[k] * BASE[k] % MOD[k] + s[i]) % MOD[k];
        }
    }

    //hash of (i,j]; full= hash(0,n); 1-based (s1 ... sn)
    Hash hash(int i, int j) {
        Hash res = new Hash();
        for(int k = 0; k < NMODS; ++ k) {
            long x = preh[j].h[k] + MOD[k] - (preh[i].h[k] * powh[j - i].h[k] % MOD[k]);
            res.h[k] = (x >= MOD[k] ? x - MOD[k] : x);
        }
        return res;
    }

    //h2 + h5 = h2 * b^5 + h5
    Hash append(Hash first, Hash second, int glen) {
        Hash res = new Hash();
        for(int k = 0; k < NMODS; ++ k) {
            long x = (first.h[k] * powh[glen].h[k] % MOD[k]) + second.h[k];
            res.h[k] = (x >= MOD[k] ? x - MOD[k] : x);
        }
        return res;
    }
    
    //zero-based
    boolean substringEqual(int from1, int from2, int len) {
        ++from1; ++from2;
        return hash(from1 - 1, from1 + len - 1).equals(hash(from2 - 1, from2 + len - 1));
    }


    public static void main(String[] args) {
        String s = "aaab";
        SubstringHash sh = new SubstringHash();
        sh.init(s.toCharArray(), 4);
        System.out.println(sh.hash(0,1));   //s[1]
        System.out.println(sh.hash(1,2));   //s[2]
        System.out.println(sh.hash(3,4));   //s[4]
        System.out.println("----------");
        System.out.println(sh.hash(0,2));   //[aa]
        System.out.println(sh.hash(1,3));   //[aa]
        System.out.println(sh.hash(2,4));   //[ab]
    }
}
