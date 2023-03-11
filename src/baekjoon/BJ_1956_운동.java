package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1956
 * <p>
 * 운동
 * V개 마을, E개 도로
 * 최소 사이클의 도로 길이의 합 출력
 * <p>
 * 풀이
 * 플로이드 워셜
 */

public class BJ_1956_운동 {
    static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 마을 수
        int V = Integer.parseInt(st.nextToken());
        // 도로 수
        int E = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        int[][] graph = new int[V + 1][V + 1];
        for (int i = 0; i <= V; i++) {
            Arrays.fill(graph[i], INF);
        }

        // 자기 자신으로 가는 길이 0으로 초기화
        for (int r = 0; r <= V; r++) {
            for (int c = 0; c <= V; c++) {
                if (r == c) graph[r][c] = 0;
            }
        }

        // 도로 정보
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // a번 마을에서 b번 마을로가는 거리 c
            graph[a][b] = c;
        }

        // 플로이드 워셜
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        // 최소 사이클 구하기
        int min = INF;
        for (int i = 1; i <= V; i++) {
            for (int j = i + 1; j <= V; j++) {
                min = Math.min(min, graph[i][j] + graph[j][i]);
            }
        }

        // 운동 경로를 찾는 것이 불가능한 경우 -1 출력
        if (min == INF) System.out.println(-1);
        else System.out.println(min);

    }
}
