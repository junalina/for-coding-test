package javaforcodingtest;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/60058

괄호 변환

괄호가 개수는 맞지만 짝이 맞지 않은 형태라 오류
모든 괄호를 뽑아서 올바른 순서대로 배치한 괄호 문자열을 알려주는 프로그램
*/

class 괄호변환 {
    public String solution(String w) {
        String answer = "";

        // 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환
        if (w.equals("")) return answer;

        // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리
        // 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리 X, v는 빈 문자열이 될 수 있다.
        int index = balancedIndex(w);
        String u = w.substring(0, index + 1);
        String v = w.substring(index + 1, w.length());

        // 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행
        if (isTrueString(u)) {
            answer = u + solution(v);
        }
        // 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면
        else {
            // 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다. 
            answer = "(";
            // 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다. 
            answer += solution(v);
            // 4-3. ')'를 다시 붙입니다.
            answer += ")";
            // 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. 
            String removedU = u.substring(1, u.length()-1);
            String reverseU = "";
            for (int i = 0; i < removedU.length(); i++) {
                if (removedU.charAt(i) == '(') reverseU += ")";
                else reverseU += "(";
            }
            answer += reverseU;
        }

        return answer;
    }

    // 올바른 괄호 문자열인지 판별
    public boolean isTrueString(String u) {
        int cnt = 0; // 왼쪽 괄호의 개수
        for (int i = 0; i < u.length(); i++) {
            if (u.charAt(i) == '(') cnt++;
            else {
                if (cnt == 0) return false;
                else cnt--;
            }
        }
        return true;
    }

    // 균형잡힌 괄호 문자열 인덱스 구하기
    public int balancedIndex(String w) {
        int cnt = 0; // 왼쪽 괄호의 개수
        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) == '(') cnt++;
            else cnt--;
            if (cnt == 0) return i;
        }
        return -1;
    }
}