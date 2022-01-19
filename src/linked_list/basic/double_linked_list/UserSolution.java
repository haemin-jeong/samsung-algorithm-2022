package linked_list.basic.double_linked_list;

class Node {
    public int data;
    public Node prev;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class UserSolution {

    private final static int MAX_NODE = 10000;

    private Node[] node = new Node[MAX_NODE];
    private int nodeCnt = 0;
    private Node head, tail;

    public Node getNode(int data) {
        node[nodeCnt] = new Node(data);
        return node[nodeCnt++];
    }

    public void init() {
        nodeCnt = 0;
        head = getNode(-1);
        tail = getNode(-1);
        head.next = tail;
        tail.prev = head;
    }

    public void addNode2Head(int data) {
        Node newNode = getNode(data);
        newNode.next = head.next;
        newNode.next.prev = newNode;
        newNode.prev = head;
        head.next = newNode;
    }

    public void addNode2Tail(int data) {
        Node newNode = getNode(data);
        newNode.prev = tail.prev;
        newNode.prev.next = newNode;
        newNode.next = tail;
        tail.prev = newNode;
    }

    public void addNode2Num(int data, int num) {
        Node prevNode = head;
        Node newNode = getNode(data);

        int count = 1;
        while (count < num) {
            prevNode = prevNode.next;
            count++;
        }

        newNode.next = prevNode.next;
        newNode.next.prev = newNode;
        prevNode.next = newNode;
        newNode.prev = prevNode;
    }

    public int findNode(int data) {
        Node node = head;

        int index = 1;
        while (node.next.data != data) {
            node = node.next;
            index++;
        }

        return index;
    }

    public void removeNode(int data) {
        Node node = head;

        while (node.next != tail) {
            if (node.next.data == data) {
                Node removeNode = node.next;
                node.next = removeNode.next;
                node.next.prev = node;
                return;
            }

            node = node.next;
        }
    }

    public int getList(int[] output) {
        int count = 0;
        Node node = head;

        while (node.next != tail) {
            output[count++] = node.next.data;
            node = node.next;
        }

        return count;
    }

    public int getReversedList(int[] output) {
        int count = 0;
        Node node = tail;

        while (node.prev != head) {
            output[count++] = node.prev.data;
            node = node.prev;
        }

        return count;
    }
}