package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1504
 * <p>
 * 특정한 최단 경로
 * 방향성 없는 그래프
 * 1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로 길이 출력
 * <p>
 * 풀이
 * 다익스트라
 */

public class BJ_1504_특정한최단경로 {
    static final int INF = (int) 1e9;
    static int N, E;

    // 1에서 v1, v1에서 v2, v2에서 N 최단 거리
    // 1에서 v2, v2에서 v1, v1에서 N 최단 거리
    static int[] d1, d2, d3;
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

        // 정점의 개수
        N = Integer.parseInt(st.nextToken());
        // 간선의 개수
        E = Integer.parseInt(st.nextToken());

        // 최단 경로 길이 담을 배열 초기화
        d1 = new int[N + 1];
        d2 = new int[N + 1];
        d3 = new int[N + 1];
        Arrays.fill(d1, INF);
        Arrays.fill(d2, INF);
        Arrays.fill(d3, INF);

        // 그래프 초가화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // a에서 b로 가는 거리 c
            graph.get(a).add(new Node(b, c));
            // b에서 a로 가는 거리 c
            graph.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        dijkstra1(1);
        dijkstra2(v1);
        dijkstra3(v2);

        // 1에서 v1, v1에서 v2, v2에서 N 최단 거리
        long sum1 = (long) d1[v1] + d2[v2] + d3[N];

        // 최단 경로 길이 담을 배열 초기화
        Arrays.fill(d2, INF);
        Arrays.fill(d3, INF);
        dijkstra2(v2);
        dijkstra3(v1);

        // 1에서 v2, v2에서 v1, v1에서 N 최단 거리
        long sum2 = (long) d1[v2] + d2[v1] + d3[N];

        long ans = Math.min(sum1, sum2);

        if (ans >= INF) System.out.println(-1);
        else System.out.println(ans);
    }

    private static void dijkstra3(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d3[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int nowIdx = now.index;
            int nowDis = now.distance;
            if (d3[nowIdx] < nowDis) continue;
            // 현재 노드와 연결되어 있는 노드들 확인
            for (Node next : graph.get(nowIdx)) {
                int nextIdx = next.index;
                int cost = d3[nowIdx] + next.distance;
                if (cost < d3[nextIdx]) {
                    d3[nextIdx] = cost;
                    pq.offer(new Node(nextIdx, cost));
                }
            }
        }
    }

    private static void dijkstra2(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d2[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int nowIdx = now.index;
            int nowDis = now.distance;
            if (d2[nowIdx] < nowDis) continue;
            // 현재 노드와 연결되어 있는 노드들 확인
            for (Node next : graph.get(nowIdx)) {
                int nextIdx = next.index;
                int cost = d2[nowIdx] + next.distance;
                if (cost < d2[nextIdx]) {
                    d2[nextIdx] = cost;
                    pq.offer(new Node(nextIdx, cost));
                }
            }
        }
    }

    private static void dijkstra1(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d1[1] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int nowIdx = now.index;
            int nowDis = now.distance;
            if (d1[nowIdx] < nowDis) continue;
            // 현재 노드와 연결되어 있는 노드들 확인
            for (Node next : graph.get(nowIdx)) {
                int nextIdx = next.index;
                int cost = d1[nowIdx] + next.distance;
                if (cost < d1[nextIdx]) {
                    d1[nextIdx] = cost;
                    pq.offer(new Node(nextIdx, cost));
                }
            }
        }
    }

}
