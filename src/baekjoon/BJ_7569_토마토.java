package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/7569
 * <p>
 * 토마토
 * 격자모양 상자의 칸에 하나씩 넣은 다음, 상자들을 수직으로 쌓아 올려 보관
 * 익지 않은 토마토들은 인접한 익은 토마토의 영향을 받아 익게된다.
 * 상하좌우앞뒤 여섯 방향
 * 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지 최소 일수 출력
 * <p>
 * 풀이
 * BFS
 */
public class BJ_7569_토마토 {
    static int M, N, H, max;
    static int[][][] box;

    // 상하좌우앞뒤
    static int[] dh = {0, 0, 0, 0, 1, -1};
    static int[] dr = {-1, 1, 0, 0, 0, 0};
    static int[] dc = {0, 0, -1, 1, 0, 0};

    static Queue<Tomato> q = new LinkedList<>();

    static class Tomato {
        int h, r, c;

        public Tomato(int h, int r, int c) {
            this.h = h;
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 상자의 크기, 수 입력
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 하나의 상자에 담긴 토마토들의 정보 입력
        box = new int[H][N][M];
        for (int h = 0; h < H; h++) {
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    box[h][r][c] = Integer.parseInt(st.nextToken());
                    // 익은 토마토들은 큐에 저장
                    if (box[h][r][c] == 1) q.offer(new Tomato(h, r, c));
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        while (!q.isEmpty()) {
            Tomato tomato = q.poll();
            int h = tomato.h;
            int r = tomato.r;
            int c = tomato.c;
            for (int i = 0; i < 6; i++) {
                int nh = h + dh[i];
                int nr = r + dr[i];
                int nc = c + dc[i];
                // 배열 범위 밖에 있으면 무시
                if (!isIn(nh, nr, nc)) continue;
                // 익지 않은 토마토면
                if (box[nh][nr][nc] == 0) {
                    box[nh][nr][nc] = box[h][r][c] + 1;
                    q.offer(new Tomato(nh, nr, nc));
                }
            }
        }
        // 익지 않은 토마토가 있다면
        if (checkZero()) return -1;
            // 모든 토마토가 다 익었다면
        else {
            max = Integer.MIN_VALUE;
            // 최소 며칠이 걸리는지 계산
            for (int h = 0; h < H; h++) {
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < M; c++) {
                        max = Math.max(max, box[h][r][c]);
                    }
                }
            }
        }
        if (max == 1) return 0;
        else return max - 1;
    }

    private static boolean checkZero() {
        for (int h = 0; h < H; h++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (box[h][r][c] == 0) return true;
                }
            }
        }
        return false;
    }

    private static boolean isIn(int nh, int nr, int nc) {
        return nh >= 0 && nh < H && nr >= 0 && nr < N && nc >= 0 && nc < M;
    }

}
