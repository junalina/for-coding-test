package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14923
 * <p>
 * 미로 탈출
 * 홍익이 (Hx, Hy) -> (Ex, Ey)
 * 벽이 있는 곳은 이동 불가
 * 지팡이로 벽을 한 번 길로 만들 수 있음
 * 가장 빠른 경로의 거리 D 출력
 * <p>
 * 풀이
 * BFS
 */

public class BJ_14923_미로탈출 {
    static int N, M, hx, hy, ex, ey;
    static int[][] maze;

    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int r, c;
        boolean broken;

        public Point(int r, int c, boolean broken) {
            this.r = r;
            this.c = c;
            this.broken = broken;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 미로 크기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 출발 지점
        st = new StringTokenizer(br.readLine());
        hx = Integer.parseInt(st.nextToken());
        hy = Integer.parseInt(st.nextToken());

        // 도착 지점
        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken());
        ey = Integer.parseInt(st.nextToken());

        // 미로 정보
        maze = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                maze[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());

    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        int[][][] time = new int[N][M][2];
        // (hx-1, hy-1)에서 벽을 부수지 않은 상태로 출발
        q.offer(new Point(hx - 1, hy - 1, false));
        time[hx - 1][hy - 1][0] = 1;

        while (!q.isEmpty()) {
            Point now = q.poll();
            int r = now.r;
            int c = now.c;
            boolean broken = now.broken;
            int index = 0;
            if (broken) index = 1;

            if (r == ex - 1 && c == ey - 1) return time[ex - 1][ey - 1][index] - 1;

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                // 배열 범위 밖이라면 무시
                if (!isIn(nr, nc)) continue;
                // 방문했다면 무시
                if (time[nr][nc][index] != 0) continue;

                // 빈칸이라면
                if (maze[nr][nc] == 0) {
                    q.offer(new Point(nr, nc, broken));
                    time[nr][nc][index] = time[r][c][index] + 1;
                }
                // 벽이라면
                else if (maze[nr][nc] == 1) {
                    // 이미 지팡이를 사용했다면 무시
                    if (broken) continue;
                    else {
                        q.offer(new Point(nr, nc, true));
                        time[nr][nc][1] = time[r][c][index] + 1;
                    }
                }
            }
        }

        // 탈출할 수 없다면 -1 출력
        return -1;

    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }
}
