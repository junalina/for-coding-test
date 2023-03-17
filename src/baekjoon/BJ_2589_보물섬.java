package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2589
 * <p>
 * 보물섬
 * 지도 칸은 육지나 바다
 * 이동 상하좌우 이웃한 육지로만 가능
 * 보물은 서로 간에 최단 거리로 이동하는데 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀 있음
 * 보물이 묻혀 있는 두 곳 사이를 최단 거리로 이동하는 시간 출력
 * <p>
 * 풀이
 * BFS
 */

public class BJ_2589_보물섬 {
    static int R, C, max;
    static char[][] map;
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
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 세로의 크기
        R = Integer.parseInt(st.nextToken());
        // 가로의 크기
        C = Integer.parseInt(st.nextToken());

        // 보물 지도
        map = new char[R][C];
        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = str.charAt(c);
            }
        }

        max = 0;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                // 육지라면
                if (map[r][c] == 'L') {
                    bfs(r, c);
                }
            }
        }
        System.out.println(max - 1);
    }

    private static void bfs(int startR, int startC) {
        Queue<Point> q = new LinkedList<>();
        int[][] dis = new int[R][C];
        q.offer(new Point(startR, startC));
        dis[startR][startC] = 1;

        while (!q.isEmpty()) {
            Point now = q.poll();
            int r = now.r;
            int c = now.c;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                // 배열 범위 밖이라면 무시
                if (!isIn(nr, nc)) continue;
                // 방문했다면 무시
                if (dis[nr][nc] != 0) continue;
                // 바다라면 무시
                if (map[nr][nc] == 'W') continue;
                q.offer(new Point(nr, nc));
                dis[nr][nc] = dis[r][c] + 1;
                max = Math.max(max, dis[nr][nc]);
            }
        }
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < R && nc >= 0 && nc < C;
    }

}
