package strings;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class AlgoZTest {

    @Test
    public void initTest() {
        Integer[] goodPos = AlgoZ.findOccurances("BABA", "A");
        assertThat(goodPos).isEqualTo(new int[]{1, 3});

        goodPos = AlgoZ.findOccurances("a10b1a12b1","a12b1");
        assertThat(goodPos).isEqualTo(new int[]{5});
    }
}