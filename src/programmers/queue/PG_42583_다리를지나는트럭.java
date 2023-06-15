package programmers.queue;

import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/42583

다리를 지나는 트럭
트럭 여러 대가 강을 가로지르는 일차선 다리 정해진 순으로 건너려 함
모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return

풀이
큐
*/

class PG_42583_다리를지나는트럭 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        // 다리를 건너는 트럭
        Queue<Integer> q = new LinkedList<>();
        // 다리에 있는 트럭들의 무게
        int sum = 0;
        for (int t : truck_weights) {
            while (true) {
                // 다리가 비어있다면
                if (q.isEmpty()) {
                    // 다리에 트럭 추가
                    q.offer(t);
                    // 다리에 트럭 무게 추가
                    sum += t;
                    // 초 증가
                    answer++;
                    break;
                } else {
                    // 다리가 트럭으로 꽉 찼다면
                    if (q.size() == bridge_length) {
                        sum -= q.poll();
                    }
                    // 다음 트럭이 무게를 초과한다면
                    else if (sum + t > weight) {
                        q.offer(0);
                        answer++;
                    }
                    // 무게 이내라면
                    else {
                        // 다리에 트럭 추가
                        q.offer(t);
                        // 다리에 트럭 무게 추가
                        sum += t;
                        // 초 증가
                        answer++;
                        break;
                    }
                }
            }
        }
        return answer + bridge_length;
    }
}
