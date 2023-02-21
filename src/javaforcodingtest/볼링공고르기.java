package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * A, B 두 사람이 볼링
 * 서로 무게가 다른 볼링공 선택
 * 두 사람이 볼링공을 고르는 경우의 수를 구하라
 * input1
 * 5 3
 * 1 3 2 3 2
 * output1
 * 8
 * input2
 * 8 5
 * 1 5 4 3 2 4 5 2
 * output2
 * 25
 */
public class 볼링공고르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N, M 공백 구분하여 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 볼링공의 무게 공백 구분하여 입력
        int[] ball = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ball[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                // 무게가 다른 공 고르면 cnt++
                if(ball[i] != ball[j]) cnt++;
            }
        }

        System.out.println(cnt);
    }
}
