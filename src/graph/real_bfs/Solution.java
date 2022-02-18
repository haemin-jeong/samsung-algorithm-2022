package graph.real_bfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    static int MAX = 100000;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[][] dy = new int[MAX+1][19];
    static boolean[] visited = new boolean[MAX+1];
    static int[] depth = new int[MAX + 1];
    static int n;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/graph/real_bfs/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i <= MAX; i++) {
            graph.add(new ArrayList<>());
        }

        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());
            for (int i = 1; i <= n; i++) {
                graph.get(i).clear();
                Arrays.fill(dy[i], 0);
            }
            Arrays.fill(visited,0, n+1, false);
            Arrays.fill(depth, 0, n+1, 0);

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 2; i <= n; i++) {
                int parent = Integer.parseInt(st.nextToken());
                dy[i][0] = parent;
                depth[i] = depth[parent]+1;
                graph.get(i).add(parent);
                graph.get(parent).add(i);
            }

            setParent();
            System.out.println("#" + test_case + " " + bfs());
        }
    }

    private static long bfs() {
        long answer = 0L;
        q.offer(1);
        int prev = 1;
        visited[1] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i = 0; i < graph.get(now).size(); i++) {
                int value = graph.get(now).get(i);
                if (!visited[value]) {
                    visited[value] = true;
                    q.offer(value);

                    int lca = lca(prev, value);
                    answer += (depth[prev] - depth[lca]);
                    answer += (depth[value] - depth[lca]);

                    prev = value;
                }
            }
        }
        q.clear();
        return answer;
    }

    private static int lca(int a, int b) {
        //depth[a] > depth[b] 형태로 만든다.
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        //두 노드의 깊이 맞추기
        for (int i = 18; i >= 0; i--) {
            //두 노드의 깊이 차이가 2^i 이상이면 a를 a의 2^i 번째 조상으로 교체한다.
            if (depth[a] - depth[b] >= (1 << i)) {
                a = dy[a][i];
            }
        }

        //LCA 찾기
        if (a == b) return a;

        for (int i = 18; i >= 0; i--) {
            if (dy[a][i] != dy[b][i]) {
                a = dy[a][i];
                b = dy[b][i];
            }
        }

        return dy[a][0];
    }

    private static void setParent() {
        for (int i = 1; i <= 18; i++) {
            for (int j = 1; j <= n; j++) {
                dy[j][i] = dy[dy[j][i - 1]][i - 1];
            }
        }
    }
}
