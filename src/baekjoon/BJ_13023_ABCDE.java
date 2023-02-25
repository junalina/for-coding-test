package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/13023
 * <p>
 * ABCDE
 * 0 ~ N-1 사람
 * 친구 관계를 가진 사람 A, B, C, D, E가 존재하는지 안하는지 구하기
 * <p>
 * 풀이
 * DFS
 */

public class BJ_13023_ABCDE {
    static int N, M, ans;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];

        // 인접 리스트 초기화
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        // a와 b 친구 관계 설정
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        ans = 0;

        for (int i = 0; i < N; i++) {
            if (ans == 1) break;
            else if (ans == 0) dfs(i, 1);
        }

        System.out.println(ans);

    }

    private static void dfs(int start, int depth) {
        // ABCDE가 존재한다면 ans를 1로 변경 후 리턴
        if (depth == 5) {
            ans = 1;
            return;
        }
        visited[start] = true;
        for (int i : graph.get(start)) {
            // 방문했으면 무시
            if (visited[i]) continue;
            dfs(i, depth + 1);
        }
        visited[start] = false;
    }
}
