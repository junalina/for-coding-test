package programmers;

import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/12906

같은 숫자는 싫어
배열 arr에서 연속적으로 나타나는 숫자는 제거하고 남은 수들을 return

풀이
스택
*/

public class PG_12906_같은숫자는싫어 {
    public int[] solution(int[] arr) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            // 스택이 비어있을 때
            if (stack.isEmpty() || stack.peek() != arr[i]) stack.push(arr[i]);
        }

        int[] answer = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            answer[i] = stack.pop();
        }

        return answer;
    }
}