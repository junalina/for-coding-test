import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 손님이 요청한 총 길이가 M일 때 적어도 M만큼의 떡을 얻기 위해 절단기에 설정할 수 있는 높이의 최댓값을 구하라
 * input
 * 4 6
 * 19 15 10 17
 * output
 * 15
 */

public class 떡볶이떡만들기_이진탐색 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N, M 공백을 구분하여 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] riceCake = new int[N];
        // 이진 탐색을 위한 시작점과 끝점 설정
        int start = 0;
        int end = Integer.MIN_VALUE;
        // 떡의 개별 높이 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            riceCake[i] = Integer.parseInt(st.nextToken());
            end = Math.max(end, riceCake[i]);
        }

        int res = 0;
        while (start <= end) {
            int sum = 0;
            int mid = (start + end) / 2;
            // 잘랐을 때 떡의 양 계산
            for ( int h : riceCake ) {
                if (h > mid) sum += h - mid;
            }
            // 떡의 양이 부족한 경우 더 많이 자르기(왼쪽 부분 탐색)
            if(sum < M) end = mid - 1;
            // 떡의 양이 충분한 경우 덜 자르기(오른쪽 부분 탐색)
            else {
                res = mid;
                start = mid + 1;
            }
        }
        System.out.println(res);
    }
}
