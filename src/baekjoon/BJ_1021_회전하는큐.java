package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/1021
 * <p>
 * 회전하는 큐
 * N개의 원소를 포함하고 있는 양방향 순환 큐에서 몇 개의 원소를 뽑아내려고 함
 * 3가지 연산 수행 가능
 * 1. 첫 번째 원소를 뽑아낸다. 이 연산을 수행하면, 원래 큐의 원소가 a1, ..., ak이었던 것이 a2, ..., ak와 같이 된다.
 * 2. 왼쪽으로 한 칸 이동시킨다. 이 연산을 수행하면, a1, ..., ak가 a2, ..., ak, a1이 된다.
 * 3. 오른쪽으로 한 칸 이동시킨다. 이 연산을 수행하면, a1, ..., ak가 ak, a1, ..., ak-1이 된다.
 * 처음에 포함되어 있던 수 N과 뽑아내려고 하는 원소의 위치가 주어진다.
 * 원소를 주어진 순서대로 뽑아내는데 드는 2번, 3번 연산의 최솟값 출력
 * <p>
 * 풀이
 * 데큐
 */

public class BJ_1021_회전하는큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 큐의 크기
        int N = Integer.parseInt(st.nextToken());
        // 뽑아내려고 하는 수의 개수
        int M = Integer.parseInt(st.nextToken());

        // 데큐 채우기
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            dq.offer(i);
        }

        // 연산 횟수
        int cnt = 0;

        // 뽑아내려고 하는 수의 위치
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());

            while (true) {
                // 1번 연산
                if (num == dq.peekFirst()) {
                    dq.pollFirst();
                    break;
                } else {
                    int idx = 0;
                    for (int j : dq) {
                        if (j == num) {
                            break;
                        }
                        idx++;
                    }

                    // 2번 연산
                    if (idx < dq.size() - idx) {
                        dq.offerLast(dq.pollFirst());
                        cnt++;
                    } else {
                        dq.offerFirst(dq.pollLast());
                        cnt++;
                    }
                }
            }
        }
        System.out.println(cnt);

    }
}
