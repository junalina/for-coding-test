package programmers;

import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/12909

올바른 괄호
괄호가 바르게 짝지어졌다는 것은 '(' 문자로 열렸으면 반드시 짝지어서 ')' 문자로 닫혀야 한다는 뜻
문자열 s가 올바른 괄호이면 true 아니면 false return

풀이
스택
*/

class PG_12909_올바른괄호 {
    boolean solution(String s) {
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 현재 괄호가 '('라면
            if (c == '(') {
                stk.push(c);
            }
            // 현재 괄호가 ')'라면
            else if (c == ')') {
                if (stk.isEmpty()) return false;
                else stk.pop();
            }
        }

        return stk.isEmpty();
    }
}
