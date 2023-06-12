package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/10816
 * <p>
 * 숫자 카드 2
 * 숫자 카드는 정수 하나가 적혀져 있는 카드
 * 상근이는 숫자 카드 N개 소유
 * 정수 M개가 주어졌을 때, 이 수가 적혀있는 숫자 카드를 상근이가 몇 개 가지고 있는지 출력
 * <p>
 * 풀이
 * 이분 탐색
 */

public class BJ_10816_숫자카드2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 상근이가 가지고 있는 숫자 카드의 개수 N
        int N = Integer.parseInt(br.readLine());

        // 숫자 카드에 적혀있는 정수
        int[] num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        // 숫자 카드 정렬
        Arrays.sort(num);

        // 정수 M
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            int low = lower(num, target, 0, N - 1); // target이 처음 나오는 위치
            int high = upper(num, target, 0, N - 1); // target보다 큰 값이 처음 나오는 위치
            sb.append(high - low).append(" ");
        }
        System.out.println(sb);
    }

    private static int upper(int[] num, int target, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;
            // target보다 큰 값 찾기
            if (target < num[mid]) {
                // 중간값보다 target이 작은 경우 중간값보다 왼쪽 확인
                end = mid - 1;
            } else {
                // 중간값보다 target이 크거나 같은 경우 오른쪽 확인
                start = mid + 1;
            }
        }
        return end;
    }

    private static int lower(int[] num, int target, int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;
            // target보다 같거나 큰 값 찾기
            if (target <= num[mid]) {
                // 중간값보다 target이 작거나 같은 경우 왼쪽 확인
                end = mid - 1;
            } else {
                // 중간값보다 target이 큰 경우 오른쪽 확인
                start = mid + 1;
            }
        }
        return end;
    }

}
