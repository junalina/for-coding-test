package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 왕실 정원 8 x 8
 * 나이트 L자 형태로 이동, 정원 밖으로는 X
 * 나이트의 위치가 주어졌을 때 나이트가 이동하 수 있는 경우의 수를 구하라
 * input
 * a1
 * output
 * 2
 */
public class 왕실의나이트 {

    // 나이트가 이동할 수 있는 8가지 방향 정의
    static int[][] knight = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 현재 나이트의 위치 입력받기
        String str = br.readLine();
        int r = str.charAt(1) - '0';
        int c = str.charAt(0) - 'a' + 1;

        int cnt = 0;

        // 8가지 방향에 대하여 각 위치로 이동이 가능한지 확인
        for (int i = 0; i < 8; i++) {
            // 이동하고자 하는 위치 확인
            int nr = r + knight[i][0];
            int nc = c + knight[i][1];

            // 해당 위치로 이동이 가능하다면 카운트 증가
            if(isIn(nr, nc)) cnt++;
        }

        System.out.println(cnt);
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 1 && nc >= 1 && nr <= 8 && nc <= 8;
    }
}
