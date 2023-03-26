package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * NxM 행렬
 * (1, 1) -> (N, M)
 * 이동하는 도중에 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 K개 까지 부수고 이동 가능
 * 최단 거리 출력
 * <p>
 * 풀이
 * BFS
 */

public class BJ_14442_벽부수고이동하기2 {
    static int N, M, K;
    static int[][] map;

    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int r, c, k;

        public Point(int r, int c, int k) {
            this.r = r;
            this.c = c;
            this.k = k;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 맵 정보 입력
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = str.charAt(c) - '0';
            }
        }

        System.out.println(bfs());

    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        int[][][] dis = new int[N][M][K + 1];

        // (0, 0)에서 최단 거리 1로 부순 벽 없이 시작
        q.offer(new Point(0, 0, 0));
        dis[0][0][0] = 1;

        while (!q.isEmpty()) {
            Point now = q.poll();
            int r = now.r;
            int c = now.c;
            int k = now.k;

            // (N, M)의 위치에 도착했다면
            if (r == N - 1 && c == M - 1) return dis[r][c][k];

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                int nk = k + 1;
                // 배열 범위 밖이라면 무시
                if (!isIn(nr, nc)) continue;
                // 이동할 수 있는 곳이고 방문하지 않았다면
                if (map[nr][nc] == 0 && dis[nr][nc][k] == 0) {
                    q.offer(new Point(nr, nc, k));
                    dis[nr][nc][k] = dis[r][c][k] + 1;
                }
                // 이동할 수 없는 벽이 있는 곳이고 방문하지 않고 부순 벽의 수가 K보다 작거나 같다면
                else if (map[nr][nc] == 1 && nk <= K && dis[nr][nc][nk] == 0) {
                    q.offer(new Point(nr, nc, nk));
                    dis[nr][nc][nk] = dis[r][c][k] + 1;
                }
            }
        }
        return -1;
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }
}
