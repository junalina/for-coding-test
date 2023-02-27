package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/3273
 * <p>
 * 두 수의 합
 * n개의 서로 다른 양의 정수로 이루어진 수열
 * ai + aj = x (1 ≤ i < j ≤ n)을 만족하는 (ai, aj)쌍의 수 출력
 * <p>
 * 풀이
 * 투 포인터
 */
public class BJ_3273_두수의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 수열의 크기
        int n = Integer.parseInt(br.readLine());

        // 수열에 포함되는 수
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());

        int cnt = 0;
        int i = 0;
        int j = a.length - 1;
        int sum = 0;

        Arrays.sort(a);

        while (i < j) {
            sum = a[i] + a[j];

            if (sum <= x) i++;
            else if (sum > x) j--;

            if (sum == x) cnt++;
        }
        System.out.println(cnt);
    }
}
