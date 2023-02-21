package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/3665
 * <p>
 * 최종 순위
 * 1 ~ n번의 팀
 * 최종 순위 발표 X, 작년에 비해 상대적인 순위가 바뀐 팀의 목록만 발표
 * 작년 순위와 상대적인 순위가 바뀐 팀의 목록이 주어졌을 때, 올해 순위를 만드는 프로그램 작성
 * 확실한 올해 순위를 만들 수 없는 경우도 있고, 일관성이 없는 정보일 수 있음
 * <p>
 * 풀이
 * 위상 정렬
 */
public class 최종순위 {

    static StringBuilder sb = new StringBuilder();
    static int n, m;
    static int[] indegree = new int[501];
    static boolean[][] graph = new boolean[501][501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 테스트 케이스의 개수
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            // 배열 초기화
            Arrays.fill(indegree, 0);
            for (int i = 0; i < 501; i++) {
                Arrays.fill(graph[i], false);
            }

            // 팀의 수
            n = Integer.parseInt(br.readLine());

            ArrayList<Integer> lastYear = new ArrayList<>();
            // 작년에 i등을 한 팀의 번호
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int x = Integer.parseInt(st.nextToken());
                lastYear.add(x);
            }

            // 방향 그래프의 간선 정보 초기화
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    graph[lastYear.get(i)][lastYear.get(j)] = true;
                    indegree[lastYear.get(j)] += 1;
                }
            }

            // 상대적인 등수가 바뀐 쌍의 수
            m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                // 간선의 방향 뒤집기
                if (graph[a][b]) {
                    graph[a][b] = false;
                    graph[b][a] = true;
                    indegree[a] += 1;
                    indegree[b] -= 1;
                } else {
                    graph[a][b] = true;
                    graph[b][a] = false;
                    indegree[a] -= 1;
                    indegree[b] += 1;
                }
            }

            topologySort();

            sb.append("\n");
        }
        System.out.println(sb);

    }

    private static void topologySort() {
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        // 처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        boolean certain = true; // 위상 정렬 결과가 오직 하나인지의 여부
        boolean cycle = false; // 그래프 내 사이클이 존재하는지 여부

        // 정확히 노드 개수만큼 반복
        for (int i = 0; i < n; i++) {
            // 큐가 비어 있다면 사이클이 발생했다는 의미
            if (q.isEmpty()) {
                cycle = true;
                break;
            }
            // 큐의 원소가 두 개 이상이라면 가능한 정렬 결과가 여러 개라는 의미
            if (q.size() >= 2) {
                certain = false;
                break;
            }
            // 큐에서 원소 꺼내기
            int now = q.poll();
            result.add(now);
            // 해당 원소와 연결된 노드들의 진입차수에서 1 빼기
            for (int j = 1; j <= n; j++) {
                if (graph[now][j]) {
                    indegree[j] -= 1;
                    // 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
                    if (indegree[j] == 0) q.offer(j);
                }
            }
        }
        // 사이클이 발생하는 경우 (일관성이 없는 경우)
        if (cycle) sb.append("IMPOSSIBLE");
        else if (!certain) sb.append("?");
        else {
            for (int i = 0; i < result.size(); i++) {
                sb.append(result.get(i)).append(" ");
            }
        }
    }
}
