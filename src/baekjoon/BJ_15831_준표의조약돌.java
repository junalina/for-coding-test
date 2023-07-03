package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/15831
 * <p>
 * 준표의 조약돌
 * 산책로에 검은색, 흰색 조약돌
 * 준표는 임의의 지점에서 산책 시작, 원하는 지점에서 산책로를 빠져나와 집으로 돌아감
 * 산책한 구간에 있는 모든 조약돌 줍는데 미미의 건강을 위해 조금이라도 더 긴 구간 산책
 * 1. 까만색 조약돌은 B개 이하
 * 2. 흰색 조약돌은 W개 이상
 * <p>
 * 풀이
 * 투 포인터
 */

public class BJ_15831_준표의조약돌 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 조약돌의 총 개수 N
        int N = Integer.parseInt(st.nextToken());
        // 준표가 원하는 검은 조약돌의 최대개수 B
        int B = Integer.parseInt(st.nextToken());
        // 하얀 조약돌의 최소개수 W
        int W = Integer.parseInt(st.nextToken());

        // N개의 조약돌 정보
        char[] stones = new char[N];
        String str = br.readLine();
        for (int i = 0; i < N; i++) {
            stones[i] = str.charAt(i);
        }

        // 1. 시작과 끝을 0에서 시작
        int start = 0;
        int end = 0;
        int wCnt = 0;
        int bCnt = 0;
        int slideSize = 0;
        int maxLen = 0;

        while (end < N) {
            // 검은돌 B개 이상 주운 경우
            if (bCnt > B) {
                if (stones[start] == 'W') {
                    wCnt--;
                }
                if (stones[start] == 'B') {
                    bCnt--;
                }
                slideSize--;
                start++;
            } else {
                if (stones[end] == 'W') {
                    wCnt++;
                }
                if (stones[end] == 'B') {
                    bCnt++;
                }
                end++;
                slideSize++;
            }

            if (bCnt <= B && wCnt >= W) {
                maxLen = Math.max(maxLen, slideSize);
            }
        }

        System.out.println(maxLen);

    }
}
