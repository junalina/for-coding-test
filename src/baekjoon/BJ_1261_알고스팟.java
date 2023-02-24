package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1261
 * <p>
 * 알고스팟
 * (1, 1)에 있는 알고스팟 운영진이 (N, M)으로 이동하려면 벽을 최소 몇 개 부수어야 하는지 출력
 * <p>
 * 풀이
 * 다익스트라
 */

public class BJ_1261_알고스팟 {
    static int M, N, min;
    static int[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point implements Comparable<Point> {
        int r, c, cnt;

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.cnt, o.cnt);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = str.charAt(c) - '0';
            }
        }

        min = Integer.MAX_VALUE;
        bfs();

        System.out.println(min);
    }

    private static void bfs() {
        PriorityQueue<Point> q = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][M];
        q.offer(new Point(0, 0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            int r = p.r;
            int c = p.c;
            int cnt = p.cnt;
            if (r == N - 1 && c == M - 1) {
                min = Math.min(min, cnt);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (!isIn(nr, nc) || visited[nr][nc]) continue;
                if (map[nr][nc] == 0) q.offer(new Point(nr, nc, cnt));
                else if (map[nr][nc] == 1) q.offer(new Point(nr, nc, cnt + 1));
                visited[nr][nc] = true;
            }
        }
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }
}
