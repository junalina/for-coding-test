package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/17090
 * <p>
 * 미로 탈출하기
 * NxM 미로
 * U : (r-1, c)
 * R : (r, c+1)
 * D : (r+1, c)
 * L : (r, c-1)
 * 미로에서 탈출 가능한 칸의 수 출력
 * <p>
 * 풀이
 * DFS, 메모이제이션
 */

public class BJ_17090_미로탈출하기 {
    static int N, M, cnt;
    static char[][] map;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 미로의 크기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 미로 정보
        map = new char[N][M];
        // 메모이제이션
        memo = new int[N][M];
        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = str.charAt(c);
                memo[r][c] = -1;
            }
        }

        cnt = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                cnt += dfs(r, c);
            }
        }

        System.out.println(cnt);

    }

    private static int dfs(int r, int c) {

        if (!isIn(r, c)) {
            return 1;
        }

        if (memo[r][c] != -1) return memo[r][c];

        memo[r][c] = 0;

        switch (map[r][c]) {
            case 'U':
                memo[r][c] = dfs(r - 1, c);
                break;
            case 'R':
                memo[r][c] = dfs(r, c + 1);
                break;
            case 'D':
                memo[r][c] = dfs(r + 1, c);
                break;
            case 'L':
                memo[r][c] = dfs(r, c - 1);
                break;
        }

        return memo[r][c];

    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
