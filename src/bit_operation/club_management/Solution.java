package bit_operation.club_management;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

class Solution {
    public static final int DIVIDE_NUM = 1000000007;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/bit_operation/club_management/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            String schedule = sc.next();
            System.out.println("#" + test_case + " " + solution(schedule));
        }
    }

    private static int solution(String schedule) {
        int[][] dy = new int[schedule.length()][16];

        //첫째날 경우의 수
        for (int i = 1; i <= 15; i++) {
            if ((i & (1 << (schedule.charAt(0) - 'A'))) > 0 && (i & 1) > 0) {
                dy[0][i] = 1;
            }
        }

        //첫째날 이후 경우의 수
        for (int i = 1; i < schedule.length(); i++) {
            int keyMan = 1 << (schedule.charAt(i) - 'A');

            for (int j = 1; j <= 15; j++) {
                for (int k = 1; k <= 15; k++) {
                    if ((j & keyMan) > 0 && (j & k) > 0) {
                        dy[i][j] += dy[i - 1][k];
                        dy[i][j] %= DIVIDE_NUM;
                    }
                }
            }
        }

        return Arrays.stream(dy[schedule.length() - 1]).reduce((answer, num) -> {
            answer += num;
            answer %= DIVIDE_NUM;
            return answer;
        }).getAsInt();
    }
}