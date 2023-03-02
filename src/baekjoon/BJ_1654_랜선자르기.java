package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1654
 * <p>
 * 랜선 자르기
 * 박성원 : N개의 랜선을 만들어야함
 * 오영식 : K개의 랜선을 가지고 있음, 랜선 길이가 제각각, 잘라서 N개의 랜선을 만들어야 함
 * N개를 만들 수 있는 랜선의 최대 길이 출력
 * <p>
 * 풀이
 * 이진 탐색
 */

public class BJ_1654_랜선자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // K개의 랜선 길이
        int[] lan = new int[K];
        for (int i = 0; i < K; i++) {
            lan[i] = Integer.parseInt(br.readLine());
        }

        // 이진탐색
        long start = 1;
        long end = Integer.MAX_VALUE;
        while (start <= end) {
            long mid = (start + end) / 2;
            long cnt = 0;
            for (int i = 0; i < K; i++) {
                cnt += lan[i] / mid;
            }
            if (cnt >= N) {
                start = mid + 1;
            } else end = mid - 1;
        }

        System.out.println(end);

    }
}
