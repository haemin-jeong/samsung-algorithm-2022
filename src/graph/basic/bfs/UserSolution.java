package graph.basic.bfs;

class UserSolution {

    private static final int QUEUE_SIZE = 100;
    Pos[] q = new Pos[QUEUE_SIZE];
    int front, rear;

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int[] dx = {0, 1, 0, -1};
    private int[] dy = {-1, 0, 1, 0};
    private int[][] dis, map;
    private int qSize, mapSize;

    void bfs_init(int map_size, int map[][]) {
        dis = new int[10][10];
        this.map = map;
        this.mapSize = map_size;
    }

    int bfs(int x1, int y1, int x2, int y2) {
        disInit();
        queueInit();

        offer(new Pos(x1-1, y1-1));

        while (qSize > 0) {
            Pos poll = poll();

            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];

                if (nx >= 0 && nx < mapSize && ny >= 0 && ny < mapSize && map[ny][nx] == 0 && dis[ny][nx] == 0) {
                    dis[ny][nx] = dis[poll.y][poll.x] + 1;

                    if (nx == (x2-1) && ny == (y2-1)) {
                        return dis[ny][nx];
                    }

                    offer(new Pos(nx, ny));
                }
            }
        }

        return -1;
    }

    private void queueInit() {
        front = rear = -1;
        qSize = 0;
    }

    private void disInit() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                dis[i][j] = 0;
            }
        }
    }

    private void offer(Pos pos) {
        rear = (rear + 1) % QUEUE_SIZE;
        q[rear] = pos;
        qSize++;
    }

    private Pos poll() {
        front = (front + 1) % QUEUE_SIZE;
        qSize--;
        return q[front];
    }
}

