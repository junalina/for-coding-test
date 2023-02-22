package review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 미로탈출_2 {
    static int N, M;
    static int[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = str.charAt(c) - '0';
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0));

        while (!q.isEmpty()) {
            Point p = q.poll();
            int r = p.r;
            int c = p.c;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (!isIn(nr, nc)) continue;
                if (map[nr][nc] == 0) continue;
                if (map[nr][nc] == 1) {
                    q.offer(new Point(nr, nc));
                    map[nr][nc] = map[r][c] + 1;
                }
            }
        }
        return map[N - 1][M - 1];
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }
}
