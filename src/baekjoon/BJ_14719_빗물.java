package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14719
 * <p>
 * 빗물
 * 2차원 세계에 블록
 * 비가 오면 블록 사이에 빗물이 고인다.
 * 고이는 빗물의 총량 출력
 * <p>
 * 풀이
 * 구현
 */

public class BJ_14719_빗물 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 세로 길이
        int H = Integer.parseInt(st.nextToken());
        // 가로 길이
        int W = Integer.parseInt(st.nextToken());

        // 블록이 쌓이 높이 입력
        int[] block = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            block[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for (int i = 1; i < W - 1; i++) {
            int left = 0;
            int right = 0;
            // 왼쪽 높이 구하기
            for (int j = 0; j < i; j++) {
                left = Math.max(left, block[j]);
            }
            // 오른쪽 높이 구하기
            for (int j = i + 1; j < W; j++) {
                right = Math.max(right, block[j]);
            }

            int sum = (Math.min(left, right) - block[i]);
            if (sum > 0) ans += sum;
        }
        System.out.println(ans);
    }
}
