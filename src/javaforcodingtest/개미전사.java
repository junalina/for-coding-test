package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 최소한 한 칸 이상 떨어진 식량창고 약탈을 해서 얻을 수 있는 식량의 최대값을 구하라
 * input
 * 4
 * 1 3 1 5
 * output
 * 8
 */

public class 개미전사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 식량창고의 개수 N 입력
        int N = Integer.parseInt(br.readLine());

        // 식량창고에 저장된 식량의 개수 K 입력
        int[] K = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            K[i] = Integer.parseInt(st.nextToken());
        }

        // 식량창고에 저장할 수 있는 최대값을 저장할 배열
        int[] DP = new int[N];
        // 다이나믹 프로그래밍 진행(보텀업)
        DP[0] = K[0];
        DP[1] = K[1];
        for (int i = 2; i < N; i++) {
            DP[i] = Math.max(K[i] + DP[i-2], DP[i-1]);
        }
        System.out.println(DP[N-1]);
    }
}
