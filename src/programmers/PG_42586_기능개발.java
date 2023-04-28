package programmers;

import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/42586

기능개발
기능 개선 작업, 각 기능 진도 100일 때 서비스 반영 가능
뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포
각 배포마다 몇 개의 기능이 배포되는지를 return

풀이
큐
*/

class PG_42586_기능개발 {
    public int[] solution(int[] progresses, int[] speeds) {

        // 작업이 몇 일 걸리는지 계산
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            q.offer((int) Math.ceil((100 - progresses[i]) / (double) speeds[i]));
        }

        // 각 배포마다 몇 개의 기능이 배포 되는지 저장할 리스트
        List<Integer> list = new ArrayList<>();
        while (!q.isEmpty()) {
            int day = q.poll();
            int cnt = 1;
            while (!q.isEmpty() && day >= q.peek()) {
                q.poll();
                cnt++;
            }
            list.add(cnt);
        }

        // 리스트 배열로 변환
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}