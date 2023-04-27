package programmers;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/43165

타겟 넘버
n개의 음이 아닌 정수들
이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만드려고 함
숫자를 적절히 더하고 빼서 타겟 넘버룰 만드는 방법의 수를 return

풀이
DFS
*/

class PG_43165_타겟넘버 {
    static int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;

        dfs(numbers, target, 0, 0);

        return answer;
    }

    private static void dfs(int[] numbers, int target, int sum, int cnt) {
        // 숫자를 모두 더하거나 뺐다면
        if (cnt == numbers.length) {
            if (sum == target) answer++;
            return;
        }

        dfs(numbers, target, sum+numbers[cnt], cnt+1);
        dfs(numbers, target, sum+(-1)*numbers[cnt], cnt+1);
    }
}