package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * forcodingtest.금광
 * n x m 크기의 forcodingtest.금광, 1 x 1 크기의 칸
 * 채굴자는 첫 번째 열부터 출발하여 금을 캐기 시작
 * 맨 처음 첫 번째 열의 어느 행에서든 출발 가능
 * 이후에 m번에 걸쳐 매번 오른쪽 위, 오른쪽, 오른쪽 아래 3가지 중 하나의 위치로 이동
 * 결과적으로 채굴자가 얻을 수 있는 금의 최대 크기 출력
 * input
 * 2
 * 3 4
 * 1 3 3 2 2 1 4 1 0 6 4 7
 * 4 4
 * 1 3 1 5 2 2 4 1 5 0 2 3 0 6 1 2
 * output
 * 19
 * 16
 */
public class 금광 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 T
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            // n, m 공백 구분하여 입력
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // n x m개의 위치에 매장된 금의 개수 공백 구분하여 입력
            int[][] arr = new int[n][m];
            st = new StringTokenizer(br.readLine());
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    arr[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            // 배열 복사
            int[][] dp = new int[n][m];
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    dp[r][c] = arr[r][c];
                }
            }

            // 다이나믹 프로그래밍
            for (int c = 1; c < m; c++) {
                for (int r = 0; r < n; r++) {
                    int leftUp, left, leftDown;
                    // 왼쪽 위에서 오는 경우
                    if (r == 0) leftUp = 0;
                    else leftUp = dp[r - 1][c - 1];
                    // 왼쪽에서 오는 경우
                    left = dp[r][c - 1];
                    // 왼쪽 아래에서 오는 경우
                    if (r == n - 1) leftDown = 0;
                    else leftDown = dp[r + 1][c - 1];
                    dp[r][c] = arr[r][c] + Math.max(leftUp, Math.max(left, leftDown));
                }
            }
            int res = 0;
            for (int r = 0; r < n; r++) {
                res = Math.max(res, dp[r][m - 1]);
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }
}
