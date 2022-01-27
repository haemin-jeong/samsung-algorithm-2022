package graph.minesweeper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static char[][] map;
    private static int[][] count;
    private static boolean[][] visited;
    private static int n, answer;

    private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String args[]) throws Exception {
//        System.setIn(new FileInputStream("src/graph/minesweeper/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());
            map = new char[n][n];
            visited = new boolean[n][n];
            answer = 0;

            for (int i = 0; i < n; i++) {
                map[i] = br.readLine().toCharArray();
            }

            initCountArr();

            solution();

            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static void solution() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (count[i][j] == 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    answer++;
                    bfs(i, j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '.' && !visited[i][j]) {
                    answer++;
                }
            }
        }
    }

    private static void bfs(int x, int y) {
        Queue<Pos> q = new LinkedList();

        q.offer(new Pos(x, y));

        while (!q.isEmpty()) {
            Pos poll = q.poll();

            for (int i = 0; i < 8; i++) {
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny] && count[nx][ny] >= 0) {
                    if (count[nx][ny] == 0) {
                        q.offer(new Pos(nx, ny));
                    }

                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static void initCountArr() {
        count = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '*') {
                    count[i][j] = -1;
                } else {
                    int mineCount = 0;
                    for (int k = 0; k < 8; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] == '*') {
                            mineCount++;
                        }
                    }

                    count[i][j] = mineCount;
                }
            }
        }
    }
}