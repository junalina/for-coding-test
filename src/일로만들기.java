import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 정수 X에 사용할 수 있는 연산 4가지
 * a. X가 5로 나누어떨어지면, 5로 나눈다.
 * b. X가 3으로 나누어떨어지면, 3으로 나눈다.
 * c. X가 2로 나누어떨어지면, 2로 나눈다.
 * d. X에서 1을 뺀다.
 * 연산 4개를 적절히 사용해서 연산을 사용하는 횟수의 최솟값을 구하라
 * input
 * 26
 * output
 * 3
 */

public class 일로만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 정수 X 입력
        int X = Integer.parseInt(br.readLine());

        int[] DP = new int[30001];

        for (int i = 2; i <= X; i++) {
            // d
            DP[i] = DP[i-1] + 1;
            // c
            if(i % 2 == 0) DP[i] = Math.min(DP[i], DP[i/2] + 1);
            // b
            if(i % 3 == 0) DP[i] = Math.min(DP[i], DP[i/3] + 1);
            // a
            if(i % 5 == 0) DP[i] = Math.min(DP[i], DP[i/5] + 1);
        }
        System.out.println(DP[X]);
    }
}
