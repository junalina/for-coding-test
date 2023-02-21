package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 학생들 0 ~ N
 * 처음 모든 학생이 서로 다른 팀 => 총 N + 1개의 팀
 * 팀 합치기 연산과 같은 팀 여부 확인 연산을 사용
 * 1. 팀 합치기 연산은 두 팀을 합치는 연산 => Union
 * 2. 같은 팀 여부 확인 연산은 특정한 두 학생이 같은 팀에 속하는지를 확인하는 연산 => 사이클 판별
 * 선생님이 M개의 연산을 수행할 수 있을 때, 같은 팀 여부 확인 연산에 대한 연산 결과 출력
 * input
 * 7 8
 * 0 1 3
 * 1 1 7
 * 0 7 6
 * 1 7 1
 * 0 3 7
 * 0 4 2
 * 0 1 1
 * 1 1 1
 * output
 * NO
 * NO
 * YES
 */

public class 팀결성 {

    static int N, M, parent[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // N, M 공백 구분하여 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 부모 테이블상에서, 부모를 자기 자신으로 초기화
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        // M개의 연산
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 팀 합치기
            if (oper == 0) unionParent(a, b);
            // 같은 팀 여부 확인
            else if (oper == 1) {
                if(findParent(a) == findParent(b)) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);
    }

    // 두 원소가 속한 집합을 합치기
    private static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    // 특정 원소가 속한 집합을 찾기
    private static int findParent(int x) {
        // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if(x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }
}
