package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/16234
 *
 * 인구 이동
 *
 * N x N 땅, 1 x 1의 칸으로 나누어짐, 땅에는 나라가 하나씩 존재
 * 인접한 나라 사이에는 국경선이 존재
 *
 * 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
 * 위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
 * 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
 * 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
 * 연합을 해체하고, 모든 국경선을 닫는다.
 * 각 나라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하라
 */

public class 인구이동 {

    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;

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
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 공백을 구분하여 N, L, R 압력
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 땅 입력
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0; // 인구 이동이 발생하는 일 수
        while (true) {
            // 인구 이동을 할 수가 없으면 반복문 탈출
            if (bfs() == 0) break;
            else result++;
        }

        System.out.println(result);

    }

    // bfs를 통해 연합 형성
    private static int bfs() {
        int unionCnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 방문하지 않은 나라라면
                if (!visited[i][j]) {
                    Queue<Point> q = new LinkedList<>();
                    Point point = new Point(i, j);
                    q.offer(point);

                    ArrayList<Point> unionList = new ArrayList<>();
                    unionList.add(point);

                    visited[i][j] = true;

                    int unionSum = map[i][j];

                    while (!q.isEmpty()) {
                        Point p = q.poll();
                        int r = p.r;
                        int c = p.c;
                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];
                            // 땅 안에 있고 방문하지 않은 나라라면
                            if (isIn(nr, nc)) {
                                // 국경선을 열 수 있다면
                                if (!visited[nr][nc] && canOpen(r, c, nr, nc)) {
                                    q.offer(new Point(nr, nc));
                                    unionList.add(new Point(nr, nc));
                                    visited[nr][nc] = true;
                                    unionCnt++;
                                    unionSum += map[nr][nc];
                                }
                            }
                        }
                    }

                    // 연합이 있으면 연합에 인구를 똑같이 분배
                    if (unionCnt > 0) {
                        int average = unionSum / unionList.size();
                        for (Point p : unionList) {
                            int r = p.r;
                            int c = p.c;
                            map[r][c] = average;
                        }
                    }
                }
            }
        }

        // 방문배열 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        return unionCnt;
    }

    private static boolean canOpen(int r, int c, int nr, int nc) {
        int diff = Math.abs(map[r][c] - map[nr][nc]);
        return diff >= L && diff <= R;
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < N;
    }


}
