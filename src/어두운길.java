import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 어두운 길
 * 0 ~ N-1까지의 N개의 집, M개의 도로
 * 모든 도로에는 가로등, 가로등 하루 켜기 위한 비용은 도로의 길이
 * 일부 가로등 비활성화, 마을에 있는 임의의 두 집에 대하여 가로등이 켜진 도로만 오갈 수 있도록 만들고자 함
 * 가로등을 비활성화하여 절약할 수 있는 최대 금액 출력
 * 풀이
 * 크루스칼
 * input
 * 7 11
 * 0 1 7
 * 0 3 5
 * 1 2 8
 * 1 3 9
 * 1 4 7
 * 2 4 5
 * 3 4 15
 * 3 5 6
 * 4 5 8
 * 4 6 9
 * 5 6 11
 * output
 * 51
 */
public class 어두운길 {

    static int[] parent;

    static class Edge implements Comparable<Edge> {
        int X, Y, Z;

        public Edge(int x, int y, int z) {
            X = x;
            Y = y;
            Z = z;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.Z, o.Z);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 집의 수
        int M = Integer.parseInt(st.nextToken()); // 도로의 수

        // 부모 테이블 자기 자신으로 초기화
        parent = new int[N];
        for (int i = 1; i < N; i++) {
            parent[i] = i;
        }

        ArrayList<Edge> edges = new ArrayList<>();

        int total = 0;

        // 모든 간선에 대한 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int Z = Integer.parseInt(st.nextToken());
            edges.add(new Edge(X, Y, Z));
            total += Z;
        }

        // 간선을 비용순으로 정렬
        Collections.sort(edges);

        // 간선을 하나씩 확인하며
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            int X = edge.X;
            int Y = edge.Y;
            int Z = edge.Z;
            // 사이클이 발생하지 않은 경우에만 집합에 포함
            if (findParent(X) != findParent(Y)) {
                unionParent(X, Y);
                total -= Z;
            }
        }

        System.out.println(total);

    }

    private static void unionParent(int x, int y) {
        x = findParent(x);
        y = findParent(y);
        if (x < y) parent[y] = x;
        else parent[x] = y;
    }

    private static int findParent(int x) {
        if (x == parent[x]) return x;
        else return parent[x] = findParent(parent[x]);
    }
}
