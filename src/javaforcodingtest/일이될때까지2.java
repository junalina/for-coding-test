package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 어떠한 수 N이 1이 될 때까지 두 과정 중 하나를 반복적으로 선택해 수행
 * 단, 두 번째 연산은 N이 K로 나누어떨어질 때만 선택할 수 있다.
 * 1. N에서 1을 뺀다.
 * 2. N을 K로 나눈다.
 * 풀이 : N이 K로 나누어 떨어지면 N을 K로 나눈다. 그렇지 않다면 N에서 1을 뺀다.
 * input
 * 25 5
 * output
 * 2
 */

public class 일이될때까지2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N, K를 공백으로 구분하여 입력받기
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int cnt = 0; // 최소 횟수

        while (N != 1) {
            if (N % K == 0) {
                N /= K;
                cnt++;
            } else {
                N--;
                cnt++;
            }
        }
        System.out.println(cnt);

    }
}
