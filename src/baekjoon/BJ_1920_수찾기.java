package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1920
 * <p>
 * 수 찾기
 * N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램 작성
 * <p>
 * 풀이
 * 이진 탐색
 */
public class BJ_1920_수찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // N개의 정수
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        // M개의 수들
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(0, N - 1, A, x)).append("\n");
        }

        System.out.println(sb);

    }

    private static int binarySearch(int start, int end, int[] a, int x) {
        while (start <= end) {
            int mid = (start + end) / 2;
            if (a[mid] == x) return 1;
            else if (a[mid] > x) end = mid - 1;
            else if (a[mid] < x) start = mid + 1;
        }
        return 0;
    }
}
