package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14938
 * <p>
 * 서강그라운드
 * 낙하한 지역을 중심으로 거리가 수색 범위 m (1 ≤ m ≤ 15) 이내의
 * 모든 지역의 아이템을 습득 가능하다고 할 때
 * 예은이가 얻을 수 있는 아이템의 최대 개수 출력
 * <p>
 * 풀이
 * 다익스트라 n번 사용
 */

public class BJ_14938_서강그라운드 {
    static final int INF = (int) 1e9;
    static int n, m, r;
    static int[] cnt;
    static int[] d;
    static ArrayList<ArrayList<Node>> graph;

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

        // 지역의 개수
        n = Integer.parseInt(st.nextToken());
        // 수색범위
        m = Integer.parseInt(st.nextToken());
        // 길의 개수
        r = Integer.parseInt(st.nextToken());

        // 각 구역에 있는 아이템의 수
        int[] t = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            t[i] = Integer.parseInt(st.nextToken());
        }

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            // a 지역의 번호
            int a = Integer.parseInt(st.nextToken());
            // b 지역의 번호
            int b = Integer.parseInt(st.nextToken());
            // 길의 길이
            int l = Integer.parseInt(st.nextToken());
            // 양방향 통행 가능
            graph.get(a).add(new Node(b, l));
            graph.get(b).add(new Node(a, l));
        }

        // 해당 지역에 떨어졌을 때 얻을 수 있는 최대 아이템 개수 저장 배열
        cnt = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 거리 배열 초기화
            d = new int[n + 1];
            Arrays.fill(d, INF);

            dijkstra(i);

            for (int j = 1; j <= n; j++) {
                // 수색 범위 내에 있다면
                if (d[j] <= m) cnt[i] += t[j]; // 해당 구역에 있는 아이템 수 추가
            }
        }

        int max = 0;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, cnt[i]);
        }

        System.out.println(max);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.index;
            int dis = node.distance;
            if (d[now] < dis) continue;
            // 현재 노드와 연결되어 있는 노드들 확인
            for (Node next : graph.get(now)) {
                int nextIdx = next.index;
                int cost = d[now] + next.distance;
                if (cost < d[nextIdx]) {
                    d[nextIdx] = cost;
                    pq.offer(new Node(nextIdx, cost));
                }
            }
        }
    }
}
