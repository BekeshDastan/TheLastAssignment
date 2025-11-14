package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class SuffixKasaiTest {
    // Creating a sorted array of all suffixes
    private String[] buildExpectedSA(String s) {
        String[] suf = new String[s.length()];
        for (int i = 0; i < s.length(); i++) {
            suf[i] = s.substring(i);
        }
        Arrays.sort(suf);
        return suf;
    }
    // Calculating the LCP of two lines
    private int lcp(String a, String b) {
        int len = Math.min(a.length(), b.length());
        int i = 0;
        while (i < len && a.charAt(i) == b.charAt(i)) i++;
        return i;
    }


    @Test
    public void testShortString() {
        String s = "banana";

        int[] sa = SuffixKasai.buildSuffixArray(s);
        int[] lcp = SuffixKasai.buildLCP(s, sa);

        String[] expected = buildExpectedSA(s);
        // Checking SA
        for (int i = 0; i < sa.length; i++) {
            assertEquals(expected[i], s.substring(sa[i]));
        }
        // Checking LSP
        for (int i = 1; i < sa.length; i++) {
            assertEquals(
                    lcp(expected[i], expected[i - 1]),
                    lcp[i]
            );
        }
    }

    @Test
    public void testMediumString() {
        String s = "abracadabra";

        int[] sa = SuffixKasai.buildSuffixArray(s);
        int[] lcp = SuffixKasai.buildLCP(s, sa);

        String[] expected = buildExpectedSA(s);

        // Checking SA
        for (int i = 0; i < sa.length; i++) {
            assertEquals(expected[i], s.substring(sa[i]));
        }
        // Checking LSP
        for (int i = 1; i < sa.length; i++) {
            assertEquals(lcp(expected[i], expected[i - 1]), lcp[i]);
        }
    }

    @Test
    public void testLongString() {
        String base = "thequickbrownfoxjumpsoverthelazydog";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) sb.append(base);
        String s = sb.toString();

        int[] sa = SuffixKasai.buildSuffixArray(s);
        int[] lcp = SuffixKasai.buildLCP(s, sa);

        String[] expected = buildExpectedSA(s);
        // Checking SA
        for (int i = 0; i < sa.length; i++) {
            assertEquals(expected[i], s.substring(sa[i]));
        }
        // Checking LSP
        for (int i = 1; i < sa.length; i++) {
            assertEquals(lcp(expected[i], expected[i - 1]), lcp[i]);
        }
    }

}
