package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/11660
 * <p>
 * 구간 합 구하기 5
 * (x1, y1)부터 (x2, y2)까지 합 출력
 * <p>
 * 풀이
 * 누적 합
 */

public class BJ_11660_구간합구하기5 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 표의 크기 N
        int N = Integer.parseInt(st.nextToken());
        // 합을 구해야 하는 횟수 M
        int M = Integer.parseInt(st.nextToken());

        // 누적 합 구하기
        int[][] prefixSum = new int[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                prefixSum[r][c] = prefixSum[r - 1][c] + prefixSum[r][c - 1] - prefixSum[r - 1][c - 1]
                        + Integer.parseInt(st.nextToken());
            }
        }

        int sum = 0;
        // x1, y1, x2, y2 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // 구간 합
            sum = prefixSum[x2][y2] - prefixSum[x1 - 1][y2] - prefixSum[x2][y1 - 1] + prefixSum[x1 - 1][y1 - 1];

            sb.append(sum).append("\n");

        }

        System.out.println(sb);

    }


}
