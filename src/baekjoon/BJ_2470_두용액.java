package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2470
 * <p>
 * 두 용액
 * 산성 용액과 알칼리성 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값 출력
 * <p>
 * 풀이
 * 투 포인터
 */

public class BJ_2470_두용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 용액의 수
        int N = Integer.parseInt(br.readLine());

        // 용액의 특성값
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 오름차순 정렬
        Arrays.sort(arr);

        int start = 0;
        int end = arr.length - 1;
        int sum;
        int min = Integer.MAX_VALUE;
        int startAns = 0;
        int endAns = 0;

        while (start < end) {
            sum = arr[start] + arr[end];
            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                startAns = start;
                endAns = end;
            }
            if (sum > 0) end--;
            else start++;
        }

        System.out.println(arr[startAns] + " " + arr[endAns]);
    }
}
