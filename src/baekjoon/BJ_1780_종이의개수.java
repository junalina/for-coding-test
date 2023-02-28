package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1780
 * N x N 종이, -1, 0, 1 중 하나 저장
 * 규칙에 따라 적절한 크기로 분할
 * 1. 만약 종이가 모두 같은 수로 되어 있다면 이 종이를 그대로 사용한다.
 * 2. (1)이 아닌 경우에는 종이를 같은 크기의 종이 9개로 자르고, 각각의 잘린 종이에 대해서 (1)의 과정을 반복한다.
 * -1로만 채워진 종이의 개수, 0으로만 채워진 종이의 개수, 1로만 채워진 종이의 개수를 출력
 * <p>
 * 풀이
 * 분할 정복
 */

public class BJ_1780_종이의개수 {

    static int minusOne, zero, one;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // 행렬
        arr = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        minusOne = 0;
        zero = 0;
        one = 0;

        divide(0, 0, N);

        sb.append(minusOne).append("\n");
        sb.append(zero).append("\n");
        sb.append(one);

        System.out.println(sb);

    }

    private static void divide(int r, int c, int n) {
        // 모두 같은 수라면
        if (check(r, c, n)) {
            // 그 수에 해당하는 개수 추가
            switch (arr[r][c]) {
                case -1:
                    minusOne++;
                    break;
                case 0:
                    zero++;
                    break;
                case 1:
                    one++;
            }
            return;
        } else {
            divide(r, c, n / 3);
            divide(r, c + n / 3, n / 3);
            divide(r, c + 2 * n / 3, n / 3);
            divide(r + n / 3, c, n / 3);
            divide(r + n / 3, c + n / 3, n / 3);
            divide(r + n / 3, c + 2 * n / 3, n / 3);
            divide(r + 2 * n / 3, c, n / 3);
            divide(r + 2 * n / 3, c + n / 3, n / 3);
            divide(r + 2 * n / 3, c + 2 * n / 3, n / 3);
        }
    }

    private static boolean check(int r, int c, int n) {
        // 처음 숫자 저장
        int num = arr[r][c];
        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                // 처음 숫자와 숫자가 다르면
                if (arr[i][j] != num) return false;
            }
        }
        // 모든 숫자가 처음 숫자와 같다면
        return true;
    }
}
