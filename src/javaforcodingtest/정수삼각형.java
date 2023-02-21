package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1932
 * <p>
 * 정수 삼각형
 * <p>
 * 맨 위층부터 시작해서 아래에 있는 수 중 하나를 선택하여 아래층으로 내려올 때, 선택된 수의 합이 최대가 되는 경로에 있는 수의 합 출력
 * 아래층에 있는 수는 현재 층에서 선택된 수의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택할 수 있음
 * <p>
 * 풀이
 * 정사각형 배열로 생각
 * 아래층 수 선택은 아래 or 오른쪽 아래 선택
 */
public class 정수삼각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 삼각형의 크기 n
        int n = Integer.parseInt(br.readLine());
        // 정사각형 배열로 생각해서 입력 받기
        int[][] arr = new int[n][n];
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            int c = 0;
            while (st.hasMoreTokens()) {
                arr[r][c] = Integer.parseInt(st.nextToken());
                c++;
            }
        }

        // 배열 복사
        int[][] dp = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                dp[r][c] = arr[r][c];
            }
        }

        // 다이나믹 프로그래밍
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int up, leftUp;
                // 위에서 내려왔을때
                if (r == 0) up = 0;
                else up = dp[r - 1][c];
                // 왼쪽 위에서 내려왔을 때
                if (r == 0 || c == 0) leftUp = 0;
                else leftUp = dp[r - 1][c - 1];
                dp[r][c] = arr[r][c] + Math.max(up, leftUp);
            }
        }

        // dp 배열 마지막 행에서 최대값 찾기
        int res = 0;
        for (int c = 0; c < n; c++) {
            res = Math.max(res, dp[n - 1][c]);
        }

        System.out.println(res);

    }
}
