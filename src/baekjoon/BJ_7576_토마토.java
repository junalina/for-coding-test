package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/7576
 * <p>
 * 토마토
 * 보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익음
 * 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지 최소 일수 출력
 * <p>
 * 풀이
 * BFS
 */
public class BJ_7576_토마토 {
    static int M, N, max;
    static int[][] arr;
    static Queue<Tomato> q = new LinkedList<>();

    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Tomato {
        int r, c;

        public Tomato(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
                if (arr[r][c] == 1) q.offer(new Tomato(r, c));
            }
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        while (!q.isEmpty()) {
            Tomato p = q.poll();
            int r = p.r;
            int c = p.c;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!isIn(nr, nc)) continue;
                if (arr[nr][nc] == 0) {
                    arr[nr][nc] = arr[r][c] + 1;
                    q.offer(new Tomato(nr, nc));
                }
            }
        }
        max = Integer.MIN_VALUE;
        if (!success()) return -1;
        else {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    max = Integer.max(max, arr[r][c]);
                }
            }
        }
        return max - 1;
    }

    private static boolean success() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (arr[r][c] == 0) return false;
            }
        }
        return true;
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }
}
