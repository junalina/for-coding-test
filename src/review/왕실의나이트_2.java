package review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 왕실의나이트_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        int r = str.charAt(1) - '1' + 1;
        int c = str.charAt(0) - 'a' + 1;

        int cnt = 0;

        int[] knightR = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] knightC = {-1, 1, -2, 2, -2, 2, -1, 1};

        for (int i = 0; i < 8; i++) {
            int nr = r + knightR[i];
            int nc = c + knightC[i];
            if (isIn(nr, nc)) cnt++;
        }

        System.out.println(cnt);
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 1 && nr <= 8 && nc >= 1 && nc <= 8;
    }
}
