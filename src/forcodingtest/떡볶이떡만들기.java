package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 손님이 요청한 총 길이가 M일 때 적어도 M만큼의 떡을 얻기 위해 절단기에 설정할 수 있는 높이의 최댓값을 구하라
 * input
 * 4 6
 * 19 15 10 17
 * output
 * 15
 */

public class 떡볶이떡만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N, M 공백을 구분하여 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] riceCake = new int[N];
        int maxHeight = Integer.MIN_VALUE;
        // 떡의 개별 높이 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            riceCake[i] = Integer.parseInt(st.nextToken());
            maxHeight = Math.max(maxHeight, riceCake[i]);
        }

        // 최댓값을 하나씩 줄여가며 떡 높이의 총 합을 M과 비교
        while(maxHeight >= 0) {
            int sum = 0;
            for ( int h : riceCake ) {
                if(h - maxHeight > 0) sum += h - maxHeight;
            }
            if (sum >= M) break;
            maxHeight--;
        }
        System.out.println(maxHeight);
    }
}
