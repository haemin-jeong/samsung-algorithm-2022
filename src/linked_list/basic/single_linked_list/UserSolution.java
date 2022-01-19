package linked_list.basic.single_linked_list;

class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class UserSolution {

    private final static int MAX_NODE = 10000;

    private Node[] node = new Node[MAX_NODE];
    private int nodeCnt = 0;
    private Node head;

    public Node getNode(int data) {
        node[nodeCnt] = new Node(data);
        return node[nodeCnt++];
    }

    public void init() {
        nodeCnt = 0;
        head = getNode(-1);
    }

    public void addNode2Head(int data) {
        Node node = getNode(data);
        node.next = head.next;
        head.next = node;
    }

    public void addNode2Tail(int data) {
        Node node = head;
        Node newNode = getNode(data);

        while (node.next != null) {
            node = node.next;
        }

        node.next = newNode;
    }

    public void addNode2Num(int data, int num) {
        Node node = head;
        Node newNode = getNode(data);

        int count = 1;
        while (count < num) {
            node = node.next;
            count++;
        }

        newNode.next = node.next;
        node.next = newNode;
    }

    public void removeNode(int data) {
        Node node = head;

        while (node.next != null) {
            if (node.next.data == data) {
                Node removeNode = node.next;
                node.next = removeNode.next;
                return;
            }

            node = node.next;
        }
    }

    public int getList(int[] output) {
        Node node = head;

        int count = 0;
        while (node.next != null) {
            output[count++] = node.next.data;
            node = node.next;
        }

        return count;
    }
}
