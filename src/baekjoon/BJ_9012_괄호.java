package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * https://www.acmicpc.net/problem/9012
 * <p>
 * 괄호
 * 입력 괄호 문자열이 올바른 괄호 문자열(VPS)이면 "YES", 아니면 "NO" 출력
 * <p>
 * 풀이
 * 스택
 */

public class BJ_9012_괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 입력 데이터의 수
        int T = Integer.parseInt(br.readLine());
        // 테스트 데이터 수만큼 반복
        for (int tc = 1; tc <= T; tc++) {
            Stack<Character> stack = new Stack<>();
            String str = br.readLine();
            boolean next = false;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                // '('면 스택에 추가
                if (c == '(') stack.push(c);
                // '('가 아닌데 스택이 비어있다면 VPS가 아님
                else if (stack.isEmpty()) {
                    sb.append("NO").append("\n");
                    next = true;
                    break;
                }
                // ')'라면 스택에서 하나 빼기
                else if (c == ')') stack.pop();
            }

            // 다음 테스트 데이터로 이동
            if (next) continue;

            if (stack.isEmpty()) sb.append("YES");
            else sb.append("NO");

            sb.append("\n");
        }
        System.out.println(sb);
    }
}
