package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * forcodingtest.숨바꼭질
 * <p>
 * 숨을 곳
 * 1 ~ N번 까지의 헛간 중 하나에 숨을 수 있으며, 술래는 1번 헛간에서 출발
 * M개의 양방향 통로
 * 1번 헛간으로부터 최단 거리가 가장 먼 헛간이 안전하다고 판단
 * 최단 거리 = 지나야 하는 길의 최소 개수
 * 숨을 헛간 번호, 그 헛간까지의 거리, 그 헛간과 같은 거리를 갖는 헛간의 개수 출력
 * 풀이
 * 다익스트라 알고리즘
 */
public class 숨바꼭질 {

    static final int INF = (int) 1e9;

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

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 최단 거리 저장할 배열
        int[] d = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, 1));
            graph.get(B).add(new Node(A, 1));
        }

        Arrays.fill(d, INF);

        // 다익스트라 알고리즘
        // 1번 헛간에서 툴발
        int start = 1;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d[start] = 0;
        while (!pq.isEmpty()) {
            Node n = pq.poll();
            int index = n.index;
            int distance = n.distance;
            // 이미 처리된 적 있는 노드라면 무시
            if (d[index] < distance) continue;
            for (int i = 0; i < graph.get(index).size(); i++) {
                int cost = d[index] + graph.get(index).get(i).distance;
                if (cost < d[graph.get(index).get(i).index]) {
                    d[graph.get(index).get(i).index] = cost;
                    pq.offer(new Node(graph.get(index).get(i).index, cost));
                }
            }
        }

        int maxIndex = 0; // 숨어야 하는 헛간 번호
        int maxDistance = Integer.MIN_VALUE; // 그 헛간까지의 거리
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (d[i] == maxDistance) {
                result.add(i); // 가장 먼 헛간과 거리가 같다면 추가
            } else if (d[i] > maxDistance) {
                maxIndex = i; // 숨어야 하는 헛간 번호
                maxDistance = d[i]; // 그 헛간까지의 거리
                result.clear();
                result.add(maxIndex);
            }
        }

        sb.append(maxIndex).append(" ").append(maxDistance).append(" ").append(result.size());

        System.out.println(sb);
    }
}
