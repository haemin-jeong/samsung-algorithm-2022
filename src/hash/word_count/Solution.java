package hash.word_count;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class Solution {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/hash/word_count/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String b = br.readLine();
            String s = br.readLine();
            System.out.println("#" + test_case + " " + kmp(b, s));
        }
    }

    private static int[] makeTable(String pattern) {
        int patternSize = pattern.length();
        int[] table = new int[patternSize];

        int j = 0;
        for (int i = 1; i < patternSize; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                table[i] = ++j;
            }
        }

        return table;
    }

    private static int kmp(String str, String pattern) {
        int count = 0;
        int[] table = makeTable(pattern);
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }

            if (str.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    count++;
                    j = table[j];
                } else {
                    j++;
                }
            }
        }

        return count;
    }
}
