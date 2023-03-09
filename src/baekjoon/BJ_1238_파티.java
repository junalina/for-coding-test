package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1238
 * <p>
 * 파티
 * N명의 학생이 X번 마을에 모여서 파티
 * M개의 단방향 도로들
 * 파티에 참석하기 위해 걸어가서 다시 마을로 돌아와야 함
 * N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간 출력
 * <p>
 * 풀이
 * 다익스트라
 * 1. 1 ~ N번 마을에서 X번 마을로 가는 최소 시간 구하기
 * 2. X번 마을에서 각자 마을로 돌아오는 최소 시간 구하기
 * 3. 1번과 2번 합이 가장 큰 값 구하기
 */

public class BJ_1238_파티 {
    static final int INF = (int) 1e9;
    static int N, M, X;
    static int[] go, goMin, back;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    static class Node implements Comparable<Node> {
        int index, time;

        public Node(int index, int time) {
            this.index = index;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.time, o.time);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            // 도로의 시작점
            int start = Integer.parseInt(st.nextToken());
            // 도로의 끝점
            int end = Integer.parseInt(st.nextToken());
            // 도로를 지나는데 필요한 소요시간
            int t = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, t));
        }

        goMin = new int[N + 1];
        // i번 마을에서 X번 마을로 가는 최소시간 구하기
        for (int i = 1; i <= N; i++) {
            // 배열 초기화
            go = new int[N + 1];
            Arrays.fill(go, INF);

            dijkstra(i, go);

            goMin[i] = go[X];
        }

        // X번 마을에서 집으로 가는 최소 시간 배열 초기화
        back = new int[N + 1];
        Arrays.fill(back, INF);

        // X번 마을에서 집으로 돌아오는 최소시간 구하기
        dijkstra(X, back);

        // 가장 오래 걸리는 학생의 소요시간
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, goMin[i] + back[i]);
        }

        System.out.println(max);

    }

    private static void dijkstra(int start, int[] arr) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        arr[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int nowIdx = now.index;
            int nowTime = now.time;
            // 이미 처리되었다면 무시
            if (arr[nowIdx] < nowTime) continue;
            // 현재 노드와 연결된 노드들 확인
            for (Node next : graph.get(nowIdx)) {
                int nextIdx = next.index;
                int cost = arr[nowIdx] + next.time;
                if (cost < arr[nextIdx]) {
                    arr[nextIdx] = cost;
                    pq.offer(new Node(nextIdx, cost));
                }
            }
        }
    }
}
