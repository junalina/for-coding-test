package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 두 개의 배열 A, B
 * N개의 원소로 구성, 원소는 모두 자연수
 * K번의 바꿔치기 연산
 * 배열 A에 있는 원소 하나와 배열 B에 있는 원소 하나를 골라서 두 원소를 서로 바꾸는 것
 * 배열 A의 모든 원소의 합이 최대가 되도록 K번의 바꿔치기 연산을 수행하여 A의 모든 원소의 합의 최댓값을 출력
 * input
 * 5 3
 * 1 2 5 4 3
 * 5 5 6 6 5
 * output
 * 26
 */

public class 두배열의원소교체 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N, K 공백으로 구분하여 입력받기
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 배열 A와 B에 원소들을 공백을 구분하여 입력받기
        int[] A = new int[N];
        Integer[] B = new Integer[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 A는 오름차순 정렬
        Arrays.sort(A);
        // 배열 B는 내림차순 정렬
        Arrays.sort(B, Collections.reverseOrder());

        // 첫 번째 인덱스부터 확인하며, 두 배열의 원소를 최대 K번 비교
        for (int i = 0; i < K; i++) {
            // A의 원소가 B의 원소보다 작은 경우
            if(A[i] < B[i]) {
                // 두 원소를 교체
                int temp = A[i];
                A[i] = B[i];
                B[i] = temp;
            }
        }

        int sum = 0;
        for (int i : A) {
            sum += i;
        }

        System.out.println(sum);
    }
}
