package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14501
 * <p>
 * forcodingtest.퇴사
 * <p>
 * 상담원 백준 forcodingtest.퇴사
 * N + 1일째 되는 날 forcodingtest.퇴사 하기 위해, 남은 N일 동안 최대한 많은 상담 하려고 함
 * 하루에 하나씩 서로 다른 상담의 상담이 예약
 * 상담을 적절히 했을 때, 백준이가 얻을 수 있는 최대 이익 출력
 */
public class 퇴사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        // 상담 일정
        int[][] reserve = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            reserve[i][0] = Integer.parseInt(st.nextToken());
            reserve[i][1] = Integer.parseInt(st.nextToken());
        }

        // 다이나믹 프로그래밍
        int[] dp = new int[N + 1];
        for (int i = 0; i < N; i++) {
            if (i + reserve[i][0] <= N) {
                dp[i + reserve[i][0]] = Math.max(dp[i + reserve[i][0]], dp[i] + reserve[i][1]);
            }
            // 해당 날짜에 일할 수 없다면 이전까지 일한 최대 수당을 넣어줌
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }
        System.out.println(dp[N]);
    }
}
