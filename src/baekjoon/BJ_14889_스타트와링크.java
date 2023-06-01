package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14889
 * <p>
 * 스타트와 링크
 * 짝수 N
 * Sij는 i번 사람과 j번 사람이 같은 팀에 속했을 때, 팀에 더해지는 능력치
 * 팀의 능력치는 팀에 속한 모든 쌍의 능력치 Sij의 합
 * 스타트 팀과 링크 팀의 능력치의 차이의 최솟값 출력
 * <p>
 * 풀이
 * 조합, 백트래킹
 */

public class BJ_14889_스타트와링크 {

    static int N, min;
    static int[][] stats;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 짝수 N
        N = Integer.parseInt(br.readLine());

        // 능력치
        stats = new int[N][N];
        visited = new boolean[N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                stats[r][c] = Integer.parseInt(st.nextToken());
            }
        } // 입력 완료

        min = Integer.MAX_VALUE;

        comb(0, 0);

        System.out.println(min);


    }

    private static void comb(int start, int cnt) {
        if (cnt == N / 2) {
            // 차이 계산하며 최소값 갱신
            cal();
            return;
        }

        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true; // 방문 처리
                comb(i + 1, cnt + 1);
                visited[i] = false; // 방문 삭제
            }
        }
    }

    private static void cal() {
        int start = 0;
        int link = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visited[i] && visited[j]) {
                    start += stats[i][j];
                    start += stats[j][i];
                } else if (!visited[i] && !visited[j]) {
                    link += stats[i][j];
                    link += stats[j][i];
                }
            }
        }
        int diff = Math.abs(start - link);

        // 0이면 최소니깐 0 출력하고 종료
        if (diff == 0) {
            System.out.println(0);
            System.exit(0);
        }

        // 스타트와 링크 점수 차이
        min = Math.min(min, diff);
    }
}
