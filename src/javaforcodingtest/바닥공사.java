package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 가로 N, 세로 2인 정사각형 형태의 바닥
 * 1 x 2, 2 x 1, 2 x 2 덮개를 이용해 채운다
 * 바닥을 채우는 모든 경우의 수를 구하라
 * input
 * 3
 * output
 * 5
 */
public class 바닥공사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N 입력받기
        int N = Integer.parseInt(br.readLine());

        // 다이나믹 프로그래밍(보텀업)
        int[] DP = new int[1001];
        DP[1] = 1;
        DP[2] = 3;
        for (int i = 3; i <= N; i++) {
            DP[i] = (DP[i-1] + 2 * DP[i-2]) % 796796;
        }
        System.out.println(DP[N]);
    }
}
