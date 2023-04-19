package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2146
 * <p>
 * 다리 만들기
 * 여러 섬으로 이루어진 나라
 * 대통령이 섬을 잇는 다리 만들겠다는 공약
 * 한 섬과 다른 섬을 잇는 다리 하나만을 만들기로 함
 * 다리를 가장 짧게 하여 돈을 절약
 * 지도가 주어질 때, 가장 짧은 다리 하나를 놓아 두 대륙을 연결하는 방법을 찾으시오
 * 가장 짧은 다리의 길이 출력
 * <p>
 * 풀이
 * BFS
 */

public class BJ_2146_다리만들기 {
    static int N, min;
    static int landNum = 2;
    static int[][] map;

    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int r, c, cnt;

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 지도의 크기 N
        N = Integer.parseInt(br.readLine());
        // 지도 정보
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // 육지면
                if (map[r][c] == 1) {
                    // 섬에 번호를 붙여주기
                    makeLand(r, c);
                }
            }
        }

        min = Integer.MAX_VALUE;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] >= 2) {
                    bfs(r, c);
                }
            }
        }

        System.out.println(min);

    }

    private static void bfs(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int curLandNum = map[r][c];
        q.offer(new Point(r, c, 0));
        visited[r][c] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();
            int pr = now.r;
            int pc = now.c;
            int cnt = now.cnt;
            for (int i = 0; i < 4; i++) {
                int nr = pr + dr[i];
                int nc = pc + dc[i];
                // 배열 범위 밖이라면 무시
                if (!isIn(nr, nc)) continue;
                // 방문했다면 무시
                if (visited[nr][nc]) continue;
                // 현재 섬이라면 무시
                if (map[nr][nc] == curLandNum) continue;

                // 방문 처리
                visited[nr][nc] = true;
                // 섬이라면
                if (map[nr][nc] == 0) q.offer(new Point(nr, nc, cnt + 1));
                else min = Math.min(min, cnt);
            }
        }
    }

    private static void makeLand(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.offer(new Point(r, c, 0));
        visited[r][c] = true;
        map[r][c] = landNum;

        while (!q.isEmpty()) {
            Point now = q.poll();
            int pr = now.r;
            int pc = now.c;
            for (int i = 0; i < 4; i++) {
                int nr = pr + dr[i];
                int nc = pc + dc[i];
                // 배열 범위 밖이라면 무시
                if (!isIn(nr, nc)) continue;
                // 방문했다면 무시
                if (visited[nr][nc]) continue;
                // 육지가 아니라면 무시
                if (map[nr][nc] != 1) continue;

                q.offer(new Point(nr, nc, 0));
                visited[nr][nc] = true;
                map[nr][nc] = landNum;

            }
        }
        landNum++;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N & c >= 0 && c < N;
    }
}