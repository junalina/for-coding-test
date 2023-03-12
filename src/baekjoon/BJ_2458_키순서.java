package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2458
 * <p>
 * 키 순서
 * 학생들의 키를 비교한 결과가 주어질 때,
 * 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇 명인지 계산하여 출력
 * <p>
 * 풀이
 * 플로이드 워셜
 */

public class BJ_2458_키순서 {
    static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 학생들의 수
        int N = Integer.parseInt(st.nextToken());
        // 두 학생 키를 비교한 횟수
        int M = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        int[][] graph = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) graph[i][j] = 0;
                else graph[i][j] = INF;
            }
        }

        // 두 학생의 키를 비교한 결과
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // a학생이 b학생보다 키가 작음
            graph[a][b] = 1;
        }

        // 플로이드 워셜
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (graph[i][j] != INF || graph[j][i] != INF) count++;
            }
            if (count == N - 1) answer++;
        }

        System.out.println(answer);

    }
}
