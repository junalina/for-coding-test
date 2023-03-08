package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1719
 * <p>
 * 택배
 * 경로표 출력
 * <p>
 * 풀이
 * 플로이드 워셜
 */

public class BJ_1719_택배 {
    static final int INF = (int) 1e9;
    static int n, m;
    static int[][] graph, table;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 경로표
        table = new int[n + 1][n + 1];
        // 최소 시간 저장 배열 초기화
        graph = new int[n + 1][n + 1];
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (r == c) graph[r][c] = 0;
                else graph[r][c] = INF;
                table[r][c] = c;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // a와 b를 오가는데 필요한 시간 c
            graph[a][b] = c;
            graph[b][a] = c;
        }

        // 플로이드 워셜
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                        table[i][j] = table[i][k];
                    }
                }
            }
        }

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (r == c) sb.append("-");
                else sb.append(table[r][c]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
}
