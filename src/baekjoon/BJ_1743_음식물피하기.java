package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1743
 * <p>
 * 음식물 피하기
 * 음식물이 통로 중간 중간 떨어져 있음, 근처에 있는 것 끼리 뭉쳐 큰 음식물 쓰레기
 * 음식물 중 가장 큰 음식물의 크기 출력
 * <p>
 * 풀이
 * DFS
 */

public class BJ_1743_음식물피하기 {
    static int N, M, K, size, max;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] map, visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 음식물이 떨어진 좌표 표시
        map = new boolean[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r - 1][c - 1] = true;
        }

        // 음식물이 떨어진 곳이면 dfs로 주변 음식물 확인
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (!visited[r][c] && map[r][c]) {
                    size = 0;
                    dfs(r, c);
                    max = Math.max(max, size);
                }
            }
        }
        System.out.println(max);
    }

    private static void dfs(int r, int c) {
        size++;
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (!isIn(nr, nc) || visited[nr][nc]) continue;
            if (map[nr][nc]) dfs(nr, nc);
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
