package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/2579
 * <p>
 * 계단 오르기
 * 계단 아래 시작점부터 계단 꼭대기에 위치한 도착점까지 가는 게임
 * 1. 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
 * 2. 연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
 * 3. 마지막 도착 계단은 반드시 밟아야 한다.
 * 첫째 줄에 계단 오르기 게임에서 얻을 수 있는 총 점수의 최댓값 출력
 * <p>
 * 풀이
 * DP
 */

public class BJ_2579_계단오르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 계단의 개수
        int n = Integer.parseInt(br.readLine());

        // 각 계단에 쓰여 있는 점수
        int[] stair = new int[300];
        for (int i = 0; i < n; i++) {
            stair[i] = Integer.parseInt(br.readLine());
        }

        // 각 계단 별 최댓값
        int[] score = new int[300];
        score[0] = stair[0];
        score[1] = stair[0] + stair[1];
        score[2] = Math.max(stair[0], stair[1]) + stair[2];

        // DP
        for (int i = 3; i < n; i++) {
            score[i] = Math.max(score[i - 3] + stair[i - 1], score[i - 2]) + stair[i];
        }

        System.out.println(score[n - 1]);

    }
}
