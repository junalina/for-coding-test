package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/2210
 * <p>
 * 숫자판 점프
 * 5x5 숫자판, 0~9의 숫자가 적혀 있음
 * 임의의 위치에서 시작해서 인접해 있는 네 방향으로 다섯 번 이동하면서, 각 칸에 적혀있는 숫자를 차례로 붙이면 6자리의 수가 된다.
 * 만들 수 있는 서로 다른 여섯 자리의 수들의 개수 출력
 * <p>
 * 풀이
 * DFS
 */

public class BJ_2210_숫자판점프 {
    static List<String> list = new ArrayList<>();
    static String[][] num;

    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 숫자판의 정보
        num = new String[5][5];
        for (int r = 0; r < 5; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 5; c++) {
                num[r][c] = st.nextToken();
            }
        }

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                dfs(r, c, 0, num[r][c]);
            }
        }
        System.out.println(list.size());
    }

    private static void dfs(int r, int c, int cnt, String tmp) {
        if (cnt == 5) {
            if (!list.contains(tmp)) list.add(tmp);
            return;
        }

        // 사방탐색
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            // 배열 범위 밖이라면 무시
            if (!isIn(nr, nc)) continue;
            dfs(nr, nc, cnt + 1, tmp + num[nr][nc]);
        }
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < 5 && nc >= 0 && nc < 5;
    }
}
