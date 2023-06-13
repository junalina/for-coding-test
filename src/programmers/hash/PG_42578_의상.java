package programmers.hash;

import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/42578

의상
매일 다른 옷을 조합하여 입는것 선호
서로 다른 옷의 조합의 수 return

풀이
해시
(의상의 종류 + 1) * (의상의 종류 + 1) * ... * (의상의 종류 + 1) - 1
*/

class PG_42578_의상 {
    public int solution(String[][] clothes) {
        int answer = 1;

        // 의상의 종류 수 해시맵에 저장
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int r = 0; r < clothes.length; r++) {
            String key = clothes[r][1];
            int value = 1;
            // 이미 존재하면 기존 종류의 수에 1개 추가
            if (hashMap.containsKey(key)) {
                value = hashMap.get(key) + 1;
            }
            hashMap.put(key, value);
        }

        // (의상의 종류 + 1) * (의상의 종류 + 1) * ... * (의상의 종류 + 1) - 1
        Set<String> keySet = hashMap.keySet();
        for (String key : keySet) {
            answer *= hashMap.get(key) + 1;
        }
        answer -= 1;

        return answer;
    }
}