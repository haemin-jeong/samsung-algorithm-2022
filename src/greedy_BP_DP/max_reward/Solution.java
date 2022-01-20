package greedy_BP_DP.max_reward;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
    private static int[] numbers;
    private static int count = 0;
    private static int answer = Integer.MIN_VALUE;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/greedy_BP_DP/max_reward/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            String s = sc.next();
            int len = s.length();
            int n = Integer.parseInt(s);
            numbers = new int[len];

            for (int i = len-1; i >= 0; i--){
                numbers[i] = n % 10;
                n /= 10;
            }

            count = sc.nextInt();
            dfs(0, 0);
            System.out.println("#" + test_case + " " + answer);

            answer = Integer.MIN_VALUE;
        }
    }

    private static void dfs(int level, int start) {
        if (level == count) {
            answer = Math.max(answer, sum(numbers));
        } else {
            for (int i = start; i < numbers.length; i++) {
                for (int j = i+1; j < numbers.length; j++) {
                    swap(numbers, i, j);
                    dfs(level+1, i);
                    swap(numbers, i, j);
                }
            }
        }
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    private static int sum(int[] arr) {
        int sum = 0;
        int multiple = 1;
        for (int i = arr.length-1; i >= 0; i--) {
            sum += arr[i] * multiple;
            multiple *= 10;
        }

        return sum;
    }
}
