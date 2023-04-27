package programmers;

import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/1844

게임 맵 최단거리
ROR 게임 두 팀으로 나누어 진행하며, 상대 팀 진영 먼저 파괴하면 승리
캐릭터가 상대 팀 진영에 도착하기 위해서 지나가야 하는 칸의 개수의 최솟값 return

풀이
BFS
*/

class PG_1844_게임맵최단거리 {

    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int solution(int[][] maps) {
        return bfs(maps);
    }

    private static int bfs(int[][] maps) {
        Queue<Point> q = new LinkedList<>();
        int[][] d = new int[maps.length][maps[0].length];
        q.offer(new Point(0, 0));
        d[0][0] = 1;

        while (!q.isEmpty()) {
            Point now = q.poll();
            int r = now.r;
            int c = now.c;

            // 상대 팀 진영에 도착했다면 리턴
            if (r == maps.length - 1 && c == maps[0].length - 1) return d[r][c];

            // 사방탐색
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                // 배열 범위 밖이면 무시
                if (!isIn(maps, nr, nc)) continue;
                // 방문했다면 무시
                if (d[nr][nc] != 0) continue;
                // 벽이라면 무시
                if (maps[nr][nc] == 0) continue;

                q.offer(new Point(nr, nc));
                d[nr][nc] = d[r][c] + 1;
            }
        }

        return -1;

    }

    private static boolean isIn(int[][] maps, int r, int c) {
        return r >= 0 && r < maps.length && c >= 0 && c < maps[0].length;
    }
}
