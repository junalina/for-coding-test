package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/10972
 * <p>
 * 다음 순열
 * 1 ~ N까지의 수로 이루어진 순열
 * 입력으로 주어진 순열의 다음에 오는 순열 출력 (사전순으로)
 * <p>
 * 풀이
 * Next Permutation
 */

public class BJ_10972_다음순열 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 입력으로 주어진 순열의 다음에 오는 순열
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        nextPermutation(arr, N);
    }

    private static void nextPermutation(int[] arr, int N) {
        // 주어진 순열의 뒤부터 탐색, 증가하는 부분을 찾는다.
        int idx = N - 1;
        while (idx > 0 && arr[idx - 1] > arr[idx]) idx--;

        // 만약 증가하는 부분이 존재하지 않는다면 다음 순열은 존재하지 않는다. (-1 출력)
        if (idx == 0) {
            System.out.println(-1);
            return;
        }

        // 해당 인덱스를 기준으로, 좌/우 지점으로 나눈다.
        // 좌측의 제일 오른쪽 숫자에 대하여, 우측의 제일 오른쪽 지점부터 탐색하며 큰 수를 찾는다.
        int bigIdx = N - 1;
        while (bigIdx > idx && arr[idx - 1] > arr[bigIdx]) bigIdx--;

        // 해당 숫자를 찾았다면 각 숫자를 서로 swap
        int temp = arr[idx - 1];
        arr[idx - 1] = arr[bigIdx];
        arr[bigIdx] = temp;

        // 우측 지점 정렬
        Arrays.sort(arr, idx, N);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }
}
