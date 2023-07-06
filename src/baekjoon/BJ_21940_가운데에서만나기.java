package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/21940
 * <p>
 * 가운데에서 만나기
 * 준형이와 친구들 서로 다른 도시
 * 조건을 만족하는 도시 X
 * 왕복시간 : 자신 -> X + X -> 자신
 * 도로를 이용하여 갈 수 있는 도시만 선택
 * 왕복시간 들 중 최대가 최소가 되는 도시 X
 * 준형이와 친구들이 이동할 수 있는 도시가 최소한 하나 이상 있음 보장
 * <p>
 * 풀이
 * 플로이드 워셜
 */

public class BJ_21940_가운데에서만나기 {
    static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 도시의 개수 N
        int N = Integer.parseInt(st.nextToken());
        // 도로의 개수 M
        int M = Integer.parseInt(st.nextToken());

        // 초기화
        int[][] arr = new int[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (r == c) {
                    arr[r][c] = 0;
                } else {
                    arr[r][c] = INF;
                }
            }
        }

        // A -> B로 이동하는데 걸리는 시간 T
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            arr[A][B] = T;
        }

        // 준형이와 친구들의 총 인원 K
        int K = Integer.parseInt(br.readLine());
        int[] C = new int[K];

        // 준형이와 친구들이 살고 있는 도시의 번호 C
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            C[i] = Integer.parseInt(st.nextToken());
        }

        // 플로이드 워셜
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        // 준형이와 친구들의 왕복시간 들 중 최대값
        int[] max = new int[N + 1];
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < K; j++) {
                int c = C[j];
                max[i] = Math.max(max[i], arr[c][i] + arr[i][c]);
            }
            min = Math.min(min, max[i]);
        }

        // 최대가 최소가 되는 도시 X 저장
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (min >= max[i]) {
                result.add(i);
            }
        }

        // 오름차순 정렬
        Collections.sort(result);

        StringBuilder sb = new StringBuilder();
        for (int X : result) {
            sb.append(X).append(" ");
        }

        System.out.println(sb);

    }
}
