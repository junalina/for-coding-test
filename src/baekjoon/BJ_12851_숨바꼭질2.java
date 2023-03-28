package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/12851
 * <p>
 * 숨바꼭질 2
 * 수빈이는 동생과 숨바꼭질
 * 수빈 : N
 * 동생 : K
 * 수빈이는 걷거나 순간이동 가능
 * 수빈이의 위치가 X일 때 걷는다면 1초 후 X-1, X+1
 * 순간이동을 하는 경우 1초 후 2*X
 * 수빈이가 동생을 찾는 가장 빠른 시간 출력
 * 가장 빠른 시간으로 수빈이가 동생을 찾는 방법의 수를 출력
 * <p>
 * 풀이
 * BFS
 */

public class BJ_12851_숨바꼭질2 {
    static int N, K, min, cnt;

    static class Point {
        int index, time;

        public Point(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 수빈이가 있는 위치
        N = Integer.parseInt(st.nextToken());
        // 동생이 있는 위치
        K = Integer.parseInt(st.nextToken());

        // 가장 빠른 시간
        min = Integer.MAX_VALUE;
        // 가장 빠른 시간으로 수빈이가 동생을 찾는 방법의 수
        cnt = 1;

        if (N >= K) {
            min = N - K;
        } else {
            bfs();
        }

        sb.append(min).append("\n").append(cnt);

        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        // 위치 N부터 시간 0으로 시작
        q.offer(new Point(N, 0));
        visited[N] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            int index = p.index;
            int time = p.time;

            visited[index] = true;

            // 동생이 있는 곳에 도달했다면
            if (index == K) {
                if (min > time) min = time;
                else if (min == time) cnt++;
                else continue;
            }

            // 왼쪽으로 걷기
            int nIndex1 = index - 1;
            // 오른쪽으로 걷기
            int nIndex2 = index + 1;
            // 순간이동
            int nIndex3 = index * 2;

            if (nIndex1 >= 0 && !visited[nIndex1]) q.offer(new Point(nIndex1, time + 1));
            if (nIndex2 <= 100000 && !visited[nIndex2]) q.offer(new Point(nIndex2, time + 1));
            if (nIndex3 <= 100000 && !visited[nIndex3]) q.offer(new Point(nIndex3, time + 1));

        }
    }
}