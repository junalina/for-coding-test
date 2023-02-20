package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 편집 거리
 * 문자열 A, B가 주어졌을 때, 문자열 A를 편집하여 B로 만들고자 함
 * 세 연산 중에서 한 번에 하나씩 선택해 편집 가능
 * 1. 삽입 : 특정한 위치에 하나의 문자 삽입
 * 2. 삭제 : 특정한 위치에 있는 하나의 문자 삭제
 * 3. 교체 : 특정한 위치에 있는 하나의 문자를 다른 문자로 교체
 * 편집 거리 : 문자열 A를 편집하여 문자열 B로 만들기 위해 사용한 연산의 수
 * 최소 편집 거리 출력
 * input1
 * cat
 * cut
 * output1
 * 1
 * intput2
 * sunday
 * saturday
 * output2
 * 3
 */

public class 편집거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String A = br.readLine();
        String B = br.readLine();

        // 최소 편집 거리 계산을 위한 다이나믹 프로그래밍
        int r = A.length();
        int c = B.length();

        // 다이나믹 프로그래밍을 위한 2차원 DP 테이블 초기화
        int[][] dp = new int[r + 1][c + 1];

        // DP 테이블 초기 설정
        for (int i = 1; i <= r; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= c; j++) {
            dp[0][j] = j;
        }

        // 최소 편집 거리 계산
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                // 문자가 같다면, 왼쪽 위에 해당하는 수를 그대로 대입
                if (A.charAt(i - 1) == B.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                // 문자가 다르다면, 세 가지 경우 중에서 최솟갑 찾기, 삽입(왼쪽), 삭제(위쪽), 교체(왼쪽 위)
                else dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
            }
        }
        System.out.println(dp[r][c]);
    }
}
