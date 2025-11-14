package org.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String shortStr = "banana";
        String mediumStr = "abracadabra";

        String base = "thequickbrownfoxjumpsoverthelazydog";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) sb.append(base);
        String longStr = sb.toString();
        // Run tests for different strings
        runTest("SHORT", shortStr);
        runTest("MEDIUM", mediumStr);
        runTest("LONG", longStr);
    }
    public static void runTest(String label, String s) {
        System.out.println(  label + " (n=" + s.length() + ")");

        // Measure time
        long t1 = System.nanoTime();
        int[] sa = SuffixKasai.buildSuffixArray(s);
        long t2 = System.nanoTime();
        int[] lcp = SuffixKasai.buildLCP(s, sa);
        long t3 = System.nanoTime();

        long saTime = t2 - t1;
        long lcpTime = t3 - t2;
        // Print results
        System.out.println("Suffix Array time: " + saTime / 1_000_000.0 + " ms");
        System.out.println("Kasai LCP time:   " + lcpTime / 1_000_000.0 + " ms");
        System.out.println();

        System.out.println("Suffix Array: " + Arrays.toString(sa));
        System.out.println("LCP Array:    " + Arrays.toString(lcp));
        System.out.println();
    }
}
