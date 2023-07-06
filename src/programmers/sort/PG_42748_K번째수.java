package programmers.sort;

import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/42748

K번째수
배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수 return

풀이
정렬
*/

class PG_42748_K번째수 {
    public int[] solution(int[] array, int[][] commands) {
        int length = commands.length;

        int[] answer = new int[length];

        for (int r = 0; r < length; r++) {
            int i = commands[r][0];
            int j = commands[r][1];
            int k = commands[r][2];

            // i번째 숫자부터 j번째 숫자까지 자른 배열
            int len = j - i + 1;
            int[] arr = new int[len];

            for (int c = 0; c < len; c++) {
                arr[c] = array[c + i - 1];
            }

            // 오름차순 정렬
            Arrays.sort(arr);

            answer[r] = arr[k - 1];
        }
        return answer;
    }
}
