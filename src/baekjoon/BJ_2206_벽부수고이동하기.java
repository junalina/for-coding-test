package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2206
 * <p>
 * 벽 부수고 이동하기
 * N x M 행렬 맵
 * (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동
 * 만약 이동하는 도중에 한 개의 벽을 붓고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동 가능
 * 최단 거리 출력
 * <p>
 * 풀이
 * BFS
 */

public class BJ_2206_벽부수고이동하기 {
    static int N, M;
    static int[][] map;

    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int r, c, cnt;
        boolean destroyed;

        public Point(int r, int c, int cnt, boolean destroyed) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.destroyed = destroyed;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 맵 정보
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = str.charAt(c) - '0';
            }
        }

        // bfs
        Queue<Point> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2];
        q.offer(new Point(0, 0, 1, false));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();
            int r = now.r;
            int c = now.c;
            int cnt = now.cnt;
            boolean destroyed = now.destroyed;
            if (r == N - 1 && c == M - 1) {
                System.out.println(cnt);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                int nextCnt = cnt + 1;

                // 배열 범위 밖이면 무시
                if (!isIn(nr, nc)) continue;
                // 이동할 수 있는 곳이라면
                if (map[nr][nc] == 0) {
                    // 벽을 한 번도 부순적이 없고 방문하지 않았더라면
                    if (!destroyed && !visited[nr][nc][0]) {
                        q.offer(new Point(nr, nc, nextCnt, false));
                        visited[nr][nc][0] = true;
                    } else if (destroyed && !visited[nr][nc][1]) {
                        q.offer(new Point(nr, nc, nextCnt, true));
                        visited[nr][nc][1] = true;
                    }
                }
                // 벽이라면
                else if (map[nr][nc] == 1) {
                    // 벽을 한 번도 부순적이 없다면
                    if (!destroyed) {
                        q.offer(new Point(nr, nc, nextCnt, true));
                        visited[nr][nc][1] = true;
                    }
                }
            }
        }

        System.out.println(-1);

    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }
}
