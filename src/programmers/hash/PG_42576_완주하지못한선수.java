package programmers.hash;

import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/42576

완주하지 못한 선수
수많은 마라톤 선수들 마라톤 참여
단 한 명의 선수를 제외하고는 모든 선수가 마라톤 완주
완주하지 못한 선수의 이름 return

풀이
해시
*/

class PG_42576_완주하지못한선수 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> hashMap = new HashMap<>();
        // 참가자 탐색
        for (int i = 0; i < participant.length; i++) {
            String key = participant[i];
            int value = 1;
            if (hashMap.containsKey(key)) {
                value = hashMap.get(key) + 1;
            }
            hashMap.put(key, value);
        }
        // 완주자 탐색
        for (int i = 0; i < completion.length; i++) {
            String key = completion[i];
            if (hashMap.containsKey(key)) {
                int value = hashMap.get(key);
                if (value == 1) {
                    hashMap.remove(key);
                } else {
                    hashMap.put(key, value - 1);
                }
            }
        }

        Set<String> keySet = hashMap.keySet();
        for (String s : keySet) {
            answer = s;
        }

        return answer;
    }
}
