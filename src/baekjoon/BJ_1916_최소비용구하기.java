package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1916
 * <p>
 * 최소비용 구하기
 * N개의 도시
 * A번째 도시에서 B번째 도시까지 가는데 드는 최소비용 출력
 * <p>
 * 풀이
 * 다익스트라
 */

public class BJ_1916_최소비용구하기 {
    static final int INF = (int) 1e9;
    static int N, M;
    static int[] d;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    static class Node implements Comparable<Node> {
        int index, cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 도시의 개수
        N = Integer.parseInt(br.readLine());
        // 버스의 개수
        M = Integer.parseInt(br.readLine());

        // 최소 비용 배열 초기화
        d = new int[N + 1];
        Arrays.fill(d, INF);

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 버스의 정보
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            // A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용(cost)
            graph.get(A).add(new Node(B, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.println(d[end]);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // start에서 비용 0으로 시작
        pq.offer(new Node(start, 0));
        d[start] = 0;

        // 큐가 비어있지 않다면
        while (!pq.isEmpty()) {
            // 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
            Node now = pq.poll();
            // 현재 노드의 인덱스
            int idx = now.index;
            // 현재 노드까지의 비용
            int cost = now.cost;
            // 현재 노드가 이미 처리된 적이 있는 노드라면 무시
            if (d[idx] < cost) continue;
            // 현재 노드와 연결된 다른 인접한 노드들을 확인
            for (int i = 0; i < graph.get(idx).size(); i++) {
                int nextCost = d[idx] + graph.get(idx).get(i).cost;
                // 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
                if (nextCost < d[graph.get(idx).get(i).index]) {
                    d[graph.get(idx).get(i).index] = nextCost;
                    pq.offer(new Node(graph.get(idx).get(i).index, nextCost));
                }
            }
        }
    }
}
