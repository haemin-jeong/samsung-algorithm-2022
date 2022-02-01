package tree.inorder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static class Node {
        Node leftChild;
        Node rightChild;
        char data;
    }

    private static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/tree/inorder/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= 10; test_case++) {
            int n = Integer.parseInt(br.readLine());
            Node[] treeNode = new Node[n+1];

            for (int i = 1; i <= n; i++) treeNode[i] = new Node();

            StringTokenizer st = null;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                int nodeNum = Integer.parseInt(st.nextToken());
                char data = st.nextToken().charAt(0);

                treeNode[nodeNum].data = data;

                if (st.hasMoreTokens()) {
                    int leftChild = Integer.parseInt(st.nextToken());
                    treeNode[nodeNum].leftChild = treeNode[leftChild];
                }

                if (st.hasMoreTokens()) {
                    int rightChild = Integer.parseInt(st.nextToken());
                    treeNode[nodeNum].rightChild = treeNode[rightChild];
                }
            }

            sb.append("#").append(test_case).append(" ");
            inorderTraversal(treeNode[1]);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.leftChild);
            sb.append(node.data);
            inorderTraversal(node.rightChild);
        }
    }
}