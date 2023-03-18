package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/13549
 * <p>
 * 숨바꼭질 3
 * 수빈 : N
 * 동생 : K
 * 수빈이는 걷거나 순간이동
 * 걷기 : X-1, X+1
 * 순간이동 : 2*X
 * 수빈이가 동생을 찾는 빠른 시간 출력
 * <p>
 * 풀이
 * BFS
 */

public class BJ_13549_숨바꼭질3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수빈이가 있는 위치 N
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // bfs
        Queue<Integer> q = new LinkedList<>();
        int[] time = new int[100001];
        q.offer(N);
        // 방문확인 하기 위해 일단 1로 처리, 결과 출력할 때 -1 해서 출력
        time[N] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();
            // 수빈이가 동생을 찾는다면
            if (now == K) break;
            // 순간이동
            int next = 2 * now;
            // 배열 범위 안에 있고 방문하지 않았다면
            if (next <= 100000 && time[next] == 0) {
                q.offer(next);
                time[next] = time[now];
            }
            // 걷기
            next = now - 1;
            // 배열 범위 안에 있고 방문하지 않았다면
            if (next >= 0 && time[next] == 0) {
                q.offer(next);
                time[next] = time[now] + 1;
            }
            next = now + 1;
            // 배열 범위 안에 있고 방문하지 않았다면
            if (next <= 100000 && time[next] == 0) {
                q.offer(next);
                time[next] = time[now] + 1;
            }
        }

        System.out.println(time[K] - 1);

    }
}
