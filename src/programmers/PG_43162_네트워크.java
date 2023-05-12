package programmers;

import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/43162

네트워크
네트워크 : 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태
A와 B가 연결, B와 C가 연결 시 A, B, C는 모두 같은 네트워크

풀이
서로소 집합
*/

class PG_43162_네트워크 {
    static int[] parent;

    public static int findParent(int x) {
        // 현재 노드가 부모 노드이면 바로 리턴
        if (x == parent[x]) return x;
            // 아니라면 재귀 반복
        else return parent[x] = findParent(parent[x]);
    }

    public static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public int solution(int n, int[][] computers) {
        // 부모 노드 초기화
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1) unionParent(i, j);
            }
        }

        HashSet<Integer> networkSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            networkSet.add(findParent(parent[i]));
        }

        return networkSet.size();
    }
}