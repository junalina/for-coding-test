package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/4485
 * <p>
 * 녹색 옷 입은 애가 젤다지?
 * 링크가 [0][0] -> [N-1][N-1] 이동하는데 잃을 수 밖에 없는 최소 금액 출력
 * <p>
 * 풀이
 * 다익스트라
 */

public class BJ_4485_녹색옷입은애가젤다지 {
    static final int INF = (int) 1e9;
    static int N;
    static int[][] rupee;
    static int[][] d;

    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Node implements Comparable<Node> {
        int r, c, cost;

        public Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = 1;

        while (true) {
            // 동굴의 크기
            N = Integer.parseInt(br.readLine());

            // N = 0인 입력이 주어지면 전체 입력이 종료
            if (N == 0) break;

            rupee = new int[N][N];

            // 최소금액 배열 초기화
            d = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(d[i], INF);
            }

            // 도둑루피의 크기
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    rupee[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            dijkstra();

            sb.append("Problem ").append(tc).append(": ").append(d[N - 1][N - 1]).append("\n");

            // 테스트 케이스 번호 증가
            tc++;
        }
        System.out.println(sb);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, rupee[0][0]));
        d[0][0] = rupee[0][0];

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int r = node.r;
            int c = node.c;
            int cost = node.cost;
            if (d[r][c] < cost) continue;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (!isIn(nr, nc)) continue;
                int nextCost = d[r][c] + rupee[nr][nc];
                if (nextCost < d[nr][nc]) {
                    d[nr][nc] = nextCost;
                    pq.offer(new Node(nr, nc, nextCost));
                }
            }
        }
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < N;
    }
}
