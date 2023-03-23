package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/6593
 * <p>
 * 상범 빌딩
 * 상범 빌딩은 각 변의 길이가 1인 정육면체
 * 당신은 각 칸에서 인접한 6개의 칸으로 1분의 시간을 들여 이동 가능
 * 대각선 이동 불가능
 * 상범 빌딜의 바깥면도 모두 금으로 막혀있어 출구를 통해서만 탈출 가능
 * 상범 빌딩을 탈출하는 데에 필요한 최단 시간 출력
 * <p>
 * 풀이
 * BFS
 */

public class BJ_6593_상범빌딩 {
    static int L, R, C;
    static char[][][] map;

    // 동서남북상하
    static int[] dl = {0, 0, 0, 0, 1, -1};
    static int[] dr = {1, -1, 0, 0, 0, 0};
    static int[] dc = {0, 0, 1, -1, 0, 0};

    static class Point {
        int l, r, c;

        public Point(int l, int r, int c) {
            this.l = l;
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            // 층 수
            L = Integer.parseInt(st.nextToken());
            // 행 수
            R = Integer.parseInt(st.nextToken());
            // 열 수
            C = Integer.parseInt(st.nextToken());

            // 입력의 끝이라면
            if (L == 0 && R == 0 && C == 0) break;

            // 시작 지점
            int startL = 0;
            int startR = 0;
            int startC = 0;
            int endL = 0;
            int endR = 0;
            int endC = 0;

            // 상범 빌딩
            map = new char[L][R][C];
            for (int l = 0; l < L; l++) {
                for (int r = 0; r < R; r++) {
                    String str = br.readLine();
                    for (int c = 0; c < C; c++) {
                        map[l][r][c] = str.charAt(c);
                        // 시작 지점이라면
                        if (map[l][r][c] == 'S') {
                            startL = l;
                            startR = r;
                            startC = c;
                        }
                        // 도착 지점이라면
                        else if (map[l][r][c] == 'E') {
                            endL = l;
                            endR = r;
                            endC = c;
                        }
                    }
                }
                br.readLine();
            }

            boolean finish = false;

            // bfs
            Queue<Point> q = new LinkedList<>();
            int[][][] time = new int[L][R][C];
            q.offer(new Point(startL, startR, startC));
            time[startL][startR][startC] = 1;

            while (!q.isEmpty()) {
                Point now = q.poll();
                int l = now.l;
                int r = now.r;
                int c = now.c;
                // 탈출할 수 있는 출구라면
                if (l == endL && r == endR && c == endC) {
                    finish = true;
                    break;
                }
                for (int i = 0; i < 6; i++) {
                    int nl = l + dl[i];
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    // 빌딩 범위 밖이거나, 방문했거나, 지나갈 수 없는 칸이라면
                    if (!isIn(nl, nr, nc) || time[nl][nr][nc] != 0 || map[nl][nr][nc] == '#') continue;
                    q.offer(new Point(nl, nr, nc));
                    time[nl][nr][nc] = time[l][r][c] + 1;
                }
            }
            if (finish) sb.append("Escaped in ").append(time[endL][endR][endC] - 1).append(" minute(s).");
            else sb.append("Trapped!");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static boolean isIn(int nl, int nr, int nc) {
        return nl >= 0 && nl < L && nr >= 0 && nr < R && nc >= 0 && nc < C;
    }
}
