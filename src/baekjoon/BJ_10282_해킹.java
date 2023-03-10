package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/10282
 * <p>
 * 해킹
 * 네트워크 시설의 한 컴퓨터 해킹
 * a가 b에 의존한다면, b가 감염되면 그로부터 일정 시간 뒤 a도 감염
 * 총 감염되는 컴퓨터 수, 마지막 컴퓨터가 감염되기까지 걸리는 시간 출력
 * <p>
 * 풀이
 * 다익스트라
 */

public class BJ_10282_해킹 {
    static final int INF = (int) 1e9;
    static int n, d, c, ansCnt, ansTime;
    static int[] dis = new int[10001];
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
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스의 개수
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            // 컴퓨터 개수
            n = Integer.parseInt(st.nextToken());
            // 의존성 개수
            d = Integer.parseInt(st.nextToken());
            // 해킹당한 컴퓨터의 번호
            c = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            // 그래프 초기화
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                // 컴퓨터 a
                int a = Integer.parseInt(st.nextToken());
                // 컴퓨터 b
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                // 컴퓨터 b가 감염되면 s초 후 a도 감염
                graph.get(b).add(new Node(a, s));
            }

            // 최소 시간 배열 초기화
            Arrays.fill(dis, INF);

            dijkstra(c);

            // 총 감염되는 컴퓨터 수
            ansCnt = 0;
            // 마지막 컴퓨터가 감염되기까지 걸리는 시간
            ansTime = 0;


            for (int i = 1; i <= n; i++) {
                if (dis[i] != INF) {
                    ansCnt++;
                    ansTime = Math.max(ansTime, dis[i]);
                }
            }

            sb.append(ansCnt).append(" ").append(ansTime).append("\n");

        }
        System.out.println(sb);

    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dis[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int nowIdx = now.index;
            int nowDis = now.distance;
            if (dis[nowIdx] < nowDis) continue;
            // 현재 노드와 연결되어 있는 노드들 확인
            for (Node next : graph.get(nowIdx)) {
                int nextIdx = next.index;
                int cost = dis[nowIdx] + next.distance;
                if (cost < dis[nextIdx]) {
                    dis[nextIdx] = cost;
                    pq.offer(new Node(nextIdx, cost));
                }
            }
        }
    }
}
