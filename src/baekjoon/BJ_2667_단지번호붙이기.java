package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * https://www.acmicpc.net/problem/2667
 * <p>
 * 단지번호붙이기
 * 지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력
 * <p>
 * 풀이
 * DFS
 */
public class BJ_2667_단지번호붙이기 {
    static int N, cnt;
    static int[][] map;
    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 지도의 크기 N 입력
        N = Integer.parseInt(br.readLine());
        // 지도 정보
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < N; c++) {
                map[r][c] = str.charAt(c) - '0';
            }
        }

        // 단지 확인하며 집의 수 확인하며 리스트에 저장
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 1) {
                    cnt = 0;
                    dfs(r, c);
                    list.add(cnt);
                }
            }
        }

        int size = list.size();

        sb.append(size).append("\n");

        Collections.sort(list);
        for (int cnt : list) {
            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int r, int c) {
        cnt++; // 집의 수 추가
        map[r][c] = 0; // 방문처리

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (isIn(nr, nc)) {
                if (map[nr][nc] == 1) dfs(nr, nc);
            }
        }
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

}
