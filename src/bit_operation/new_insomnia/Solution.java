package bit_operation.new_insomnia;

import java.io.FileInputStream;
import java.util.Scanner;

class Solution {
    //1111111111
    private static final int answer = 1023;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/bit_operation/new_insomnia/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();
            System.out.println("#" + test_case + " " + solution(n));

        }
    }

    private static int solution(int n) {
        int check = 0;
        int count = 0;

        while (check != answer) {
            int num = n*++count;

            while (num > 0) {
                int temp = 1 << num%10;
                num /= 10;
                check = check|temp;
            }
        }

        return n*count;
    }
}