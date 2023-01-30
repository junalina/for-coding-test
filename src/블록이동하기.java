import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/60063

블록 이동하기

2 x 1 로봇 준비, 0과 1로 이루어진 N x N 지도
로봇은 가장 왼쪽 상단에서 가로방향으로 시작, (N, N) 까지 이동
로봇이 움직일 때는 현재 놓여있는 상태 유지하며 이동
로봇이 차지하는 두 칸 중 어느 한 칸이라도 (N, N) 위치에 도착하면 도착
로봇은 90도씩 회전 가능
두 칸 중 어느 칸이든 축이 될 수 있지만, 화전하는 방향에 벽이 없어야 함
회전하는 데 걸리는 시간 = 1초
"0"과 "1"로 이루어진 지도인 board가 주어질 때, 
로봇이 (N, N) 위치까지 이동하는데 필요한 최소 시간을 return 하도록 solution 함수를 완성
*/
class 블록이동하기 {
    static class Node {
        int r1, r2, c1, c2, time;

        public Node(int r1, int c1, int r2, int c2, int time) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.time = time;
        }
    }

    public int solution(int[][] board) {
        // 맵 바깥쪽에 벽을 두는 형태로 맵 변형
        int n = board.length;
        int[][] map = new int[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(map[i], 1);
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                map[r + 1][c + 1] = board[r][c];
            }
        }

        // BFS
        Queue<Node> q = new LinkedList<>();
        ArrayList<Node> visited = new ArrayList<>();
        Node start = new Node(1, 1, 1, 2, 0);
        q.offer(start);
        visited.add(start);

        while(!q.isEmpty()) {
            Node node = q.poll();
            // 로봇이 차지하는 두 칸 중 어느 한 칸이라도 (N, N) 위치에 도착하면 도착
            if ((node.r1 == n && node.c1 == n) || (node.r2 == n && node.c2 == n)) return node.time;
            // 현재 위치에서 이동할 수 있는 위치 확인
            ArrayList<Node> nextNodeList = getNextNode(node, map);
            for(int i = 0; i < nextNodeList.size(); i++) {
                boolean check = true;
                Node next = nextNodeList.get(i);
                for (int j = 0; j < visited.size(); j++) {
                    Node visitedNode = visited.get(j);
                    // 방문한 위치라면
                    if (next.r1 == visitedNode.r1 && next.r2 == visitedNode.r2 && next.c1 == visitedNode.c1 && next.c2 == visitedNode.c2) {
                        check = false;
                        break;
                    }
                }
                // 큐에 삽입하고 방문 처리
                if (check) {
                    q.offer(next);
                    visited.add(next);
                }
            }
        }
        return 0;
    }

    public ArrayList<Node> getNextNode(Node node, int[][] map) {
        int nowR1 = node.r1;
        int nowC1 = node.c1;
        int nowR2 = node.r2;
        int nowC2 = node.c2;
        int time = node.time;
        // 다음 이동할 수 있는 노드 리스트 (반환값)
        ArrayList<Node> nextNodeList = new ArrayList<>();
        // 상하좌우
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        for(int i = 0; i < 4; i++) {
            int nextR1 = nowR1 + dr[i];
            int nextC1 = nowC1 + dc[i];
            int nextR2 = nowR2 + dr[i];
            int nextC2 = nowC2 + dc[i];
            // 현재 로봇이 이동하려 하는 두 칸이 모두 비어있을 때 리스트에 추가
            if (map[nextR1][nextC1] == 0 && map[nextR2][nextC2] == 0) nextNodeList.add(new Node(nextR1, nextC1, nextR2, nextC2, time + 1));
            int[] move = {-1, 1};
            // 현재 로봇이 가로 상태일 때
            if (nowC1 == nowC2) {
                // 상하로 회전
                for (int j = 0; j < 2; j++) {
                    // 현재 로봇의 위나 아래 두 칸이 모두 0이라면
                    if (map[nowR1][nowC1 + move[j]] == 0 && map[nowR2][nowC2 + move[j]] == 0) {
                        nextNodeList.add(new Node(nowR1, nowC1, nowR1, nowC1 + move[j], time + 1));
                        nextNodeList.add(new Node(nowR2, nowC2, nowR2, nowC2 + move[j], time + 1));
                    }
                }
            }
            // 현재 로봇이 세로 상태일 때
            if (nowR1 == nowR2) {
                // 좌우로 회전
                for (int j = 0; j < 2; j++) {
                    // 현재 로봇의 왼쪽이나 오른쪽 두 칸이 모두 0이라면
                    if (map[nowR1 + move[j]][nowC1] == 0 && map[nowR2 + move[j]][nowC2] == 0) {
                        nextNodeList.add(new Node(nowR1, nowC1, nowR1 + move[j], nowC1, time + 1));
                        nextNodeList.add(new Node(nowR2, nowC2, nowR2 + move[j], nowC2, time + 1));
                    }
                }
            }
        }

        return nextNodeList;
    }
}