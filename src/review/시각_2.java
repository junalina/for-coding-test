package review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 시각_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int cnt = 0;

        for (int h = 0; h <= N; h++) {
            for (int m = 0; m <= 59; m++) {
                for (int s = 0; s <= 59; s++) {
                    String time = Integer.toString(h) + Integer.toString(m) + Integer.toString(s);
                    if (time.contains("3")) cnt++;
                }
            }
        }

        System.out.println(cnt);

    }
}
