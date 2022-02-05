package heap.middle_value;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/*
1. a를 center 값으로 한다.
2. center 값보다 작은 값은 최대 힙, 큰 값은 최소 힙에 넣는다.
3.
- 최소힙 사이즈 == 최대힙 사이즈 -> 같으면 중간 값은 center 값.
- 최소힙 사이즈 > 최대힙 사이즈 -> 최대힙에 center 값을 넣고, 최소힙에서 하나 꺼낸 값이 중간 값
- 최소힙 사이즈 < 최대힙 사이즈 -> 최소힙에 center 값으 넣고, 최대힙에서 하나 꺼낸 값이 중간 값.
 */
class Solution {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/heap/middle_value/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int test_case = 1; test_case <= T; test_case++) {
            int answer = 0;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int center = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());

                if (n1 > center) {
                    minHeap.offer(n1);
                } else {
                    maxHeap.offer(n1);
                }

                if (n2 > center) {
                    minHeap.offer(n2);
                } else {
                    maxHeap.offer(n2);
                }

                if (minHeap.size() < maxHeap.size()) {
                    minHeap.offer(center);
                    center = maxHeap.poll();
                } else if (minHeap.size() > maxHeap.size()) {
                    maxHeap.offer(center);
                    center = minHeap.poll();
                }

                answer = (answer + center) % 20171109;

            }

            minHeap.clear();
            maxHeap.clear();

            System.out.println("#" + test_case + " " + answer);
        }
    }
}