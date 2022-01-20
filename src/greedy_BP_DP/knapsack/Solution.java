package greedy_BP_DP.knapsack;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/greedy_BP_DP/knapsack/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] dy = new int[k+1];

            for (int i = 0; i < n; i++) {
                int v = sc.nextInt();
                int c = sc.nextInt();

                for (int j = k; j >= v; j--) {
                    dy[j] = Math.max(dy[j], dy[j-v] + c);
                }
            }

            System.out.println("#" + test_case + " " + dy[k]);
        }
    }
}