package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2110
 * <p>
 * 공유기 설치
 * <p>
 * 도현이의 집 N개 수직선 위에, 같은 좌표 X
 * 어디서나 와이파이를 즐기기 위해 집에 공유기 C개 설치
 * 한 집에는 공유기 하나만 설치, 가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치
 * 가장 인접한 두 공유기 사이의 최대 거리 출력
 */
public class 공유기설치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N, C 공백 구분하여 입력
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        // N개의 집의 좌표 입력
        int[] home = new int[N];
        for (int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

        // 이진 탐색을 위한 정렬
        Arrays.sort(home);

        int start = 1; // 최소 거리 최솟값
        int end = home[N - 1] - home[0]; // 최소 거리 최댓값
        int max = 0;

        while (start <= end) {
            int mid = (start + end) / 2; // 처음 탐색할 최소 거리
            // 첫번째 집에 공유기 설치
            int prev = home[0];
            // 공유기 설치한 집의 수
            int cnt = 1;
            for (int i = 1; i < N; i++) {
                // 집의 좌표가 직전 설치한 집 + 최소 거리보다 크거나 같다면
                if (home[i] >= prev + mid) {
                    prev = home[i];
                    cnt++;
                }
            }
            // 공유기 설치한 집의 수가 C보다 크거나 같다면
            if (cnt >= C) {
                start = mid + 1;
                max = mid;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(max);
    }
}
