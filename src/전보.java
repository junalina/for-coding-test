import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * N개의 도시
 * 단방향
 * C에서 보낸 메시지를 받게 되는 도시의 개수, 모두 메시지를 받는 데까지 걸리는 시간을 구하라
 * 풀이 : 다익스트라 (우선순위큐)
 * input
 * 3 2 1
 * 1 2 4
 * 1 3 2
 * output
 * 2 4
 */

public class 전보 {

    static int N, M, C;
    // 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    // 최단 거리 테이블 만들기
    static int[] d = new int[30001];
    static final int INF = (int) 1e9;

    static class Node implements Comparable<Node> {

        int index, dis;

        public Node(int index, int dis) {
            this.index = index;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            if(this.dis < o.dis) {
                return -1;
            }
            return 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N, M, C 공백 구분하여 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 모든 간선 정보를 입력받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int Z = Integer.parseInt(st.nextToken());
            // X번 노드에서 Y번 노드로 가는 비용이 Z라는 의미
            graph.get(X).add(new Node(Y, Z));
        }

        // 최단 거리 테이블을 모두 무한으로 초기화
        Arrays.fill(d, INF);

        // 다익스트라 알고리즘 수행
        dijkstra();

        // 도달할 수 있는 노드의 개수
        int cnt = 0;
        // 도달할 수 있는 노드 중에서, 가장 멀리 있는 노드와의 최단 거리
        int maxDis = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            // 도달할 수 있는 노드인 경우
            if (d[i] != INF) {
                cnt += 1;
                maxDis = Math.max(maxDis, d[i]);
            }
        }

        // 시작 노드는 제외해야 하므로 cnt - 1을 출력
        System.out.println((cnt - 1) + " " + maxDis);

    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 시작 노드로 가기 위한 최단 경로는 0으로 설정하며, 큐에 삽입
        pq.offer(new Node(C, 0));
        d[C] = 0;
        while (!pq.isEmpty()) { // 큐가 비어있지 않다면
            // 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
            Node node = pq.poll();
            int dis = node.dis; // 현재 노드까지의 비용
            int now = node.index; // 현재 노드
            // 현재 노드가 이미 처리된 적이 있는 노드라면 무시
            if(d[now] < dis) continue;
            // 현재 노드와 연결된 다른 인접한 노드들을 확인
            for (int i = 0; i < graph.get(now).size(); i++) {
                int cost = d[now] + graph.get(now).get(i).dis;
                // 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
                if(cost < d[graph.get(now).get(i).index]) {
                    d[graph.get(now).get(i).index] = cost;
                    pq.offer(new Node(graph.get(now).get(i).index, cost));
                }
            }
        }
    }
}
