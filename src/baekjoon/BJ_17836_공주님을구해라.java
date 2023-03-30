package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/17836
 * <p>
 * 공주님을 구해라!
 * 용사 (1, 1) -> 공주 (N, M)
 * 공주님을 구출하고 프러포즈 하고 싶은 용사는 반드시 T시간 내에 공주님이 있는 곳에 도달해야 한다.
 * 한 칸을 이동하는데 한 시간
 * 그람을 구하면 벽이 있는 칸을 무시할 수 있다.
 * 용사가 T시간 이내에 공주에게 도달할 수 있다면, 공주에게 도달할 수 있는 최단 시간 출력
 * T시간 이내에 구출할 수 없다면 "Fail" 출력
 * <p>
 * 풀이
 * BFS
 */

public class BJ_17836_공주님을구해라 {
    static int N, M, T;
    static int[][] map;

    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int r, c;
        boolean gram;

        public Point(int r, int c, boolean gram) {
            this.r = r;
            this.c = c;
            this.gram = gram;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 성의 크기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 저주의 제한 시간
        T = Integer.parseInt(st.nextToken());

        // 성의 구조 (0은 빈 공간, 1은 마법의 벽, 2는 그람)
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int res = bfs();

        if (res == -1) System.out.println("Fail");
        else System.out.println(res);

    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        int[][][] time = new int[N][M][2];
        // (0, 0)에서 그람이 없는 채로 시작
        q.offer(new Point(0, 0, false));
        time[0][0][0] = 1;

        while (!q.isEmpty()) {
            Point now = q.poll();
            int r = now.r;
            int c = now.c;
            boolean gram = now.gram;

            // 공주에게 도착했다면
            if (r == N - 1 && c == M - 1) {
                // T시간 이내에 도착했다면
                if (time[r][c][gram ? 1 : 0] - 1 <= T) return time[r][c][gram ? 1 : 0] - 1;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                // 배열 범위 밖이라면 무시
                if (!isIn(nr, nc)) continue;
                // 방문했다면 무시
                if (time[nr][nc][gram ? 1:0] != 0) continue;

                if (map[nr][nc] == 2) {
                    q.offer(new Point(nr, nc, true)); // 그람을 얻는 경우 큐에 추가
                    time[nr][nc][1] = time[r][c][gram ? 1 : 0] + 1;
                } else if (!gram && map[nr][nc] == 1) {
                    continue; // 그람이 없고 벽이 있는 경우 무시
                } else {
                    q.offer(new Point(nr, nc, gram)); // 그람을 얻지 않았을 때 큐에 추가
                    time[nr][nc][gram ? 1 : 0] = time[r][c][gram ? 1 : 0] + 1;
                }
            }
        }

        return -1;

    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }
}
