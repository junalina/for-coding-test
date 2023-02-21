package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14888
 *
 * 연산자 끼워넣기
 *
 * N개의 수로 이루어진 수열, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자
 * N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하라
 *
 * 풀이
 * 수 사이에 들어갈 연산자를 순열을 이용해서 끼워넣음
 */
public class 연산자끼워넣기 {

    static int N, max, min;
    static int[] num, oper;
    static int[] operCnt = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        // 수열 N개
        num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operCnt[i] = Integer.parseInt(st.nextToken());
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        oper = new int[N-1];

        perm(0);

        sb.append(max).append("\n").append(min);

        System.out.println(sb);
    }

    // 순열
    private static void perm(int cnt) {
        if (cnt == N-1) {
            int res = calculation();
            max = Math.max(max, res);
            min = Math.min(min, res);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (operCnt[i] > 0) {
                oper[cnt] = i;
                operCnt[i]--;
                perm(cnt+1);
                operCnt[i]++;
            }
        }
    }

    // 식의 결과 구하기
    private static int calculation() {
        int res = num[0];
        for (int i = 0; i < N-1; i++) {
            switch (oper[i]) {
                case 0: // 덧셈
                    res += num[i+1];
                    break;
                case 1: // 뺄셈
                    res -= num[i+1];
                    break;
                case 2: // 곱셈
                    res *= num[i+1];
                    break;
                case 3: // 나눗셈
                    res /= num[i+1];
                    break;
            }
        }
        return res;
    }
}
