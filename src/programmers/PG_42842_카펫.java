package programmers;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/42842

카펫
중앙 노란색, 테두리 1줄 갈색 격자 모양 카펫
갈색 격자의 수와 노란색 격자의 수가 매개변수로 주어질 때
카펫의 가로, 세로 크기를 순서대로 배열에 담아 return

풀이
완전탐색
갈색 격자와 노란색 격자의 수를 합을 나눌 수 있는 모든 경우의 수 탐색
*/

class PG_42842_카펫 {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        // 갈색 격자와 노란색 격자의 합
        int sum = brown + yellow;

        int width = 0;
        while (++width <= sum) {
            // 가로 길이로 나누어 진다면
            if (sum % width == 0) {
                // 세로 길이
                int length = sum / width;
                // 가로 세로 길이로 테두리 1줄을 계산한 것과 갈색 격자의 수가 같다면
                if ((width + length) * 2 - 4 == brown) {
                    answer[0] = width;
                    answer[1] = length;
                }
            }
        }
        return answer;
    }
}