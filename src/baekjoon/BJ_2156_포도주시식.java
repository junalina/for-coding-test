package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/2156
 * <p>
 * 포도주 시식
 * 다양한 포도주
 * 1. 포도주 잔을 선택하면 그 잔의 포도주는 모두 마셔야 하고, 마신 후에는 원래 위치에 다시 놓아야 한다.
 * 2. 연속으로 놓여 있는 3잔을 모두 마실 수는 없다.
 * 최대로 마실 수 있는 포도주의 양을 출력
 * <p>
 * 풀이
 * DP
 */

public class BJ_2156_포도주시식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 포도주 잔의 개수
        int n = Integer.parseInt(br.readLine());

        // 포도주의 양
        int[] drink = new int[n];
        for (int i = 0; i < n; i++) {
            drink[i] = Integer.parseInt(br.readLine());
        }

        // 다이나믹 프로그래밍
        int[] dp = new int[n];
        dp[0] = drink[0];
        for (int i = 1; i < n; i++) {
            if (i == 1) {
                dp[1] = dp[0] + drink[1];
                continue;
            } else if (i == 2) {
                dp[2] = Math.max(dp[1], Math.max(dp[0] + drink[2], drink[1] + drink[2]));
                continue;
            }
            // OOX, OXO, XOO 비교
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + drink[i], dp[i - 3] + drink[i - 1] + drink[i]));
        }
        System.out.println(dp[n - 1]);
    }
}
