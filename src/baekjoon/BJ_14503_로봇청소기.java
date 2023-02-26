package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14503
 * <p>
 * 로봇 청소기
 * 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
 * 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
 * 1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
 * 2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
 * 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
 * 1. 반시계 방향으로 90도 회전한다.
 * 2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
 * 3. 1번으로 돌아간다.
 * 로봇 청소기가 작동을 시작한 후 작동을 멈출 때까지 청소하는 칸의 개수 출력
 * <p>
 * 풀이
 * DFS
 */

public class BJ_14503_로봇청소기 {

    static int N, M, cnt;
    static int[][] room;

    // 북동남서
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 방의 크기 N, M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        room = new int[N][M];

        // 처음 로봇 청소기 좌표, 방향
        st = new StringTokenizer(br.readLine());
        int startR = Integer.parseInt(st.nextToken());
        int startC = Integer.parseInt(st.nextToken());
        int startD = Integer.parseInt(st.nextToken());

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                room[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(startR, startC, startD);

        System.out.println(cnt);

    }

    private static void dfs(int r, int c, int d) {
        // 현재 칸 청소
        if (room[r][c] == 0) {
            room[r][c] = 2;
            cnt++;
        }

        // 청소되지 않은 빈 칸이 있는지 확인
        boolean existNoClean = false;
        for (int i = 0; i < 4; i++) {
            // 반시계 방향으로 90도 회전
            d = (d + 3) % 4;
            int nr = r + dr[d];
            int nc = c + dc[d];
            // 배열 범위 밖이면 무시
            if (!isIn(nr, nc)) continue;
            if (room[nr][nc] == 0) {
                // 청소되지 않은 빈 칸인 경우 한 칸 전진하고 1번으로 돌아감
                dfs(nr, nc, d);
                existNoClean = true;
                break;
            }
        }

        // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
        if (!existNoClean) {
            int nd = (d + 2) % 4;
            int nr = r + dr[nd];
            int nc = c + dc[nd];
            // 배열 범위 안이라면
            if (isIn(nr, nc)) {
                // 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아감
                if (room[nr][nc] != 1) dfs(nr, nc, d);
            }
        }
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M;
    }
}
