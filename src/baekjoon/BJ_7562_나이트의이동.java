package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/7562
 * <p>
 * 나이트의 이동
 * 나이트가 최소 몇 번만에 이동할 수 있는지 출력
 * <p>
 * 풀이
 * BFS
 */

public class BJ_7562_나이트의이동 {
    static int l, startR, startC, endR, endC;
    static int[][] map;

    // 나이트의 이동
    static int[] dr = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dc = {-1, 1, -2, 2, -2, 2, -1, 1};

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            // 체스판의 한 변의 길이
            l = Integer.parseInt(br.readLine());

            // 체스판
            map = new int[l][l];

            // 나이트가 현재 있는 칸
            st = new StringTokenizer(br.readLine());
            startR = Integer.parseInt(st.nextToken());
            startC = Integer.parseInt(st.nextToken());

            // 나이트가 이동하려고 하는 칸
            st = new StringTokenizer(br.readLine());
            endR = Integer.parseInt(st.nextToken());
            endC = Integer.parseInt(st.nextToken());

            sb.append(bfs()).append("\n");
        }

        System.out.println(sb);
    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        // 나이트가 현재 있는 칸에서 시작
        q.offer(new Point(startR, startC));

        while (!q.isEmpty()) {
            Point now = q.poll();
            int r = now.r;
            int c = now.c;
            if (r == endR && c == endC) return map[r][c];
            // 나이트의 이동
            for (int i = 0; i < 8; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                // 배열 범위 밖이거나 방문했다면 무시
                if (!isIn(nr, nc) || map[nr][nc] != 0) continue;
                map[nr][nc] = map[r][c] + 1;
                q.offer(new Point(nr, nc));
            }
        }
        return 0;
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < l && nc >= 0 && nc < l;
    }
}
