package heap.social_media;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UserSolution {

    static class Post {
        int pID;
        int uID;
        int timestamp;
        int like;

        public Post(int pID, int uID, int timestamp) {
            this.pID = pID;
            this.uID = uID;
            this.timestamp = timestamp;
        }
    }

    private List<List<Integer>> followList;
    private List<Post> postList;

    public void init(int N) {
        postList = new ArrayList<>();
        followList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            followList.add(new ArrayList<>());
        }
    }

    public void follow(int uID1, int uID2, int timestamp) {
        followList.get(uID1).add(uID2);
    }

    public void makePost(int uID, int pID, int timestamp) {
        postList.add(new Post(pID, uID, timestamp));
    }

    public void like(int pID, int timestamp) {
        postList.get(pID - 1).like++;
    }

    public void getFeed(int uID, int timestamp, int pIDList[]) {
        Arrays.fill(pIDList, 0);

        for (int i = postList.size() - 1; i >= 0; i--) {
            Post post = postList.get(i);

            if (!isContain(uID, post.uID)) continue;

            if (timestamp - post.timestamp > 1000 && pIDList[9] != 0) {
                break;
            }

            for (int j = 0; j < pIDList.length;j++) {
                if (pIDList[j] == 0) {
                    pIDList[j] = post.pID;
                    break;
                }

                Post tempPost = postList.get(pIDList[j] - 1);

                if ((timestamp - post.timestamp <= 1000 && timestamp - tempPost.timestamp <= 1000)) {
                    if (post.like > tempPost.like) {
                        move(j, pIDList);
                        pIDList[j] = post.pID;
                        break;
                    }
                }
            }
        }
    }

    private void move(int from, int[] arr) {
        for (int k = arr.length-1; k > from; k--) {
            arr[k] = arr[k-1];
        }
    }

    private boolean isContain(int uID, int postUID) {
        if (postUID == uID) {
            return true;
        }

        for (int id : followList.get(uID)) {
            if (postUID == id) {
                return true;
            }
        }

        return false;
    }
}