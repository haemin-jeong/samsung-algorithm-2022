package practice.memo;

import java.util.*;

class UserSolution {
    private int maxRow, maxColumn, cursorRow, cursorColumn;
    private List<Character>[] memo = new List[301];

    //각 줄의 알파벳 개수를 저장해서 countCharacter 메서드의 실행 시간 단축
    private int[][] charCount = new int[301][26];

    public UserSolution() {
        for (int i = 1; i <= 300; i++) {
            memo[i] = new LinkedList<>();
        }
    }

    void init(int H, int W, char mStr[]) {
        for (int i = 1; i <= H; i++) {
            memo[i].clear();
        }

        for (int i = 1; i <= H; i++) {
            Arrays.fill(charCount[i], 0);
        }

        maxRow = H;
        maxColumn = W;

        cursorRow = 1;
        cursorColumn = 0;

        for (char c : mStr) {
            if (c == '\0') break;
            insert(c);
        }

        cursorRow = 1;
        cursorColumn = 0;
    }

    void insert(char mChar) {
        memo[cursorRow].add(cursorColumn, mChar);
        charCount[cursorRow][mChar - 97]++;
        cursorColumn++;

        int row = cursorRow;
        while (memo[row].size() > maxColumn) {
            char move = memo[row].remove(maxColumn);
            memo[row + 1].add(0, move);

            charCount[row][move - 97]--;
            charCount[row + 1][move - 97]++;

            row++;
        }

        if (cursorColumn == maxColumn) {
            cursorRow++;
            cursorColumn = 0;
        }
    }

    char moveCursor(int mRow, int mCol) {
        cursorRow = mRow;
        cursorColumn = mCol-1;

        if (memo[cursorRow].size() <= cursorColumn) {

            while (memo[cursorRow].size() == 0) {
                cursorRow--;
            }

            cursorColumn = memo[cursorRow].size();
            return '$';
        }
        return memo[cursorRow].get(cursorColumn);
    }

    int countCharacter(char mChar) {
        int count = 0;

        for (int i = cursorColumn; i < memo[cursorRow].size(); i++) {
            if (memo[cursorRow].get(i) == mChar) {
                count++;
            }
        }

        for (int i = cursorRow+1; i <= maxRow; i++) {
            if (memo[i].size() == 0) {
                break;
            }

            count += charCount[i][mChar - 97];
        }

        return count;
    }
}
