package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2573
 * <p>
 * 빙산
 * 빙산의 높이는 바닷물에 많이 접해있는 부분에서 더 빨리 줄어든다.
 * 한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간 출력
 * <p>
 * 풀이
 * 1. 빙산이 몇 덩어리인지 구하기 (DFS)
 * 2. 빙산 녹이기 (BFS)
 */
public class BJ_2573_빙산 {
    static int N, M, time;
    static int[][] sea;

    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Ice {
        int r, c;

        public Ice(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행, 열
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 배열 정보
        sea = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                sea[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            int cnt = seperateCnt();
            if (cnt >= 2) break;

            // 빙산이 다 녹을때까지 분리되지 않으면 0 출력
            if (cnt == 0) {
                time = 0;
                break;
            }

            // 2. 빙산 녹이기 (BFS)
            meltingIce();

            time++;
        }
        System.out.println(time);

    }

    // 2. 빙산 녹이기 (BFS)
    private static void meltingIce() {
        Queue<Ice> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (sea[r][c] != 0) {
                    q.offer(new Ice(r, c));
                    visited[r][c] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Ice ice = q.poll();
            int r = ice.r;
            int c = ice.c;
            int seaNum = 0;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                // 배열 범위 밖이거나 방문했다면 무시
                if (isNotIn(nr, nc) || visited[nr][nc]) continue;
                if (sea[nr][nc] == 0) seaNum++;
            }
            if (sea[r][c] - seaNum < 0) sea[r][c] = 0;
            else sea[r][c] -= seaNum;
        }
    }

    // 1. 빙산이 몇 덩어리인지 구하기 (DFS)
    private static int seperateCnt() {
        boolean[][] visited = new boolean[N][M];
        int cnt = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (sea[r][c] != 0 && !visited[r][c]) {
                    dfs(r, c, visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void dfs(int r, int c, boolean[][] visited) {
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (isNotIn(nr, nc) || visited[nr][nc]) continue;
            if (sea[nr][nc] > 0) {
                dfs(nr, nc, visited);
            }
        }
    }

    private static boolean isNotIn(int nr, int nc) {
        return nr < 0 || nr >= N || nc < 0 || nc >= M;
    }
}
