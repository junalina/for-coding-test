package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2805
 * <p>
 * 나무 자르기
 * 나무 M미터가 필요
 * 절단기 높이 H를 지정해서 한 줄에 연속해있는 나무를 모두 절단
 * <p>
 * 풀이
 * 이진 탐색
 */

public class BJ_2805_나무자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 나무의 수
        int N = Integer.parseInt(st.nextToken());
        // 상근이가 집으로 가져가려고 하는 나무의 길이
        int M = Integer.parseInt(st.nextToken());

        // 나무의 높이
        int[] tree = new int[N];
        long start = 0;
        long end = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            end = Math.max(end, tree[i]);
        }

        // 이진 탐색
        while (start <= end) {
            long mid = (start + end) / 2;
            long sum = 0;
            for (int i = 0; i < N; i++) {
                if (tree[i] >= mid) sum += tree[i] - mid;
            }
            if (sum >= M) {
                start = mid + 1;
            }
            else end = mid - 1;
        }
        System.out.println(end);
    }
}
