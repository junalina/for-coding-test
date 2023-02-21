package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 모험가 N명
 * 공포도가 X인 모험가는 반드시 X명 이상으로 그룹 참여
 * 그룹 수의 최댓값을 구하라
 * input
 * 5
 * 2 3 1 2 2
 * output
 * 2
 */
public class 모험가길드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N 입력
        int N = Integer.parseInt(br.readLine());
        // 모험가의 공포도 입력
        int[] horror = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            horror[i] = Integer.parseInt(st.nextToken());
        }

        // 공포도 배열을 오름차순 정렬
        Arrays.sort(horror);

        // 그룹 수
        int res = 0;
        // 현재 그룹에 포함된 모험가의 수
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            // 해당 그룹에 해당 모험가 포함
            cnt++;
            // 현재 그룹의 모험가 수 >= 현재 공포도이면, 그룹 결성
            if(cnt >= horror[i]) {
                res++;
                // 현재 그룹에 포함된 모험가의 수 초기화
                cnt = 0;
            }
        }
        System.out.println(res);
    }
}
