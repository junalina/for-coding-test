package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1012
 * <p>
 * 유기농 배추
 * 배추흰지렁이 : 배추에 한 마리라도 살고 있으면 이 지렁이는 인접한 다른 배추로 이동 가능
 * 필요한 최소의 배추흰지렁이 마리 수 출력
 * <p>
 * 풀이
 * DFS
 */
public class BJ_1012_유기농배추 {

    static int M, N, cnt;
    static int[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            map = new int[M][N];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                map[X][Y] = 1;
            }

            // 최소의 배추 흰지렁이 마리 수
            cnt = 0;

            for (int r = 0; r < M; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] == 1) {
                        dfs(r, c);
                        cnt++;
                    }
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int r, int c) {
        if (!isIn(r, c)) return;
        map[r][c] = 0; // 방문처리
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (isIn(nr, nc) && map[nr][nc] == 1) dfs(nr, nc);
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < M && c >= 0 && c < N;
    }
}
