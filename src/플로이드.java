import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/11404
 * <p>
 * 플로이드
 * <p>
 * n개의 도시
 * 한 도시에서 출발하여 다른 도시에 도착하는 m개의 버스, 각 버스는 한 번 사용할 때 필요한 비용이 있다.
 * 모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하는 프로그램을 작성하시오.
 * <p>
 * 풀이
 * 플로이드 워셜 알고리즘
 */

public class 플로이드 {

    static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 도시의 개수 n, 버스의 개수 m
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] graph = new int[n + 1][n + 1];

        // 최단 거리 테이블을 모두 무한으로 초기화
        for (int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (r == c) graph[r][c] = 0;
            }
        }

        // 버스의 정보
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 노선은 하나가 아닐 수 있음
            if (c < graph[a][b]) graph[a][b] = c;
        }

        // i -> k -> j
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (graph[r][c] == INF) sb.append(0).append(" ");
                else sb.append(graph[r][c]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);


    }
}
