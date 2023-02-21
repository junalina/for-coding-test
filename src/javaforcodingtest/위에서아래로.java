package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

/**
 * 하나의 수열에는 다양한 수가 존재
 * 이러한 수는 크기에 상관없이 나열되어 있다.
 * 이 수를 큰 수부터 작은 수의 순서로 정렬 (내림차순 정렬)
 * input
 * 3
 * 15
 * 27
 * 12
 * output
 * 27 15 12
 */

public class 위에서아래로 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // N을 입력받기
        int N = Integer.parseInt(br.readLine());

        // N개의 정수를 입력받아 배열에 저장
        Integer[] num = new Integer[N];
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }
        // 자바 정렬 수행
        Arrays.sort(num, Collections.reverseOrder());

        // 정렬이 수행된 결과를 출력
        for (int i = 0; i < N; i++) {
            sb.append(num[i]).append(" ");
        }
        System.out.println(sb);
    }
}
