package heap.partial_sort;

public class UserSolution {

    private final int MAX_USER = 100000;
    private User[] heap;
    private int heapSize;

    static class User {
        int uID;
        int income;

        public User(int uID, int income) {
            this.uID = uID;
            this.income = income;
        }
    }

    public void init() {
        heap = new User[MAX_USER];
        heapSize = 0;
    }

    public void addUser(int uID, int income) {
        heap[heapSize] = new User(uID, income);

        int cur = heapSize;
        while(cur > 0) {
            if (heap[cur].income < heap[(cur - 1) / 2].income) {
                break;
            }

            if (heap[cur].income == heap[(cur-1)/2].income && (heap[cur].uID > heap[(cur - 1) / 2].uID)) {
                break;
            }

            User temp = heap[cur];
            heap[cur] = heap[(cur - 1) / 2];
            heap[(cur - 1) / 2] = temp;
            cur = (cur - 1) / 2;
        }

        heapSize++;
    }

    int getTop10(int[] result) {
        int count = 0;
        User[] removeUser = new User[10];

        while (heapSize > 0 && count < 10) {
            removeUser[count++] = heap[0];
            heapSize--;
            heap[0] = heap[heapSize];

            int current = 0;
            while (current * 2 + 1 < heapSize) {
                int child;
                if (current * 2 + 2 == heapSize) {
                    child = current * 2 + 1;
                } else {
                    if (heap[current * 2 + 1].income > heap[current * 2 + 2].income) {
                        child = current * 2 + 1;
                    } else if (heap[current * 2 + 1].income == heap[current * 2 + 2].income) {
                        child = heap[current * 2 + 1].uID < heap[current * 2 + 2].uID ? current * 2 + 1 : current * 2 + 2;
                    } else {
                        child = current * 2 + 2;
                    }
                }

                if (heap[current].income > heap[child].income) {
                    break;
                }

                User temp = heap[current];
                heap[current] = heap[child];
                heap[child] = temp;
                current = child;
            }
        }

        for (int i = 0; i < count; i++) {
            result[i] = removeUser[i].uID;
            addUser(removeUser[i].uID, removeUser[i].income);
        }

        return count;
    }
}