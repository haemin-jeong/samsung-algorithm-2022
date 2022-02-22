package heap.heap;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/heap/heap/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            sb.append("#").append(test_case);
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

            for (int i = 0; i < n; i++) {
                String input = br.readLine();
                char command = input.charAt(0);

                if (command == '1') {
                    int value = Integer.parseInt(input.substring(2));
                    pq.offer(value);
                } else {
                    Integer removeValue = pq.poll();

                    if (removeValue == null) {
                        sb.append(" ").append(-1);
                    } else {
                        sb.append(" ").append(removeValue);
                    }
                }
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}