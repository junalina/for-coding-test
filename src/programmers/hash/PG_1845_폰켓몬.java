package programmers.hash;

import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/1845

폰켓몬
N마리의 폰켓몬 중에서 N/2마리를 가져가도 좋다
가장 많은 폰켓몬을 선택하는 방법을 찾아, 그때의 폰켓몬 종류 번호의 개수를 return

풀이
해시
*/

class PG_1845_폰켓몬 {
    public int solution(int[] nums) {
        int answer = 0;

        // num 배열의 길이
        int length = nums.length;

        // 해시맵
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            int key = nums[i];
            int value = 1;
            if (hashMap.containsKey(key)) value = hashMap.get(key);
            hashMap.put(key, value);
        }

        // 해시맵의 크기가 N/2 보다 크거나 같다면
        if (hashMap.size() >= length / 2) {
            answer = length / 2;
        } else {
            answer = hashMap.size();
        }

        return answer;
    }
}