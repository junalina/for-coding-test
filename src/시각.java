import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 정수 N이 입력되면 00시 00분 00초부터 N시 59분 59초까지의 모든 시각 중에서
 * 3이 하나라도 포함되는 모든 경우의 수를 구하라
 * 풀이 : 3중 for문으로 완탐
 * input
 * 5
 * output
 * 11475
 */

public class 시각 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int cnt = 0;
        for (int h = 0; h <= N; h++) {
            for (int m = 0; m < 60; m++) {
                for (int s = 0; s < 60; s++) {
                    String hms = Integer.toString(h) + Integer.toString(m) + Integer.toString(s);
                    if(hms.contains("3")) cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
