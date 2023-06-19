package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * https://www.acmicpc.net/problem/1927
 * <p>
 * 최소 힙
 * 1. 배열에 자연수 x를 넣는다.
 * 2. 배열에서 가장 작은 값을 출력하고, 그 값을 배열에서 제거
 * 입력에서 0이 주어진 횟수만큼 답을 출력, 배열이 비어 있는 경우에 가장 작은 값을 출력하는 경우 0 출력
 * <p>
 * 풀이
 * 우선순위 큐
 */

public class BJ_1927_최소힙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 연산의 개수 N
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int x;
        // N개의 줄에 연산에 대한 정보를 나타내는 정수 x
        for (int i = 0; i < N; i++) {
            x = Integer.parseInt(br.readLine());
            // x가 자연수라면 x라는 값을 추가
            if (x > 0) {
                pq.offer(x);
            }
            // x가 0이라면 pq에서 가장 작은 값을 출력, 그 값을 배열에서 제거
            else if (x == 0) {
                // pq가 비어 있는 경우 가장 작은 값을 출력하라고 한 경우
                if (pq.isEmpty()) {
                    sb.append(0);
                } else {
                    sb.append(pq.poll());
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}
