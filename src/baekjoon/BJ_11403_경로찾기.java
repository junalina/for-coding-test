package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/11403
 * <p>
 * 경로 찾기
 * 가중치 없는 방향 그래프 G가 주어졌을 때, 모든 정점 (i, j)에 대해서, i에서 j로 가는 경로가 있는지 없는지 구하는 프로그램 작성
 * <p>
 * 풀이
 * 플로이드 워셜
 */

public class BJ_11403_경로찾기 {
    static int N;
    static int[][] adjMatrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 정점의 개수 N
        N = Integer.parseInt(br.readLine());

        // 그래프의 인접 행렬
        adjMatrix = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                adjMatrix[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드 워셜 알고리즘
        for (int i = 0; i < N; i++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    // r에서 i로 가는 경로와 i에서 c로 가는 경로가 있다면 r에서 c로 가는 경로가 있다.
                    if (adjMatrix[r][i] == 1 && adjMatrix[i][c] == 1) adjMatrix[r][c] = 1;
                }
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                sb.append(adjMatrix[r][c]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
}
