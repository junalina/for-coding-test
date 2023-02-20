package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/2447
 * <p>
 * 별 찍기 - 10
 * <p>
 * 재귀적인 패턴으로 별 찍기
 * N이 3의 거듭제곱
 * 크기 3의 패턴은 가운데에 공백이 있고, 가운데를 제외한 모든 칸에 별이 하나씩 있는 패턴이다.
 * ***
 * * *
 * ***
 */
public class BJ_2447_별찍기_10 {

    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        arr = new char[N][N];

        star(0, 0, N, false);

        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                sb.append(arr[r][c]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void star(int r, int c, int n, boolean blank) {
        // 공백칸인 경우
        if (blank) {
            for (int i = r; i < r + n; i++) {
                for (int j = c; j < c + n; j++) {
                    arr[i][j] = ' ';
                }
            }
            return;
        }

        // 더이상 쪼갤 수 없는 블록일 때
        if (n == 1) {
            arr[r][c] = '*';
            return;
        }

        int size = n / 3;
        int cnt = 0;
        for (int i = r; i < r + n; i += size) {
            for (int j = c; j < c + n; j += size) {
                cnt++;
                // 공백 칸일 경우
                if (cnt == 5) star(i, j, size, true);
                else star(i, j, size, false);
            }
        }

    }

}
