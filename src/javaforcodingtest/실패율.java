package javaforcodingtest;

import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/42889

forcodingtest.실패율

신규 사용자의 수가 급감
신규 사용자와 기존 사용자 사이에 스테이지 차이가 너무 큰 것이 문제
forcodingtest.실패율 : 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
*/

class 실패율 {
    static class Node implements Comparable<Node> {
        int stage;
        double failPer;

        public Node (int stage, double failPer) {
            this.stage = stage;
            this.failPer = failPer;
        }

        @Override
        public int compareTo(Node o) {
            if (this.failPer == o.failPer) return Integer.compare(this.stage, o.stage); // 스테이지 번호 오름차순 정렬
            else return Double.compare(o.failPer, this.failPer); // forcodingtest.실패율 내림차순 정렬
        }
    }

    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];

        ArrayList<Node> list = new ArrayList<>();
        int arrive = stages.length; // 1번 스테이지에 도달한 플레이어 수
        for (int i = 1; i <= N; i++) {
            int notClear = 0; // 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수
            for (int stage : stages) {
                if (stage == i) notClear++;
            }
            double failPer = 0;
            if (arrive >= 1) failPer = (double) notClear / arrive;
            list.add(new Node(i, failPer));
            arrive -= notClear;
        }

        // 오름차순 정렬
        Collections.sort(list);

        for (int i = 0; i < N; i++) {
            answer[i] = list.get(i).stage;
        }

        return answer;
    }
}