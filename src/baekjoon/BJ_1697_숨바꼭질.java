package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1697
 * <p>
 * 숨바꼭질
 * 수빈 현재 점 N, 동생 점 K
 * 수빈이는 걷거나 순간이동
 * 걷는다면 1초 후에 X-1 또는 X+1
 * 순간이동 하는 경우 2*X
 * 수빈이가 동생을 찾는 가장 빠른 시간 출력
 * <p>
 * 풀이
 * BFS
 */

public class BJ_1697_숨바꼭질 {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[100001];
        q.offer(N);
        visited[N] = 1;

        while (!q.isEmpty()) {
            int X = q.poll();
            if (X == K) {
                return visited[X] - 1;
            }
            if (X - 1 >= 0 && visited[X - 1] == 0) {
                q.offer(X - 1);
                visited[X - 1] = visited[X] + 1;
            }
            if (X + 1 <= 100000 && visited[X + 1] == 0) {
                q.offer(X + 1);
                visited[X + 1] = visited[X] + 1;
            }
            if (2 * X <= 100000 && visited[2 * X] == 0) {
                q.offer(2 * X);
                visited[2 * X] = visited[X] + 1;
            }
        }
        return 0;
    }
}
