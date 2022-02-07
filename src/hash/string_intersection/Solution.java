package hash.string_intersection;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Solution {
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/hash/string_intersection/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        Map<String, Object> map = new HashMap<>();

        for (int test_case = 1; test_case <= T; test_case++) {
            int count = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                map.put(st.nextToken(), null);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                if (map.containsKey(st.nextToken())) {
                    count++;
                }
            }

            map.clear();
            System.out.println("#" + test_case + " " + count);
        }
    }
}
