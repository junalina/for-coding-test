package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/1946
 * <p>
 * 신입 사원
 * 인재 선발 시험
 * 1차 서류심사
 * 2차 면접시험
 * 다른 모든 지원자와 비교했을 때 서류심사 성적과 면접시험 성적 중
 * 적어도 하나가 다른 지원자보다 떨어지지 않는 자만 선발한다는 원칙
 * <p>
 * 풀이
 * 서류 성적이 가장 높은 사람을 뽑고 그 사람 성적과 면접 시험 성적을 비교해서 최대 인원 수 추가
 */

public class BJ_1946_신입사원 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스의 개수
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            // 지원자의 숫자
            int N = Integer.parseInt(br.readLine());

            int ans = 1;

            int[] interviewRank = new int[N + 1];
            // 성적의 순위
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int document = Integer.parseInt(st.nextToken());
                int interview = Integer.parseInt(st.nextToken());
                interviewRank[document] = interview;
            }

            int bestDocInterviewRank = interviewRank[1]; // 서류 성적 1등의 면접 순위

            for (int i = 2; i <= N; i++) {
                if (interviewRank[i] < bestDocInterviewRank) {
                    ans++;
                    bestDocInterviewRank = interviewRank[i];
                }
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
