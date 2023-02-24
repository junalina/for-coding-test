package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2583
 *
 * 영역 구하기
 * 모눈종이 위에 직사각형 3개를 그렸다면 나머지 부분이 몇 개의 분리된 영역으로 나누어지는지, 각 영역의 넓이가 얼마인지 출력
 *
 */
public class BJ_2583_영역구하기 {
    static int M, N, s;
    static int[][] map;

    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[M][N];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int r = M - y2; r < M - y1; r++) {
                for (int c = x1; c < x2; c++) {
                    map[r][c] = 1;
                }
            }
        }
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 0) {
                    s = 0;
                    dfs(r, c);
                    list.add(s);
                }
            }
        }

        int size = list.size();

        sb.append(size).append("\n");

        Collections.sort(list);

        for (int i = 0; i < size; i++) {
            sb.append(list.get(i)).append(" ");
        }
        System.out.println(sb);
    }

    private static void dfs(int r, int c) {
        map[r][c] = 1;
        s++;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (!isIn(nr, nc)) continue;
            if (map[nr][nc] == 0) dfs(nr, nc);
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < M && c >= 0 && c < N;
    }
}
