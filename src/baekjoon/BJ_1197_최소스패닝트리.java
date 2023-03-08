package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1197
 * <p>
 * 최소 스패닝 트리
 * 그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리의 가중치 출력
 * <p>
 * 풀이
 * 크루스칼 정렬
 */

public class BJ_1197_최소스패닝트리 {
    static int V, E;
    static int[] parent;
    static ArrayList<Edge> edges = new ArrayList<>();

    static class Edge implements Comparable<Edge> {
        int nodeA, nodeB, weight;

        public Edge(int nodeA, int nodeB, int weight) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.weight = weight;
        }

        // 가중치가 작은 것이 높은 우선순위
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점의 개수
        V = Integer.parseInt(st.nextToken());
        // 간선의 개수
        E = Integer.parseInt(st.nextToken());

        // 부모 배열을 자기 자신으로 초기화
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            // A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다.
            edges.add(new Edge(A, B, C));
        }

        // 간선을 비용순으로 정렬
        Collections.sort(edges);

        int result = 0;
        // 간선을 하나씩 확인하며
        for (Edge edge : edges) {
            int nodeA = edge.nodeA;
            int nodeB = edge.nodeB;
            int weight = edge.weight;
            // 사이클이 발생하지 않는 경우에만 집합에 포함
            if (findParent(nodeA) != findParent(nodeB)) {
                unionParent(nodeA, nodeB);
                result += weight;
            }
        }

        System.out.println(result);
    }

    // 두 원소가 속한 집합을 합치기
    private static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    // 특정 원소가 속한 집합을 찾기
    private static int findParent(int x) {
        if (x == parent[x]) return x;
        else return parent[x] = findParent(parent[x]);
    }
}
