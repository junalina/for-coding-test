package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/10866
 * 출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력
 * <p>
 * 풀이
 * 덱
 */

public class BJ_10866_덱 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 명령의 수
        int N = Integer.parseInt(br.readLine());

        Deque<Integer> deque = new ArrayDeque<>();

        // N개의 명령
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String str = st.nextToken();
            if (str.equals("push_front")) {
                deque.addFirst(Integer.parseInt(st.nextToken()));
                continue;
            } else if (str.equals("push_back")) {
                deque.addLast(Integer.parseInt(st.nextToken()));
                continue;
            } else if (str.equals("pop_front")) {
                if (deque.isEmpty()) sb.append(-1);
                else sb.append(deque.pollFirst());
            } else if (str.equals("pop_back")) {
                if (deque.isEmpty()) sb.append(-1);
                else sb.append(deque.pollLast());
            } else if (str.equals("size")) {
                sb.append(deque.size());
            } else if (str.equals("empty")) {
                if (deque.isEmpty()) sb.append(1);
                else sb.append(0);
            } else if (str.equals("front")) {
                if (deque.isEmpty()) sb.append(-1);
                else sb.append(deque.getFirst());
            } else if (str.equals("back")) {
                if (deque.isEmpty()) sb.append(-1);
                else sb.append(deque.getLast());
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
