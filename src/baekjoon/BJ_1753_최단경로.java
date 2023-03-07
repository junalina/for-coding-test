package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/1753
 * <p>
 * 최단경로
 * 방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로값 출력
 * <p>
 * 풀이
 * 다익스트라
 */

public class BJ_1753_최단경로 {
    static final int INF = (int) 1e9;
    static int V;
    static int[] d;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    static class Node implements Comparable<Node> {
        int index, distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 정점의 개수
        V = Integer.parseInt(st.nextToken());
        // 간선의 개수
        int E = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        // 시작 정점
        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            // u에서 v로 가는 가중치 w인 간선이 존재
            graph.get(u).add(new Node(v, w));
        }

        // 최단 경로값 담을 배열 초기화
        d = new int[V + 1];
        Arrays.fill(d, INF);

        dijkstra(K);

        for (int i = 1; i <= V; i++) {
            if (d[i] == 1e9) sb.append("INF");
            else sb.append(d[i]);
            sb.append("\n");
        }

        System.out.println(sb);

    }

    private static void dijkstra(int k) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 시작점 자신 0으로 시작
        pq.offer(new Node(k, 0));
        d[k] = 0;

        while (!pq.isEmpty()) {
            // 가장 최단 경로가 짧은 노드 뽑기
            Node node = pq.poll();
            int idx = node.index;
            int dis = node.distance;
            // 이미 처리된 노드면 무시
            if (d[idx] < dis) continue;
            for (int i = 0; i < graph.get(idx).size(); i++) {
                int cost = d[idx] + graph.get(idx).get(i).distance;
                // 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
                if (cost < d[graph.get(idx).get(i).index]) {
                    d[graph.get(idx).get(i).index] = cost;
                    pq.offer(new Node(graph.get(idx).get(i).index, cost));
                }
            }
        }
    }
}
