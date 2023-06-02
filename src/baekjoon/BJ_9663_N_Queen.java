package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/9663
 * <p>
 * N-Queen
 * N x N 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제
 * 서로 공격할 수 없게 퀸을 놓는 방법의 수 출력
 * <p>
 * 풀이
 * 백트래킹
 */

public class BJ_9663_N_Queen {

    static int N, cnt;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        nQueen(0);

        System.out.println(cnt);
    }

    private static void nQueen(int idx) {
        if (idx == N) {
            cnt++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[idx] = i;
            // 퀸을 놓을 수 있는 위치이면
            if (canPut(idx)) nQueen(idx + 1);
        }
    }

    private static boolean canPut(int idx) {
        for (int i = 0; i < idx; i++) {
            // 같은 행에 존재할 경우
            if (arr[idx] == arr[i]) return false;

            // 대각선에 존재할 경우
            else if (Math.abs(idx - i) == Math.abs(arr[idx] - arr[i])) return false;
        }
        return true;
    }
}
