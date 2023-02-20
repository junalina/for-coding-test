package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/18310
 *
 * forcodingtest.안테나
 *
 * 일직선 상의 마을에 여러 채의 집 위치
 * 이 중에서 특정위치집에 한 개의 forcodingtest.안테나 설치
 * 효율성을 위해 안테나로부터 모든 집까지의 거리의 총 합이 최소가 되도록 설치
 * 안테나는 집이 위치한 곳에만 설치
 * 논리적으로 동일한 위치에 여러 개의 집이 존재하는 것이 가능
 * 안테나를 설치할 위치를 선택
 */

public class 안테나 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 집의 수 N
        int N = Integer.parseInt(br.readLine());
        // N채의 집의 위치
        int[] home = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(st.nextToken());
        }
        
        // 배열의 중간값이 정답
        Arrays.sort(home);
        
        // N이 짝수개이면
        if (N % 2 == 0) System.out.println(home[N/2-1]);
        // N이 홀수개이면
        else System.out.println(home[N/2]);

        // 시간복잡도가 O(N^2)이기 때문에 시간초과 발생
//        // 집의 위치 오름차순 정렬
//        Arrays.sort(home);
//
//        // i번째 집에 안테나를 설치했을 때 모든 집까지의 거리의 총 합
//        int[] dis = new int[N];
//        for (int i = 0; i < N; i++) {
//            int sum = 0;
//            for (int j = 0; j < N; j++) {
//                sum += Math.abs(home[i] - home[j]);
//            }
//            dis[i] = sum;
//        }
//
//        // 안테나를 설치할 수 있는 위치 값
//        int index = -1;
//        int min = Integer.MAX_VALUE;
//        for (int i = 0; i < N; i++) {
//            // 여러 값이 도출될 경우 가장 작은 값을 출력
//            if (min > dis[i]) {
//                min = dis[i];
//                index = i;
//            }
//        }
//        System.out.println(home[index]);
    }
}
