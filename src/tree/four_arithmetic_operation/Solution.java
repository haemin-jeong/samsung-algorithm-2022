package tree.four_arithmetic_operation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static class Node {
        String data;
        Node leftChild;
        Node rightChild;
    }

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/tree/four_arithmetic_operation/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= 10; test_case++) {
            int n = Integer.parseInt(br.readLine());
            Node[] treeNode = new Node[n + 1];

            for (int i = 1; i <= n; i++) treeNode[i] = new Node();

            StringTokenizer st = null;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                int nodeNum = Integer.parseInt(st.nextToken());
                String data = st.nextToken();

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

            int answer = (int) postOrderTraversal(treeNode[1]);
            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static double postOrderTraversal(Node node) {
        if (node.leftChild == null && node.rightChild == null) {
            return Double.parseDouble(node.data);
        }

        double n1 = postOrderTraversal(node.leftChild);
        double n2 = postOrderTraversal(node.rightChild);
        String operation = node.data;

        if (operation.equals("+")) return n1 + n2;
        else if (operation.equals("-")) return n1 - n2;
        else if (operation.equals("*")) return n1 * n2;
        else return n1 / n2;
    }
}

