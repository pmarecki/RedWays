package math;

public class Mod {
    int[] getModInverse(int MX, int mod) {
        int[] inv = new int[MX];
        inv[1] = 1;
        for(int i = 2 ; i < MX ; ++i){
            long x = (((1L * (-(mod / i)) * inv[mod % i]) % mod) + mod) % mod;
            inv[i] = (int)x;
        }
        return inv;
    }
}
