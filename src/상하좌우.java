import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * input
 * 5
 * R R R U D D
 * output
 * 3 4
 */

public class 상하좌우 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N을 입력 받기
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = 1;
        int c = 1;

        // L, R, U, D에 따른 이동
        while (st.hasMoreTokens()) {
            switch (st.nextToken()) {
                case "L":
                    c--;
                    if(isNotIn(r, c)) {
                        c++;
                    }
                    break;
                case "R":
                    c++;
                    if(isNotIn(r, c)) {
                        c--;
                    }
                    break;
                case "U":
                    r--;
                    if(isNotIn(r, c)) {
                        r++;
                    }
                    break;
                case "D":
                    r++;
                    if(isNotIn(r, c)) {
                        r--;
                    }
                    break;
            }
        }
        System.out.println(r + " " + c);
    }

    private static boolean isNotIn(int r, int c) {
        return r < 1 || c < 1 || r > N || c > N;
    }
}
