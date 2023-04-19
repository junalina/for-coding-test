package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/5972
 * <p>
 * 택배 배송
 * 현서는 찬홍이에게 택배 배달
 * 평화롭게 가려면 가는 길에 만나는 모든 소들에게 맛있는 여물을 줘야 함
 * N개의 헛간, M개의 양방향 길
 * <p>
 * 풀이
 * 다익스트라
 */

public class BJ_5972_택배배송 {
    static final int INF = (int) 1e9;
    static int N, M;
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

        // 헛간의 수
        N = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 소 길의 수
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        // 최단 경로값 담을 배열 초기화
        d = new int[N + 1];
        Arrays.fill(d, INF);

        dijkstra(1);

        System.out.println(d[N]);

    }

    private static void dijkstra(int i) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(i, 0));
        d[i] = 0;

        while (!pq.isEmpty()) {
            // 가장 최단 경로가 짧은 노드 뽑기
            Node now = pq.poll();
            int index = now.index;
            int distance = now.distance;
            if (d[index] < distance) continue;
            for (int j = 0; j < graph.get(index).size(); j++) {
                int cost = d[index] + graph.get(index).get(j).distance;
                // 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
                if (cost < d[graph.get(index).get(j).index]) {
                    d[graph.get(index).get(j).index] = cost;
                    pq.offer(new Node(graph.get(index).get(j).index, cost));
                }
            }
        }
    }
}
