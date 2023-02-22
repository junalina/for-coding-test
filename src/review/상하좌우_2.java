package review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 상하좌우_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int r = 1;
        int c = 1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            switch (st.nextToken()) {
                case "U":
                    if (r - 1 >= 1) r -= 1;
                    break;
                case "D":
                    if (r + 1 <= N) r += 1;
                    break;
                case "L":
                    if (c - 1 >= 1) c -= 1;
                    break;
                case "R":
                    if (c + 1 <= N) c += 1;
                    break;
            }
        }

        System.out.println(r + " " + c);
    }
}
