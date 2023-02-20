package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * N x M 직사각형 미로
 * 동빈이 위치 : (1, 1)
 * 출구 위치 : (N, M)
 * 괴물이 있는 부분 : 0
 * 괴물이 없는 부분 : 1
 * 탈출하기 위해 움직여야 하는 최소 칸의 개수 구하기
 * input
 * 5 6
 * 101010
 * 111111
 * 000001
 * 111111
 * 111111
 * output
 * 10
 */

public class 미로탈출 {
    static int N, M;
    static int[][] map;

    // forcodingtest.상하좌우
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

        // N, M을 공백으로 구분하여 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 2차원 배열의 맵 정보 입력받기
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = str.charAt(c) - '0';
            }
        } // 입력 완료

        System.out.println(BFS());
    }

    private static int BFS() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0));

        // 큐가 빌 때까지 반복
        while (!q.isEmpty()) {
            Point p = q.poll();
            int r = p.r;
            int c = p.c;
            // 현재 위치에서 네 방향으로의 위치 확인
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 미로 찾기 공간을 벗어나거나 벽인 경우 무시
                if(isNotIn(nr, nc) || map[nr][nc] == 0) continue;

                // 해당 노드를 처음 방문하는 경우에만 최단 거리 기록
                if(map[nr][nc] == 1) {
                    map[nr][nc] = map[r][c] + 1;
                    q.offer(new Point(nr, nc));
                }
            }
        }
        // 가장 오른쪽 아래까지 최단 거리 반환
        return map[N-1][M-1];
    }

    private static boolean isNotIn(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= M;
    }
}
