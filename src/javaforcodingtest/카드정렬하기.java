package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * https://www.acmicpc.net/problem/1715
 *
 * 카드 정렬하기
 *
 * 정렬된 두 묶음의 숫자 카드 A, B를 합쳐서 하나로 만드는 데에는 A + B번의 비교를 해야 함, 최소 비교 횟수 출력
 *
 * 풀이
 * 1. 우선순위 큐 사용
 * 2. 우선순위 큐에서 poll 2번으로 A, B 뽑기
 * 3. A + B를 res에 추가
 * 4. A + B 값을 우선순위 큐에 추가
 */
public class 카드정렬하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N개의 숫자 카드 묶음
        int N = Integer.parseInt(br.readLine());
        // 1. 우선순위 큐 사용
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int res = 0;

        if (N == 1) {
            System.out.println(0);
            System.exit(0);
        }

        while (true) {
            // 2. 우선순위 큐에서 poll 2번으로 A, B 뽑기
            int A = pq.poll();
            int B = pq.poll();
            // 3. A + B를 res에 추가
            int sum = A + B;
            res += sum;
            if (pq.isEmpty()) {
                break;
            } else {
                // 4. A + B 값을 우선순위 큐에 추가
                pq.offer(sum);
            }
        }

        System.out.println(res);

    }
}
