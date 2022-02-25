package practice.restaurant_value;

import java.util.Arrays;

class UserSolution {
    static class Node {
        int data;
        Node prev, next;

        public Node(int data) {
            this.data = data;
        }
    }

    static class Queue {
        Node head = new Node(-1);
        Node tail = new Node(-1);
        int size = 0;

        public Queue() {
            head.next = tail;
            tail.prev = head;
        }

        void add(int data) {
            Node newNode = new Node(data);
            newNode.next = head.next; newNode.next.prev = newNode;
            head.next = newNode; newNode.prev = head;
            size++;
        }

        int remove() {
            Node removeNode = tail.prev;
            tail.prev = removeNode.prev;
            removeNode.prev.next = tail;
            size--;
            return removeNode.data;
        }

        boolean isEmpty() {
            return size == 0;
        }
    }

    int[][] graph = new int[51][51];
    int[][] restTop3 = new int[51][3];
    int[] subStrMax = new int[15001001];
    int[] restCity = new int[15001001];
    int[] restScore = new int[15001001];

    int[] max = new int[3];
    int[] dis = new int[51];
    boolean[] visited = new boolean[51];
    Queue q = new Queue();

    int getHash(char[] mStr) {
        int hash = 0;
        for (int i = 0; mStr[i] != '\0'; i++) {
            hash *= 27;
            hash += (mStr[i] - 'a' + 1);
        }
        return hash;
    }

    int getLen(char[] arr) {
        int len = 0;
        while (arr[len] != '\0') len++;
        return len;
    }

    public void init(int N, int M, int mRoads[][]) {
        Arrays.fill(subStrMax, 0);
        Arrays.fill(restScore, 0);

        for (int i = 0; i < 51; i++) {
            Arrays.fill(restTop3[i], 0);
            Arrays.fill(graph[i], 0);
        }

        for (int i = 0; i < M; i++) {
            int v1 = mRoads[i][0]; int v2 = mRoads[i][1];
            graph[v1][v2] = 1; graph[v2][v1] = 1;
        }
    }

    public void addRestaurant(int mCityID, char mName[]) {
        int hash = getHash(mName);
        restCity[hash] = mCityID;
    }

    public void addValue(char mName[], int mScore) {
        int len = getLen(mName);
        int hash = getHash(mName);
        restScore[hash] += mScore;

        for (int i = 0; i < len; i++) {
            int tempHash = 0;
            for (int j = i; j < len; j++) {
                tempHash *= 27;
                tempHash += (mName[j] - 'a' + 1);
                subStrMax[tempHash] = Math.max(subStrMax[tempHash], restScore[hash]);
            }
        }

        int cityId = restCity[hash];

        for (int i = 0; i < 3; i++) {
            if (restTop3[cityId][i] == hash) {
                for (int j = i; j < 2; j++) {
                    restTop3[cityId][j] = restTop3[cityId][j + 1];
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (restScore[restTop3[cityId][i]] < restScore[hash]) {
                for (int j = 2; j > i; j--) restTop3[cityId][j] = restTop3[cityId][j - 1];
                restTop3[cityId][i] = hash;
                break;
            }
        }
    }

    public int bestValue(char mStr[]) {
        int hash = getHash(mStr);
        return subStrMax[hash];
    }

    public int regionalValue(int mCityID, int mDist) {
        for (int i = 0; i < 3; i++) max[i] = restScore[restTop3[mCityID][i]];

        q.add(mCityID);
        visited[mCityID] = true;

        while (!q.isEmpty()) {
            int curCity = q.remove();

            if (dis[curCity] <= mDist) {
                for (int i = 1; i <= 50; i++) {
                    if (graph[curCity][i] == 1 && !visited[i]) {
                        visited[i] = true;
                        dis[i] = dis[curCity] + 1;
                        q.add(i);

                        if (dis[i] <= mDist) {
                            for (int rest : restTop3[i]) {
                                for (int j = 0; j < 3; j++) {
                                    if (restScore[rest] > max[j]) {
                                        for (int k = 2; k > j; k--) max[k] = max[k - 1];
                                        max[j] = restScore[rest];
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        int sum = 0;
        for (int i : max) sum += i;

        Arrays.fill(visited, false);
        Arrays.fill(dis, 0);

        return sum;
    }
}
