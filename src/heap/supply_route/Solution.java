package heap.supply_route;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
    static class Road implements Comparable<Road> {
        int x;
        int y;
        int time;

        public Road(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Road o) {
            return this.time - o.time;
        }
    }

    private static boolean[][] check;
    private static int[][] map, dis;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int n;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/heap/supply_route/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());
            check = new boolean[n][n];
            map = new int[n][n];
            dis = new int[n][n];

            StringTokenizer st = null;
            for (int i = 0; i < n; i++) {
                char[] chars = br.readLine().toCharArray();
                for (int j = 0; j < n; j++) {
                    map[i][j] = Character.getNumericValue(chars[j]);
                }
            }

            System.out.println("#" + test_case + " " + bfs());
        }
    }

    private static int bfs() {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        int answer = Integer.MAX_VALUE;

        pq.offer(new Road(0, 0, map[0][0]));
        check[0][0] = true;

        while (!pq.isEmpty()) {
            Road poll = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];

                if (nx == n - 1 && ny == n - 1) {
                    answer = Math.min(answer, poll.time);
                    continue;
                }

                if (answer <= dis[poll.x][poll.y]) {
                    continue;
                }

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (!check[nx][ny] || dis[nx][ny] > poll.time + map[nx][ny]) {
                        check[nx][ny] = true;
                        dis[nx][ny] = (poll.time + map[nx][ny]);
                        pq.offer(new Road(nx, ny, dis[nx][ny]));
                    }
                }
            }
        }

        return answer;
    }
}