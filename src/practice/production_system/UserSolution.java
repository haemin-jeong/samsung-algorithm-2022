package practice.production_system;

import java.util.*;

class UserSolution {

    static class Product {
        int pId;
        int mLine;
        int eId;
        int mTime;

        public Product(int pId, int mLine, int eId, int mTime) {
            this.pId = pId;
            this.mLine = mLine;
            this.eId = eId;
            this.mTime = mTime;
        }
    }

    static class Job {
        Product product;
        int endTime;

        public Job(Product product, int endTime) {
            this.product = product;
            this.endTime = endTime;
        }
    }

    private final int MAX = 500;

    Map<Integer, Integer> statusMap = new HashMap<>();
    PriorityQueue<Job> pq = new PriorityQueue<>((p1, p2) -> p1.endTime - p2.endTime);
    Queue<Product>[] lineQue = new Queue[MAX];
    int[] lineUse = new int[MAX];
    boolean[] equipUse = new boolean[MAX];
    int L, M;

    public UserSolution() {
        for (int i = 0; i < MAX; i++) {
            lineQue[i] = new LinkedList<>();
        }
    }

    void init(int L, int M) {
        this.L = L; this.M = M;
        Arrays.fill(lineUse, -1);
        Arrays.fill(equipUse, false);

        for (int i = 0; i < MAX; i++) {
            lineQue[i].clear();
        }
        pq.clear();
        statusMap.clear();
    }

    /**
     * time 시간 까지의 작업을 한다.
     */
    void execute(int time) {
        while (!pq.isEmpty() && pq.peek().endTime <= time) {
            int now = pq.peek().endTime;

            while (!pq.isEmpty() && pq.peek().endTime == now) {
                Job job = pq.poll();
                if (job.product == null) continue;
                Product product = job.product;
                lineUse[product.mLine] = -1;
                equipUse[product.eId] = false;
                statusMap.put(product.pId, 3);
            }

            for (int i = 0; i < L; i++) {
                if (lineUse[i] == -1 && !lineQue[i].isEmpty()) {
                    Product product = lineQue[i].peek();

                    if (!equipUse[product.eId]) {
                        equipUse[product.eId] = true;
                        lineUse[product.mLine] = product.pId;
                        statusMap.put(product.pId, 2);
                        pq.offer(new Job(product, now + product.mTime));
                        lineQue[i].poll();
                    }
                }
            }
        }
    }

    int request(int tStamp, int pId, int mLine, int eId, int mTime) {
        execute(tStamp - 1);
        lineQue[mLine].offer(new Product(pId, mLine, eId, mTime));
        statusMap.put(pId, 1);
        pq.offer(new Job(null, tStamp));
        execute(tStamp);
        return lineUse[mLine] == -1 ? -1 : lineUse[mLine];
    }

    int status(int tStamp, int pId) {
        execute(tStamp);
        return statusMap.getOrDefault(pId, 0);
    }
}
