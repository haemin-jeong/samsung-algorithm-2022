package greedy_bp_dp.back_to_room;

import java.io.FileInputStream;
import java.util.*;

class Solution {

    static class Student implements Comparable<Student>{
        boolean isArrived;
        int lowNumRoom;
        int highNumRoom;

        public Student(int lowNumRoom, int highNumRoom) {
            this.lowNumRoom = lowNumRoom;
            this.highNumRoom = highNumRoom;
        }

        @Override
        public int compareTo(Student o) {
            return this.lowNumRoom - o.lowNumRoom;
        }
    }

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/greedy_BP_DP/back_to_room/sample_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        List<Student> list = new ArrayList<>();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();

            for (int i = 0; i < n; i++) {
                int room1 = sc.nextInt();
                int room2 = sc.nextInt();

                if (room1 > room2) {
                    list.add(new Student(room2, room1));
                } else  {
                    list.add(new Student(room1, room2));
                }
            }

            System.out.println("#" + test_case + " " + solution(n, list));

            list.clear();
        }
    }

    private static int solution(int n, List<Student> list) {
        int time = 0;

        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isArrived) continue;

            list.get(i).isArrived = true;
            int lastRoomNum = list.get(i).highNumRoom;

            for (int j = i + 1; j < n; j++) {
                if (!list.get(j).isArrived
                        && list.get(j).lowNumRoom > (lastRoomNum%2 == 0 ? lastRoomNum : lastRoomNum+1)) {
                    list.get(j).isArrived = true;
                    lastRoomNum = list.get(j).highNumRoom;
                }
            }

            time++;
        }

        return time;
    }
}