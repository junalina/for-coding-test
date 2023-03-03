package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/11047
 * <p>
 * 동전 0
 * 동전 총 N 종류
 * 가치의 합 K로 만드려고 할 때, 필요한 동전 개수의 최소값 출력
 * <p>
 * 풀이
 * 그리디
 */

public class BJ_11047_동전0 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] money = new int[N];
        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(br.readLine());
        }

        // 동전 개수의 최솟값
        int cnt = 0;

        while (K != 0) {
            int max = 0;
            for (int m : money) {
                // K보다 작은 동전의 최대값 찾기
                if (K >= m) {
                    max = m;
                } else break;
            }
            K -= max;
            cnt++;
        }

        System.out.println(cnt);
    }
}
