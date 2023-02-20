package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N개의 동전을 이용하여 만들 수 없는 양의 정수 금액 중 최솟값을 구하라
 * input
 * 5
 * 3 2 1 1 9
 * output
 * 8
 */

public class 만들수없는금액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] coin = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            coin[i] = Integer.parseInt(st.nextToken());
        }

        // 오름차순 정렬
        Arrays.sort(coin);

        int target = 1;
        for (int i = 0; i < N; i++) {
            // 만들 수 없는 금액을 찾았을 때 반복 종료
            if (target < coin[i]) break;
            target += coin[i];
        }

        System.out.println(target);

    }
}
