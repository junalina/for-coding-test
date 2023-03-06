package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/5014
 * <p>
 * 스타트링크
 * 스타트링크 F층의 건물에 사무실이 있고, 스타트링크가 있는 곳의 위치는 G층
 * 강호는 현재 S층 엘리베이터를 타고 G층으로 가려고 한다.
 * 엘리베이터에 버튼이 2개밖에 없다.
 * U버튼은 위로 U층을 가는 버튼, D버튼은 아래로 D층을 가는 버튼이다. (만약, U층 위, 또는 D층 아래에 해당하는 층이 없을 때는, 엘리베이터는 움직이지 않는다)
 * 강호가 S층에서 G층으로 가기 위해 눌러야 하는 버튼의 수의 최솟값을 출력
 * <p>
 * 풀이
 * bfs
 */

public class BJ_5014_스타트링크 {

    static final int INF = (int) 1e9;
    static int F, S, G, U, D;
    static int[] dist; // 거리 저장할 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken()); // 범위
        S = Integer.parseInt(st.nextToken()); // 시작 위치
        G = Integer.parseInt(st.nextToken()); // 목표
        U = Integer.parseInt(st.nextToken()); // 위로 U층
        D = Integer.parseInt(st.nextToken()); // 아래로 D층

        // 거리 저장할 배열에 최대값 삽입
        dist = new int[F + 1];
        for (int i = 1; i <= F; i++) {
            dist[i] = INF;
        }

        bfs();

        if (dist[G] == INF) System.out.println("use the stairs");
        else System.out.println(dist[G]);

    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        // 시작 지점
        q.offer(S);
        dist[S] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            int up = now + U;
            int down = now - D;

            // 범위 안에 있고 방문하지 않았다면
            if (up <= F && dist[up] == INF) {
                q.offer(up);
                dist[up] = dist[now] + 1;
            }

            // 범위 안에 있고 방문하지 않았다면
            if (down >= 1 && dist[down] == INF) {
                q.offer(down);
                dist[down] = dist[now] + 1;
            }
        }
    }
}
