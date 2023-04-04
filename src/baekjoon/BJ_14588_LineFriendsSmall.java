package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14588
 * <p>
 * Line Friends (Small)
 * 수직선 위에 N개의 선분
 * 영역이 겹치는 선분끼리만 친구
 * Q개의 줄에 걸쳐 두 선분이 가까운 정도를 출력
 * <p>
 * 풀이
 * 플로이드 워셜
 */

public class BJ_14588_LineFriendsSmall {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int[][] edge = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            edge[i][0] = Integer.parseInt(st.nextToken());
            edge[i][1] = Integer.parseInt(st.nextToken());
        }

        // 인접 행렬 초기화
        int[][] adj = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(adj[i], 301);

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                // 선분의 영역이 겹쳐있다면
                if ((edge[j][0] >= edge[i][0] && edge[j][0] <= edge[i][1])
                        || (edge[j][1] >= edge[i][0] && edge[j][1] <= edge[i][1])
                        || (edge[j][0] < edge[i][0] && edge[j][1] > edge[i][1])) {
                    adj[i][j] = adj[j][i] = 1;
                }
            }
        }

        // 플로이드 워셜
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                }
            }
        }

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            sb.append(adj[A - 1][B - 1] == 301 ? -1 : adj[A - 1][B - 1]).append('\n');
        }

        System.out.println(sb);

    }
}
