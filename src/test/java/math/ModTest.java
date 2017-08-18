package math;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ModTest {

    @Test
    public void simpleModInverseTest() throws Exception {
        Mod mmod = new Mod();
        int mx = 10000;
        int mod = (int) (1e9 + 7);
        int[] inv = mmod.getModInverse(mx, mod);

        for (int i = 1; i < mx; i++) {
            assertThat((long)i * inv[i] % mod).isEqualTo(1);
        }

    }
}
