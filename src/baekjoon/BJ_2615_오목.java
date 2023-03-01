package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2615
 * <p>
 * 오목
 * 바둑판의 상태가 주어졌을 때, 검은색이 이겼는지, 흰색이 이겼는지, 승부가 결정되지 않았는지 출력
 */

public class BJ_2615_오목 {
    static int[][] arr = new int[21][21];
    static int[][][] visited = new int[21][21][4];
    static int[] dx = {1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 1; i <= 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 19; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int j = 1; j <= 19; j++) {
            for (int i = 1; i <= 19; i++) {
                if (arr[i][j] == 0) continue;
                for (int d = 0; d < 4; d++) { // 네 방향으로 탐색
                    if (visited[i][j][d] == 0 && go(i, j, d, arr[i][j]) == 5) {
                        System.out.println(arr[i][j] + "\n" + i + " " + j);
                        return;
                    }
                }
            }
        }
        System.out.println(0);
    }

    private static int go(int x, int y, int dir, int color) { // 주어진 방향으로 계속 탐색
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (arr[nx][ny] == color) {
            return visited[nx][ny][dir] = go(nx, ny, dir, color) + 1; // 재귀식으로 저장.
        }
        return 1;
    }

}