package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/13913
 * <p>
 * 숨바꼭질 4
 * 수빈이 동생과 숨바꼭질
 * 수빈 : N
 * 동생 : K
 * 걷기 : 1초 후에 X-1 or X+1
 * 순간이동 : 1초 후에 2*X
 * 수빈이가 동생을 찾는 가장 빠른 시간과 어떻게 이동해야 하는지 출력
 * <p>
 * 풀이
 * BFS
 */

public class BJ_13913_숨바꼭질4 {
    static int N, K;
    static int[] time = new int[100001];
    static int[] parent = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();

        // 동생을 찾는 가장 빠른 시간
        sb.append(time[K] - 1).append("\n");

        // 경로 구하기
        Stack<Integer> stack = new Stack<>();
        stack.push(K);
        int index = K;

        // N이 나올때까지 넣기
        while (index != N) {
            int prev = parent[index];
            stack.push(prev);
            index = prev;
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        // N에서 시작
        q.offer(N);
        time[N] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();

            if (now == K) return;

            // 왼쪽으로 걷기
            int next = now - 1;
            // 배열 범위 안이고 방문하지 않았다면
            if (next >= 0 && time[next] == 0) {
                q.offer(next);
                time[next] = time[now] + 1;
                parent[next] = now;
            }

            // 오른쪽으로 걷기
            next = now + 1;
            if (next <= 100000 && time[next] == 0) {
                q.offer(next);
                time[next] = time[now] + 1;
                parent[next] = now;
            }

            // 순간이동
            next = 2 * now;
            if (next <= 100000 && time[next] == 0) {
                q.offer(next);
                time[next] = time[now] + 1;
                parent[next] = now;
            }
        }
    }
}
