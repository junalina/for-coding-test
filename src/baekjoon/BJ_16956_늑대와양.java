package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/16956
 * <p>
 * 늑대와 양
 * RxC 목장
 * 양은 이동 X
 * 늑대는 인접한 칸 이동 가능
 * 목장에 울타리를 설치해 늑대가 양이 있는 칸으로 갈 수 없게 하려고 함
 * <p>
 * 풀이
 * BFS
 */

public class BJ_16956_늑대와양 {

    static int R, C;

    // 상하좌우
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
        StringBuilder sb = new StringBuilder();

        // 목장의 크기 R, C
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        // 목장의 상태
        char[][] map = new char[R][C];
        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = str.charAt(c);
                // 늑대라면
                if (map[r][c] == 'W') {
                    q.offer(new Point(r, c));
                    visited[r][c] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point now = q.poll();
                int r = now.r;
                int c = now.c;
                for (int j = 0; j < 4; j++) {
                    int nr = r + dr[j];
                    int nc = c + dc[j];
                    // 배열 범위 밖이거나 방문했다면 무시
                    if (!isIn(nr, nc) || visited[nr][nc]) continue;
                    // 양을 만난다면 0을 출력
                    if (map[nr][nc] == 'S') {
                        System.out.println(0);
                        return;
                    }
                    map[nr][nc] = 'D';
                }
            }
        }

        sb.append(1).append("\n");
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                sb.append(map[r][c]);
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < R && nc >= 0 && nc < C;
    }
}
