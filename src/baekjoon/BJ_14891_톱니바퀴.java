package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14891
 * <p>
 * 톱니바퀴
 * 8개의 톱니를 가지고 있는 톱니바퀴 4개 일렬로
 * 톱니는 N극 또는 S극 중 하나
 * 톱니바퀴 총 K번 회전, 회전은 시계, 반시계 방향
 * 톱니바퀴를 회전시키려면, 회전시킬 톱니바퀴와 방향 결정
 * 회전할 때, 서로 맞닿은 극에 따라서 옆에 있는 톱니바퀴르 회전, 회전 X
 * 맞닿은 톱니의 극이 다르다면 반대 방향으로 회전 시킨다.
 * 톱니바퀴의 초기 상태와 톱니바퀴를 회전시킨 방법이 주어졌을 때, 최종 톱니바퀴의 점수의 합 출력
 * <p>
 * 풀이
 * 구현
 */

public class BJ_14891_톱니바퀴 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 톱니바퀴의 상태
        int[][] arr = new int[4][8];
        for (int r = 0; r < 4; r++) {
            String str = br.readLine();
            for (int c = 0; c < 8; c++) {
                arr[r][c] = str.charAt(c) - '0';
            }
        }

        // 회전 횟수
        int K = Integer.parseInt(br.readLine());
        // 회전 방법
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            // 회전시킨 톱니바퀴의 번호
            int index = Integer.parseInt(st.nextToken()) - 1;
            // 방향
            int dir = Integer.parseInt(st.nextToken());
            // 각 톱니바퀴가 회전할 방향을 저장하는 배열
            int[] dirs = new int[4];

            // 입력된 톱니바퀴는 먼저 방향 저장
            dirs[index] = dir;
            // 왼쪽 톱니바퀴들 방향 결정
            for (int j = index - 1; j >= 0; j--) {
                // 맞닿은 톱니의 극이 다르다면
                if (arr[j][2] != arr[j + 1][6]) {
                    dirs[j] = -dirs[j + 1];
                } else break;
            }
            // 오른쪽 톱니바퀴들 방향 결정
            for (int j = index + 1; j < 4; j++) {
                // 맞닿은 톱니의 극이 다르다면
                if (arr[j][6] != arr[j - 1][2]) {
                    dirs[j] = -dirs[j - 1];
                } else break;
            }

            // 각 톱니바퀴 회전
            for (int k = 0; k < 4; k++) {
                // 회전하지 않는다면 무시
                if (dirs[k] == 0) continue;
                int temp;
                // 시계 방향 회전
                if (dirs[k] == 1) {
                    temp = arr[k][7];
                    for (int j = 7; j >= 1; j--) {
                        arr[k][j] = arr[k][j - 1];
                    }
                    arr[k][0] = temp;
                }
                // 반시계 방향 회전
                else if (dirs[k] == -1) {
                    temp = arr[k][0];
                    for (int j = 0; j < 7; j++) {
                        arr[k][j] = arr[k][j + 1];
                    }
                    arr[k][7] = temp;
                }
                dirs[k] = 0;
            }
        }

        // 점수 계산
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            if (arr[i][0] == 1) {
                sum += Math.pow(2, i);
            }
        }

        System.out.println(sum);

    }
}
