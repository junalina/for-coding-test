package forcodingtest;

import java.util.*;

class 문자열압축 {
    public int solution(String s) {
        int answer = s.length();

        // 1개 단위부터 압축 단위를 늘려가며 확인
        for (int step = 1; step <= s.length() / 2 ; step++) {
            String compressed = "";
            String prev = s.substring(0, step);
            int cnt = 1;
            // 단위 크기 만큼 증가시키며 이전 문자열과 비교
            for (int j = step; j < s.length(); j += step) {
                // 이전 상태와 동일하다면 압축 횟수 증가
                String sub = "";
                for (int k = j; k < j + step; k++) {
                    if (k < s.length()) sub += s.charAt(k);
                }
                if (prev.equals(sub)) cnt += 1;
                    // 다른 문자열이 나왔다면(더 이상 압축 X)
                else {
                    compressed += (cnt >= 2) ? cnt + prev : prev;
                    sub = "";
                    for (int k = j; k < j + step; k++) {
                        if (k < s.length()) sub += s.charAt(k);
                    }
                    // 상태 초기화
                    prev = sub;
                    cnt = 1;
                }
            }
            // 남아있는 문자열에 대해서 처리
            compressed += (cnt >= 2) ? cnt + prev : prev;
            // 만들어지는 압축 문자열이 가장 짧은 것이 정답
            answer = Math.min(answer, compressed.length());
        }

        return answer;
    }
}