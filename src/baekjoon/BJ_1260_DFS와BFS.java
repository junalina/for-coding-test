package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/1260
 * <p>
 * DFS와 BFS
 * 그래프를 DFS로 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과 출력
 * <p>
 * 풀이
 * DFS, BFS
 */
public class BJ_1260_DFS와BFS {
    static int N, M, V;
    static int[][] adjMatrix;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        adjMatrix = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjMatrix[a][b] = adjMatrix[b][a] = 1;
        }

        dfs(V);

        sb.append("\n");

        Arrays.fill(visited, false);

        bfs();

        System.out.println(sb);

    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(V);
        visited[V] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" ");
            for (int i = 1; i <= N; i++) {
                if (!visited[i] && adjMatrix[now][i] == 1) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
    }

    private static void dfs(int v) {
        if (visited[v]) return;

        visited[v] = true;
        sb.append(v).append(" ");

        for (int i = 1; i <= N; i++) {
            if (!visited[i] && adjMatrix[v][i] == 1) dfs(i);
        }
    }

}
