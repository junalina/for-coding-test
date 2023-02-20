package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/18428
 *
 * 감시 피하기
 *
 * N x N 복도, 1 x 1 크기의 칸, 특정 위치 선생님, 학생, 장애물
 * 선생님 forcodingtest.상하좌우 감시, 장애물 있는 경우 뒤에 있는 학생 볼 수 없음
 * 장애물 3개를 설치하여 학생들이 선생님들의 감시를 피하도록 할 수 있는지 출력
 *
 *
 */
public class 감시피하기 {
    static int N;
    static char[][] map;
    static ArrayList<Point> student = new ArrayList<>();

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
        StringTokenizer st;

        // 복도의 크기 N
        N = Integer.parseInt(br.readLine());

        // 복도의 정보
        map = new char[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = st.nextToken().charAt(0);
                if (map[r][c] == 'S') student.add(new Point(r, c));
            }
        }

        dfs(0);
        
        System.out.println("NO");
    }

    private static void dfs(int cnt) {
        if (cnt == 3) {
            bfs();
            return;
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 'X') {
                    map[r][c] = 'O';
                    dfs(cnt + 1);
                    map[r][c] = 'X';
                }
            }
        }
    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        // 배열 복사
        char[][] copyMap = new char[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                copyMap[r][c] = map[r][c];
                if (copyMap[r][c] == 'T') {
                    q.offer(new Point(r, c));
                    visited[r][c] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Point p = q.poll();
            int r = p.r;
            int c = p.c;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                // 복도 안이면
                while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                    // 장애물을 만나지 않았다면
                    if (copyMap[nr][nc] != 'O') {
                        visited[nr][nc] = true;
                        nr += dr[i];
                        nc += dc[i];
                    } else break;
                }
            }
        }
        if (canAvoid(visited)) {
            System.out.println("YES");
            System.exit(0);
        }
    }

    private static boolean canAvoid(boolean[][] visited) {
        for (Point p:student) {
            int r = p.r;
            int c = p.c;
            if (visited[r][c]) return false;
        }
        return true;
    }
}
