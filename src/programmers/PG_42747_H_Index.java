package programmers;

import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/42747

H-Index
논문 n편 중, h번 이상 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 h의 최댓값

풀이
정렬
*/

class PG_42747_H_Index {
    public int solution(int[] citations) {
        int answer = 0;

        // 발표한 논문 n편
        int n = citations.length;
        int h;

        // 오름차순 정렬
        Arrays.sort(citations);

        for (int i = 0; i < n; i++) {
            // 현재 논문부터 마지막 논문까지의 논문 개수
            h = n - i;

            // 현재 논문의 인용 횟수가 h번 이상이라면
            if (citations[i] >= h) {
                answer = h;
                break;
            }
        }

        return answer;
    }
}