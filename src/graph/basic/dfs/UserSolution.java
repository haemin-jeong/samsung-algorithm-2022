package graph.basic.dfs;

public class UserSolution {

    private int[][] graph;
    private int[] graphCount;
    private int N, answer;
    private boolean flag;

    public void dfs_init(int N, int[][] path) {
        graph = new int[100][100];
        graphCount = new int[100];

        for (int i = 0; i < N - 1; i++) {
            int parent = path[i][0];
            int child = path[i][1];
            graph[parent][graphCount[parent]] = child;
            graphCount[parent]++;
        }
    }

    public int dfs(int N) {
        this.N = N;
        answer = -1;
        flag = true;
        myDfs(N);
        return answer;
    }

    private void myDfs(int n) {
        for (int i = 0; i < graphCount[n]; i++) {
            if (!flag) return;

            int next = graph[n][i];

            if (next > N) {
                answer = next;
                flag = false;
                return;
            }

            myDfs(next);
        }
    }
}
