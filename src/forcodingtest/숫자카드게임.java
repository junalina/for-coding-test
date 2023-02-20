package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 숫자 카드 게임 : 여러 개의 숫자 카드 중 가장 높은 숫자가 쓰인 카드 한 장을 뽑는 게임
 * 카드 N x M
 * 풀이 : 행마다 최솟값 구해서 다음 행 최솟값과 비교
 * input
 * 3 3
 * 3 1 2
 * 4 1 4
 * 2 2 2
 * output
 * 2
 * input2
 * 2 4
 * 7 3 1 8
 * 3 3 3 4
 * output2
 * 3
 */

public class 숫자카드게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] card = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                card[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int max = Integer.MIN_VALUE;

        for (int r = 0; r < N; r++) {
            int min = Integer.MAX_VALUE;
            for (int c = 0; c < M; c++) {
                min = Math.min(min, card[r][c]);
            }
            max = Math.max(max, min);
        }

        System.out.println(max);
    }
}
