package greedy_BP_DP.easy_change;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

class Solution {
    private static final int[] coins = {10, 50, 100, 500, 1000, 5000, 10000, 50000};
    private static int[] coinCount;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/greedy_BP_DP/easy_change/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        coinCount = new int[coins.length];

        for (int test_case = 1; test_case <= T; test_case++) {
            int money = sc.nextInt();
            System.out.println("#" + test_case);

            int[] coinCount = solution(money);

            for (int i = coinCount.length - 1; i >= 0; i--) {
                System.out.print(coinCount[i] + " ");
            }
            System.out.println();
        }
    }

    private static int[] solution(int money) {
        Arrays.fill(coinCount, 0);

        for (int j = coins.length - 1; j >= 0; j--) {
            coinCount[j] += money / coins[j];
            money %= coins[j];
        }

        return coinCount;
    }
}