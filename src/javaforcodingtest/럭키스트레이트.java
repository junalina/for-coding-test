package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 현재 캐릭터의 점수 : N
 * 자릿수를 기준으로 N을 반으로 나누어 왼쪽 부분의 각 자릿수의 합과 오른쪽 부분의 각 자릿수의 합을 더한 값이 동일하면 사용 가능
 * 점수 N이 주어지면 럭키 스트레이트를 사용할 수 있는지 아닌지 알려주기
 * input1
 * 123402
 * output1
 * LUCKY
 * input2
 * 7755
 * output2
 * READY
 */

public class 럭키스트레이트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String N = br.readLine();

        int left = 0;
        int right = 0;

        // 왼쪽 부분의 각 자릿수의 합 구하기
        for (int i = 0; i < N.length()/2; i++) {
            int n = N.charAt(i) - '0';
            left += n;
        }

        // 오른쪽 부분의 각 자릿수의 합 구하기
        for (int i = N.length()/2; i < N.length(); i++) {
            int n = N.charAt(i) - '0';
            right += n;
        }

        if(left == right) System.out.println("LUCKY");
        else System.out.println("READY");
    }
}
