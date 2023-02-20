package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * input
 * 15 14
 * 00000111100000
 * 11111101111110
 * 11011101101110
 * 11011101100000
 * 11011111111111
 * 11011111111100
 * 11000000011111
 * 01111111111111
 * 00000000011111
 * 01111111111000
 * 00011111111000
 * 00000001111000
 * 11111111110011
 * 11100011111111
 * 11100011111111
 * output
 * 8
 */

public class 음료수얼려먹기 {

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N, M을 공백으로 구분하여 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 2차원 배열의 맵 정보 입력받기
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = str.charAt(c) - '0';
            }
        } // 입력 완료

        int cnt = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                // 현재 위치에서 DFS 수행
                if(DFS(r, c)) cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static boolean DFS(int r, int c) {
        // 주어진 범위를 벗어나는 경우에는 즉시 종료
        if(isNotIn(r, c)) return false;
        // 현재 노드를 아직 방문하지 않았다면
        if(map[r][c] == 0) {
            // 해당 노드 방문 처리
            map[r][c] = 1;
            // forcodingtest.상하좌우 모두 재귀적으로 호출
            DFS(r - 1, c);
            DFS(r + 1, c);
            DFS(r, c - 1);
            DFS(r, c + 1);
            return true;
        }
        return false;
    }

    private static boolean isNotIn(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }
}
