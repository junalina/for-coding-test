package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2630
 * <p>
 * 색종이 만들기
 * 입력으로 주어진 종이의 한 변의 길이 N과 각 정사각형칸의 색(하얀색 또는 파란색)이 주어질 때 잘라진 하얀색 색종이와 파란색 색종이의 개수 출력
 * <p>
 * 풀이
 * 분할 정복
 */

public class BJ_2630_색종이만들기 {
    static int white, blue;
    static int[][] paper;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 전체 종이의 한 변의 길이
        int N = Integer.parseInt(br.readLine());

        // 색종이의 정보
        paper = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                paper[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        white = 0;
        blue = 0;

        // 색종이 나누기
        divide(0, 0, N);

        sb.append(white).append("\n");
        sb.append(blue);

        System.out.println(sb);


    }

    private static void divide(int r, int c, int n) {
        // 색종이가 전부 색이 같으면
        if (check(r, c, n)) {
            color(r, c);
            return;
        }
        // 색종이 다른 색이 존재하면 분할하기
        else {
            divide(r, c, n / 2);
            divide(r + n / 2, c, n / 2);
            divide(r, c + n / 2, n / 2);
            divide(r + n / 2, c + n / 2, n / 2);
        }
    }

    private static void color(int r, int c) {
        if (paper[r][c] == 0) white++;
        else blue++;
    }

    private static boolean check(int r, int c, int n) {
        int color = paper[r][c];
        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                if (paper[i][j] != color) return false;
            }
        }
        return true;
    }

}
