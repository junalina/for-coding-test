package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1번 회사에서 K번 회사를 방문한 뒤에 X번 회사로 가는 것이 목표
 * 방문 판매원이 회사 사이를 이동하게 되는 최소 시간을 계산
 * input1
 * 5 7
 * 1 2
 * 1 3
 * 1 4
 * 2 4
 * 3 4
 * 3 5
 * 4 5
 * 4 5
 * output1
 * 3
 * input2
 * 4 2
 * 1 3
 * 2 4
 * 3 4
 * output2
 * -1
 */

public class 미래도시 {

    static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N, M 공백 구분하여 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N + 1][N + 1];

        // 최단 거리 테이블을 모두 무한으로 초기화
        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], INF);
        }

        // 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(i == j) graph[i][j] = 0;
            }
        }

        // 각 간선에 대한 정보를 입력 받아, 그 값으로 초기화
        for (int i = 0; i < M; i++) {
            // A와 B가 서로에게 가는 비용은 1이라고 설정
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A][B] = graph[B][A] = 1;
        }

        // X, K 공백 구분하여 입력
        st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int dis = graph[1][K] + graph[K][X];

        // 도달할 수 없는 경우, 1을 출력
        if(dis >= INF) System.out.println(-1);
        // 도달할 수 있다면, 최단 거리를 출력
        else System.out.println(dis);
    }
}
