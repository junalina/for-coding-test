package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2660
 * <p>
 * 회장뽑기
 * 월드컵 응원 모임 회장 선출
 * 회장 : 회원들 중에서 점수가 가장 작은 사람
 * 횢아의 점수와 회장이 될 수 있는 모든 사람 출력
 * <p>
 * 풀이
 * 플로이드 워셜
 */

public class BJ_2660_회장뽑기 {
    static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 회원의 수
        int n = Integer.parseInt(br.readLine());

        int[][] adjMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(adjMatrix[i], INF);
            adjMatrix[i][i] = 0; // 대각선 항목에 0 할당
        }

        // 두 회원이 서로 친구임을 표시
        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 마지막 줄이면
            if (a == -1 && b == -1) break;
            // 친구 표시
            adjMatrix[a - 1][b - 1] = adjMatrix[b - 1][a - 1] = 1;
        }

        // 플로이드 워셜
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
                }
            }
        }

        // 회원들의 점수 계산
        int[] score = new int[n];
        // 회장 후보의 점수
        int min = INF;
        for (int r = 0; r < n; r++) {
            int max = Integer.MIN_VALUE;
            for (int c = 0; c < n; c++) {
                if (adjMatrix[r][c] == INF) continue;
                max = Math.max(max, adjMatrix[r][c]);
            }
            score[r] = max;
            min = Math.min(min, max);
        }

        // 후보의 수
        List<Integer> list = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (score[i] == min) {
                cnt++;
                list.add(i + 1);
            }
        }

        sb.append(min).append(" ").append(cnt).append("\n");

        for (int index : list) {
            sb.append(index).append(" ");
        }

        System.out.println(sb);
    }
}
