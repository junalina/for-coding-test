package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/15486
 * <p>
 * 퇴사 2
 * N일 동안 상담을 적절히 했을 때, 백준이가 얻을 수 있는 최대 이익 출력
 * <p>
 * 풀이
 * DP
 */

public class BJ_15486_퇴사2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // 상담을 완료하는데 걸리는 기간
        int[] T = new int[N + 1];
        // 상담을 했을 때 받을 수 있는 금액
        int[] P = new int[N + 1];
        // 상담 일정표
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        // 다이나믹 프로그래밍
        int[] dp = new int[N + 2];
        for (int i = 1; i <= N; i++) {
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            // i날 상담을 완료하는데 걸리는 시간이 N+1일 이하이면
            if (i + T[i] <= N + 1) dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
        }

        // 백준이가 얻을 수 있는 최대 이익 출력
        System.out.println(dp[N + 1]);
    }
}
