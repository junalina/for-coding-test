package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.acmicpc.net/problem/2665
 * <p>
 * 미로만들기
 * nxn 바둑판 모양
 * 일부분은 검은 방 나머지는 모두 흰 방
 * 검은 방은 사면이 벽으로 싸여 있어 들어갈 수 없다.
 * 서로 붙어 있는 두 개의 흰 방 사이에는 문이 있어서 지나다닐 수 있다.
 * 시작 방에서 출발하여 길을 찾아서 끝방으로 가는 것이 목적, 갈 수 없는 경우가 존재
 * 검은 방에서 흰 바으로 바꾸어야 할 최소의 수 출력
 * <p>
 * 풀이
 * BFS
 */

public class BJ_2665_미로만들기 {
    static int n;
    static int[][] room, dis;

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 한 줄에 들어가는 방의 수 n
        n = Integer.parseInt(br.readLine());

        // 방 정보 입력
        room = new int[n][n];
        // 최소의 수를 저장하는 배열
        dis = new int[n][n];

        for (int r = 0; r < n; r++) {
            String str = br.readLine();
            for (int c = 0; c < n; c++) {
                room[r][c] = str.charAt(c) - '0';
                dis[r][c] = Integer.MAX_VALUE;
            }
        }

        bfs();

        System.out.println(dis[n - 1][n - 1]);

    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0));
        dis[0][0] = 0;

        while (!q.isEmpty()) {
            Point now = q.poll();
            int r = now.r;
            int c = now.c;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 배열 범위 밖이라면
                if (!isIn(nr, nc)) continue;
                if (dis[nr][nc] <= dis[r][c]) continue;

                // 흰 방
                if (room[nr][nc] == 1) {
                    dis[nr][nc] = dis[r][c];
                }
                // 검은 방
                else if (room[nr][nc] == 0) {
                    dis[nr][nc] = dis[r][c] + 1;
                }
                q.offer(new Point(nr, nc));
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}
