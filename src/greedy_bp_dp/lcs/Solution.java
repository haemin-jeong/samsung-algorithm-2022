package greedy_bp_dp.lcs;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/greedy_BP_DP/longest_common_sequence/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            String s1 = sc.next();
            String s2 = sc.next();
            System.out.println("#" + test_case + " " + LCS(s1, s2));
        }
    }

    private static int LCS(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
