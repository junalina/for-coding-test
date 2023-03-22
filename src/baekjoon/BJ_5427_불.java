package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/5427
 * <p>
 * 불
 * 상근이 건물에 갇혀있음
 * 건물의 일부에는 불, 상근이는 출구를 향해 뛰고 있음
 * 매 초마다, 불은 동서남북 방향으로 퍼짐, 벽에는 불이 붙지 앟는다.
 * 상근이는 동서남북 인접한 칸으로 이동 가능, 1초가 걸림
 * 상근이는 벽을 통과할 수 없고, 불이 옮겨지는 칸 또는 이제 불이 붙으려는 칸으로 이동할 수 없음
 * 상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸으로 이동 가능
 * <p>
 * 풀이
 * BFS
 */

public class BJ_5427_불 {

    static int w, h;
    // 동서남북
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

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

        // 테스트 케이스의 개수
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            // 지도의 너비
            w = Integer.parseInt(st.nextToken());
            // 지도의 높이
            h = Integer.parseInt(st.nextToken());

            Queue<Point> sangQ = new LinkedList<>();
            Queue<Point> fireQ = new LinkedList<>();

            boolean[][] sangVisited = new boolean[h][w];
            boolean[][] fireVisited = new boolean[h][w];

            // 빌딩의 지도
            char[][] map = new char[h][w];
            for (int r = 0; r < h; r++) {
                String str = br.readLine();
                for (int c = 0; c < w; c++) {
                    map[r][c] = str.charAt(c);
                    // 상근이라면
                    if (map[r][c] == '@') {
                        sangQ.offer(new Point(r, c));
                        sangVisited[r][c] = true;
                    }
                    // 불이라면
                    else if (map[r][c] == '*') {
                        fireQ.offer(new Point(r, c));
                        fireVisited[r][c] = true;
                    }
                }
            }

            // 탈출하는데 가장 빠른 시간
            int time = 0;
            boolean finish = false;

            while (!sangQ.isEmpty()) {
                int sangSize = sangQ.size();
                int fireSize = fireQ.size();
                for (int i = 0; i < fireSize; i++) {
                    Point fire = fireQ.poll();
                    int r = fire.r;
                    int c = fire.c;
                    for (int j = 0; j < 4; j++) {
                        int nr = r + dr[j];
                        int nc = c + dc[j];
                        // 배열 범위 밖이거나, 방문했거나, 벽이라면 무시
                        if (!isIn(nr, nc) || fireVisited[nr][nc] || map[nr][nc] == '#') continue;
                        map[nr][nc] = '*';
                        fireQ.offer(new Point(nr, nc));
                        fireVisited[nr][nc] = true;
                    }
                }

                for (int i = 0; i < sangSize; i++) {
                    Point sang = sangQ.poll();
                    int r = sang.r;
                    int c = sang.c;
                    for (int j = 0; j < 4; j++) {
                        int nr = r + dr[j];
                        int nc = c + dc[j];

                        // 빌딩을 탈출했다면
                        if (!isIn(nr, nc)) {
                            finish = true;
                            break;
                        }

                        // 배열 범위 밖이거나, 방문했거나, 벽이라면 무시
                        if (sangVisited[nr][nc] || map[nr][nc] != '.') continue;
                        sangQ.offer(new Point(nr, nc));
                        sangVisited[nr][nc] = true;
                    }
                }
                time++;
                if (finish) break;
            }

            if (finish) sb.append(time);
            else sb.append("IMPOSSIBLE");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < h && nc >= 0 && nc < w;
    }
}
