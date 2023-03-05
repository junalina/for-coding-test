package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/17086
 * <p>
 * 아기 상어 2
 * NxM 공간 아기 상어 여러 마리
 * 어떤 칸이 안전 거리 : 그 칸과 가장 거리가 가까운 아기 상어와의 거리
 * 두 칸의 거리 : 하나의 칸에서 다른 칸으로 가기 위해서 지나야 하는 칸의 수
 * 이동은 인접한 8방향 가능
 * 안전 거리의 최댓값 출력
 * <p>
 * 풀이
 * BFS
 */

public class BJ_17086_아기상어2 {

    static int N, M;

    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};


    static class Shark {
        int r, c;

        public Shark(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 공간의 크기 N, M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Queue<Shark> q = new LinkedList<>();

        // 공간의 상태
        int[][] map = new int[N][M];
        int[][] dis = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                // 상어라면 q에 추가
                if (map[r][c] == 1) q.offer(new Shark(r, c));
            }
        }

        int max = Integer.MIN_VALUE;

        // bfs
        while (!q.isEmpty()) {
            Shark shark = q.poll();
            int r = shark.r;
            int c = shark.c;
            for (int i = 0; i < 8; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                // 배열 범위 밖이면 무시
                if (!isIn(nr, nc)) continue;
                // 방문했거나 상어가 있으면 무시
                if (dis[nr][nc] != 0 || map[nr][nc] == 1) continue;
                dis[nr][nc] = dis[r][c] + 1;
                if (dis[nr][nc] > max) max = dis[nr][nc];
                q.offer(new Shark(nr, nc));
            }
        }

        System.out.println(max);

    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }
}
