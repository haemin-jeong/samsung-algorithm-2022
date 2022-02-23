package divide_and_conquer.ad_time;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static class Time {
        int s, e;

        public Time(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    static Time[] arr;
    static int[] sum;

    static int N;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/divide_and_conquer/ad_time/2_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int L = Integer.parseInt(br.readLine());
            N = Integer.parseInt(br.readLine());
            arr = new Time[N];
            sum = new int[N];

            StringTokenizer st = null;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                arr[i] = new Time(s, e);
                if (i == 0) sum[i] = (e-s);
                else sum[i] = sum[i - 1] + (e - s);
            }

            int answer = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                int target = arr[i].s + L;
                int ub = upperBound(target);
                int temp = sum[ub - 1];
                if (i!=0) temp -= sum[i - 1];
                if (ub != N && target > arr[ub].s) temp += (target - arr[ub].s);
                answer = Math.max(answer, temp);
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }

    static int upperBound(int target) {
        int start = 0; int end = N-1; int answer =  N;

        while (start <= end) {
            int mid = (start + end)/2;

            if (arr[mid].e > target) {
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return answer;
    }

}