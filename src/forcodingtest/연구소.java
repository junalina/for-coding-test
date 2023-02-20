package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14502
 *
 * forcodingtest.연구소
 *
 * 연구소에서 바이러스 유출
 * 확산을 막기 위해서 연구소에 벽을 세우려고 함
 * forcodingtest.연구소 N x M 직사각형, 1 x 1 크기의 정사각형으로 나누어짐
 * 바이러스 상하좌우로 인접한 빈 칸으로 퍼짐
 * 새로 세울 수 있는 벽의 개수 3개, 꼭 3개를 세워야 함
 * 바이러스가 퍼질 수 없는 곳 : 안전 영역
 * 안전 영역 크기의 최댓값을 구하라
 *
 * 풀이
 * 1. DFS로 벽을 세우며 안전 영역 크기 계산
 * 2. DFS로 바이러스 확산
 * 3. 안전 영역 크기 구하기
 */

public class 연구소 {

    static int N, M, max;
    static int[][] map, copy;

    // forcodingtest.상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 세로 N, 가로 M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 지도
        map = new int[N][M];
        copy = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        } // 입력 끝

        max = Integer.MIN_VALUE;

        // 조합으로 빈 칸에 벽 세우기
        dfs(0);

        System.out.println(max);
    }

    // 조합
    private static void dfs(int cnt) {
        if(cnt == 3) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    copy[r][c] = map[r][c];
                }
            }
            // 벽을 세웠으니 바이러스 위치에서 재귀적으로 바이러스 확산
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (copy[r][c] == 2) virus(r, c);
                }
            }
            // 안전 영역 크기 구하기
            max = Math.max(max, getSafe());
            return;
        }
        // 빈 공간에 벽 설치
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 0) {
                    map[r][c] = 1;
                    cnt++;
                    dfs(cnt);
                    map[r][c] = 0;
                    cnt--;
                }
            }
        }
    }

    private static int getSafe() {
        int size = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (copy[r][c] == 0) size++;
            }
        }
        return size;
    }

    private static void virus(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            // 지도 범위 안에서
            if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                // 빈칸이면 바이러스 확산
                if (copy[nr][nc] == 0) {
                    copy[nr][nc] = 2;
                    virus(nr, nc);
                }
            }
        }
    }
}
