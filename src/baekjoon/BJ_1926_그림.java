package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1926
 * <p>
 * 그림
 * 그림 : 가로나 세로가 1로 연결된 것
 * 큰 도화지에 그림이 그려져 있을 때, 그 그림의 개수와, 그 그림 중 넓이가 가장 넓은 것의 넓이를 출력
 * <p>
 * 풀이
 * bfs
 */

public class BJ_1926_그림 {
    static int n, m;
    static int[][] paper;
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

        // 도화지의 세로 크기
        n = Integer.parseInt(st.nextToken());
        // 도화지의 가로 크기
        m = Integer.parseInt(st.nextToken());

        // 그림의 정보
        paper = new int[n][m];
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                paper[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 그림의 개수
        int cnt = 0;
        // 가장 넓은 그림의 넓이 (그림이 없는 경우 0)
        int max = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (paper[r][c] == 1) {
                    max = Math.max(max, bfs(r, c));
                    cnt++;
                }
            }
        }

        sb.append(cnt).append("\n").append(max);

        System.out.println(sb);

    }

    private static int bfs(int r, int c) {
        int width = 0;

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c));
        paper[r][c] = 0; // 방문처리
        width++; // 넓이 1 증가

        while (!q.isEmpty()) {
            Point now = q.poll();
            int nowR = now.r;
            int nowC = now.c;
            for (int i = 0; i < 4; i++) {
                int nextR = nowR + dr[i];
                int nextC = nowC + dc[i];
                // 도화지 범위 밖이면 무시
                if (!isIn(nextR, nextC)) continue;
                // 연결되어 있지 않다면 무시
                if (paper[nextR][nextC] == 0) continue;
                q.offer(new Point(nextR, nextC));
                paper[nextR][nextC] = 0; // 방문처리
                width++; // 넓이 1 증가
            }

        }
        return width;
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}
