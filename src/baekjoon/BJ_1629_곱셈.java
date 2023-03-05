package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1629
 * <p>
 * 곱셈
 * A를 B번 곱한 수를 C로 나눈 나머지 출력
 * <p>
 * 풀이
 * 분할정복
 */

public class BJ_1629_곱셈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        System.out.println(power(A, B, C));
    }

    private static long power(long a, long b, long c) {
        if (b == 1) return a % c;
        else {
            // 재귀함수
            long temp = power(a, b / 2, c);
            if (b % 2 == 0) return temp * temp % c;
            else return ((temp * temp) % c * a) % c;
        }
    }
}
