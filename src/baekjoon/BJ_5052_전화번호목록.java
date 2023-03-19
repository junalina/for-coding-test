package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/5052
 * <p>
 * 전화번호 목록
 * 전화번호 목록이 일관성을 유지하려면, 한 번호가 다른 번호의 접두어인 경우가 없어야 한다.
 * <p>
 * 풀이
 * 문자열
 */

public class BJ_5052_전화번호목록 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스의 개수
        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            // 전화번호의 수
            int n = Integer.parseInt(br.readLine());

            String[] nums = new String[n];
            // 전화번호 입력
            for (int i = 0; i < n; i++) {
                nums[i] = br.readLine();
            }

            // 전화번호 목록 오름차순 정렬
            Arrays.sort(nums);

            boolean isPrefix = false;
            for (int i = 0; i < n - 1; i++) {
                if (nums[i + 1].startsWith(nums[i])) isPrefix = true;
            }

            if (isPrefix) sb.append("NO");
            else sb.append("YES");

            sb.append("\n");
        }
        System.out.println(sb);
    }
}
