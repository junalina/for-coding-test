package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1018
 * <p>
 * 체스판 다시 칠하기
 * MxN 보드에서 8x8 체스판으로 잘라낸 후에 몇 개의 정사각형을 다시 칠해 체스판으로 만듬
 * 다시 칠해야 하는 정사각형의 최소 개수 출력
 */

public class BJ_1018_체스판다시칠하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 보드의 상태 입력
        char[][] board = new char[N][M];
        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < M; c++) {
                board[r][c] = str.charAt(c);
            }
        }

        int min = Integer.MAX_VALUE;

        for (int r = 0; r <= N - 8; r++) {
            for (int c = 0; c <= M - 8; c++) {
                int cnt = 0;
                int firstWhiteCnt = 0;
                int firstBlackCnt = 0;
                // 맨 왼쪽 위 칸이 흰색인 경우
                if (board[r][c] == 'W') {
                    // 체스판 맨 왼쪽 위 칸을 흰색으로 만드는 경우
                    for (int i = r; i < r + 8; i++) {
                        for (int j = c; j < c + 8; j++) {
                            // 흰색이어야 하는데 아닌 경우
                            if (((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) && board[i][j] != 'W')
                                firstWhiteCnt++;
                                // 검은색이어야 하는데 아닌 경우
                            else if (((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) && board[i][j] != 'B')
                                firstWhiteCnt++;
                        }
                    }
                    // 체스판 맨 왼쪽 위 칸을 검은색으로 만드는 경우
                    for (int i = r; i < r + 8; i++) {
                        for (int j = c; j < c + 8; j++) {
                            // 검은색이어야 하는데 아닌 경우
                            if (((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) && board[i][j] != 'B')
                                firstBlackCnt++;
                                // 흰색이어야 하는데 아닌 경우
                            else if (((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) && board[i][j] != 'W')
                                firstBlackCnt++;
                        }
                    }
                }
                // 검은색인 경우
                else if (board[r][c] == 'B') {
                    // 체스판 맨 왼쪽 위 칸을 검은색으로 만드는 경우
                    for (int i = r; i < r + 8; i++) {
                        for (int j = c; j < c + 8; j++) {
                            // 검은색이어야 하는데 아닌 경우
                            if (((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) && board[i][j] != 'B')
                                firstBlackCnt++;
                                // 흰색이어야 하는데 아닌 경우
                            else if (((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) && board[i][j] != 'W')
                                firstBlackCnt++;
                        }
                    }
                    // 체스판 맨 왼쪽 위 칸을 흰색으로 만드는 경우
                    for (int i = r; i < r + 8; i++) {
                        for (int j = c; j < c + 8; j++) {
                            // 흰색이어야 하는데 아닌 경우
                            if (((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) && board[i][j] != 'W')
                                firstWhiteCnt++;
                                // 검은색이어야 하는데 아닌 경우
                            else if (((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) && board[i][j] != 'B')
                                firstWhiteCnt++;
                        }
                    }
                }
                cnt = Math.min(firstWhiteCnt, firstBlackCnt);
                min = Math.min(min, cnt);
            }
        }
        System.out.println(min);
    }

}
