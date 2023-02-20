package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 정확한 순위
 * <p>
 * N명의 성적 분실, 성적을 비교한 결과의 일부만 가지고 있음, 성적은 모두 다름
 * 성적을 비교한 결과가 주어질 때, 성적 순위를 정확히 알 수 있는 학생은 몇 명인지 계산
 * 풀이
 * forcodingtest.플로이드 워셜 알고리즘
 * input
 * 6 6
 * 1 5
 * 3 4
 * 4 2
 * 4 6
 * 5 2
 * 5 4
 * output
 * 1
 */
public class 정확한순위 {

    static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N][N];

        for (int r = 0; r < N; r++) {
            Arrays.fill(graph[r], INF);
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (r == c) graph[r][c] = 0;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A - 1][B - 1] = 1;
        }

        // forcodingtest.플로이드 워셜
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int res = 0;
        for (int r = 0; r < N; r++) {
            int cnt = 0;
            for (int c = 0; c < N; c++) {
                if (graph[r][c] != INF || graph[c][r] != INF) cnt++;
            }
            if (cnt == N) res++;
        }

        System.out.println(res);

    }
}
