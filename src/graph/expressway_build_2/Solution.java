package graph.expressway_build_2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    static class Edge implements Comparable<Edge> {
        int v1;
        int v2;
        int cost;

        public Edge(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    private static int[] unf;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/graph/expressway_build_2/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            int m = Integer.parseInt(br.readLine());
            int answer = 0;
            List<Edge> list = new ArrayList<>();

            unf = new int[n + 1];
            for (int i = 1; i <= n; i++) unf[i] = i;

            StringTokenizer st = null;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                list.add(new Edge(v1, v2, cost));
            }

            Collections.sort(list);

            int edgeCount = 0;
            for (Edge edge : list) {
                int f1 = find(edge.v1);
                int f2 = find(edge.v2);

                if (f1 != f2) {
                    union(edge.v1, edge.v2);
                    answer += edge.cost;
                    edgeCount++;
                }

                if (edgeCount == n-1) {
                    break;
                }
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static int find(int v) {
        if (v == unf[v]) return v;
        else {
            return unf[v] = find(unf[v]);
        }
    }

    private static void union(int v1, int v2) {
        int fv1 = find(v1);
        int fv2 = find(v2);
        if (fv1 != fv2) unf[fv1] = fv2;
    }
}