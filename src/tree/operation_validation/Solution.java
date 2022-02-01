package tree.operation_validation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static class Node {
        char data;
        Node rightChild;
        Node leftChild;
    }

    private static int answer;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/tree/operation_validation/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int test_case = 1; test_case <= 10; test_case++) {
            int n = Integer.parseInt(br.readLine());
            answer = 1;
            Node[] treeNode = new Node[n + 1];

            for (int i = 1; i <= n; i++) treeNode[i] = new Node();

            StringTokenizer st = null;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                int treeNum = Integer.parseInt(st.nextToken());
                char data = st.nextToken().charAt(0);

                treeNode[treeNum].data = data;

                if (st.hasMoreTokens()) {
                    int leftChild = Integer.parseInt(st.nextToken());
                    treeNode[treeNum].leftChild = treeNode[leftChild];
                }

                if (st.hasMoreTokens()) {
                    int rightChild = Integer.parseInt(st.nextToken());
                    treeNode[treeNum].rightChild = treeNode[rightChild];
                }
            }

            postOrderTraversal(treeNode[1]);
            sb.append("#").append(test_case).append(" ");
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    private static void postOrderTraversal(Node node) {
        if (answer == 0) return;

        if (node.leftChild == null || node.rightChild == null) {
            return;
        }

        postOrderTraversal(node.leftChild);
        postOrderTraversal(node.rightChild);

        if (!Character.isDigit(node.leftChild.data) || !Character.isDigit(node.rightChild.data)) {
            answer = 0;
            return;
        }

        node.data = '1'; //단순히 연산자가 아닌 숫자임을 나타내기 위한 값
    }
}
