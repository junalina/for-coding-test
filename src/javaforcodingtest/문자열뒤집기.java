package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 0과 1로만 이루어진 문자열 S
 * S에서 연속된 하나 이상의 숫자를 잡고 모두 뒤집어 모든 숫자를 같게 만든다.
 * 뒤집는 최소 횟수 구하기
 * input
 * 0001100
 * output
 * 1
 */

public class 문자열뒤집기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 문자열 S
        String S = br.readLine();

        // 0 그룹의 개수
        int zero = 0;
        // 1 그룹의 개수
        int one = 0;
        char c = S.charAt(0);
        if (c == '0') zero++;
        else one++;
        for (int i = 1; i < S.length(); i++) {
            // 전의 문자와 현재 문자가 다르면 문자 그룹의 개수를 하나 추가
            char now = S.charAt(i);
            if (now == '0' && c == '1') {
                zero++;
                c = '0';
            } else if (now == '1' && c == '0') {
                one++;
                c = '1';
            }
        }
        System.out.println(Math.min(zero, one));
    }
}
