package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/4179
 * <p>
 * 불!
 * 지훈이가 미로에서 탈출하기
 * 지훈이와 불은 매 분마다 한칸씩 수평 또는 수직 이동
 * 불은 각 지점에서 네 방향으로 확산
 * 지훈이는 미로의 가장자리에 접한 공간에서 탈출
 * 지훈이와 불은 벽이 있는 공간은 통과 불가
 * <p>
 * 풀이
 * BFS
 */

public class BJ_4179_불 {
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

        // R, C 공백 구분하여 입력
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        Queue<Point> jihunQ = new LinkedList<>();
        Queue<Point> fireQ = new LinkedList<>();

        boolean[][] jihunVisited = new boolean[R][C];
        boolean[][] fireVisited = new boolean[R][C];

        // 미로 정보 입력
        char[][] map = new char[R][C];
        for (int r = 0; r < R; r++) {
            String str = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = str.charAt(c);
                if (map[r][c] == 'J') {
                    jihunQ.offer(new Point(r, c));
                    jihunVisited[r][c] = true;
                } else if (map[r][c] == 'F') {
                    fireQ.offer(new Point(r, c));
                    fireVisited[r][c] = true;
                }
            }
        }

        int time = 0;

        // bfs
        while (!jihunQ.isEmpty()) {
            int fireSize = fireQ.size();
            int jihunSize = jihunQ.size();
            for (int i = 0; i < fireSize; i++) {
                Point fire = fireQ.poll();
                int r = fire.r;
                int c = fire.c;
                for (int j = 0; j < 4; j++) {
                    int nr = r + dr[j];
                    int nc = c + dc[j];
                    // 배열 범위 밖이거나 방문했거나 벽이면 무시
                    if (!isIn(nr, nc) || fireVisited[nr][nc] || map[nr][nc] == '#') continue;
                    map[nr][nc] = 'F';
                    fireVisited[nr][nc] = true;
                    fireQ.offer(new Point(nr, nc));
                }
            }

            for (int i = 0; i < jihunSize; i++) {
                Point jihun = jihunQ.poll();
                int r = jihun.r;
                int c = jihun.c;
                for (int j = 0; j < 4; j++) {
                    int nr = r + dr[j];
                    int nc = c + dc[j];
                    if (!isIn(nr, nc)) {
                        time++;
                        System.out.println(time);
                        System.exit(0);
                    }
                    // 방문했거나 지나갈 수 있는 공간이 아니면
                    if (jihunVisited[nr][nc] || map[nr][nc] != '.') continue;
                    jihunVisited[nr][nc] = true;
                    jihunQ.offer(new Point(nr, nc));
                }
            }
            time++;
        }

        System.out.println("IMPOSSIBLE");
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < R && nc >= 0 && nc < C;
    }

}
