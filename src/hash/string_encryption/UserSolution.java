package hash.string_encryption;


public class UserSolution {

    static class Node {
        int idx;
        Node next, prev;

        public Node(int data) {
            this.idx = data;
        }
    }

    final int MAX = 20011;

    Node[] table = new Node[MAX];
    Node[] tails = new Node[MAX];
    char[] str = new char[50000];
    int n;

    void add(int key, int idx) {
        Node cur = table[key];
        Node tail = tails[key];
        Node newNode = new Node(idx);

        while (cur.next != tail) {
            if (cur.next.idx > idx) {
                newNode.next = cur.next;
                newNode.next.prev = newNode;
                cur.next = newNode;
                newNode.prev = cur;
                return;
            }
            cur = cur.next;
        }

        newNode.next = tail;
        tail.prev = newNode;
        cur.next = newNode;
        newNode.prev = cur;
    }

    void remove(int key, int idx) {
        Node cur = table[key];
        Node tail = tails[key];

        while (cur.next != tail) {
            cur = cur.next;

            if (cur.idx == idx) {
                cur.prev.next = cur.next;
                cur.next.prev = cur.prev;
                return;
            }
        }
    }

    public UserSolution() {
        for (int i = 0; i < MAX; i++) {
            table[i] = new Node(-1);
            tails[i] = new Node(-1);
        }
    }

    void init(int N, char[] initString) {
        n = N;

        for (int i = 0; i < MAX; i++) {
            table[i].next = tails[i];
            tails[i].prev = table[i];
        }

        for (int i = 0; i < N; i++) str[i] = initString[i];

        for (int i = 0; i <= N - 3; i++) add(getKey(str, i), i);
    }

    int change(char[] stringA, char[] stringB) {
        int key = getKey(stringA, 0);
        int changeCount = 0;
        Node cur = table[key];
        Node tail = tails[key];
        int idx;

        while (cur.next != tail) {
            cur = cur.next;
            
            boolean conflict = false;
            int j = cur.idx;
            for (int i = 0; i < 3; i++) {
                if (str[j++] != stringA[i]) {
                    conflict = true;
                    break;
                };
            }

            if (conflict) continue;

            changeCount++;

            idx = cur.idx;
            cur = cur.prev;

            for (int i = idx - 2; i <= idx + 2; i++) {
                if (i < 0 || i > n-3) continue;
                remove(getKey(str, i), i);
            }

            j = 0;
            for (int i = idx; i < idx + 3; i++) str[i] = stringB[j++];

            for (int i = idx - 2; i <= idx + 2; i++) {
                if (i < 0 || i > n-3) continue;
                add(getKey(str, i), i);
            }

            while (cur.next.idx < idx+3 && cur.next != tail) cur = cur.next;
        }

        return changeCount;
    }

    void result(char[] ret) {
        System.arraycopy(str, 0, ret, 0, n);
    }

    int getKey(char[] arr, int start) {
        long hash = 5381L;

        for (int i = start; i < start + 3; i++) {
            hash = (((hash << 5) + hash) + arr[i]);
        }
        return (int)(((hash % MAX) + MAX) % MAX);
    }
}
