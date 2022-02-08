package hash.big_picture;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//라빈-카프 알고리즘 사용
class Solution {

    private static final int MAX_LEN = 2000;
    private static int[] dreamRowHash = new int[MAX_LEN];
    private static int[][] teacherRowHash = new int[MAX_LEN][MAX_LEN];
    private static int h, w, n, m, power, hash, answer;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/hash/big_picture/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            answer = 0;

            //은기 그림 가로 해시값 구하기
            for (int i = 0; i < h; i++) {
                String r = br.readLine();
                power = 1;
                hash = 0;

                for (int j = 0; j < w; j++) {
                    hash += r.charAt(w - j - 1) * power;
                    power *= 33;
                }

                dreamRowHash[i] = hash;
            }

            //앞서 구한 은기 그림 가로 해시값을 이용해서 전체 그림 해시값 구하기
            power = 1;
            int dreamHash = 0;
            for (int i = 0; i < h; i++) {
                dreamHash += dreamRowHash[h - i - 1] * power;
                power *= 5381;
            }

            //선생님 그림 가로 해시값 구하기
            for (int i = 0; i < n; i++) {
                hash = 0;
                power = 1;
                String r = br.readLine();
                for (int j = 0; j <= m - w; j++) {
                    if (j == 0) {
                        for (int k = 0; k < w; k++) {
                            hash += r.charAt(w - k - 1) * power;
                            if (k < w - 1) power *= 33;
                        }
                    } else {
                        hash = 33 * (hash - r.charAt(j - 1) * power) + r.charAt(j + w - 1);
                    }

                    teacherRowHash[i][j] = hash;
                }
            }

            //앞서 구한 선생님 그림 가로 해시값을 이용해 은기 그림과 같은 사이즈 단위의 부분 그림 해시값을 구해가며 은기 그림 해시값과 비교
            for (int i = 0; i <= m - w; i++) {
                hash = 0;
                power = 1;

                for (int j = 0; j <= n - h; j++) {
                    if (j == 0) {
                        for (int k = 0; k < h; k++) {
                            hash += teacherRowHash[h - k - 1][i] * power;
                            if (k < h - 1) power *= 5381;
                        }
                    } else {
                        hash = 5381 * (hash - teacherRowHash[j - 1][i] * power) + teacherRowHash[h + j - 1][i];
                    }

                    if (hash == dreamHash) {
                        answer++;
                    }

                }
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }
}
