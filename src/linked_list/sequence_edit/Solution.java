package linked_list.sequence_edit;

import java.util.Scanner;

class Solution {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    static class LinkedList {
        Node head;

        public LinkedList(int[] arr) {
            head = new Node(-1);
            Node firstNode = new Node(arr[0]);
            Node prevNode = firstNode;

            for (int i = 1; i < arr.length; i++) {
                prevNode.next = new Node(arr[i]);
                prevNode = prevNode.next;
            }

            head.next = firstNode;
        }

        public void addNodePos(int pos, int data) {
            Node newNode = new Node(data);
            Node prevNode = head;

            int count = 0;
            while(count < pos) {
                prevNode = prevNode.next;
                count++;
            }

            newNode.next = prevNode.next;
            prevNode.next = newNode;
        }

        public void deleteNode(int pos) {
            Node prevNode = head;

            int count = 0;
            while(count < pos) {
                prevNode = prevNode.next;
                count++;
            }

            Node deleteNode = prevNode.next;
            prevNode.next = deleteNode.next;
        }

        public void changeNodeData(int pos, int data) {
            Node curNode = head;

            int count = -1;
            while(count < pos) {
                curNode = curNode.next;
                count++;
            }

            curNode.data = data;
        }

        public int findData(int pos) {
            Node curNode = head;

            int count = -1;
            while(curNode != null) {
                if (count == pos) {
                    return curNode.data;
                }
                curNode = curNode.next;
                count++;
            }

            return -1;
        }
    }

    private static LinkedList sequence;

    public static void main(String args[]) throws Exception {
//        System.setIn(new FileInputStream("src/linked_list/sequence_edit/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int l = sc.nextInt();

            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            LinkedList sequence = new LinkedList(arr);

            for (int i = 0; i < m; i++) {
                String command = sc.next();
                int index = sc.nextInt();

                if (command.equals("I")) {
                    int data = sc.nextInt();
                    sequence.addNodePos(index, data);
                } else if (command.equals("D")) {
                    sequence.deleteNode(index);
                } else if (command.equals("C")) {
                    int data = sc.nextInt();
                    sequence.changeNodeData(index, data);
                }
            }

            System.out.println("#" + test_case + " " + sequence.findData(l));
        }
    }
}