package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/11779
 * <p>
 * 최소비용 구하기 2
 * A번째 도시에서 B번째 도시 까지 가는데 드는 최소비용과 경로 출력
 * <p>
 * 풀이
 * 다익스트라, 역추적
 */

public class BJ_11779_최소비용구하기2 {
    static final int INF = (int) 1e9;
    static int n, m;
    static int[] d, prevCity;
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
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 도시의 개수
        n = Integer.parseInt(br.readLine());
        // 버스의 개수
        m = Integer.parseInt(br.readLine());

        // 이전 도시 담을 배열
        prevCity = new int[n + 1];

        // 최소 비용 배열 초기화
        d = new int[n + 1];
        Arrays.fill(d, INF);

        // 그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 버스의 정보
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            // 출발 도시 번호
            int a = Integer.parseInt(st.nextToken());
            // 도착 도시 번호
            int b = Integer.parseInt(st.nextToken());
            // 버스 비용
            int c = Integer.parseInt(st.nextToken());
            // a에서 b로 가는데 드는 비용 c
            graph.get(a).add(new Node(b, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        // 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력
        sb.append(d[end]).append("\n");

        int cnt = 0;
        // 역추적으로 스택에 경로의 도시들 넣기
        Stack<Integer> route = new Stack<>();
        while (end != 0) {
            cnt++;
            route.add(end);
            end = prevCity[end];
        }
        // 최소 비용을 갖는 경로에 포함되어있는 도시의 개수를 출력
        sb.append(cnt).append("\n");
        // 스택이 빌 때까지 경로의 도시들 꺼내기
        while (!route.isEmpty()) {
            sb.append(route.pop()).append(" ");
        }

        System.out.println(sb);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 자기 자신에서 0으로 시작
        pq.offer(new Node(start, 0));
        d[start] = 0;
        prevCity[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int nowIdx = now.index;
            int dis = now.distance;
            // 이미 처리되었다면 무시
            if (d[nowIdx] < dis) continue;
            // 연결되어 있는 노드 확인
            for (Node next : graph.get(nowIdx)) {
                int nextIdx = next.index;
                int cost = d[nowIdx] + next.distance;
                if (cost < d[nextIdx]) {
                    d[nextIdx] = cost;
                    prevCity[nextIdx] = nowIdx; // 이전 도시 입력
                    pq.offer(new Node(nextIdx, cost));
                }
            }
        }
    }
}
