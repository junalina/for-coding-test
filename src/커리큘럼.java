import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 온라인 강의 선수 강의 존재
 * 총 N개의 강의 : 1 ~ N
 * N개의 강의를 수강하기까지 걸리는 최소 시간을 구하라
 * 풀이 : 위상 정렬
 */
public class 커리큘럼 {

    static int N;
    static int[] indegree, times;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 듣고자 하는 강의의 수
        N = Integer.parseInt(br.readLine());

        indegree = new int[N + 1];
        times = new int[N + 1];
        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 방향 그래프의 모든 간선 정보를 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            // 해당 강의를 듣기 위해 먼저 들어야 하는 강의들의 번호 입력
            while (true) {
                int x = Integer.parseInt(st.nextToken());
                if (x == -1) break;
                indegree[i] += 1;
                graph.get(x).add(i);
            }
        }

        topologySort();

        System.out.println(sb);
    }

    private static void topologySort() {
        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            result[i] = times[i];
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if(indegree[i] == 0) q.offer(i);
        }

        // 큐가 빌 때까지 반복
        while (!q.isEmpty()) {
            int now = q.poll();
            // 해당 원소와 연결된 노드들의 진입차수에서 1빼기
            for (int i = 0; i < graph.get(now).size(); i++) {
                result[graph.get(now).get(i)] = Math.max(result[graph.get(now).get(i)], result[now] + times[graph.get(now).get(i)]);
                indegree[graph.get(now).get(i)] -= 1;
                // 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
                if (indegree[graph.get(now).get(i)] == 0) q.offer(graph.get(now).get(i));
            }
        }

        // 위상 정렬을 수행한 결과 출력
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append("\n");
        }
    }
}
