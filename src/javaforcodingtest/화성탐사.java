package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 화성 탐사
 * <p>
 * 화성 탐사 기계가 출발 지점에서 목표 지점까지 이동할 때 항상 최적의 경로
 * N x N 2차원 공간
 * [0][0]에서 [N - 1][N - 1]로 이동하는 최소 비용 출력
 * <p>
 * 풀이
 * 다익스트라 알고리즘
 * input
 * 3
 * 3
 * 5 5 4
 * 3 9 1
 * 3 2 7
 * 5
 * 3 7 2 0 1
 * 2 8 0 9 1
 * 1 2 1 8 1
 * 9 8 9 2 0
 * 3 6 5 1 5
 * 7
 * 9 0 5 1 1 5 3
 * 4 1 2 1 6 5 3
 * 0 7 6 1 6 8 5
 * 1 1 7 8 3 2 3
 * 9 4 0 7 6 4 1
 * 5 8 3 2 4 8 3
 * 7 4 8 4 8 3 4
 * output
 * 20
 * 19
 * 36
 */
public class 화성탐사 {

    static final int INF = (int) 1e9;

    // forcodingtest.상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Node implements Comparable<Node> {
        int r, c, distance;

        public Node(int r, int c, int distance) {
            this.r = r;
            this.c = c;
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

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());

            int[][] graph = new int[N][N];
            int[][] d = new int[N][N];

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    graph[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            for (int r = 0; r < N; r++) {
                Arrays.fill(d[r], INF);
            }

            // 다익스트라 알고리즘
            int r = 0, c = 0;
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(r, c, graph[r][c]));
            d[r][c] = graph[r][c];

            while (!pq.isEmpty()) {
                Node now = pq.poll();
                r = now.r;
                c = now.c;
                int dis = now.distance;
                // 현재 노드가 이미 처리된 적이 있는 노드라면 무시
                if (d[r][c] < dis) continue;
                // forcodingtest.상하좌우
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    // 이동한 위치가 공간 안에 없다면
                    if (!isIn(nr, nc, N)) continue;
                    // 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
                    if (d[nr][nc] > dis + graph[nr][nc]) {
                        d[nr][nc] = dis + graph[nr][nc];
                        pq.offer(new Node(nr, nc, d[nr][nc]));
                    }
                }
            }
            sb.append(d[N - 1][N - 1]).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean isIn(int nr, int nc, int N) {
        return nr >= 0 && nr < N && nc >= 0 && nc < N;
    }
}
