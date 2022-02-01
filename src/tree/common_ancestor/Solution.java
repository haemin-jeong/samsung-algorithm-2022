package tree.common_ancestor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class Solution {

    static class Node {
        int num;
        Node parent;
        List<Node> children = new LinkedList<>();

        public Node(int num) {
            this.num = num;
        }
    }

    private static int[] depth;
    private static Node[] treeNode;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/tree/common_ancestor/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            treeNode = new Node[v + 1];
            depth = new int[v + 1];

            for (int i = 1; i <= v; i++) treeNode[i] = new Node(i);

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < e; i++) {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                treeNode[parent].children.add(treeNode[child]);
                treeNode[child].parent = treeNode[parent];
            }

            calculateNodeDepth();
            int lca = lca(n1, n2);
            System.out.println("#" + test_case + " " + lca + " " + getNodeCount(treeNode[lca]));
        }
    }

    private static int getNodeCount(Node node) {
        Queue<Node> q = new LinkedList<>();
        int count = 0;
        q.offer(node);
        count++;

        while (!q.isEmpty()) {
            Node parent = q.poll();
            count += parent.children.size();

            for (Node child : parent.children) {
                q.offer(child);
            }
        }

        return count;
    }

    private static int lca(int a, int b) {
        while (depth[a] != depth[b]) {
            if (depth[a] > depth[b]) {
                a = treeNode[a].parent.num;
            } else {
                b = treeNode[b].parent.num;
            }
        }

        while (a != b) {
            a = treeNode[a].parent.num;
            b = treeNode[b].parent.num;
        }

        return a;
    }

    private static void calculateNodeDepth() {
        Queue<Node> q = new LinkedList<>();

        q.offer(treeNode[1]);

        while (!q.isEmpty()) {
            Node parent = q.poll();

            for (Node child : parent.children) {
                q.offer(child);
                depth[child.num] = depth[parent.num] + 1;
            }
        }
    }
}