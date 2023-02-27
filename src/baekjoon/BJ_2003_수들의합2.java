package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2003
 * <p>
 * 수들의 합 2
 * N개의 수로 된 수열 A[1], A[2], …, A[N] 이 있다. 이 수열의 i번째 수부터 j번째 수까지의 합 A[i] + A[i+1] + … + A[j-1] + A[j]가 M이 되는 경우의 수 출력
 * <p>
 * 풀이
 * 투 포인터
 */

public class BJ_2003_수들의합2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        int start = 0;
        int end = 0;
        int sum = 0;

        while (true) {
            // sum이 M보다 크거나 같으면 sum에서 start값 빼고 start 인덱스 값 하나 증가
            if (sum >= M) sum -= arr[start++];
            // end 인덱스 값이 N에 도착했다면 while문 탈출
            else if (end == N) break;
            // sum이 M보다 작으면 sum에서 end값 더하고 end 인덱스 값 하나 증가
            else if (sum < M) sum += arr[end++];

            // sum이 M과 같으면 경우의 수 증가
            if (sum == M) cnt++;
        }

        System.out.println(cnt);
    }
}
