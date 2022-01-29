package graph.hanaro;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Solution {

    static class Edge implements Comparable<Edge>{
        int v1;
        int v2;
        long length;

        public Edge(int v1, int v2, long length) {
            this.v1 = v1;
            this.v2 = v2;
            this.length = length;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.length < o.length) {
                return -1;
            } else if (this.length > o.length) {
                return 1;
            } else {
                return 0;
            }
        }

    }

    private static int[] x, y;
    private static double e;
    private static List<Edge> list = new ArrayList<>();
    private static int[] unf;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/graph/hanaro/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            unf = new int[n];
            x = new int[n];
            y = new int[n];

            for (int i = 0; i < n; i++) {
                unf[i] = i;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                x[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                y[i] = Integer.parseInt(st.nextToken());
            }

            e = Double.parseDouble(br.readLine());

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    long length = (long)(Math.pow(x[i] - x[j], 2) + Math.pow(y[i] - y[j], 2));
                    list.add(new Edge(i, j, length));
                }
            }

            Collections.sort(list);

            int edgeCount = 0;
            long answer = 0;
            for (Edge edge : list) {
                int fv1 = find(edge.v1);
                int fv2 = find(edge.v2);

                if (fv1 != fv2) {
                    union(edge.v1, edge.v2);
                    answer += edge.length;
                    edgeCount++;
                }

                if (edgeCount == (n-1)) break;
            }

            list.clear();
            System.out.println("#" + test_case + " " + Math.round(e*answer));
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
