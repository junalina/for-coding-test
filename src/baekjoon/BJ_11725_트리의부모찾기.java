package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/11725
 * <p>
 * 트리의 부모 찾기
 * 루트 없는 트리가 주어짐
 * 트리의 루트를 1이라고 정했을 때, 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력
 * <p>
 * 풀이
 * BFS
 */

public class BJ_11725_트리의부모찾기 {

    static int N;
    static int[] answer;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 노드의 개수
        N = Integer.parseInt(br.readLine());

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        answer = new int[N + 1];

        // 트리 정보 입력
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        bfs();

        for (int i = 2; i <= N; i++) {
            sb.append(answer[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        // 트리의 루트 1에서 시작
        q.offer(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i = 0; i < graph.get(now).size(); i++) {
                int next = graph.get(now).get(i);
                if (visited[next]) continue;
                answer[next] = now;
                q.offer(next);
                visited[next] = true;
            }
        }
    }
}
