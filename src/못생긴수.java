import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 못생긴 수
 * 오직 2, 3, 5를 소인수로 가지는 수
 * n번째 못생긴 수를 출력
 * input1
 * 10
 * output1
 * 12
 * input2
 * 4
 * output2
 * 4
 */

public class 못생긴수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] ugly = new int[n];

        // 배수를 위한 인덱스
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;

        // 곱할 값
        int next2 = 2;
        int next3 = 3;
        int next5 = 5;

        ugly[0] = 1; // 첫번째 못생긴 수는 1
        // 1부터 n까지 못생긴 수 찾기
        for (int i = 1; i < n; i++) {
            ugly[i] = Math.min(next2, Math.min(next3, next5));
            if (ugly[i] == next2) {
                i2 += 1;
                next2 = ugly[i2] * 2;
            }
            if (ugly[i] == next3) {
                i3 += 1;
                next3 = ugly[i3] * 3;
            }
            if (ugly[i] == next5) {
                i5 += 1;
                next5 = ugly[i5] * 5;
            }
        }

        System.out.println(ugly[n-1]);
    }
}
