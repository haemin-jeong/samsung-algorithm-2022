package graph.processor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    static class Core {
        int x;
        int y;

        public Core(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int[][] board = new int[12][12];
    private static Core[] cores = new Core[12];
    private static int numOfCores, maxCoreCount, minLineLen, n;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/graph/processor/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            numOfCores = 0;
            maxCoreCount = Integer.MIN_VALUE;
            minLineLen = Integer.MAX_VALUE;
            n = Integer.parseInt(br.readLine());

            StringTokenizer st = null;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());

                    if (board[i][j] == 1) {
                        if (i == 0 || i == (n - 1) || j == 0 || j == (n - 1)) {
                            continue;
                        }

                        cores[numOfCores++] = new Core(i, j);
                    }
                 }
            }

            dfs(0, 0, 0);

            System.out.println("#" + test_case + " " + minLineLen);
        }
    }

    private static void dfs(int level, int coreCount, int lineLen) {
        if(level == numOfCores) {
            if (coreCount == maxCoreCount) {
                minLineLen = Math.min(lineLen, minLineLen);
            } else {
                if (coreCount > maxCoreCount) {
                    maxCoreCount = coreCount;
                    minLineLen = lineLen;
                }
            }
        } else {
            Core core = cores[level];

            for (int i = 0; i < 4; i++) {
                int length = getLineLength(i, core);

                if (length == -1) {
                    continue;
                }

                int nx = core.x;
                int ny = core.y;
                for (int j = 0; j < length; j++) {
                    nx += dx[i];
                    ny += dy[i];
                    board[nx][ny] = -1;
                }

                dfs(level+1, coreCount+1, lineLen+length);

                for (int j = 0; j < length; j++) {
                    board[nx][ny] = 0;
                    nx -= dx[i];
                    ny -= dy[i];
                }
            }

            dfs(level + 1, coreCount, lineLen);
        }
    }

    private static int getLineLength(int dir, Core core) {
        int x = core.x + dx[dir];
        int y = core.y + dy[dir];
        int length = 0;
        
        while (x >= 0 && x < n && y >= 0 && y < n) {
            if (board[x][y] == 1 || board[x][y] == -1) {
                return -1;
            }

            x += dx[dir];
            y += dy[dir];
            length++;
        }

        return length;
    }
}
