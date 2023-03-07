package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1051
 * <p>
 * 숫자 정사각형
 * NxM 직사각형
 * 꼭짓점에 쓰여 있는 수가 모두 같은 가장 큰 정사각형의 크기 출력
 * <p>
 * 풀이
 * 브루트포스
 */

public class BJ_1051_숫자정사각형 {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 직사각형 정보
        int[][] arr = new int[N][M];
        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < M; c++) {
                arr[r][c] = str.charAt(c) - '0';
            }
        }

        int max = 1;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                int size = 1;
                while (true) {
                    int x = arr[r][c];
                    if (!isIn(r + size, c + size)) break;
                    // 네 꼭짓점의 수가 모두 같다면
                    if (arr[r + size][c] == x && arr[r][c + size] == x && arr[r + size][c + size] == x)
                        max = Math.max(max, (size + 1) * (size + 1));
                    size++;
                }
            }
        }

        System.out.println(max);

    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
