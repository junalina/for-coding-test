package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/1157
 * <p>
 * 단어 공부
 * 알파벳 대소문자로 된 단어가 주어지면, 이 단어에서 가장 많이 사용된 알파벳 출력
 */

public class BJ_1157_단어공부 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 알파벳 대소문자로 이루어진 단어
        String word = br.readLine();

        int[] arr = new int[26];

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c >= 'A' && c <= 'Z') arr[c - 65]++;
            else arr[c - 97]++;
        }

        // 알파벳이 사용된 개수
        int max = 0;
        // 많이 사용된 알파벳의 개수
        int cnt = 1;
        int index = 0;

        for (int i = 0; i <= 25; i++) {
            if (arr[i] > max) {
                max = arr[i];
                cnt = 1;
                index = i;
            } else if (arr[i] == max) cnt++;

        }

        if (cnt == 1) System.out.println((char) (index + 'A'));
        else System.out.println("?");

    }
}
