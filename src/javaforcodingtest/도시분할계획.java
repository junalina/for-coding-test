package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 마을 N개의 집, 그 집들을 연결하는 M개의 길, 길마다 유지비
 * 마을을 2개로 분할, 두 집 사이에 경로가 항상 존재하게 길을 없애고 남은 길의 유지비의 합의 최솟값 구하기
 * 풀이 : 크루스칼 알고리즘 / 두 개의 마을이니 최소 신장 트리를 만들고 가장 유지비가 큰 간선만큼 유지비 빼주기
 */
public class 도시분할계획 {

    static int N, M;
    static int[] parent = new int[100001];

    static class Edge implements Comparable<Edge> {

        int cost, nodeA, nodeB;

        public Edge(int cost, int nodeA, int nodeB) {
            this.cost = cost;
            this.nodeA = nodeA;
            this.nodeB = nodeB;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.cost < o.cost) return -1;
            else if(this.cost > o.cost) return 1;
            else return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N, M 공백 구분하여 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 부모를 자기 자신으로 초기화
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        
        // 모든 간선에 대한 정보 입력
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edges.add(new Edge(C, A, B));
        }

        // 간선 비용순으로 정렬
        Collections.sort(edges);

        int sum = 0;
        int last = 0;
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            int c = edge.cost;
            int a = edge.nodeA;
            int b = edge.nodeB;
            // 사이클이 발생하지 않는 경우에만 집합에 포함
            if(findParent(a) != findParent(b)) {
                unionParent(a, b);
                sum += c;
                last = c;
            }
        }

        System.out.println(sum - last);
    }

    // 합치기 연산
    private static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    // 찾기 함수
    private static int findParent(int x) {
        if(x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }
}
