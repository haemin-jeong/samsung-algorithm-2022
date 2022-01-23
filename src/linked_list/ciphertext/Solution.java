package linked_list.ciphertext;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
        }
    }

    private static Node head = new Node(-1);
    private static Node tail = new Node(-1);

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/linked_list/ciphertext/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= 10; test_case++) {
            int n = Integer.parseInt(br.readLine());
            init();
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                Node node = new Node(Integer.parseInt(st.nextToken()));

                node.prev = tail.prev;
                node.prev.next = node;
                node.next = tail;
                tail.prev = node;
            }

            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int x, y;
            int[] s;

            for (int i = 0; i < n; i++) {
                String command = st.nextToken();

                if (command.equals("I")) {
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    s = new int[y];

                    for (int j = 0; j < y; j++) {
                        s[j] = Integer.parseInt(st.nextToken());
                    }

                    insert(x, y, s);
                } else if (command.equals("D")) {
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    delete(x, y);
                } else if (command.equals("A")) {
                    y = Integer.parseInt(st.nextToken());
                    s = new int[y];

                    for (int j = 0; j < y; j++) {
                        s[j] = Integer.parseInt(st.nextToken());
                    }

                    append(y, s);
                }
            }

            Node node = head;
            sb.append("#").append(test_case);
            for (int i = 0; i < 10; i++) {
                node = node.next;
                sb.append(" ").append(node.data);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void init() {
        head.next = tail;
        tail.prev = head;
    }

    private static void insert(int x, int y, int[] s) {
        Node node = head;

        for (int i = 0; i < x; i++) {
            node = node.next;
        }

        for (int i = s.length - 1; i >= 0; i--) {
            Node temp = new Node(s[i]);

            temp.next = node.next;
            node.next.prev = temp;
            node.next = temp;
            temp.prev = node;
        }
    }

    private static void delete(int x, int y) {
        Node deleteStartNode = head;

        for (int i = 0; i < x; i++) {
            deleteStartNode = deleteStartNode.next;
        }

        Node deleteEndNode = deleteStartNode;

        for (int i = 0; i < y; i++) {
            deleteEndNode = deleteEndNode.next;
        }

        deleteStartNode.next = deleteEndNode.next;
        deleteEndNode.next.prev = deleteStartNode;
    }

    private static void append(int y, int[] s) {
        for (int i = 0; i < y; i++) {
            Node node = new Node(s[i]);

            node.prev = tail.prev;
            node.prev.next = node;
            tail.prev = node;
            node.next = tail;
        }
    }
}