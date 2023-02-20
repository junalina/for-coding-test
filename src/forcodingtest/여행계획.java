package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 여행 계획
 * <p>
 * 1 ~ N개의 여행지, 여행지 사이에는 도로가 존재할 수 있음
 * 도로가 존재 = 양방향 이동 가능
 * 여행 계획이 가능한지의 여부 판별
 * <p>
 * 풀이
 * 서로소 집합, 여행 계획의 부모가 모두 같다면 가능
 */
public class 여행계획 {

    static int N, M;

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // 여행지가 연결되어 있는지 여부 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) unionParent(i + 1, j + 1);
            }
        }

        // 여행 계획에 포함된 여행지의 번호들 입력
        ArrayList<Integer> plan = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken());
            plan.add(x);
        }

        boolean result = true;
        // 여행 계획에 속하는 모든 노드의 루트가 동일한지 확인
        for (int i = 0; i < M - 1; i++) {
            if (findParent(plan.get(i)) != findParent(plan.get(i + 1))) result = false;
        }

        if (result) System.out.println("YES");
        else System.out.println("NO");

    }

    private static void unionParent(int i, int j) {
        i = findParent(i);
        j = findParent(j);
        if (i < j) parent[j] = i;
        else parent[i] = j;
    }

    private static int findParent(int i) {
        if (i == parent[i]) return i;
        else return parent[i] = findParent(parent[i]);
    }

}
