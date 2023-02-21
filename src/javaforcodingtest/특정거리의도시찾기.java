package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/18352
 *
 * 특정 거리의 도시 찾기
 *
 * 1번부터 N번까지 도시, M개의 단방향 도로, 모든 도로 거리 1(가중치 X)
 * 특정 도시 X에서 출발하여 도달할 수 있는 모든 도시 중에서, 최단 거리가 정확히 K인 모든 도시의 번호 출력
 *
 * 풀이 : 그래프에 가중치가 없을땐 BFS
 */
public class 특정거리의도시찾기 {

    static int N, M, K, X;

    static int[] dis;

    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        dis = new int[N+1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            dis[i] = -1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
        }

        // 출발 도시까지의 거리 0
        dis[X] = 0;

        bfs(X);

        // 최단 거리가 K인 모든 도시 번호 오름차순으로 출력
        boolean check = false;
        for (int i = 1; i <= N; i++) {
            if (dis[i] == K) {
                sb.append(i).append("\n");
                check = true;
            }
        }

        if (check) System.out.println(sb);
        else System.out.println(-1);

    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int now = q.poll();
            // 현재 도시에서 이동할수 있는 모든 도시 확인
            for (int i = 0; i < graph.get(now).size(); i++) {
                int next = graph.get(now).get(i);
                // 아직 방문하지 않은 도시라면
                if (dis[next] == -1) {
                    // 최단 거리 갱신
                    dis[next] = dis[now] + 1;
                    q.offer(next);
                }
            }
        }
    }

}
