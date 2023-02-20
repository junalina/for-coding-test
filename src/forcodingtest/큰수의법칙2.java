package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * input
 * 5 8 3
 * 2 4 5 4 6
 * output
 * 46
 */

public class 큰수의법칙2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N, M, K 공백으로 구분하여 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] num = new int[N];

        // N개의 수를 공백으로 구분하여 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        } // 입력 끝

        Arrays.sort(num); // 배열 정렬

        // 가장 큰 수가 더해지는 횟수 계산
        int cnt = M / (K + 1) * K;
        cnt += M % (K + 1);

        int sum = 0;
        sum += num[N-1] * cnt; // 가장 큰 수 더하기
        sum += num[N-2] * (M - cnt); // 두 번째로 큰 수 더하기

        System.out.println(sum);

    }
}