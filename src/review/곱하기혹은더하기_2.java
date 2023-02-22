package review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 곱하기혹은더하기_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();

        int res = S.charAt(0) - '0';
        for (int i = 1; i < S.length(); i++) {
            int x = S.charAt(i) - '0';
            if (res <= 1 || x <= 1) res += x;
            else res *= x;
        }

        System.out.println(res);
    }
}
