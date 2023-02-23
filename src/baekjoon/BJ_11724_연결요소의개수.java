package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/11724
 * <p>
 * 연결 요소의 개수
 * 방향 없는 그래프가 주어졌을 때, 연결 요소의 개수를 출력
 * <p>
 * 풀이
 * DFS
 */

public class BJ_11724_연결요소의개수 {

    static int N, M, ans;
    static int[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u][v] = graph[v][u] = 1;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i);
                ans++;
            }
        }

        System.out.println(ans);
    }

    private static void dfs(int i) {
        if (visited[i]) return;
        else {
            visited[i] = true;
            for (int j = 1; j <= N; j++) {
                if (graph[i][j] == 1) dfs(j);
            }
        }
    }
}