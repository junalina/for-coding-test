package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 맵 N x N (육지 or 바다)
 * 캐릭터 움직임
 * 1. 현재 방향을 기준으로 왼쪽 방향(반시계 방향으로 90도 회전한 방향)부터 차례대로 갈 곳을 정한다
 * 2. 왼쪽 방향에 아직 가보지 않았다면 왼쪽 방향으로 회전 후 한 칸 전진, 가보지 않은 칸이 없다면 왼쪽 방향으로 회전만 하고 1단계
 * 3. 네 방향 모두 가본 칸이거나 바다면 방향 유지한 채로 한 칸 뒤로 가고 1단계로, 뒤쪽 방향이 바다면 움직임을 멈춘다.
 * 캐릭터가 방문한 칸의 수를 구하라
 * input
 * 4 4
 * 1 1 0
 * 1 1 1 1
 * 1 0 0 1
 * 1 1 0 1
 * 1 1 1 1
 * output
 * 3
 */

public class 게임개발 {

    static int N, M, A, B, d;

    // 북 동 남 서
    static int[] dA = {-1, 0, 1, 0};
    static int[] dB = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // N과 M 입력받기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 1;
        int turnTime = 0;
        // 1. 현재 위치 A, B에서 d를 기준으로 반시계 방향으로 90도 회전한 방향부터 차례대로 갈 곳을 정한다.
        while (true) {
            turnLeft();
            int nA = A + dA[d];
            int nB = B + dB[d];
            // 2. 왼쪽 방향에 아직 가보지 않았다면 왼쪽 방향으로 회전 후 한 칸 전진
            if(!visited[nA][nB] && map[nA][nB] == 0) {
                visited[nA][nB] = true;
                A = nA;
                B = nB;
                cnt++;
            } else { // 가보지 않은 칸이 없다면 왼쪽 방향으로 회전만 하고 1단계
                turnTime++;
            }
            // 3. 네 방향 모두 가본 칸이거나 바다면 방향 유지한 채로 한 칸 뒤로 가고 1단계로, 뒤쪽 방향이 바다면 움직임을 멈춘다.
            if (turnTime == 4) {
                nA = A - dA[d];
                nB = B - dB[d];
                if(map[nA][nB] == 0) {
                    A = nA;
                    B = nB;
                } else break;
                turnTime = 0;
            }
        }
        System.out.println(cnt);
    }

    // 왼쪽으로 회전
    // 북쪽인 경우 0에서 -1이 되므로 서쪽(3)으로 변경
    private static void turnLeft() {
        d--;
        if(d < 0) d = 3;
    }

}
