package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/10819
 * <p>
 * 차이를 최대로
 * 배열 A가 주어지면 정수의 순서를 적절히 바꿔서 다음 식의 최댓값을 출력
 * |A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2] - A[N-1]|
 * <p>
 * 풀이
 * 순열
 */

public class BJ_10819_차이를최대로 {
    static int N, max;
    static int[] A, arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 배열 A 입력
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        max = 0;
        arr = new int[N];
        visited = new boolean[N];

        perm(0);

        System.out.println(max);
    }

    private static void perm(int idx) {
        if (idx == N) {
            // 계산
            max = Math.max(max, cal());
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[idx] = A[i];
                perm(idx + 1);
                visited[i] = false;
            }
        }
    }

    private static int cal() {
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            sum += Math.abs(arr[i] - arr[i + 1]);
        }
        return sum;
    }
}
