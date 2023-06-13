package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/1992
 * <p>
 * 쿼드트리
 * 흑백 영상 압축하여 표현하는 데이터 구조
 * 같은 숫자의 점들이 한 곳에 많이 몰려있으면, 쿼드트리에서는 이를 압축
 * <p>
 * 풀이
 * 분할 정복
 */

public class BJ_1992_쿼드트리 {

    static int[][] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 영상의 크기를 나타내는 숫자 N
        int N = Integer.parseInt(br.readLine());

        // 영상의 각 점들 입력
        arr = new int[N][N];
        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < N; c++) {
                arr[r][c] = str.charAt(c) - '0';
            }
        }

        quadTree(0, 0, N);
        System.out.println(sb);

    }

    private static void quadTree(int r, int c, int n) {
        if (canCompress(r, c, n)) {
            sb.append(arr[r][c]);
            return;
        }

        sb.append("(");

        quadTree(r, c, n / 2); // 왼쪽 위
        quadTree(r, c + n / 2, n / 2); // 오른쪽 위
        quadTree(r + n / 2, c, n / 2); // 왼쪽 아래
        quadTree(r + n / 2, c + n / 2, n / 2); // 오른쪽 아래

        sb.append(")");
    }

    private static boolean canCompress(int r, int c, int n) {
        int color = arr[r][c];
        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                if (arr[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }

}
