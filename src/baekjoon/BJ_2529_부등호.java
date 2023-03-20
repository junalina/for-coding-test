package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2529
 * <p>
 * 부등호
 * 부등호 순서를 만족하는 정수 중에서 최대 정수, 최소 정수 출력
 * <p>
 * 풀이
 * 순열
 */

public class BJ_2529_부등호 {
    static int k;
    static long max, min;
    static char[] sign;
    static int[] num;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 부등호 문자의 개수
        k = Integer.parseInt(br.readLine());

        // 부등호 기호
        sign = new char[k];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            sign[i] = st.nextToken().charAt(0);
        }

        max = Long.MIN_VALUE;
        min = Long.MAX_VALUE;

        num = new int[k+1];
        visited = new boolean[10];

        perm(0);

        String strMax;
        if (max >= Math.pow(10, k)) {
            strMax = Long.toString(max);
        }
        else {
            strMax = "0";
            strMax += Long.toString(max);
        }

        String strMin;
        if (min >= Math.pow(10, k)) {
            strMin = Long.toString(min);
        }
        else {
            strMin = "0";
            strMin += Long.toString(min);
        }

        sb.append(strMax).append("\n").append(strMin);

        System.out.println(sb);

    }

    private static void perm(int idx) {
        if (idx == k+1) {
            // 모든 부등호 관계를 만족시킨다면
            if (check()) {
                long num = getNum();
                max = Math.max(max, num);
                min = Math.min(min, num);
            }
            return;
        }
        for (int i = 0; i <= 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                num[idx] = i;
                perm(idx + 1);
                visited[i] = false;
            }
        }
    }

    private static boolean check() {
        boolean check = true;
        for (int i = 0; i < k; i++) {
            if (sign[i] == '<') {
                if (num[i] >= num[i+1]) {
                    check = false;
                    break;
                }
            }
            else if (sign[i] == '>') {
                if (num[i] <= num[i+1]) {
                    check = false;
                    break;
                }
            }
        }
        return check;
    }

    private static long getNum() {
        long res = 0;
        long mul = 1;
        for (int i = k; i >= 0; i--) {
            res += num[i] * mul;
            mul *= 10;
        }
        return res;
    }
}
