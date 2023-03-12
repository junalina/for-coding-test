package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14940
 * <p>
 * 쉬운 최단거리
 * 지도가 주어지면 각 지점에서 목표 지점에 대해서 목표지점까지의 거리 출력
 * 가로와 세로로만 이동 가능
 * <p>
 * 풀이
 * BFS
 */

public class BJ_14940_쉬운최단거리 {
    static int n, m;
    static int[][] map;
    static int[][] d;

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
        StringBuilder sb = new StringBuilder();

        // 세로의 크기
        n = Integer.parseInt(st.nextToken());
        // 가로의 크기
        m = Integer.parseInt(st.nextToken());

        // 목표지점
        int startR = 0;
        int startC = 0;

        // 지도 정보
        map = new int[n][m];
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                // 목표지점이라면
                if (map[r][c] == 2) {
                    startR = r;
                    startC = c;
                }
            }
        }

        // 각 지점에서 목표지점까지 거리 담을 배열
        d = new int[n][m];

        bfs(startR, startC);

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                // 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치는 -1 출력
                if (map[r][c] == 1 && d[r][c] == 0) sb.append("-1");
                // 원래 갈 수 없는 땅인 위치는 0 출력
                else sb.append(d[r][c]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    private static void bfs(int startR, int startC) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(startR, startC));
        d[startR][startC] = 0;

        while (!q.isEmpty()) {
            Point now = q.poll();
            int r = now.r;
            int c = now.c;
            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                // 배열 범위 밖이라면 무시
                if (!isIn(nr, nc)) continue;
                // 이미 방문했다면 무시
                if (d[nr][nc] != 0) continue;
                // 갈 수 없는 땅이거나 목표지점이면 무시
                if (map[nr][nc] == 0 || map[nr][nc] == 2) continue;
                d[nr][nc] = d[r][c] + 1;
                q.offer(new Point(nr, nc));
            }
        }
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < n && nc >= 0 && nc < m;
    }
}
