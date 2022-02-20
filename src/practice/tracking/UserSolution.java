package practice.tracking;

import java.util.Arrays;

class UserSolution {

    static class Place {
        int pID;
        int r, c;
        boolean visited;

        public Place(int pID, int r, int c) {
            this.pID = pID;
            this.r = r;
            this.c = c;
        }
    }

    static class Node {
        Place p;
        Node prev, next;

        public Node(Place p) {
            this.p = p;
        }
    }

    Node[] list1 = new Node[10000];
    Node[] list2 = new Node[20000];
    Node[] list3 = new Node[10000];
    Node[] list4 = new Node[20000];

    Node[] list1Tails = new Node[10000];
    Node[] list2Tails = new Node[20000];
    Node[] list3Tails = new Node[10000];
    Node[] list4Tails = new Node[20000];

    int[][] userVisit = new int[10001][100];
    Place[] places = new Place[50001];

    public UserSolution() {
        for (int i = 0; i < 10000; i++) {
            list1[i] = new Node(null); list2[i] = new Node(null);
            list3[i] = new Node(null); list4[i] = new Node(null);

            list1Tails[i] = new Node(null); list2Tails[i] = new Node(null);
            list3Tails[i] = new Node(null); list4Tails[i] = new Node(null);
        }

        for (int i = 10000; i < 20000; i++) {
            list2[i] = new Node(null); list2Tails[i] = new Node(null);
            list4[i] = new Node(null); list4Tails[i] = new Node(null);
        }
    }

    void init() {
        for (int i = 0; i < 10000; i++) {
            list1[i].next = list1Tails[i]; list1Tails[i].prev = list1[i];
            list2[i].next = list2Tails[i]; list2Tails[i].prev = list2[i];
            list3[i].next = list3Tails[i]; list3Tails[i].prev = list3[i];
            list4[i].next = list4Tails[i]; list4Tails[i].prev = list4[i];
        }

        for (int i = 10000; i < 20000; i++) {
            list2[i].next = list2Tails[i]; list2Tails[i].prev = list2[i];
            list4[i].next = list4Tails[i]; list4Tails[i].prev = list4[i];
        }

        for (int i = 0; i <= 1000; i++) {
            Arrays.fill(userVisit[i], 0);
        }
    }

    void add(int dir, Place newPlace) {
        Node cur = null; Node tail = null;

        if (dir == 1) {
            cur = list1[newPlace.c]; tail = list1Tails[newPlace.c];
        } else if (dir == 2) {
            cur = list2[newPlace.r+ newPlace.c]; tail = list2Tails[newPlace.r+ newPlace.c];
        } else if (dir == 3) {
            cur = list3[newPlace.r]; tail = list3Tails[newPlace.r];
        } else {
            cur = list4[10000 + newPlace.r - newPlace.c]; tail = list4Tails[10000 + newPlace.r - newPlace.c];
        }

        Node newNode = new Node(newPlace);

        while (true) {
            cur = cur.next;

            if (cur == tail) {
                newNode.prev = tail.prev; cur.prev.next = newNode;
                newNode.next = tail; tail.prev = newNode;
                break;
            }

            if (dir == 1 || dir == 4) {
                if (cur.p.r < newPlace.r) {
                    newNode.prev = cur.prev; cur.prev.next = newNode;
                    newNode.next = cur; cur.prev = newNode;
                    break;
                }
            } else {
                if (cur.p.c > newPlace.c) {
                    newNode.prev = cur.prev; cur.prev.next = newNode;
                    newNode.next = cur; cur.prev = newNode;
                    break;
                }
            }
        }
    }

    void addPlace(int pID, int r, int c) {
        Place newPlace = new Place(pID, r, c);
        places[pID] = newPlace;
        add(1, newPlace);
        add(2, newPlace);
        add(3, newPlace);
        add(4, newPlace);
    }

    void removePlace(int pID) {
        Place place = places[pID];
        remove(list1[place.c], list1Tails[place.c], place);
        remove(list2[place.r + place.c], list2Tails[place.r + place.c], place);
        remove(list3[place.r],list3Tails[place.r] ,place);
        remove(list4[10000 + place.r - place.c],list4Tails[10000 + place.r - place.c] ,place);
        places[pID] = null;
    }

    void remove(Node head, Node tail, Place target) {
        Node cur = head;
        while(cur.next != tail) {
            cur = cur.next;

            if (cur.p == target) {
                cur.prev.next = cur.next; cur.next.prev = cur.prev;
                return;
            }
        }
    }

    Place find(int dir, Place now) {
        Node cur = null; Node head = null; Node tail = null;

        if (dir == 0 || dir == 4) {
            head = list1[now.c]; tail = list1Tails[now.c];
        } else if (dir == 1 || dir == 5) {
            head = list2[now.r + now.c]; tail = list2Tails[now.r + now.c];
        } else if (dir == 2 || dir == 6) {
            head = list3[now.r]; tail = list3Tails[now.r];
        } else if (dir == 3 || dir == 7) {
            head = list4[10000 + now.r - now.c]; tail = list4Tails[10000 + now.r - now.c];
        }

        cur = head.next;

        while(cur.p != now) cur = cur.next;

        if (dir == 0 || dir == 1 || dir == 2 || dir == 7) {
            while(cur.next != tail) {
                cur = cur.next;
                if (!cur.p.visited) return cur.p;
            }
        } else {
            while(cur.prev != head) {
                cur = cur.prev;
                if (!cur.p.visited) return cur.p;
            }
        }

        return null;
    }

    void contactTracing(int uID, int visitNum, int moveInfo[], int visitList[]) {
        userVisit[uID][0] = moveInfo[0];
        Place now = places[moveInfo[0]];

        for (int i = 1; i < visitNum; i++) {
            int dir = moveInfo[i];
            now = find(dir, now);
            userVisit[uID][i] = now.pID;
        }

        for (int i = 0; i < visitNum; i++) places[userVisit[uID][i]].visited = true;

        System.arraycopy(userVisit[uID], 0, visitList, 0, visitNum);
    }

    void disinfectPlaces(int uID) {
        int[] pIds = userVisit[uID];
        for (int pId : pIds) {
            if (places[pId] != null) places[pId].visited = false;
        }
    }
}