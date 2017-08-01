package strings;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SubstringHashTest {

    @Test
    public void initTest() throws Exception {
        String s = "aaab";
        SubstringHash sh = new SubstringHash();
        sh.init(s.toCharArray(), 4);
        //hashes of single letters
        assertThat(sh.hash(0, 1).h[0]).isEqualTo((int) 'a');
        assertThat(sh.hash(1, 2).h[0]).isEqualTo((int) 'a');
        assertThat(sh.hash(3, 4).h[0]).isEqualTo((int) 'b');

        //len-2 substrings
        assertThat(sh.hash(0, 2).h[0]).isEqualTo(sh.hash(1, 3).h[0]); //[aa]==[aa]
        assertThat(sh.hash(0, 2).h[0]).isNotEqualTo(sh.hash(2, 4).h[0]);    //[aa]!=[ab]
    }

    @Test
    public void otherSimpleTest() throws Exception {
        String s = "aabaa";
        SubstringHash sh = new SubstringHash();
        sh.init(s.toCharArray(), 5);
        assertThat(sh.hash(0, 2).h[0]).isEqualTo(sh.hash(3, 5).h[0]);   //[aa...]==[...aa]

        String g = "";
        for(char c : s.toCharArray()) g = c + g;
        SubstringHash sh_rev = new SubstringHash();
        sh_rev.init(g.toCharArray(), 5);

        assertThat(sh.hash(0, 5)).isEqualTo(sh_rev.hash(0, 5));
        assertThat(sh.substringEqual(0, 3, 2));
    }

}
