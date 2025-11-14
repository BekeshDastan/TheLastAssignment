package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class SuffixKasai {
    // Build suffix array using doubling + sorting
    public static int[] buildSuffixArray(String s) {
        int n = s.length();
        int[] sa = new int[n];
        int[] ranks = new int[n];
        int[] tmp = new int[n];


        for (int i = 0; i < n; i++) {
            sa[i] = i;
            ranks[i] = s.charAt(i);
        }
        // Doubling loop
        for (int k = 1; k < n; k <<= 1) {
            final int K = k;
            Comparator<Integer> cmp = new Comparator<Integer>() {
                public int compare(Integer i, Integer j) {
                    if (ranks[i] != ranks[j]) return Integer.compare(ranks[i], ranks[j]);
                    int ri = i + K < n ? ranks[i + K] : -1;
                    int rj = j + K < n ? ranks[j + K] : -1;
                    return Integer.compare(ri, rj);
                }
            };


            Integer[] saBoxed = new Integer[n];
            for (int i = 0; i < n; i++) saBoxed[i] = sa[i];
            Arrays.sort(saBoxed, cmp);
            for (int i = 0; i < n; i++) sa[i] = saBoxed[i];


            tmp[sa[0]] = 0;
            for (int i = 1; i < n; i++) {
                tmp[sa[i]] = tmp[sa[i-1]] + (cmp.compare(sa[i-1], sa[i]) < 0 ? 1 : 0);
            }


            System.arraycopy(tmp, 0, ranks, 0, n);

            // Stop if all ranks are unique
            if (ranks[sa[n-1]] == n-1) break;
        }


        return sa;
    }
    // Build LCP array using Kasai's algorithm
    public static int[] buildLCP(String s, int[] sa) {
        int n = s.length();
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) rank[sa[i]] = i;


        int[] lcp = new int[n];
        int h = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] > 0) {
                int j = sa[rank[i] - 1];
                while (i + h < n && j + h < n && s.charAt(i + h) == s.charAt(j + h)) h++;
                lcp[rank[i]] = h;
                if (h > 0) h--;
            } else {
                lcp[rank[i]] = 0;
            }
        }
        return lcp;
    }
}
